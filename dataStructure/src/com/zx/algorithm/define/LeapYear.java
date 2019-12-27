package com.zx.algorithm.define;

import java.io.IOException;
import java.util.Scanner;
/**
 * 闰年
 * @author Yee kyle
 *
 */
public class LeapYear {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入一个年份：");
		int year=sc.nextInt();
		System.out.println("输入的年份："+year);
		if(year%4==0 && year%100!=0 || year%400==0) {
			System.out.println(year+"年是闰年");
		}else {
			System.out.println(year+"年不是闰年");
		}
	}

}
