package com.zx.algorithm.define;

import java.io.IOException;
import java.util.Scanner;
/**
 * ����
 * @author Yee kyle
 *
 */
public class LeapYear {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("������һ����ݣ�");
		int year=sc.nextInt();
		System.out.println("�������ݣ�"+year);
		if(year%4==0 && year%100!=0 || year%400==0) {
			System.out.println(year+"��������");
		}else {
			System.out.println(year+"�겻������");
		}
	}

}
