package com.zx.singleList;

public class NodeTest {
	
	//测试代码
	//插入结点
//		public static void main(String[] args) {
//			// 新建结点
//			Node n1 = new Node(1);
//			Node n2 = new Node(2);
//			Node n3 = new Node(3);
//			Node n4 = new Node(4);
//			//追加结点
//			n1.append(n2).append(n3).append(n4);
//			//插入结点前显示 1 2 3
//			n1.show();
//			n1.insertNode(new Node(5));
//			//插入结点后显示 1 4 2 3
//			n1.show();
//		}
		
	//删除结点
//		public static void main(String[] args) {
//			// 新建结点
//			Node n1 = new Node(1);
//			Node n2 = new Node(2);
//			Node n3 = new Node(3);
//			//追加结点
//			n1.append(n2).append(n3);
//			//删除前，打印1 2 3
//			n1.show();
//			n1.removeNode();
//			//删除后，打印 1 3
//			n1.show();
//		}
	
	public static void main(String[] args) {
		// 新建结点
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		//追加结点
		n1.append(n2).append(n3).append(new Node(4));
		//输出结点值
		System.out.println(n1.next().next().next().getData());
		//判断是否到尾结点
		System.out.println(n1.next().next().next().isLast());
	}
}
