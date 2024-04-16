package com.zy.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ServiceMainApplicationTest {

    @Test
    void contextLoads() {
    }

    // 表示单条规则的类
    public static class Rule {
        private String ruleContent;

        private String ruleOrder;
    }

    // 包含规则列表的类
    public static class RuleListContainer {
        private List<Rule> ruleList;
    }

    @Test
    void testApiAvailability() {
        String url = "http://120.26.59.7:23262/docxFile2rule";

        // 指定要上传的文件路径
        String filePath = "D:\\Documents\\WeChat Files\\wxid_sxbldpuy9ydo22\\FileStorage\\File\\2024-04/北京市网络预约出租汽车经营服务管理实施细则.docx";

        // 创建一个HttpRequest对象，并设置为POST方法
        HttpRequest request = HttpRequest.post(url);

        // 使用multipart方式添加文件。第一个参数为服务端接收的参数名，第二个参数是文件的路径
        request.form("file", FileUtil.file(filePath), "北京市网络预约出租汽车经营服务管理实施细则.docx");

        // 发送请求并接收响应
        HttpResponse response = request.execute();

        // 检查响应状态码是否为200
        assertEquals(200, response.getStatus(), "API不可用");

        // 使用HuTool的JSON工具解析响应内容
        String body = response.body();
//        System.out.println("Response Body: " + body);

        // 举例解析JSON响应
        if (JSONUtil.isJson(body)) {
//            RuleListContainer result = (RuleListContainer) JSONUtil.parse(body);
            Object result = JSONUtil.parse(body);

            // 将Object转换为Map，以便于访问其内部结构
            Map<String, Rule> resultMap = (Map<String, Rule>) result;

            // 从Map中获取ruleList
            List<Map<String, Rule>> ruleList = (List<Map<String, Rule>>) resultMap.get("ruleList");


            for (Map<String, Rule> rule : ruleList){
                System.out.println(rule.get("rule_content") + ": " + rule.get("rule_order"));
            }

            // 这里你可以进一步检查解析出的结果
            assertNotNull(result, "响应内容为空");
        } else {
            fail("响应内容不是有效的JSON");
        }
    }

}
