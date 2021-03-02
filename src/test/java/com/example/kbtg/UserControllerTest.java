package com.example.kbtg;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com.example.kbtg.user.ErrorResponse;
import com.example.kbtg.user.MyUser;
import com.example.kbtg.user.UserRepository;
import com.example.kbtg.user.UserResponse;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class UserControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private UserRepository userRepository;
	
	@AfterEach
	public void clearUser() {
		userRepository.deleteAll();
	}
	
	@Test
	public void success_get_user_id_1() {
		// Arrange
		MyUser supakorn = new MyUser();
		supakorn.setId(1);
		supakorn.setName("supakorn");
		supakorn.setAge(30);
		userRepository.save(supakorn);
		// Act
		UserResponse response = restTemplate.getForObject("/user/1", UserResponse.class);
		// Assert
		assertEquals(1, response.getId());
		assertEquals("supakorn", response.getName());
		assertEquals(30, response.getAge());
		
		UserResponse expected = new UserResponse(1, "supakorn", 30);
		assertEquals(expected, response);
	}
	
	@Test
	public void fail_when_get_user_id_11() {
		ErrorResponse errorResponse = restTemplate.getForObject("/user/11", ErrorResponse.class);
		assertEquals(1234, errorResponse.getCode());
		assertEquals("User not found id = 11", errorResponse.getMessage());
	}
}
