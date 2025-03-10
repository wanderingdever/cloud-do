package com.easy.server.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easy.api.vo.ConfigVO;
import com.easy.server.bean.dto.config.ConfigAddDTO;
import com.easy.server.bean.dto.config.ConfigEditDTO;
import com.easy.server.bean.dto.config.ConfigSearchDTO;
import com.easy.server.bean.entity.Config;
import com.easy.server.service.ConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 参数配置
 * <p>
 * 2022/1/13 18:05
 *
 * @author Matt
 */
@RestController
@RequestMapping("/config")
@Tag(name = "参数配置")
public class ConfigController {

    private final ConfigService configService;

    public ConfigController(ConfigService configService) {
        this.configService = configService;
    }

    /**
     * 重载缓存
     */
    @PostMapping(value = "/overload")
    @Operation(summary = "重载参数缓存")
    public void overloadConfig() {
        configService.loadConfigCache();
    }

    /**
     * 获取参数配置
     *
     * @param dto key
     * @return ConfigCache
     */
    @PostMapping("/get")
    @Operation(summary = "获取参数配置")
    public ConfigVO getConfig(@RequestBody ConfigSearchDTO dto) {
        return configService.getConfig(dto.getConfigKey());
    }


    /**
     * 分页查询
     *
     * @param dto 查询入参
     * @return IPage<Config>
     */
    @PostMapping("/page")
    @Operation(summary = "分页查询")
    @SaCheckPermission(value = "system.config.page")
    public Page<Config> pageConfig(@RequestBody ConfigSearchDTO dto) {
        return configService.pageConfig(dto);
    }

    /**
     * 新增参数配置
     *
     * @param dto 入参
     */
    @PostMapping("/add")
    @Operation(summary = "新增参数配置")
    @SaCheckPermission(value = "system.config.add")
    public String addConfig(@Valid @RequestBody ConfigAddDTO dto) {
        configService.addConfig(dto);
        return "新增成功";
    }

    /**
     * 更新参数配置
     *
     * @param config 入参
     */
    @PostMapping("/update")
    @Operation(summary = "更新参数配置")
    @SaCheckPermission(value = "system.config.update")
    public String updateConfig(@Valid @RequestBody ConfigEditDTO config) {
        configService.updateConfig(config);
        return "更新成功";
    }

    /**
     * 删除参数配置
     *
     * @param ids 主键集合
     */
    @PostMapping("/del")
    @Operation(summary = "删除参数配置")
    @SaCheckPermission(value = "system.config.del")
    public String delConfig(@RequestBody List<String> ids) {
        configService.delConfig(ids);
        return "删除成功";
    }
}