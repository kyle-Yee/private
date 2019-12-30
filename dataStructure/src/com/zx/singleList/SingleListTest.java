package com.zx.singleList;

/**
 * 
 * @author Yee kyle
 *
 */
public class SingleListTest {
	
	int size=0;//大小
	static Node headNode;
	
    int i=0;
	class Node{
		private Node next;//指针
		
		private int data;//数据域
		
	}
	
	//尾插法创建单链表  队列形式先进先出
	public void fun(Node node,int data){
		if(i<=10){
			Node newNode=new Node();//创建新的结点
			newNode.data=i;//设置数据域
			newNode.next=null;
			node.next=newNode;
			System.out.println("尾插法"+newNode.data);
			fun(newNode,++i);
		}
	}
	
	//头插法创建单链表  栈形式先进后出
	public void afterInsert(Node node,int data){
		
		if(i<=10){
			Node newNode=new Node();//创建新的结点
			newNode.data=i;//设置数据域
			newNode.next=node.next;
			node.next=newNode;
			System.out.println("头插法"+newNode.data);
			afterInsert(node,++i);
		}
		
	}
	
 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		   SingleListTest test=new SingleListTest();
		   headNode=test.new Node();//头指针
		   new SingleListTest().fun(headNode,0);//尾插法
		   //new SingleListTest().afterInsert(headNode,0);//头插法
           System.out.println("创建后的链表是：");//0 1 2 3 4 5 6 7 8 9 10
           while(headNode.next!=null){
        	   headNode=headNode.next;
        	   System.out.print(headNode.data+" ");
           }
	}
 
}