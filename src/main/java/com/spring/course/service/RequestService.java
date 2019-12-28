package com.spring.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.course.domain.Request;
import com.spring.course.domain.User;
import com.spring.course.enums.RequestState;
import com.spring.course.exception.NotFoundException;
import com.spring.course.model.PageModel;
import com.spring.course.model.PageRequestModel;
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
		
		return result.orElseThrow(() -> new NotFoundException("There are not request with id = " + id));
		
	}
	
	public List<Request>listAll(){
		
		List<Request> requests  = requestRepository.findAll();
		
		return requests;
	}
	
	public PageModel<Request> listAllOnLazyMode(PageRequestModel pr){
		
		Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());
		
		Page<Request> page = requestRepository.findAll(pageable);
		
		PageModel<Request> pm = new PageModel<>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
		
		return pm;
		
	}	
	
	public List<Request>listAllByOwnerId(Long ownerId){
		
		List<Request> request = requestRepository.findAllByOwnerId(ownerId);
		
		return request;
	}
	
	public PageModel<Request> listAllByOwnerIdOnLazyModel(Long ownerId, PageRequestModel pr){
		
		Pageable pegeable = PageRequest.of(pr.getPage(), pr.getSize());
		
		Page <Request> page = requestRepository.findAllByOwnerId(ownerId, pegeable);
		
		PageModel<Request> pm = new PageModel<>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
		
		return pm;
	}


}
