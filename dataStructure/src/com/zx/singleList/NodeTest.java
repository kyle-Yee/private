package com.zx.singleList;

public class NodeTest {
	
	//���Դ���
	//������
//		public static void main(String[] args) {
//			// �½����
//			Node n1 = new Node(1);
//			Node n2 = new Node(2);
//			Node n3 = new Node(3);
//			Node n4 = new Node(4);
//			//׷�ӽ��
//			n1.append(n2).append(n3).append(n4);
//			//������ǰ��ʾ 1 2 3
//			n1.show();
//			n1.insertNode(new Node(5));
//			//���������ʾ 1 4 2 3
//			n1.show();
//		}
		
	//ɾ�����
//		public static void main(String[] args) {
//			// �½����
//			Node n1 = new Node(1);
//			Node n2 = new Node(2);
//			Node n3 = new Node(3);
//			//׷�ӽ��
//			n1.append(n2).append(n3);
//			//ɾ��ǰ����ӡ1 2 3
//			n1.show();
//			n1.removeNode();
//			//ɾ���󣬴�ӡ 1 3
//			n1.show();
//		}
	
	public static void main(String[] args) {
		// �½����
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		//׷�ӽ��
		n1.append(n2).append(n3).append(new Node(4));
		//������ֵ
		System.out.println(n1.next().next().next().getData());
		//�ж��Ƿ�β���
		System.out.println(n1.next().next().next().isLast());
	}
}
