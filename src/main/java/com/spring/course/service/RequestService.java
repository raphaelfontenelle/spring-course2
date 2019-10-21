package com.spring.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.course.domain.Request;
import com.spring.course.enums.RequestState;
import com.spring.course.repository.RequestRepository;

@Service
public class RequestService {

	@Autowired
	private RequestRepository requestRepository;
	
	public Request save(Request request) {
		
		request.setState(RequestState.OPEN);
		
		Request createdRequest = requestRepository.save(request);
		
		return createdRequest;
	}
	
	public Request update(Request request) {
		
		Request updateRequest = requestRepository.save(request);
		
		return updateRequest;
	}

	public Request getById(Long id) {
		
		Optional<Request> result = requestRepository.findById(id);
		
		return result.get();
		
	}
	
	public List<Request>listAll(){
		
		List<Request> requests  = requestRepository.findAll();
		
		return requests;
	}
	
	public List<Request>listAllByOwnerId(Long ownerId){
		
		List<Request> request = requestRepository.findAllByOwnerId(ownerId);
		
		return request;
	}
	
}
