package com.zx.Sequential.storage.structure.insert;

public class ListInsert {
	/*
	 * static int MAXSIZE=5;
	 */
		/*
		 * int i=2;//插入的位置 int e=6;//要插入的元素 //List<Integer> list = new
		 * ArrayList<Integer>(2); int arr[] = {1,2,3,4,5}; int k;
		 * System.out.println(arr[2]); if(arr.length==MAXSIZE) {
		 * System.out.println("空间已满"); } if(i<1 || i>arr.length+1) {
		 * System.out.println("超出范围"); } System.out.println(arr.length+"111111111");
		 * 
		 * if(i<=arr.length) { for(k=arr.length-1;k>=i-1;k--) { arr[k+1]=arr[k]; } arr =
		 * Arrays.copyOf(arr, arr.length+1); arr[i]=e; } System.out.println(arr.length);
		 */
		 public static long[] insert(long[] arr,int i,long l){
				//新建数组,对原数组扩容
			 	//arr = Arrays.copyOf(arr, arr.length+1);数组扩容，在原来数组后面加一位，默认值为0
				long[] arr1 = new long[arr.length+1];
				//将原数组数据赋值给新数组
				for(int j = 0;j<arr.length;j++){
					arr1[j] = arr[j];
				}
				//将大于i的数据向后移动一位
				for(int j = arr1.length-2;j>i;j--){
					arr1[j+1] = arr1[j];
				}
				//赋值到i位置
				arr1[i+1] = l;
				return arr1;
			}
			//测试
			public static void main(String[] args){
				long[] arr = {12,25,11,36,14};
				long[] arr1 = insert(arr, 2, 100);
				for (long l : arr1) {
					System.out.print(l+" ");
				}

	}
}
