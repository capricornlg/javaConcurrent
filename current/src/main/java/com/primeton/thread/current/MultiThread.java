package com.primeton.thread.current;

import java.util.concurrent.atomic.AtomicInteger;

public class MultiThread {

	//private static int num = 0;
	private AtomicInteger atomicInteger = new AtomicInteger(0);

	public synchronized void printNum(String tag) {
		try {
			if (tag.equals("a")) {
				atomicInteger.set(100);
				System.out.println("tag a ,set num over");
				System.out.println("a=" + atomicInteger);
				Thread.sleep(1000);
			} else {
				atomicInteger.set(200);
				System.out.println("tag b ,set num over");
				System.out.println("b=" + atomicInteger);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		final MultiThread m1 = new MultiThread();
		final MultiThread m2 = new MultiThread();

		Thread t1 = new Thread(new Runnable() {
			public void run() {
				m1.printNum("a");
			}

		});
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				m2.printNum("b");
			}

		});
		t1.start();
		t2.start();
	}

}
