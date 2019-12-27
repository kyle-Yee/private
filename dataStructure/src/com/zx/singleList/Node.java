package com.zx.singleList;

//手动实现一个单链表
public class Node {

	// 定义一个数据域
	int data;
	// 定义一个指针域
	Node next;

	// 构造方法
	public Node(int data) {
		this.data = data;
	}

	// 获取结点中的数据
	public int getData() {
		return this.data;
	}
	
	

	public void setData(int data) {
		this.data = data;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	// 追加结点
	public Node append(Node node) {
		Node currentNode = this;
		while (true) {
			Node nextNode = currentNode.next;
			//到尾结点时退出
			if (nextNode == null)
				break;
			currentNode = nextNode;
		}
		currentNode.next = node;
		return this;
	}
	
	//插入结点
	public void insertNode(Node node) {
		//保存下一个结点作为下下个结点
		Node nextNext=next;
		//将要插入的结点放到此结点后
		this.next=node;
		//将指针指向下下个结点完成插入结点
		node.next=nextNext;
	}
	
	// 判断结点是否为尾结点
	public boolean isLast() {
		return this.next == null;
	}

	// 获取下一个结点
	public Node next() {
		return this.next;
	}
	
	//删除节点
		public void removeNode() {
			//取下下个结点
			Node nextNode=next.next;
			//将当前结点指向下下个结点实现删除
			this.next=nextNode;
		}
		
		//显示所有节点
		public void show() {
			//当前结点
			Node currentNode1=this;
			while(true) {
				if(currentNode1.next!=null)
					{
					//循环打印出所有结点（不为尾结点）
					System.out.print(currentNode1.data+" ");
					//指针向后移一位
					currentNode1=currentNode1.next;
					}
				else {
					System.out.println(currentNode1.data+" ");
					break;
				}
			}
			System.out.println();
		}
}