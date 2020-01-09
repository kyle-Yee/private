package com.zx.stack;

public class StackListTest {
    public static void main(String[] args) {
        StackList sta=new StackList();
        sta.push("张三");
        sta.push("李四");
        sta.push("王二");
        sta.push("麻子");
        System.out.println(sta.pop());
        System.out.println(sta.peek());
        System.out.println(sta.isEmpty());
        System.out.println(sta.size());

    }

}