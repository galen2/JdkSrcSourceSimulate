package javatest.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

import org.junit.Test;
@SuppressWarnings ( { "unchecked", "rawtypes" } )
public class PriorityQueueTest {
	//逆序排列
//	PriorityQueue queue = new PriorityQueue(11, Collections.reverseOrder());
	//顺序排列
	PriorityQueue queue = new PriorityQueue();

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
	
	@Test
	public void queueTest(){
		queue.add(5);
		queue.add(4);
		queue.add(3);
		queue.add(2);
		queue.add(1);
		print();
	}
	
	public void print(){
		while(queue.peek()!=null){
		    System.out.print(queue.poll() + " ");
		}	
	}
	@Test
	public void base(){
        int parent = (5 - 1) >>> 1;
        System.out.println(parent);

	}
}
