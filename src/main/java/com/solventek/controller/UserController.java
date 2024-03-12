package com.solventek.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solventek.entity.User;
import com.solventek.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/")
	public List<User> getUser(){
		return userRepository.findAll();
	}
	
	@PostMapping("/")
	public User addUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@PutMapping("/{id}")
	public User updateUser(@RequestBody User user,@PathVariable Integer id) {
		return userRepository.findById(id).map(
				
				newUser->{
					newUser.setUserName(user.getUserName());
					newUser.setPassWord(user.getPassWord());
					newUser.setRole(user.getRole());
					
					return newUser;
				}).orElseThrow(()-> new NoSuchElementException("user id not found with the id "+id));
				
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Integer id) {
		 userRepository.deleteById(id);
	}
	
	
}
