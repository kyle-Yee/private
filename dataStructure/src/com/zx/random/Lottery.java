package com.zx.random;
/**
 * 使用随机数抽奖
 * @author Yee kyle
 *
 */
public class Lottery {
	public static void main(String[] args) {
		int  number = (int) ((Math.random()*100)+1);
		if(number>=50) {
			System.out.println("你中奖啦"+number);
		}else {
			System.out.println("很遗憾，您没有中奖"+number);
		}
	}
}
