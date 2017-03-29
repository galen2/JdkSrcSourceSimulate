package javatest.util;

import java.util.ArrayDeque;

import org.junit.Test;
@SuppressWarnings ( { "unchecked", "rawtypes" } )
/**
 *  1.队列先进先出，栈先进后出。 
 *  2、实现双队列和栈功能
 *  3、实现队列功能
 */
public class ArrayDequeTest {
	int initialCapacity = 8;
	ArrayDeque que = new ArrayDeque(initialCapacity);
	@Test
	public void insertInto(){
		int head = 0 ;
		Object[] elements = new Object[2^3];
        int index =(head - 1) & (elements.length - 1);
		System.out.println(index);
	}
	
	@Test
	public void stackTest(){
		que.addFirst(1);
		que.addFirst(11);
		System.out.println(que.removeFirst());
		System.out.println(que.removeFirst());
		que.addLast(2);
		que.addLast(22);
		System.out.println(que.removeLast());
		System.out.println(que.removeLast());
	}
	
	@Test
	public void queueTest(){
		que.addFirst(1);
		que.addFirst(11);
		System.out.println(que.removeLast());
		System.out.println(que.removeLast());
		
		que.addLast(2);
		que.addLast(22);
		System.out.println(que.removeFirst());
		System.out.println(que.removeFirst());
	}
	
}
