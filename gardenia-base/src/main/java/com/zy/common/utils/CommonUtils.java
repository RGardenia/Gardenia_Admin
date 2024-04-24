package com.zy.common.utils;

import com.zy.common.constant.CommonConstant;
import com.zy.common.constant.DataBaseConstant;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class CommonUtils {

    //中文正则
    private static Pattern ZHONGWEN_PATTERN = Pattern.compile("[\u4e00-\u9fa5]");

    /**
     * 判断文件名是否带盘符，重新处理
     *
     * @param fileName
     * @return
     */
    public static String getFileName(String fileName) {
        //判断是否带有盘符信息
        // Check for Unix-style path
        int unixSep = fileName.lastIndexOf('/');
        // Check for Windows-style path
        int winSep = fileName.lastIndexOf('\\');
        // Cut off at latest possible point
        int pos = (winSep > unixSep ? winSep : unixSep);
        if (pos != -1) {
            // Any sort of path separator found...
            fileName = fileName.substring(pos + 1);
        }
        //替换上传文件名字的特殊字符
        fileName = fileName.replace("=", "").replace(",", "").replace("&", "")
                .replace("#", "").replace("“", "").replace("”", "");
        //替换上传文件名字中的空格
        fileName = fileName.replaceAll("\\s", "");
        return fileName;
    }

    // java 判断字符串里是否包含中文字符
    public static boolean ifContainChinese(String str) {
        if (str.getBytes().length == str.length()) {
            return false;
        } else {
            Matcher m = ZHONGWEN_PATTERN.matcher(str);
            if (m.find()) {
                return true;
            }
            return false;
        }
    }

    /**
     * 当前系统数据库类型
     */
    private static String DB_TYPE = "" ;

    /**
     * 全局获取平台数据库类型（作废了）
     *
     * @return
     */
    @Deprecated
    public static String getDatabaseType() {
        if (ConvertUtils.isNotEmpty(DB_TYPE)) {
            return DB_TYPE;
        }
        DataSource dataSource = SpringContextUtils.getApplicationContext().getBean(DataSource.class);
        try {
            return getDatabaseTypeByDataSource(dataSource);
        } catch (SQLException e) {
            //e.printStackTrace();
            log.warn(e.getMessage(), e);
            return "" ;
        }
    }

    /**
     * 获取数据库类型
     *
     * @param dataSource
     * @return
     * @throws SQLException
     */
    private static String getDatabaseTypeByDataSource(DataSource dataSource) throws SQLException {
        if ("".equals(DB_TYPE)) {
            Connection connection = dataSource.getConnection();
            try {
                DatabaseMetaData md = connection.getMetaData();
                String dbType = md.getDatabaseProductName().toLowerCase();
                if (dbType.indexOf("mysql") >= 0) {
                    DB_TYPE = DataBaseConstant.DB_TYPE_MYSQL;
                } else if (dbType.indexOf("oracle") >= 0 || dbType.indexOf("dm") >= 0) {
                    DB_TYPE = DataBaseConstant.DB_TYPE_ORACLE;
                } else if (dbType.indexOf("sqlserver") >= 0 || dbType.indexOf("sql server") >= 0) {
                    DB_TYPE = DataBaseConstant.DB_TYPE_SQLSERVER;
                } else if (dbType.indexOf("postgresql") >= 0) {
                    DB_TYPE = DataBaseConstant.DB_TYPE_POSTGRESQL;
                } else if (dbType.indexOf("mariadb") >= 0) {
                    DB_TYPE = DataBaseConstant.DB_TYPE_MARIADB;
                } else {
                    log.error("数据库类型:[" + dbType + "]不识别!");
                    //throw new JeecgBootException("数据库类型:["+dbType+"]不识别!");
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            } finally {
                connection.close();
            }
        }
        return DB_TYPE;

    }

    public static String encode(String str, String charset)
            throws UnsupportedEncodingException {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]+");
        Matcher m = p.matcher(str);
        StringBuffer b = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(b, URLEncoder.encode(m.group(0), charset));
        }
        m.appendTail(b);
        return b.toString();
    }


    public static String getLoginRole(String platform) {
        if (platform.equalsIgnoreCase(CommonConstant.PLATFORM_DRIVER))
            return CommonConstant.ROLE_DRIVER;
        if (platform.equalsIgnoreCase(CommonConstant.PLATFORM_OWNER_APP)
                || platform.equalsIgnoreCase(CommonConstant.PLATFORM_OWNER_PC))
            return CommonConstant.ROLE_CONSIGNOR;
        if (platform.equalsIgnoreCase(CommonConstant.PLATFORM_AGENT_APP))
            return CommonConstant.ROLE_AGENT;
        if (platform.equalsIgnoreCase(CommonConstant.PLATFORM_ADMIN))
            return CommonConstant.ROLE_ADMIN;
        return null;
    }

//    private static String NetImageToBase64(String netImagePath) {
//        String result = null;
//        final ByteArrayOutputStream data = new ByteArrayOutputStream();
//        try {
//            // 创建URL
//            URL url = new URL(netImagePath);
//            final byte[] by = new byte[1024];
//            // 创建链接
//            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            conn.setConnectTimeout(5000);
//
//            try {
//                InputStream is = conn.getInputStream();
//                // 将内容读取内存中
//                int len = -1;
//                while ((len = is.read(by)) != -1) {
//                    data.write(by, 0, len);
//                }
//                // 对字节数组Base64编码
//                BASE64Encoder encoder = new BASE64Encoder();
//                result = encoder.encode(data.toByteArray());
//                // 关闭流
//                is.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
}
