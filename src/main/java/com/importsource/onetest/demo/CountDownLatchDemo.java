package com.importsource.onetest.demo;


import com.importsource.onetest.After;
import com.importsource.onetest.Function;
import com.importsource.onetest.OneTest;


public class CountDownLatchDemo {
	
	public static void main(String[] args) throws InterruptedException {
       OneTest oneTest=new OneTest("Redis set(1byte)",10000,1);
		
		Function function=new Function() {
			public void function(Object args) {
				System.out.println("nihao");
				
			}
		};
		After after=new After() {
			public void after(Object args) {
				System.out.println("after");
			}
		};
		
		
		oneTest.setFunction(function);
		oneTest.setAfter(after);
		oneTest.start();
		

	}

}