package com.example.kbtg.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public UserResponse getInfo(int id) {
		Optional<MyUser> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException("User not found id = " + id);
		}
		return new UserResponse(id, user.get().getName(), user.get().getAge());
	}
}
