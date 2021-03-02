package com.example.kbtg;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.kbtg.user.MyUser;
import com.example.kbtg.user.UserNotFoundException;
import com.example.kbtg.user.UserRepository;
import com.example.kbtg.user.UserResponse;
import com.example.kbtg.user.UserService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@Test
	public void found_user_by_id_1() {
		// Arrange
		MyUser somkiat = new MyUser();
		somkiat.setId(1);
		somkiat.setName("somkiat");
		somkiat.setAge(30);
		when(userRepository.findById(1)).thenReturn(Optional.of(somkiat));

		UserService userService = new UserService(userRepository);
		UserResponse response = userService.getInfo(1);
		// Assert
		assertEquals(1, response.getId());
		assertEquals("somkiat", response.getName());
		assertEquals(30, response.getAge());
	}

	@Test
	public void user_not_found_with_id_15_should_throw_exception() {
		// Arrange
		when(userRepository.findById(15)).thenThrow(new UserNotFoundException("User not found id = 15"));
		// Act
		Exception exception = assertThrows(UserNotFoundException.class, () -> {
			UserService userService = new UserService(userRepository);
			userService.getInfo(15);
		});
		// Assert
		assertEquals("User not found id = 15", exception.getMessage());
	}
}
