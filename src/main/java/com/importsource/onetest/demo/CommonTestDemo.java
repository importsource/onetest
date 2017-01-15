package com.importsource.onetest.demo;

import com.importsource.onetest.After;
import com.importsource.onetest.Function;
import com.importsource.onetest.OneTest;
import com.importsource.onetest.Report;

/**
 * @author hezhuofan
 */
public class CommonTestDemo {
    public static void main(String[] args) {

        for (int i = 0; i <2 ; i++) {
            test();
        }

    }

    private static void test() {
        final OneTest oneTest=new OneTest("Redis set(1byte)",10);
        Function function=new Function() {
            public void function(Object args) {
               // throw new RuntimeException("sdf");
                // System.out.println(args);
            }
        };
        After after=new After() {
            public void after(Object args) {
                if(oneTest!=null){
                 oneTest.clear();}
               // Report.clearAll();
                oneTest.stop();
            }
        };


        oneTest.setFunction(function);
        oneTest.setAfter(after);
        oneTest.setDebug(false);
        oneTest.start();
    }
}
