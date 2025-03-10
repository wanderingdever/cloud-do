package com.easy.server.service;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easy.api.service.DictRemoteService;
import com.easy.api.vo.DictTypeVO;
import com.easy.common.core.dto.IdDTO;
import com.easy.common.core.exception.CustomizeException;
import com.easy.common.datasource.utils.PageUtils;
import com.easy.common.redis.constant.CacheConstants;
import com.easy.common.redis.utils.RedisUtils;
import com.easy.common.util.lang.BeanUtils;
import com.easy.common.util.lang.StringUtils;
import com.easy.server.bean.dto.dict.*;
import com.easy.server.bean.entity.DictData;
import com.easy.server.bean.entity.DictType;
import com.easy.server.dao.DictTypeMapper;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * ${desc}
 * </p>
 *
 * @author Matt
 */
@Service
@AllArgsConstructor
@DubboService(interfaceClass = DictRemoteService.class)
public class DictTypeService extends ServiceImpl<DictTypeMapper, DictType> implements DictRemoteService {

    private final DictDataService dictDataService;

    /**
     * 初始化系统启用字典到缓存
     */
    public void loadingDictCache() {
        DictSearchDTO dto = new DictSearchDTO();
        dto.setEnable(true);
        dto.setIsSystem(true);
        List<DictType> dictTypeList = getTypeAndDataList(dto);
        if (StringUtils.isNotEmpty(dictTypeList)) {
            // 设置缓存
            RedisUtils.setCacheObject(CacheConstants.SYSTEM_DICT, JSON.toJSONString(dictTypeList));
        }
    }

    /**
     * 根据字典类型获取字典
     *
     * @param dictType 字典类型
     * @return {@link DictTypeVO}
     */
    @Override
    public DictTypeVO getDictTypeByDictType(String dictType) {
        // 获取缓存
        List<DictType> dictDataCacheList = getCacheDictList();
        // 缓存没有获取到就查询数据库
        if (CollectionUtils.isEmpty(dictDataCacheList)) {
            DictSearchDTO search = new DictSearchDTO();
            search.setEnable(true);
            search.setIsSystem(true);
            search.setDictType(dictType);
            dictDataCacheList = getTypeAndDataList(search);
        }
        // 筛选
        Optional<DictType> first = dictDataCacheList.parallelStream().filter(dict -> dict.getDictType().equals(dictType)).findFirst();
        DictType dict = first.orElse(null);
        if (dict != null) {
            DictTypeVO dictVO = BeanUtils.copyProperties(dict, DictTypeVO.class);
            return dictVO;
        }
        return null;
    }

    /**
     * 分页查询字典类型
     *
     * @param dto 查询条件
     * @return {@link Page<DictType>}
     */
    public Page<DictType> pageDictType(DictSearchDTO dto) {
        return lambdaQuery()
                .like(StringUtils.isNotBlank(dto.getDictName()), DictType::getDictName, dto.getDictName())
                .eq(StringUtils.isNotBlank(dto.getDictType()), DictType::getDictType, dto.getDictType())
                .eq(StringUtils.isNotNull(dto.getIsSystem()), DictType::getIsSystem, dto.getIsSystem())
                .eq(StringUtils.isNotNull(dto.getEnable()), DictType::getEnable, dto.getEnable())
                .page(PageUtils.getPage(dto));
    }

    /**
     * 新增字典类型
     *
     * @param dto 入参
     */
    @Transactional(rollbackFor = Exception.class)
    public void addDictType(DictTypeAddDTO dto) {
        DictType dictType = BeanUtils.copyProperties(dto, DictType.class);
        this.save(dictType);
    }

    /**
     * 更新数据
     *
     * @param dto 字典类型
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateDictType(DictTypeEditDTO dto) {
        DictType dictType = BeanUtils.copyProperties(dto, DictType.class);
        this.updateById(dictType);
    }

    /**
     * 通过ID删除字典类型
     *
     * @param id id集合
     */
    @Transactional(rollbackFor = Exception.class)
    public void delDictType(IdDTO id) {
        // 获取type
        DictType dictType = this.getById(id.getId());
        // 查询出所有关联的data
        LambdaQueryWrapper<DictData> dictDataRmQueryWrapper = new LambdaQueryWrapper<>();
        dictDataRmQueryWrapper.eq(DictData::getId, dictType.getId());
        if (dictDataService.lambdaQuery().eq(DictData::getId, dictType.getId()).count() > 0) {
            throw new CustomizeException(String.format("%1$s已分配,不能删除", dictType.getDictName()));
        } else {
            // 删除字典类型
            this.removeById(id.getId());
            // 删除字典数据
            dictDataService.remove(dictDataRmQueryWrapper);
        }
    }

