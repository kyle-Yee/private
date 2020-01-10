package com.zx.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @param
 * @Author: AaNeei
 * @Date: 2019/6/14  22:37
 * @Description: 游学网
 * @throws:
 */
/**
 * 
 * @param
 * @Author: Yee kyle
 * @Date: 2020年1月9日下午4:15:08
 * @throws:
 */
public class ReversePolishCalculator {

    public static void main(String[] args) {
        String infixNotation = "9+(3-1)*3+10/2";
        List<String> stringList = infixToStringList(infixNotation);
        List<String> suffixStringList = toSuffixStringList(stringList);
        String s = suffixString(suffixStringList);
        System.out.println(stringList + "===>" + suffixStringList + "===>" + s);
        List<String> string = getListString(s);
        int calculate = calculate(string);
        System.out.println(infixNotation + "=" + calculate);
    }

    public static String suffixString(List<String> suffixStringList) {
        StringBuffer sb = new StringBuffer();
        for (String s : suffixStringList) {
            sb.append(s + " ");
        }
        return sb.toString().substring(0, sb.length() - 1);
    }

    public static List<String> toSuffixStringList(List<String> infixStringList) {
        Stack<String> stack = new Stack<>();
        int size = infixStringList.size();
        List<String> suffixStringList = new ArrayList<>(size);
        for (String s : infixStringList) {
            if (s.matches("\\d+")) {
                suffixStringList.add(s);
            } else if ("(".equals(s)) {
                stack.push(s);
            } else if (")".equals(s)) {
                while (!"(".equals(stack.peek())) {
                    suffixStringList.add(stack.pop());
                }
                stack.pop();//将符号栈的"("弹出
            } else {
                while (stack.size() != 0 && (symblo(s) <= symblo(stack.peek()))) {
                    suffixStringList.add(stack.pop());
                }
                stack.push(s);
            }
        }
        while (stack.size() != 0) {
            suffixStringList.add(stack.pop());
        }
        return suffixStringList;

    }

    public static int symblo(String symbol) {
        int result = 0;
        switch (symbol) {
            case "+":
            case "-":
                result = 1;
                break;
            case "*":
            case "/":
                result = 2;
                break;
            default:
                break;
        }
        return result;
    }

    public static List<String> infixToStringList(String infixNotation) {
        int length = infixNotation.length();
        List<String> stringList = new ArrayList<>(length);
        int i = 0;
        char ch;
        do {
            if ((ch = infixNotation.charAt(i)) < 48 || (ch = infixNotation.charAt(i)) > 57) {
                stringList.add(ch + "");
                i++;
            } else {
                StringBuffer sb = new StringBuffer("");
                while (i < length && (ch = infixNotation.charAt(i)) >= 48 && (ch = infixNotation.charAt(i)) <= 57) {
                    sb.append(ch + "");
                    i++;
                }
                stringList.add(sb.toString());
            }
        } while (i < length);
        return stringList;

    }

    public static int calculate(List<String> notation) {
        Stack<String> stack = new Stack<>();
        for (String s : notation) {
            if (s.matches("\\d+")) {
                stack.push(s);
            } else {
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int result = 0;
                switch (s) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num2 - num1;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        result = num2 / num1;
                        break;
                    default:
                        break;
                }
                stack.push("" + result);
            }
        }
        return Integer.parseInt(stack.pop());
    }

    //reversePolishNotation用 空格隔开
    public static List<String> getListString(String reversePolishNotation) {
        String[] array = reversePolishNotation.split(" ");
        return Arrays.asList(array);
    }
}


