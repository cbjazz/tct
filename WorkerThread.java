package com.lgcns.tct.multiprocess;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class WorkerThread extends Thread {
	String queueName; 
	
	public WorkerThread(String name, String queueName) {
		super(name);
		this.queueName = queueName;
	}
	
	@Override
	public void run() {
		while (MultiThread.liveWorker) {
	        try {
	            Thread.sleep(1000);
	            //Get database connection, delete unused data from DB
	            doWork();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
		}
		System.out.println("Worker - END "+Thread.currentThread().getName());	
	}
	
    private void doWork() throws InterruptedException {
    	BlockingQueue<String> queue = MultiThread.queueMap.get(queueName);
    	
    	if(!queue.isEmpty()) {
    		String job = queue.poll();
    		System.out.println(Thread.currentThread().getName() + " DONE: " + job);
    	}
    }
}
