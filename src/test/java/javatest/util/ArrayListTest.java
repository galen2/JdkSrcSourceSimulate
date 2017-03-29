package javatest.util;

import java.util.ArrayList;

import org.junit.Test;
@SuppressWarnings ( { "unchecked", "rawtypes" } )
public class ArrayListTest {
	ArrayList list = new ArrayList();
	@Test
	public void run(){
		list.add(1);
		list.add(2);
		Object one = list.get(0);
		System.out.println(one);
	}
}
