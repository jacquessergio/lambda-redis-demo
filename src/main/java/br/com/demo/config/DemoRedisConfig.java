package br.com.demo.config;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

public abstract class DemoRedisConfig {

	private static final String ENDPOINT_REDIS = (System.getenv("endpointredis") != null)
			? System.getenv("endpointredis")
			: "127.0.0.1";
	private static final String AUTH = (System.getenv("auth") != null) ? System.getenv("auth") : "123456";

	public JedisCluster getConnection() {

		System.out.println("URL REDIS: " + ENDPOINT_REDIS);

		Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
		// jedisClusterNodes.add(new HostAndPort(ENDPOINT_REDIS, 6379));
		jedisClusterNodes.add(new HostAndPort(ENDPOINT_REDIS, 6379));
		JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes);
		// jedisCluster.(PASSWORD);

		return jedisCluster;

	}

	public Jedis getConnectionDisabled() {
		System.out.println("URL REDIS: " + ENDPOINT_REDIS);
		System.out.println("AUTH: " + AUTH);
		
		try {
			final Jedis jedis = new Jedis(ENDPOINT_REDIS, 6379, true);
			jedis.auth(AUTH);
			return jedis;
		} catch (Exception e) {
			System.out.println("ERRO: " + e);
			e.printStackTrace();
		}
		return null;
	}

}
