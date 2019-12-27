package com.zx.linearList;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Yee kyle
 *
 */
public class LinearList {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
		System.out.println(list);
		
		List<Integer> listB = new ArrayList<Integer>();
		listB.add(10);
		listB.add(1);
		listB.add(2);
		listB.add(3);
		listB.add(4);
		listB.add(5);
		for(Integer i : listB) {
			if(!list.contains(i)) {
				list.add(i);
			}else {
				System.out.println("该数字已经存在"+i);
			}
		}
		System.out.println(list);
		/*
		 * list.clear(); System.out.println(list);
		 */
		/*
		 * Integer i = list.get(2); System.out.println(i); list.add(2, 100);
		 * System.out.println(list); Integer length = list.size();
		 * System.out.println(length); list.remove(2); System.out.println(list);
		 */
	}

}
