package com.zx.random;
/**
 * ʹ��������齱
 * @author Yee kyle
 *
 */
public class Lottery {
	public static void main(String[] args) {
		int  number = (int) ((Math.random()*100)+1);
		if(number>=50) {
			System.out.println("���н���"+number);
		}else {
			System.out.println("���ź�����û���н�"+number);
		}
	}
}
