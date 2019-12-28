package com.spring.course.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.course.domain.Request;
import com.spring.course.enums.RequestState;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long>{

	public List<Request> findAllByOwnerId(Long id);
	
	public Page<Request> findAllByOwnerId(Long id, Pageable pageble);
	
	@Transactional(readOnly = false)
	@Modifying
	@Query("UPDATE request SET state = :state WHERE id = :id")
	public int updateStatus(Long id, RequestState state);
	
}
