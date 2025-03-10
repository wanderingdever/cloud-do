package com.easy.common.util.tree;

import java.util.List;

/**
 * 树形构建类
 * </p>
 *
 * @author Matt
 */
public interface Parents<T extends Parents<T>> {
    String getId();

    String getParentId();

    List<T> getChildren();

    void setChildren(List<T> children);
}