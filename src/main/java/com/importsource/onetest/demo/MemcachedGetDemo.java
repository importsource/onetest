package com.importsource.onetest.demo;
import com.importsource.onetest.Function;
import com.importsource.onetest.OneTest;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;

public class MemcachedGetDemo {
	private static MemcachedClient MEMCACHED_CLIENT;

	static {
		try {
			MEMCACHED_CLIENT = new MemcachedClient(AddrUtil.getAddresses("localhost:11211"));
		} catch (Exception e) {
			System.err.println("Cannot init MEMCACHED Memcached Client");
		}
	}

	public static void set(String key, String value, Integer expireTime) throws Exception {
		MEMCACHED_CLIENT.set(key, expireTime, value);
	}

	public static String get(String key) throws Exception {
		Object value = MEMCACHED_CLIENT.get(key);
		if (null != value)
			return value.toString();
		return null;
	}

	public static void main(String[] args) {
		OneTest oneTest = new OneTest("Memcached get(1byte)",10000);

		Function function = new Function() {

			public void function(Object args) {
				try {
					get(args.toString()+"1");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		};

		oneTest.setFunction(function);
		oneTest.start();
		

	}
}