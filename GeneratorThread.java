package com.lgcns.tct.multiprocess;

import java.util.Random;

public class GeneratorThread extends Thread {
	
	Random rand = new Random();
	Integer job_seq = 0;
	
	public GeneratorThread(String name) {
		super(name);
		rand.setSeed(1L);
	}
	
	@Override
	public void run() {
		while (MultiThread.liveGenerator) {
	        try {
	            Thread.sleep(1000);
	            //Get database connection, delete unused data from DB
	            genWorkload();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
		}
	    System.out.println("Generator - END "+Thread.currentThread().getName());		
	}
	
    private void genWorkload() throws InterruptedException {
    	String workload = ++job_seq + " JOB";
    	
    	if (rand.nextInt() % 2 == 0) {
    		MultiThread.jobQueue1.add(workload);
    		System.out.println(">>>>Generate Workload in queue1: " + workload);
    	} else {
    		MultiThread.jobQueue2.add(workload);
    		System.out.println("<<<<Generate Workload in queue2: " + workload);
    	}
    }
}
