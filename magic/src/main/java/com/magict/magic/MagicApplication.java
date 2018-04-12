package com.magict.magic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class MagicApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(MagicApplication.class, args);



	}

	@Autowired
	RedisTemplate<String,String> redisTemplate;


	@Override
	public void run(String... strings) throws Exception {
		redisTemplate.opsForValue().set("a","aaaaa");
		String a = redisTemplate.opsForValue().get("a");
		System.err.println("====="+a);
	}
}
