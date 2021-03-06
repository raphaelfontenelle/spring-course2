package com.spring.course.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.course.domain.Request;
import com.spring.course.domain.RequestStage;
import com.spring.course.enums.RequestState;

@Repository
public interface RequestStageRepository extends JpaRepository<RequestStage, Long>{

	public List<RequestStage> findAllByRequestId(Long id);
	
	public Page<RequestStage> findAllByRequestId(Long id, Pageable pageable);
	
	@Query("UPDATE request SET state = :state WHERE id = :id")
	public Request updateStatus(Long id, RequestState state);
	
}
