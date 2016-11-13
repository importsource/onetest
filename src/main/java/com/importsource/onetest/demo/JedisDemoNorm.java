package com.importsource.onetest.demo;
import redis.clients.jedis.Jedis;

public class JedisDemoNorm {

	private static Jedis jedis = new Jedis("localhost", 6379);

	public static void main(String[] args) {
		System.out.println(jedis.configGet("appendonly"));
		
	}

}
