package com.example.ztcaoll222.calculator;

/**
 * Created by ztcaoll222 on 2017/8/26.
 */

public class TreeNode {
    private int value;
    private ExpressionTreeNode eNode;

    public TreeNode(int value, Object sympol) {
        this.value = value;
        this.eNode = new ExpressionTreeNode(sympol);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ExpressionTreeNode geteNode() {
        return eNode;
    }

    public void seteNode(ExpressionTreeNode eNode) {
        this.eNode = eNode;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "value=" + value +
                ", eNode=" + eNode.toString() +
                '}';
    }
}
