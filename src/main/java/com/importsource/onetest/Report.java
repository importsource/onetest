/**
 * 
 */
package com.importsource.onetest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import com.importsource.onetest.PerRequest;
import com.importsource.onetest.Result;

/**
 * 用来保存聚合报告的
 * @author Hezf
 *
 */
public class Report {
	public  static  Result result = new Result();
	
	private static BigDecimal average;

	private static List<PerRequest> perRequests = new ArrayList<PerRequest>();

	private static long min;
	private static long max;
	private static long total = 0;

	private static float tps;
	
	public static synchronized void setErrorNum(){
		
	}

	public static synchronized void add(PerRequest perRequest) {
		perRequests.add(perRequest);
	}

	public static List<PerRequest> list() {
		return perRequests;
	}

	public static synchronized void setMax(long time) {
		if (time > max) {
			max = time;
		}

	}

	public static synchronized void setMin(long time) {
		if (time < min) {
			min = time;
		}

	}

	public static float tps() {
		if (tps == 0) {
			BigDecimal loopNumDecimal = new BigDecimal(list().size());
			float tps = (loopNumDecimal.floatValue() / total) * 1000;
			Report.tps = tps;
		}
		return tps;
	}

	public static long min() {
		return min;
	}

	public static long max() {
		return max;
	}

	public static synchronized void setTotal(long time) {
		total += time;
	}
	
	public static long total() {
		return total;
	}
	
	public  static float successPercent(int sample){return (100 * (success() / sample));}

	private static long success() {
		return result.getSuccessNum();
	}

	public  static float errorPercent(int sample){return (100 * (error() / sample));}

	private static long error() {
		return result.getErrorNum();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  static  void sort() {
		Collections.sort(perRequests, new Comparator() {
			public int compare(Object o1, Object o2) {
				PerRequest perRequest1 = (PerRequest) o1;
				PerRequest perRequest2 = (PerRequest) o2;
				return new Long(perRequest1.getTime()).compareTo(new Long(perRequest2.getTime()));
			}

		});
		System.out.println("medain:" + median());

		System.out.println("10% Line:" + perLine(10));
		System.out.println("30% Line:" + perLine(30));
		System.out.println("50% Line:" + perLine(50));
		System.out.println("90% Line:" + perLine(90));
		System.out.println("95% Line:" + perLine(95));
		System.out.println("99% Line:" + perLine(99));

	}

	private static  long perLine(int i) {
		return perRequests.get((perRequests.size() / 10) * (i / 10)).getTime();
	}

	private static long median() {
		PerRequest perRequest = perRequests.get(perRequests.size() / 2);
		long median = perRequest.getTime();
		return median;
	}
	
	
	public  static   BigDecimal average(int sample){
		BigDecimal totalDecimal = new BigDecimal(total);
		BigDecimal loopNumDecimal = new BigDecimal(sample);

		average = totalDecimal.divide(loopNumDecimal);
		return average;
	}
	
	

}
