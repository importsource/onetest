package com.importsource.onetest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.importsource.onetest.After;
import com.importsource.onetest.Function;

public class OneTest {
	
	private String label="OneTest";
	private int sample=1;
	private int threadNum=1;
	
	private Function function;

	private After after;
	
	private static final int WORKER_POOL_SIZE = 200;
	private  ExecutorService workerPool;
	
	final  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
     *  可以指定线程数的测试
     * @param label 名称（你可以给自己的本次测试起一个名字）
     * @param sample 数量（执行次数）
     * @param num 线程数量
     * @param function 要执行的函数
     */
	public OneTest(String label, int sample, int num,Function function) {
		this.label=label;
		this.sample=sample;
		this.threadNum=num;
		this.function=function;
	}
	
	/**
     *  可以指定线程数的测试
     * @param label 名称（你可以给自己的本次测试起一个名字）
     * @param sample 数量（执行次数）
     * @param num 线程数量
     * @param function 要执行的函数
     * @param after 整个测试执行完毕后的清理工作
     */
	public OneTest(String label, int sample, int num,Function function,After after) {
		this.label=label;
		this.sample=sample;
		this.threadNum=num;
		this.function=function;
		this.after=after;
	}

    /**
     *  可以指定线程数的测试
     * @param label 名称（你可以给自己的本次测试起一个名字）
     * @param sample 数量（执行次数）
     * @param num 线程数量
     */
	public OneTest(String label, int sample, int num) {
		this.label=label;
		this.sample=sample;
		this.threadNum=num;
	}

	/**
	 * 线程默认为1的测试
	 * @param label 名称（你可以给自己的本次测试起一个名字）
	 * @param sample 数量（执行次数）
	 */
	public OneTest(String label, int sample) {
		this.label=label;
		this.sample=sample;
	}
	
	public OneTest(String label) {
		this.label=label;
	}
	
	public OneTest() {
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getSample() {
		return sample;
	}

	public void setSample(int sample) {
		this.sample = sample;
	}

	public int getThreadNum() {
		return threadNum;
	}

	public void setThreadNum(int threadNum) {
		this.threadNum = threadNum;
	}

	public void setFunction(Function function) {
		this.function=function;
	}

	public void setAfter(After after) {
		this.after=after;
	}

	public void start() {
		int remainder = sample % (threadNum);
		int loopNum = (sample - remainder) / threadNum;
		// System.out.println(getLoopNum(threadNum, sample));
		CountDownLatch latch = new CountDownLatch(threadNum);// 两个工人的协作
		workerPool = Executors.newFixedThreadPool(WORKER_POOL_SIZE);
		for (int i = 0; i < threadNum; i++) {

			Worker worker = null;
			if (i == threadNum - 1 && remainder != 0) {
				worker = new Worker("default", loopNum + remainder, latch,function);
			} else {
				worker = new Worker("default", loopNum, latch,function);
			}
			workerPool.execute(worker);
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
		}// 等待所有工人完成工作
		
		System.out.println("all work done at " + sdf.format(new Date()));
		System.out.println("Labels:" + label);
		System.out.println("#Samples:" + sample);
		System.out.println("Average:" + Report.average(sample));
		Report.sort();
		System.out.println("Min:" + Report.min());
		System.out.println("Max:" + Report.max());

		System.out.println("Total:" + Report.total());
		System.out.println("Tps:" + Report.tps());
		
		System.out.println("Error(%):" + Report.errorPercent(sample) + "%");
		System.out.println("Success(%):" + Report.successPercent(sample) + "%");
		
		
		
		
	}

	public Function getFunction() {
		return function;
	}

	public After getAfter() {
		return after;
	}

}
