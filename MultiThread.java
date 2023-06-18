package com.lgcns.tct.multiprocess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MultiThread {
	public static BlockingQueue<String> jobQueue1 = new LinkedBlockingQueue<>();
	public static BlockingQueue<String> jobQueue2 = new LinkedBlockingQueue<>();
	
	public static Map<String, BlockingQueue<String>> queueMap = new HashMap();
	
	public static Boolean liveGenerator = true;
	public static Boolean liveWorker = true;
	
	
	public static void main(String[] args) throws InterruptedException {
		queueMap.put("q1", MultiThread.jobQueue1);
		queueMap.put("q2", MultiThread.jobQueue2);
			
        //Thread t1 = new Thread(new WorkerProcessRunnable(), "t1");
        //Thread t2 = new Thread(new WorkerProcessRunnable(), "t2");
        //System.out.println("Starting Runnable threads");
        //t1.start();
        //t2.start();
		
		Thread genT = new GeneratorThread("Starting Generator");
		
		Thread t1 = new WorkerThread("worker1", "q1");
        Thread t2 = new WorkerThread("worker2", "q2");
        
        genT.start();
        
        System.out.println("Starting WorkThreads");
        t1.start();
        t2.start();
        
        Thread.sleep(60000L);
        MultiThread.liveGenerator = false;
        
        Thread.sleep(3000L);
        MultiThread.liveWorker = false;        
	}

}
