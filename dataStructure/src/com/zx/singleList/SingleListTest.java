package com.zx.singleList;

/**
 * 
 * @author Yee kyle
 *
 */
public class SingleListTest {
	
	int size=0;//��С
	static Node headNode;
	
    int i=0;
	class Node{
		private Node next;//ָ��
		
		private int data;//������
		
	}
	
	//β�巨����������  ������ʽ�Ƚ��ȳ�
	public void fun(Node node,int data){
		if(i<=10){
			Node newNode=new Node();//�����µĽ��
			newNode.data=i;//����������
			newNode.next=null;
			node.next=newNode;
			System.out.println("β�巨"+newNode.data);
			fun(newNode,++i);
		}
	}
	
	//ͷ�巨����������  ջ��ʽ�Ƚ����
	public void afterInsert(Node node,int data){
		
		if(i<=10){
			Node newNode=new Node();//�����µĽ��
			newNode.data=i;//����������
			newNode.next=node.next;
			node.next=newNode;
			System.out.println("ͷ�巨"+newNode.data);
			afterInsert(node,++i);
		}
		
	}
	
 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		   SingleListTest test=new SingleListTest();
		   headNode=test.new Node();//ͷָ��
		   new SingleListTest().fun(headNode,0);//β�巨
		   //new SingleListTest().afterInsert(headNode,0);//ͷ�巨
           System.out.println("������������ǣ�");//0 1 2 3 4 5 6 7 8 9 10
           while(headNode.next!=null){
        	   headNode=headNode.next;
        	   System.out.print(headNode.data+" ");
           }
	}
 
}