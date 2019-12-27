package com.zx.singleList;

//�ֶ�ʵ��һ��������
public class Node {

	// ����һ��������
	int data;
	// ����һ��ָ����
	Node next;

	// ���췽��
	public Node(int data) {
		this.data = data;
	}

	// ��ȡ����е�����
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

	// ׷�ӽ��
	public Node append(Node node) {
		Node currentNode = this;
		while (true) {
			Node nextNode = currentNode.next;
			//��β���ʱ�˳�
			if (nextNode == null)
				break;
			currentNode = nextNode;
		}
		currentNode.next = node;
		return this;
	}
	
	//������
	public void insertNode(Node node) {
		//������һ�������Ϊ���¸����
		Node nextNext=next;
		//��Ҫ����Ľ��ŵ��˽���
		this.next=node;
		//��ָ��ָ�����¸������ɲ�����
		node.next=nextNext;
	}
	
	// �жϽ���Ƿ�Ϊβ���
	public boolean isLast() {
		return this.next == null;
	}

	// ��ȡ��һ�����
	public Node next() {
		return this.next;
	}
	
	//ɾ���ڵ�
		public void removeNode() {
			//ȡ���¸����
			Node nextNode=next.next;
			//����ǰ���ָ�����¸����ʵ��ɾ��
			this.next=nextNode;
		}
		
		//��ʾ���нڵ�
		public void show() {
			//��ǰ���
			Node currentNode1=this;
			while(true) {
				if(currentNode1.next!=null)
					{
					//ѭ����ӡ�����н�㣨��Ϊβ��㣩
					System.out.print(currentNode1.data+" ");
					//ָ�������һλ
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