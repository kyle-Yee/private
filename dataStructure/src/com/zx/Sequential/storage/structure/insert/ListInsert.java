package com.zx.Sequential.storage.structure.insert;

public class ListInsert {
	/*
	 * static int MAXSIZE=5;
	 */
		/*
		 * int i=2;//�����λ�� int e=6;//Ҫ�����Ԫ�� //List<Integer> list = new
		 * ArrayList<Integer>(2); int arr[] = {1,2,3,4,5}; int k;
		 * System.out.println(arr[2]); if(arr.length==MAXSIZE) {
		 * System.out.println("�ռ�����"); } if(i<1 || i>arr.length+1) {
		 * System.out.println("������Χ"); } System.out.println(arr.length+"111111111");
		 * 
		 * if(i<=arr.length) { for(k=arr.length-1;k>=i-1;k--) { arr[k+1]=arr[k]; } arr =
		 * Arrays.copyOf(arr, arr.length+1); arr[i]=e; } System.out.println(arr.length);
		 */
		 public static long[] insert(long[] arr,int i,long l){
				//�½�����,��ԭ��������
			 	//arr = Arrays.copyOf(arr, arr.length+1);�������ݣ���ԭ����������һλ��Ĭ��ֵΪ0
				long[] arr1 = new long[arr.length+1];
				//��ԭ�������ݸ�ֵ��������
				for(int j = 0;j<arr.length;j++){
					arr1[j] = arr[j];
				}
				//������i����������ƶ�һλ
				for(int j = arr1.length-2;j>i;j--){
					arr1[j+1] = arr1[j];
				}
				//��ֵ��iλ��
				arr1[i+1] = l;
				return arr1;
			}
			//����
			public static void main(String[] args){
				long[] arr = {12,25,11,36,14};
				long[] arr1 = insert(arr, 2, 100);
				for (long l : arr1) {
					System.out.print(l+" ");
				}

	}
}
