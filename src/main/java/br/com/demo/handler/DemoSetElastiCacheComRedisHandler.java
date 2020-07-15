package br.com.demo.handler;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;

import br.com.demo.config.DemoRedisConfig;
import br.com.demo.dto.CacheRedisDTO;
import br.com.demo.exception.DemoElastiCacheException;
import redis.clients.jedis.Jedis;

public class DemoSetElastiCacheComRedisHandler extends DemoRedisConfig
		implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

	private static final Logger LOG = LoggerFactory.getLogger(DemoSetElastiCacheComRedisHandler.class);
	private static final String EXEC = "EXEC> {} {} {} {} {}";
	
	@Override
	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {

		Jedis cacheComRedis = null;

		try {
			LOG.info("Conexão com Redis...");

			cacheComRedis = getConnectionDisabled();
			
			LOG.info("Payload: {}", request.getBody());

			final CacheRedisDTO body = new Gson().fromJson(request.getBody(), CacheRedisDTO.class);

			body.hasError();

			final String value = body.getValue();
			final String key = body.getKey();
			final Integer seconds = body.getTtl().getSeconds();
			final Integer miliseconds = body.getTtl().getMiliseconds();
			final Boolean nx = body.getTtl().getNx();
			final Boolean xx = body.getTtl().getXx();

			cacheComRedis.set(key, value);

			if (nx) {
				if (seconds > 0) {
					LOG.info(EXEC, key, value, "NX", "EX", seconds);
					cacheComRedis.set(key, value);
				} else if (miliseconds > 0) {
					LOG.info(EXEC, key, value, "NX", "PX", miliseconds);
					cacheComRedis.set(key, value);
				} else {
					cacheComRedis.set(key, value);
				}
			}

			if (xx) {
				if (seconds > 0) {
					LOG.info(EXEC, key, value, "XX", "EX", seconds);
					cacheComRedis.set(key, value);
				} else if (miliseconds > 0) {
					LOG.info(EXEC, key, value, "XX", "PX", miliseconds);
					cacheComRedis.set(key, value);
				} else {
					cacheComRedis.set(key, value);
				}
			}

			LOG.info("Registro: {} - Inserido com sucesso!!!", cacheComRedis.get(key));
		} catch (final DemoElastiCacheException e) {
			LOG.info("Requisição invalida: {}", e.getMessage());
			final String response = "{\"data\":\"" + e.getMessage() + "\"}";
			return new APIGatewayProxyResponseEvent().withStatusCode(HttpStatus.SC_BAD_REQUEST).withBody(response);
		} catch (final Exception e) {
			LOG.error("Erro: {}", e);
			LOG.info("Ocorreu um erro ao processar a solicitação: {}", e.getMessage());
			return new APIGatewayProxyResponseEvent().withStatusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
		} finally {
			LOG.info("Fechando conexão com Redis...");
			cacheComRedis.close();
		}
		return new APIGatewayProxyResponseEvent().withStatusCode(HttpStatus.SC_CREATED);

	}

}
