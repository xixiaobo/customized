package com.swj.customized;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
@Slf4j
public class CustomizedApplicationTests {

    @Test
    public void contextLoads() {
        String pwd="$2a$10$IPAaAH2c0FY086Vj7NopSOQdwdDsEndYwuCRBn9EgCmCrbKPBTgyG";
        if(new BCryptPasswordEncoder().matches("admin",pwd)){
            log.info("message:登录成功");
        }else {
            log.info("message:用户名或密码错误");
        }
    }

}
