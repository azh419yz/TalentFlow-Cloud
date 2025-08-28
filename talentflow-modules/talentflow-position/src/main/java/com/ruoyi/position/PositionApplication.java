package com.ruoyi.position;

import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 岗位管理模块
 *
 * @author ruoyi
 */
@EnableCustomConfig
@EnableRyFeignClients
@SpringBootApplication
public class PositionApplication {
    public static void main(String[] args) {
        SpringApplication.run(PositionApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  岗位管理模块启动成功   ლ(´ڡ`ლ)ﾞ  ");
    }
}