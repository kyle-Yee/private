package com.zx.stack;
/**
 * 递归
 * @author Yee kyle
 *
 */
public class MyRecursive {
	public static int Fibonacci(int i) {
		int count = 0;
			switch (i) {
				case 0:
					count = 1;
					break;
				case 1:
					count = 1;
					break;
			default:
					count = Fibonacci(i-1)+Fibonacci(i-2); 
					break;
					
				}
		return count;
	}
		
	public static void main(String[] args) {
		for (int i = 0; i < 40; i++) {
			//array[i]=Fibonacci(i,array);
			System.out.println("赋值之后的"+Fibonacci(i));
		}
	}

	
//	public static void main(String[] args) {
//		int i;
//		int[] array = new int[10];
//		array[0]=1;
//		array[1]=1;
//		int count;
//		for (i = 0; i < array.length;i++) {
//			switch (i) {
//				case 0:
//					count = array[0];
//					break;
//				case 1:
//					count = array[1];
//					break;
//				default:
//					array[i] = array[i-1]+array[i-2]; 
//					break;
//				}
//			System.out.println(array[i]);
//		}
//	}
}
