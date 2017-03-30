package javatest.util;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.Test;
@SuppressWarnings ( { "unchecked", "rawtypes" } )

public class TreeMapTest {
	@Test
	public void baseMap(){
//		Map map = new TreeMap();
		Map map = new TreeMap(new Comparator<Integer>(){
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2-o1;
			}
		} 
		);
		initMap(map);
		print(map);
	}
	
	@Test
	public void SortedMapTest(){
		SortedMap map = new TreeMap(new Comparator<Integer>(){
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2-o1;
			}
		} 
		);
		initMap(map);
		System.out.println("firstKey"+map.firstKey());
		System.out.println("lastKey"+map.lastKey());
		print(map.subMap(map.firstKey(), map.lastKey()));
	}
	
	@Test
	public void NavigableMapTest(){
		NavigableMap map = new TreeMap(new Comparator<Integer>(){
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2-o1;
			}
		} 
		);
		
		initMap(map);
		Object firstKey = map.firstKey();
		System.out.println(firstKey);
		System.out.println(map.lowerKey(firstKey));
	}
	
	public void initMap(Map map){
		map.put(2, "zhangsan");
		map.put(1, "zhangsan");
		map.put(3, "zhangsan");
		map.put(4, "zhangsan");
	}
	public void print(Map map){
		Set<Entry> sets = map.entrySet();
		for(Entry entry : sets){
			System.out.println(entry.getKey());
		}
	}
}
