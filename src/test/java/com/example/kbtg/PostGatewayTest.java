package com.example.kbtg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.kbtg.post.PostGateway;
import com.example.kbtg.post.PostResponse;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class PostGatewayTest {
	
	@Autowired
	private PostGateway postGateway;
	
	@Test
	public void success_with_id_1() {
		Optional<PostResponse> response = postGateway.getPostById(1);
		assertEquals(1, response.get().getId());
	}
	
	@Test
    public void should_return_empty_when_exception_is_occured() {
		Optional<PostResponse> response = postGateway.getPostById(101);
		assertFalse(response.isPresent());
    }
}
