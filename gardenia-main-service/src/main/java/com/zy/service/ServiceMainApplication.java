package com.zy.service;

import com.zy.common.annotation.MapperScanner;
import com.zy.common.utils.ConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author zhangyu
 */
@Slf4j
@SpringBootApplication
public class ServiceMainApplication {
    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application =  SpringApplication.run(ServiceMainApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = ConvertUtils.getString(env.getProperty("server.servlet.context-path"));
        log.info("\n----------------------------------------------------------\n\t" +
                "Application Gardenia-Boot is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:" + port + path + "/doc.html\n" +
                "External: \thttp://" + ip + ":" + port + path + "/doc.html\n" +
                "文档: \thttp://" + ip + ":" + port + path + "/doc.html\n" +
                "----------------------------------------------------------");
    }
}
