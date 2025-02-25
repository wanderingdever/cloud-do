package com.easy.common.util.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 树形示例
 * </p>
 *
 * @author Matt
 */
public class TreeNode implements Parents<TreeNode> {
    private final String id;
    private final String parentId;
    private List<TreeNode> children;

    public TreeNode(String id, String parentId) {
        this.id = id;
        this.parentId = parentId;
        this.children = new ArrayList<>();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getParentId() {
        return parentId;
    }

    @Override
    public List<TreeNode> getChildren() {
        return children;
    }

    @Override
    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public static void main(String[] args) {
        List<TreeNode> nodes = new ArrayList<>();
        // 根节点
        nodes.add(new TreeNode("1", "0"));
        nodes.add(new TreeNode("2", "1"));
        nodes.add(new TreeNode("3", "1"));
        nodes.add(new TreeNode("4", "2"));
        nodes.add(new TreeNode("5", "2"));

        TreeBuilder<TreeNode> builder = new TreeBuilder<>();
        List<TreeNode> treeNodes = builder.buildTree(nodes, "0");
    }
}