package com.easy.api.service;

import com.easy.api.vo.DictTypeVO;

/**
 * 系统参数配置
 * </p>
 *
 * @author Matt
 */
public interface DictRemoteService {

    /**
     * 根据字典类型获取字典
     *
     * @param dictType 字典类型
     * @return {@link DictTypeVO}
     */
    DictTypeVO getDictTypeByDictType(String dictType);
}