package com.magict.magic;


import com.magict.magic.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.magict.magic.mapper")
//@MapperScan(basePackageClasses = BaseMapper.class)
public class MagicStareApplication{
	public static void main(String[] args) {
		SpringApplication.run(MagicStareApplication.class, args);


	}
}
