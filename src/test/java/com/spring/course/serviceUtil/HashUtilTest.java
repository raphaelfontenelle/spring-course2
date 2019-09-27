package com.spring.course.serviceUtil;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.course.service.util.HashUtil;

@RunWith(SpringRunner.class)
public class HashUtilTest {
	
	@Test
	public void getSecureHashTest() {
		
		String hash = HashUtil.getSecureHash("123");
		
		System.out.println(hash);
		
		assertThat(hash.length()).isEqualTo(64);		
	}

}
