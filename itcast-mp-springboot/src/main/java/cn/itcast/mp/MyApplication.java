package cn.itcast.mp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.itcast.mp.mapper")//设置mapper接口的扫描包
public class MyApplication {

    public static void main(String[] args) {

        SpringApplication.run(MyApplication.class, args);
    }
}