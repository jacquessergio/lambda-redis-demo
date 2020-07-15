package br.com.demo.handler;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import br.com.demo.config.DemoRedisConfig;
import br.com.demo.exception.DemoElastiCacheException;
import redis.clients.jedis.Jedis;

public class DemoGetElastiCacheComRedisHandler extends DemoRedisConfig
		implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
	private static final Logger LOG = LoggerFactory.getLogger(DemoSetElastiCacheComRedisHandler.class);
	private static final String KEY = "key";

	@Override
	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent header, Context context) {

		String response = null;
		Jedis cacheComRedis = null;
		try {
			LOG.info("Conexão com Redis...");

			cacheComRedis = getConnectionDisabled();

			if (header.getPathParameters().get(KEY) == null) {
				throw new DemoElastiCacheException("Parametro Chave é obrigatorio");
			}
			final String chave = header.getPathParameters().get(KEY);

			response = "{\"data\":\"" + cacheComRedis.get(chave) + "\"}";

		} catch (final DemoElastiCacheException e) {
			return new APIGatewayProxyResponseEvent().withStatusCode(HttpStatus.SC_BAD_REQUEST).withBody("");
		} catch (Exception e) {
			LOG.error("Erro: {}", e);
			LOG.info("Ocorreu um erro ao tentar se conectar com Redis: {}", e.getMessage());
			return new APIGatewayProxyResponseEvent().withStatusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
		} finally {
			LOG.info("Fechando conexão com Redis...");
			cacheComRedis.close();
		}
		return new APIGatewayProxyResponseEvent().withStatusCode(HttpStatus.SC_OK).withBody(response.toString());
	}

}
