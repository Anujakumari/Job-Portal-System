package com.portal.jobportalproject.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.portal.jobportalproject.model.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByTitleContaining(String keyword);
    
    List<Job> findByTitleContainingIgnoreCaseOrLocationContainingIgnoreCase(String title, String location);

}