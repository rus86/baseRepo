package com.ruscorporation.REST;

import java.util.concurrent.Callable;

public class ExecuteMethod implements Callable<String>{

	public String call() throws Exception {
		Long start = System.currentTimeMillis();		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Long finish = System.currentTimeMillis();
		System.out.println("---Execution finished---");
		return Long.toString(finish-start);
	}

}
