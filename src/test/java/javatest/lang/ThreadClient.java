package javatest.lang;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;

public class ThreadClient {
	
	
	@Test
	public void  joinTest() throws Exception{
		final CountDownLatch countDown = new CountDownLatch(1);
		
		Thread one = new Thread(new Runnable() {
			
			@Override
			public void run() {
					try {
						System.out.println("thred one into");
						countDown.await();
						System.out.println("thred one is beging");
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("thred one is ending");
			}
		});
		
		Thread two = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					System.out.println("thred two into");
					countDown.await();
					System.out.println("thred two is beging");
					Thread.sleep(1000);
					System.out.println("thred two is ending");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		one.start();
		two.start();
//		one.join();
//		two.join();
		
		Thread.sleep(2000);
		countDown.countDown();
		System.out.println("main is beging");
		Thread.sleep(5000);
	}
	
	@Test
	public void openFile(){
		try {
			for(int i=0;i<500000;i++){
				FileInputStream file = new FileInputStream("E://aa.xlsx");
				try {
					int read = file.read();
					System.out.println(read);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
