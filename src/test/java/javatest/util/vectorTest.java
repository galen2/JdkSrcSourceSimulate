package javatest.util;

import java.util.Stack;
import java.util.Vector;

import org.junit.Test;

/**
 * 只能实现简单的操作
 */
public class vectorTest {
	Vector vc = new Vector();
	
	@Test
	public void commonOperate(){
		vc.add(1);
		vc.add(2);
		vc.firstElement();
		vc.lastElement();
	}
	
}
