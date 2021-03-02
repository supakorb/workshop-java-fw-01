package com.example.kbtg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.kbtg.user.MyUser;
import com.example.kbtg.user.UserRepository;

@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void found_get_user_by_id() {
		// Arrange
		MyUser supakorn = new MyUser();
		supakorn.setName("supakorn");
		supakorn.setAge(30);
		userRepository.save(supakorn);
		// Act
		Optional<MyUser> user = userRepository.findById(1);
		// Assert
		assertTrue(user.isPresent());
        assertEquals(new MyUser(1, "supakorn", 30), user.get());
	}
	
	@Test
    public void not_found_get_user_by_id() {
        // Act
        Optional<MyUser> user = userRepository.findById(1);
        // Assert
        assertFalse(user.isPresent());
    }
}
