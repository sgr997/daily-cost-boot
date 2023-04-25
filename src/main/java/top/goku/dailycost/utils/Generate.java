package top.goku.dailycost.utils;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;
import top.goku.dailycost.entity.BaseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author ZXP
 * @link <a href="https://github.com/sgr997">sgr997</a>
 * @date 2023/4/17 19:49
 */
public class Generate {

    public static void main(String[] args) {
        generate();
    }

    public static void generate() {
        List<String> tables = Arrays.asList("dc_type");
        FastAutoGenerator.create("jdbc:sqlite:/Users/zhangxiaopei/IdeaProjects/daily-cost/src/main/resources/db/dailycost.db",
                "", "")
                .globalConfig(builder -> {
                    builder.author("ZXP") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("/Users/zhangxiaopei/IdeaProjects/daily-cost/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("top.goku.dailycost") // 设置父包名
//                            .moduleName() // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "/Users/zhangxiaopei/IdeaProjects/daily-cost/src/main/resources/mappers")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables) // 设置需要生成的表名
                            .addTablePrefix("dc_"); // 设置过滤表前缀
                })
                .strategyConfig(builder -> {
                    builder.controllerBuilder()
                            .enableRestStyle();
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder()
                            .superClass(BaseEntity.class)
                            .idType(IdType.ASSIGN_ID)
//                            .enableRemoveIsPrefix() // 移除前缀
                            .enableChainModel()
                            .enableLombok()
                            .enableTableFieldAnnotation()
                            .addSuperEntityColumns("create_time", "update_time")
                            .addTableFills(new Column("create_time", FieldFill.INSERT))
                            .addTableFills(new Property("updateTime", FieldFill.INSERT_UPDATE))
                    ;
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}
