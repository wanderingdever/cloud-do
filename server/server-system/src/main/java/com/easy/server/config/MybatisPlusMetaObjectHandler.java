package com.easy.server.config;import cn.dev33.satoken.stp.StpUtil;import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;import com.easy.common.util.lang.DateUtils;import org.apache.ibatis.reflection.MetaObject;import org.springframework.stereotype.Component;/** * mybatis plus字段自动填充拦截 * </p> * * @author Matt */@Componentpublic class MybatisPlusMetaObjectHandler implements MetaObjectHandler {    /**     * 创建     *     * @param metaObject 拦截     */    @Override    public void insertFill(MetaObject metaObject) {        setFieldValByName("createTime", DateUtils.nowDateTime(), metaObject);        String createBy = "0";        try {            createBy = StpUtil.getLoginId().toString();        } catch (Exception ignored) {        }        setFieldValByName("createBy", createBy, metaObject);    }    /**     * 最后一次更新     *     * @param metaObject 拦截     */    @Override    public void updateFill(MetaObject metaObject) {        String updateBy = "0";        try {            updateBy = StpUtil.getLoginId().toString();        } catch (Exception ignored) {        }        setFieldValByName("updateTime", DateUtils.nowDateTime(), metaObject);        setFieldValByName("updateBy", updateBy, metaObject);    }}