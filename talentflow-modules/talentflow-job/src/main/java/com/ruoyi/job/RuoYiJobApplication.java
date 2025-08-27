package com.ruoyi.job;

import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 定时任务
 *
 * @author kenmi
 */
@EnableCustomConfig
@EnableRyFeignClients
@SpringBootApplication
public class RuoYiJobApplication {
    public static void main(String[] args) {
        SpringApplication.run(RuoYiJobApplication.class, args);
        System.out.println("""
                (♥◠‿◠)ﾉﾞ  定时任务模块启动成功   ლ(´ڡ`ლ)ﾞ \s
                 .-------.       ____     __       \s
                 |  _ _   \\      \\   \\   /  /   \s
                 | ( ' )  |       \\  _. /  '      \s
                 |(_ o _) /        _( )_ .'        \s
                 | (_,_).' __  ___(_ o _)'         \s
                 |  |\\ \\  |  ||   |(_,_)'        \s
                 |  | \\ `'   /|   `-'  /          \s
                 |  |  \\    /  \\      /          \s
                 ''-'   `'-'    `-..-'             \s""");
    }
}
