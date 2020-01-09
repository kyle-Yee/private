package com.zx.stack;

import java.util.Stack;

public class MyStack {
	public static void main(String[] args) {
		Stack<Integer> st = new Stack<Integer>();
		st.add(2);
		st.add(3);
		st.add(4);
		st.add(5);
		st.add(6);
		st.add(7);
		st.add(8);
		st.add(9);
		st.add(0);
		st.peek();
		st.search(2);
		st.pop();
		st.empty();
		System.out.println(st);
	}

}
