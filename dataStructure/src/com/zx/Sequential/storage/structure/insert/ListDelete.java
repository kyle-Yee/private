package com.zx.Sequential.storage.structure.insert;

import java.util.Arrays;

public class ListDelete {
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5};
		int[] arr1= delete(arr,2);
		for(int v : arr1) {
			System.out.println(v+"");
		}
	}

	private static int[] delete(int[] arr, int i) {
		int arr1[] = Arrays.copyOf(arr, arr.length-1);
		if(i<1 || i>arr.length) {
			System.out.println("删除位置超出数组范围");
		}
		for(int j=0;j<i;j++) {
			arr1[i]=arr[i];
		}
		for(int j=i-1;j<=arr1.length-1;j++) {
			arr1[i-1]=arr[i];
			i++;
		}
		return arr1;
	}
}
