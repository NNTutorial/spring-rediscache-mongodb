package com.nishant.spring.redis.cache.vo;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class RedisVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6434150130889335930L;
	@Id
	private Integer keyID;
	private String databasename;
	private String databaseversion;



	public Integer getKeyID() {
		return keyID;
	}
	public void setKeyID(Integer keyID) {
		this.keyID = keyID;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getDatabasename() {
		return databasename;
	}
	public void setDatabasename(String databasename) {
		this.databasename = databasename;
	}
	public String getDatabaseversion() {
		return databaseversion;
	}
	public void setDatabaseversion(String databaseversion) {
		this.databaseversion = databaseversion;
	}

}
