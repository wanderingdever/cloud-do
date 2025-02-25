package com.easy.common.util.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * </p>
 *
 * @author Matt
 */

public class TreeBuilder<T extends Parents<T>> {

    /**
     * 根据parentId构建树形结构
     *
     * @param nodes        节点列表
     * @param rootParentId 根节点的parentId
     * @return 构建好的树形结构
     */
    public List<T> buildTree(List<T> nodes, String rootParentId) {
        Map<String, T> nodeMap = new HashMap<>();
        List<T> roots = new ArrayList<>();

        // 将所有节点放入map中，以id作为key
        for (T node : nodes) {
            nodeMap.put(node.getId(), node);
        }

        // 遍历所有节点，构建父子关系
        for (T node : nodes) {
            String parentId = node.getParentId();
            if (parentId.equals(rootParentId)) {
                // 如果当前节点的parentId等于rootParentId，则为根节点
                roots.add(node);
            } else {
                // 否则，将当前节点添加到其父节点的children列表中
                T parent = nodeMap.get(parentId);
                if (parent != null) {
                    List<T> children = parent.getChildren();
                    if (children == null) {
                        children = new ArrayList<>();
                        parent.setChildren(children);
                    }
                    children.add(node);
                }
            }
        }

        return roots;
    }
}