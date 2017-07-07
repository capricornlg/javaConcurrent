package com.primeton.thread.current;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchClient {

	public static void main(String[] args) {

		CountDownLatch latch = new CountDownLatch(3);

		Waiter waiter = new Waiter(latch);
		Decrementer decrementer = new Decrementer(latch);

		new Thread(waiter).start();
		new Thread(decrementer).start();

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
