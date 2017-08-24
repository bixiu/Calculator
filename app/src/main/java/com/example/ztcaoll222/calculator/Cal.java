package com.example.ztcaoll222.calculator;

import java.util.Objects;
import java.util.Stack;

/**
 * Created by ztcaoll222 on 2017/8/24.
 */

public class Cal {
    private static boolean isDouble(char c) {
        return c != '(' &&
                c != ')' &&
                c != '+' &&
                c != '-' &&
                c != '*' &&
                c != '/';
    }

    private static Stack<Object> reverseStack(Stack<Object> stack) {
        Stack<Object> m_stack = new Stack<Object>();

        while (!stack.empty()) {
            m_stack.push(stack.pop());
        }

        return m_stack;
    }

    private static Stack<Object> handleCommon(String common) throws java.lang.NumberFormatException {
        Stack<Object> expression = new Stack<Object>();

        for (int i = 0; i < common.length(); i++) {
            if (!isDouble(common.charAt(i))) {
                switch (common.charAt(i)) {
                    case '+':
                        if (Objects.equals(expression.peek().getClass().getName(), "java.lang.Double") || Objects.equals(expression.peek(), ')')) {
                            expression.push('+');
                        }
                        break;

                    case '-':
                        if (Objects.equals(expression.peek().getClass().getName(), "java.lang.Double") || Objects.equals(expression.peek(), ')')) {
                            expression.push('-');
                        } else {
                            expression.push('#');
                        }
                        break;

                    case '*':
                    case '/':
                    case '(':
                    case ')':
                        expression.push(common.charAt(i));
                        break;
                }
            } else {
                StringBuilder temp = new StringBuilder();
                while (isDouble(common.charAt(i))) {
                    temp.append(common.charAt(i));
                    i++;
                    if (i >= common.length()) {
                        break;
                    }
                }

                double num = 0;
                try {
                    num = Double.valueOf(temp.toString());
                } catch (java.lang.NumberFormatException e) {
                    throw new java.lang.NumberFormatException(String.valueOf(i));
                }

                if (!expression.isEmpty()) {
                    while (Objects.equals(expression.peek(), '#')) {
                        num = -1 * num;
                        expression.pop();
                    }
                }

                expression.push(num);

                i--;
            }
        }

        return expression;
    }

    private static Stack<Object> toPolishNotation(Stack<Object> commonList) {
        Stack<Object> s1 = new Stack<Object>();
        Stack<Object> s2 = new Stack<Object>();

        for (Object i :
                commonList) {
            if (Objects.equals(i.getClass().getName(), "java.lang.Double")) {
                // 遇到操作数时
                s2.push(i);
            } else {
                String temp = i.toString();
                if (!(Objects.equals(i, '(') || Objects.equals(i, ')'))) {
                    // 遇到运算符时
                    while (true) {
                        if (s1.empty() || Objects.equals(s1.peek(), ')')) {
                            // 如果S1为空，或栈顶运算符为右括号“)”，则直接将此运算符入栈
                            s1.push(i);
                            break;
                        } else if (Objects.equals(s1.peek(), '+') || Objects.equals(s1.peek(), '-')) {
                            // 否则，若优先级比栈顶运算符的较高或相等，也将运算符压入S1
                            s1.push(i);
                            break;
                        } else {
                            // 否则，将S1栈顶的运算符弹出并压入到S2中，再次转到前面与S1中新的栈顶运算符相比较
                            s2.push(s1.pop());
                        }
                    }
                } else {
                    // 遇到括号时
                    if (Objects.equals(i, ')')) {
                        // 如果是右括号“)”，则直接压入S1
                        s1.push(i);
                    } else {
                        // 如果是左括号“(”，则依次弹出S1栈顶的运算符，并压入S2，直到遇到右括号为止，此时将这一对括号丢弃
                        while (!Objects.equals(s1.peek(), ')')) {
                            s2.push(s1.pop());
                        }
                        s1.pop();
                    }
                }
            }
        }

        while (!s1.empty()) {
            s2.push(s1.pop());
        }

        return s2;
    }

    private static Double calc(Double a, Character operator, Double b) {
        Double ret = null;
        switch (operator) {
            case '+':
                ret = a+b;
                break;
            case '-':
                ret = a-b;
                break;
            case '*':
                ret = a*b;
                break;
            case '/':
                ret = a/b;
                break;
            default:
                break;
        }
        return ret;
    }

    private static Double cal(Stack<Object> expression) {
        Stack<Object> temp = new Stack<Object>();

        for (Object i :
                expression) {
            if (Objects.equals(i.getClass().getName(), "java.lang.Double")) {
                // 从右至左扫描，将操作数压入堆栈
                temp.push(i);
            } else {
                // 遇到运算符，弹出栈顶元素和次顶元素，计算出值，再将值入栈
                temp.push(calc((Double)temp.pop(), (Character)i, (Double) temp.pop()));
            }
        }

        return (Double)temp.pop();
    }

    Double start(String common) {
        Stack<Object> commonList = null;

        try {
            commonList = handleCommon(common);
        } catch (java.lang.NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        if (commonList == null) {
            return 0.0;
        }

        Stack<Object> expression = null;
        expression = toPolishNotation(reverseStack(commonList));

        return cal(expression);
    }
}
