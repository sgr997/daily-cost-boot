package top.goku.dailycost;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("top.goku.dailycost.mapper")
@SpringBootApplication
public class DailyCostApplication {

    public static void main(String[] args) {
        SpringApplication.run(DailyCostApplication.class, args);
    }

}
