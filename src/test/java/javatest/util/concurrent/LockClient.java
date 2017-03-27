package javatest.util.concurrent;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class LockClient {

	@Test
	public void ReenLockRun(){
		ReentrantLock lock  = new ReentrantLock();
		lock.lock();
		lock.unlock();

//		Hashtable<K, V>
//		HashMap<K, V>
		
//		ThreadPoolExecutor		
	}
}
