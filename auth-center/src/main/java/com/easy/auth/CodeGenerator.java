package com.easy.auth;


import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.easy.common.core.base.BaseIdEntity;
import org.apache.ibatis.annotations.Mapper;

import java.nio.file.Paths;
import java.util.List;

/**
 * </p>
 *
 * @author Matt
 */
public class CodeGenerator {

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://192.168.5.111:3124/easy_sass?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false&allowPublicKeyRetrieval=true", "xc_dev", "E7F@oFw2Ks")
                .globalConfig(builder -> builder
                        .author("Matt")
                        .enableSpringdoc()
                        .disableOpenDir()
                        .dateType(DateType.TIME_PACK) // 设置时间类型策略
                        .outputDir(Paths.get(System.getProperty("user.dir")) + "/easy-auth-center/src/main/java")
                        .commentDate("yyyy-MM-dd")
                        .disableServiceInterface()
                )
                .packageConfig(builder -> builder
                        .parent("com.easy.auth")
                        .entity("bean.entity")
                        .mapper("dao")
                        .serviceImpl("service")
                        .controller("")
                        .xml("mapper")
                )
                .strategyConfig(builder -> builder
                        .addInclude(List.of("sass_login_logs"))
                        .addTablePrefix("sass_")
                        .entityBuilder().disableSerialVersionUID()
                        .superClass(BaseIdEntity.class)
                        .enableTableFieldAnnotation().enableLombok().enableFileOverride()
                        .mapperBuilder().disableMapperXml().mapperAnnotation(Mapper.class).enableFileOverride()
                        .serviceBuilder().disableService().formatServiceImplFileName("%sService").enableFileOverride()
                        .controllerBuilder().disable()
                )
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}