package com.importsource.onetest.demo;
import com.importsource.onetest.After;
import com.importsource.onetest.Function;
import com.importsource.onetest.OneTest;

import redis.clients.jedis.Jedis;

public class JedisDemo {

	private static Jedis jedis = new Jedis("localhost", 6379);

	public static void main(String[] args) {
		OneTest oneTest=new OneTest("Redis set(1byte)",10000);
		
		Function function=new Function() {
			public void function(Object args) {
				jedis.set(args.toString()+"1", args.toString()
						);
				
			}
		};
		After after=new After() {
			public void after(Object args) {
				jedis.close();
			}
		};
		
		
		oneTest.setFunction(function);
		oneTest.setAfter(after);
		oneTest.start();
		
	}

}
