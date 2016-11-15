package com.importsource.onetest;
import java.util.List;

/**
 * 执行结果。主要是成功和失败
 * @author Hezf
 *
 */
public class Result {
	private volatile List<Error> errors;
	private volatile List<Success> successes;

	private volatile long errorNum=0;
	private volatile long successNum=0;

	public synchronized List<Error> getErrors() {
		return errors;
	}

	public synchronized void setErrors(List<Error> errors) {
		this.errors = errors;
	}

	public synchronized List<Success> getSuccesses() {
		return successes;
	}

	public synchronized void setSuccesses(List<Success> successes) {
		this.successes = successes;
	}

	public synchronized long getErrorNum() {
		return errorNum;
	}

	public synchronized void setErrorNum(long errorNum) {
		this.errorNum = errorNum;
	}

	public long getSuccessNum() {
		return successNum;
	}

	public void setSuccessNum(long successNum) {
		this.successNum = successNum;
	}
}