    /**
     * 获取字典集合-包含dict data数据
     *
     * @param search 查询条件
     * @return {@link List<DictType>}
     */
    public List<DictType> getTypeAndDataList(DictSearchDTO search) {
        // 查询出字典类型
        List<DictType> dictTypeList = lambdaQuery()
                .like(StringUtils.isNotBlank(search.getDictName()), DictType::getDictName, search.getDictName())
                .eq(StringUtils.isNotBlank(search.getDictType()), DictType::getDictType, search.getDictType())
                .eq(StringUtils.isNotNull(search.getIsSystem()), DictType::getIsSystem, search.getIsSystem())
                .eq(StringUtils.isNotNull(search.getEnable()), DictType::getEnable, search.getEnable())
                .list();
        if (StringUtils.isEmpty(dictTypeList)) {
            return null;
        }
        // 查询出所有关联的data
        LambdaQueryWrapper<DictData> dictDataQueryWrapper = new LambdaQueryWrapper<>();
        dictDataQueryWrapper.in(DictData::getDictTypeId,
                dictTypeList.stream().map(DictType::getId).collect(Collectors.toList()));
        List<DictData> dictDataList = dictDataService.list(dictDataQueryWrapper);
        // 组装数据
        dictTypeList.forEach(type ->
                type.setDictDataList(dictDataList.stream().filter(data ->
                        data.getDictTypeId().equals(type.getId())).collect(Collectors.toList())));
        return dictTypeList;
    }

    /**
     * 获取缓存的字典数据
     *
     * @return {@link List<DictType>}
     */
    public List<DictType> getCacheDictList() {
        String cacheObject = RedisUtils.getCacheObject(CacheConstants.SYSTEM_DICT);
        if (StringUtils.isNotBlank(cacheObject)) {
            return JSON.parseArray(cacheObject, DictType.class);
        }
        return null;
    }

    /**
     * 更具字典类型ID获取字典类型和下属数据
     *
     * @param id 字典类型ID
     * @return {@link DictType}
     */
    public DictType getDictTypeInfoById(String id) {
        DictType dictType = this.baseMapper.selectById(id);
        if (dictType == null) {
            return null;
        }
        // 获取字典数据
        List<DictData> dataList = dictDataService.lambdaQuery().eq(DictData::getId, dictType.getId()).list();
        dictType.setDictDataList(dataList);
        return dictType;
    }

    /**
     * 分页查询字典数据
     *
     * @param search 查询参数
     * @return {@link DictData}
     */
    public Page<DictData> pageDictData(DictSearchDTO search) {
        return dictDataService.lambdaQuery()
                .eq(DictData::getDictTypeId, search.getDictTypeId())
                .like(StringUtils.isNotBlank(search.getDictLabel()), DictData::getDictLabel, search.getDictLabel())
                .eq(StringUtils.isNotNull(search.getEnable()), DictData::getEnable, search.getEnable())
                .page(PageUtils.getPage(search));
    }

    /**
     * 新增字典数据
     *
     * @param dto 字典数据
     */
    @Transactional(rollbackFor = Exception.class)
    public void addDictData(DictDataAddDTO dto) {
        DictData dictType = BeanUtils.copyProperties(dto, DictData.class);
        dictDataService.save(dictType);
    }

    /**
     * 编辑字典数据
     *
     * @param dto 字典数据
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateDictData(DictDataEditDTO dto) {
        DictData dictType = BeanUtils.copyProperties(dto, DictData.class);
        dictDataService.updateById(dictType);
    }

    /**
     * 删除字典数据
     *
     * @param ids 字典数据ID集合
     */
    @Transactional(rollbackFor = Exception.class)
    public void delDictData(List<String> ids) {
        dictDataService.removeBatchByIds(ids);
    }

}