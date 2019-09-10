package com.springcourse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.course.domain.Request;
import com.spring.course.domain.RequestStage;
import com.spring.course.enums.RequestState;

@Repository
public interface RequestSatgeRepository extends JpaRepository<RequestStage, Long>{

	public List<RequestStage> findAllByRequest(Long id);
	
	@Query("UPDATE Request SET state = ?2 WHERE id = ?1")
	public Request updateStatus(Long id, RequestState state);
	
}
