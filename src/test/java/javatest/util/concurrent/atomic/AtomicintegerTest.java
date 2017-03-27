package javatest.util.concurrent.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class AtomicintegerTest {
	
	volatile int  testInteger = 0;
	@Test
	public void demoOne() throws Exception{
		final CountDownLatch startCountDown = new CountDownLatch(1);
//		final AtomicInteger  testInteger = new AtomicInteger();
		
		Thread[] threads = new Thread[20];
		for(int i = 0 ; i < 20 ; i ++){
			threads[i] = new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						startCountDown.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					for(int j = 0 ; j < 1000 ;j ++){
						System.out.println(Thread.currentThread().getName() +"is incrementGeting");
//						testInteger.incrementAndGet();
						testInteger++;
					}
					
				}
			});
			threads[i].start();
		}
		
		startCountDown.countDown();
		for(Thread thread : threads){
			thread.join();
		}
		
		System.out.println("The value of result is :"+testInteger);
	}
}
