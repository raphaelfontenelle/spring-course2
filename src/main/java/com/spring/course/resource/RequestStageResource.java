package com.spring.course.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.course.domain.Request;
import com.spring.course.domain.RequestStage;
import com.spring.course.service.RequestService;
import com.spring.course.service.RequestStageService;

@RestController
@RequestMapping(value = "request-stages")
public class RequestStageResource {

	@Autowired private RequestService requestService;
	@Autowired private RequestStageService stageService;
	
	
	@PostMapping
	public ResponseEntity<RequestStage>save(@RequestBody RequestStage requestStage){
		RequestStage createdRequestStage = stageService.save(requestStage);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdRequestStage);
		}
	
	@GetMapping 
	public ResponseEntity<List<Request>> listAll(){
		
		List<Request> requests = requestService.listAll();
		return ResponseEntity.ok(requests);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RequestStage> getById(@PathVariable(name = "id") Long id){
		
		RequestStage stage = stageService.getById(id);
		return ResponseEntity.ok(stage);
	}
	
	//http://localhost:8080/requests/1/request-stages
	@GetMapping("/{id}/request-stages")
	public ResponseEntity<List<RequestStage>> listAllStagesById(@PathVariable(name = "id") Long id){
		
		List<RequestStage> stages = stageService.listAllByRequestId(id);
		
		return ResponseEntity.ok(stages);
		
	}
}
