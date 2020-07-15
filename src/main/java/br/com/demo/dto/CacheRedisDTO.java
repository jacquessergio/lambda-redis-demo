package br.com.demo.dto;

import java.io.Serializable;

import br.com.demo.exception.DemoElastiCacheException;

public class CacheRedisDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String key;
	private String value;
	private TTLCacheRedisDTO ttl;

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public TTLCacheRedisDTO getTtl() {
		return ttl;
	}

	public void hasError() throws DemoElastiCacheException {
		if (key == null || value == null) {
			throw new DemoElastiCacheException(">> Parâmetros Key e Value não pode ser null");
		} else if (ttl.getNx() && ttl.getXx()) {
			throw new DemoElastiCacheException(">> Deve-se escolher apenas uma opção como true - [(NX) Somente seta se o Key não existir] ou [(XX) Somente seta se o Key já existir]");
		} else if (ttl.getSeconds() > 0 && ttl.getMiliseconds() > 0) {
			throw new DemoElastiCacheException(">> Deve-se escolher apenas um Time to Life (TTL) - Tempo em [(EX) Segundos] ou [(PX) milesegundos].");
		}

	}

}
