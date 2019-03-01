package com.swj.customized.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * Created by xxb on 2018/11/1.
 */
@Slf4j
@Component
public class StartLog implements ApplicationRunner {

    @Value("${server.port}")
    private String serverPort;
    @Value("${spring.application.name}")
    private String serverName;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        InetAddress addr = InetAddress.getLocalHost();
        String ip=addr.getHostAddress();//获得本机IP
        String str="\n-----------------------------------\n" +
                "\n" +
                "\n" +
                "\t"+serverName+"\t服务已启动\t\t\t\t\n" +
                "\t服务本机地址：127.0.0.1:"+serverPort+"\t\n" +
                "\t服务对外地址："+ip+":"+serverPort+"\t\n" +
                "\t\n" +
                "\t\n" +
                "-----------------------------------";
        log.info(str);
    }
}
