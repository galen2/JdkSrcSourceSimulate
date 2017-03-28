package javatest.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

import org.junit.Test;

public class PriorityQueueTest {
	//逆序排列
	PriorityQueue queue = new PriorityQueue(11, Collections.reverseOrder());
	//顺序排列
//	PriorityQueue queue = new PriorityQueue();

	@Test
	public void test(){
		queue.offer(10);
		queue.add(22);
		queue.addAll(Arrays.asList(new Integer[]{
		    11, 12, 34, 2, 7, 4, 15, 12, 8, 6, 19, 13 }));
		while(queue.peek()!=null){
		    System.out.print(queue.poll() + " ");
		}
	}
}
