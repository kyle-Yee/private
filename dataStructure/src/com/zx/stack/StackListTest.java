package com.zx.stack;

public class StackListTest {
    public static void main(String[] args) {
        StackList sta=new StackList();
        sta.push("����");
        sta.push("����");
        sta.push("����");
        sta.push("����");
        System.out.println(sta.pop());
        System.out.println(sta.peek());
        System.out.println(sta.isEmpty());
        System.out.println(sta.size());

    }

}