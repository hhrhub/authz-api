package com.hhrhub.authz;

import org.junit.jupiter.api.Test;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthzAppTests {
	@Autowired
	private RedissonClient redissonClient;

	@Test
	void test() {
		System.out.println("----start");
		System.out.println(redissonClient);
		RBucket<String> bucket = redissonClient.getBucket("123");
		System.out.println(bucket.isExists());
		if (bucket.isExists()) {
			System.out.println("existing");
		} else {
			System.out.println("not found");
		}
		System.out.println("....end ");
	}


}
