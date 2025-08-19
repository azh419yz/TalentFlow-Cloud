package com.ruoyi.file;

import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import org.dromara.x.file.storage.spring.EnableFileStorage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 文件服务
 *
 * @author kenmi
 */
@EnableFileStorage
@EnableRyFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RuoYiFileApplication {
    public static void main(String[] args) {
        SpringApplication.run(RuoYiFileApplication.class, args);
        System.out.println("""
                (♥◠‿◠)ﾉﾞ  文件服务模块启动成功   ლ(´ڡ`ლ)ﾞ \s
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
