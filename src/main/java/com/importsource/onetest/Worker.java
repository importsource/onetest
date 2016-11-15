package com.importsource.onetest;

import java.util.concurrent.CountDownLatch;

import com.importsource.onetest.Function;
import com.importsource.onetest.PerRequest;

/**
 * 工作线程
 * @author Hezf
 *
 */
public class Worker extends Thread {
		String workerName;
		int loopNum;
		CountDownLatch latch;
		Function function;

		public Worker(String workerName, int loopNum, CountDownLatch latch) {
			this.workerName = workerName;
			this.loopNum = loopNum;
			this.latch = latch;
		}

		public Worker(String workerName, int loopNum, CountDownLatch latch, Function function) {
			this(workerName, loopNum, latch);
			this.function=function;
		}

		public void run() {
			try {
				for (int i = 0; i < loopNum; i++) {
					doWork();// 工作了
				}
			} finally {
				latch.countDown();// 工人完成工作，计数器减一
			}
		}

		private void doWork() {
			long start=0;
			long end=0;
			boolean isError=false;
			try {
				start = System.currentTimeMillis();
				function.function(latch.getCount());
				end = System.currentTimeMillis();
				System.out.println(latch.getCount());
				isError=false;
			} catch (RuntimeException e) {
				end = System.currentTimeMillis();
				isError=true;
				Report.result.setErrorNum(Report.result.getErrorNum() + 1);
			} finally {
				if (!isError) {
					Report.result.setSuccessNum(Report.result.getSuccessNum() + 1);
				}
				long time = end - start;

				PerRequest perRequest = new PerRequest();
				perRequest.setTime(time);

				Report.add(perRequest);
				Report.setMin(time);
				Report.setMax(time);
				Report.setTotal(time);
			}
		}

	}