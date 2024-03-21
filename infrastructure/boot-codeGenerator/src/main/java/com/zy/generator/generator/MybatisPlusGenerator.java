package com.zy.generator.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
/**
 * @author 张雨 - 栀
 * @version 1.0
 * @create 2022/3/1 17:05
 */
public class MybatisPlusGenerator {

    /**
     * 代码生成位置
     */
    public static final String PARENT_NAME = "com.miaoying.generator.modulardb";

    /**
     * modular 名字
     */
    public static final String MODULAR_NAME = "";

    /**
     * 基本路径
     */
    public static final String SRC_MAIN_JAVA = "src/main/java/";

    /**
     * 作者
     */
    public static final String AUTHOR = "CodeGenerator";

    /**
     * 是否是 rest 接口
     */
    private static final boolean REST_CONTROLLER_STYLE = true;

    public static final String JDBC_MYSQL_URL = "jdbc:mysql://localhost:3306/garbagesort?" +
            "serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8";

    public static final String JDBC_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

    public static final String JDBC_USERNAME = "root";

    public static final String JDBC_PASSWORD = "123456";

    public void test(String[] args) {
//        String moduleName = scanner("模块名");
//        String tableName = scanner("表名");
//        String tablePrefix = scanner("表前缀(无前缀输入#)").replaceAll("#", "");
//        autoGenerator(moduleName, tableName, tablePrefix);
    }
//
//    public static void autoGenerator(String moduleName, String tableName, String tablePrefix) {
//        new AutoGenerator()
//                .setGlobalConfig(getGlobalConfig())
//                .setDataSource(getDataSourceConfig())
//                .setPackageInfo(getPackageConfig(moduleName))
//                .setStrategy(getStrategyConfig(tableName, tablePrefix))
//                .setCfg(getInjectionConfig(moduleName))
//                .setTemplate(getTemplateConfig())
//                .execute();
//    }
//
//    private static String getDateTime() {
//        LocalDateTime localDate = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        return localDate.format(formatter);
//    }
//
//    private static InjectionConfig getInjectionConfig(final String moduleName) {
//        return new InjectionConfig() {
//            @Override
//            public void initMap() {
//                Map map = new HashMap();
//                map.put("dateTime", getDateTime());
//                setMap(map);
//                final String projectPath = System.getProperty("user.dir");
//                List<FileOutConfig> fileOutConfigList = new ArrayList<FileOutConfig>();
//                // 自定义配置会被优先输出
//                fileOutConfigList.add(new FileOutConfig("/templates/mapper.xml.vm") {
//                    @Override
//                    public String outputFile(TableInfo tableInfo) {
//                        // 自定义输出文件名，如果entity设置了前后缀，此次注意xml的名称也会跟着发生变化
//                        return projectPath + "/src/main/resources/mapper/" +
//                                moduleName + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//                    }
//                });
//                setFileOutConfigList(fileOutConfigList);
//            }
//        };
//    }
//
//
//    private static StrategyConfig getStrategyConfig(String tableName, String tablePrefix) {
//        return new StrategyConfig()
//                .setNaming(NamingStrategy.underline_to_camel)
//                .setColumnNaming(NamingStrategy.underline_to_camel)
//                .setInclude(tableName)
//                .setRestControllerStyle(REST_CONTROLLER_STYLE)
//                .setEntityBuilderModel(true)
//                .setControllerMappingHyphenStyle(true)
//                .entityTableFieldAnnotationEnable(true)
//                .setTablePrefix(tablePrefix + "_");
//    }
//
//    private static PackageConfig getPackageConfig(String moduleName) {
//        return new PackageConfig()
//                .setModuleName(moduleName)
//                .setParent(PARENT_NAME)
//                .setService("service")
//                .setServiceImpl("service.impl")
//                .setController("controller")
//                .setEntity("entity");
//    }
//
//    private static DataSourceConfig getDataSourceConfig() {
//        return new DataSourceConfig()
//                .setUrl(JDBC_MYSQL_URL)
//                .setDriverName(JDBC_DRIVER_NAME)
//                .setUsername(JDBC_USERNAME)
//                .setPassword(JDBC_PASSWORD);
//    }
//
//    private static GlobalConfig getGlobalConfig() {
//        String projectPath = System.getProperty("user.dir");
//        String filePath = projectPath + "/" + MODULAR_NAME + SRC_MAIN_JAVA;
//        if (PlatformUtil.isWindows()) {
//            filePath = filePath.replaceAll("/+|\\\\+", "\\\\");
//        } else {
//            filePath = filePath.replaceAll("/+|\\\\+", "/");
//        }
//        return new GlobalConfig()
//                .setOutputDir(filePath)
//                .setDateType(DateType.ONLY_DATE)
//                .setIdType(IdType.UUID)
//                .setAuthor(AUTHOR)
//                .setBaseColumnList(true)
//                .setSwagger2(true)
//                .setEnableCache(false)
//                .setBaseResultMap(true)
//                .setOpen(false);
//    }
//
//    private static TemplateConfig getTemplateConfig() {
//        return new TemplateConfig()
//                .setController("/templates-generator/controller.java.vm")
//                .setService("/templates-generator/service.java.vm")
//                .setServiceImpl("/templates-generator/serviceImpl.java.vm")
//                .setEntity("/templates-generator/entity.java.vm")
//                .setMapper("/templates-generator/mapper.java.vm")
//                .setXml("/templates-generator/mapper.xml.vm");
//    }
//
//    private static String scanner(String tip) {
//        Scanner scanner = new Scanner(System.in);
//        StringBuilder sb = new StringBuilder();
//        sb.append("please input " + tip + " : ");
//        System.out.println(sb.toString());
//        if (scanner.hasNext()) {
//            String ipt = scanner.next();
//            if (StringUtils.isNotEmpty(ipt)) {
//                return ipt;
//            }
//        }
//        throw new MybatisPlusException("please input the correct " + tip + ". ");
//    }

}
