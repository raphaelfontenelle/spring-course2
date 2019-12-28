package com.spring.course.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.course.domain.Request;
import com.spring.course.domain.User;
import com.spring.course.dto.UserLogindto;
import com.spring.course.model.PageModel;
import com.spring.course.model.PageRequestModel;
import com.spring.course.service.RequestService;
import com.spring.course.service.UserService;

@RestController
@RequestMapping(value = "users")
public class UserResource {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RequestService requestService;
	
	@PostMapping
	public  ResponseEntity<User> save(@RequestBody User user){
		
		User createdUser = userService.save(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable(name = "id")Long id, @RequestBody User user){
		
		user.setId(id);
		
		User updateUser = userService.update(user);
		
		return ResponseEntity.ok(updateUser);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User>getById(@PathVariable("id") Long id){
		
		User user = userService.getById(id);
		
		return ResponseEntity.ok(user);
		
	}
	
	@GetMapping
	public ResponseEntity<PageModel<User>> listAll(
			@RequestParam(value = "page") int page, 
			@RequestParam(value = "size") int size){
	
		PageRequestModel pr = new PageRequestModel(page, size);
		
		PageModel<User> pm = userService.listAllOnLazyMode(pr);
		
		return ResponseEntity.ok(pm);
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody UserLogindto user){
		
		User loggerUser = userService.login(user.getEmail(), user.getPassword());
		
		return ResponseEntity.ok(loggerUser);
	}
	
	@GetMapping("/{id}/requests")
	public ResponseEntity<PageModel<Request>>listAllRequestById(
			@PathVariable(name = "id")Long id,
			@RequestParam(name = "size") int size,
			@RequestParam(value = "page") int page){
		
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<Request> pm = requestService.listAllByOwnerIdOnLazyModel(id, pr);
		
		return ResponseEntity.ok(pm);
	}
	
}
