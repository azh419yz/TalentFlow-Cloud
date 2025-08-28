package com.ruoyi.talent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;

/**
 * 候选人管理模块
 *
 * @author ruoyi
 */
@EnableCustomConfig
@EnableRyFeignClients
@SpringBootApplication
public class TalentApplication {
    public static void main(String[] args) {
        SpringApplication.run(TalentApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  候选人管理模块启动成功   ლ(´ڡ`ლ)ﾞ  ");
    }
}