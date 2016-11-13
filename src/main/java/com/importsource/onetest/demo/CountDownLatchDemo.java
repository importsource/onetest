package com.importsource.onetest.demo;


import com.importsource.onetest.After;
import com.importsource.onetest.Function;
import com.importsource.onetest.OneTest;

import redis.clients.jedis.Jedis;

public class CountDownLatchDemo {
	private static Jedis jedis = new Jedis("localhost", 6379);
	
	public static void main(String[] args) throws InterruptedException {
       OneTest oneTest=new OneTest("Redis set(1byte)",10000,1);
		
		Function function=new Function() {
			public void function(Object args) {
				jedis.set(args.toString(), args.toString());
				
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

	static int getLoopNum(int threadNum, int sample) {
		int remainder = sample % threadNum;
		return remainder;
	}

	

}