package br.com.demo.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TTLCacheRedisDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int seconds;

	private int miliseconds;

	private Boolean nx;

	private Boolean xx;

	private Boolean keepttl;

	public int getSeconds() {
		return seconds;
	}

	public int getMiliseconds() {
		return miliseconds;
	}

	public Boolean getNx() {
		return nx;
	}

	public Boolean getXx() {
		return xx;
	}

	public Boolean getKeepttl() {
		return keepttl;
	}

}
