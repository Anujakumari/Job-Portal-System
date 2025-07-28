package com.portal.jobportalproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.jobportalproject.model.Job;
import com.portal.jobportalproject.repo.JobRepository;

@Service
public class JobService {
	@Autowired 
	private JobRepository jobRepository;

    public void save(Job job) {
        jobRepository.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    
    public List<Job> searchJobs(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return jobRepository.findAll();
        }
        return jobRepository.findByTitleContainingIgnoreCaseOrLocationContainingIgnoreCase(keyword, keyword);
    }


    public Job getById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

}
