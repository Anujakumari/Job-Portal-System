package com.portal.jobportalproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.jobportalproject.model.*;
import com.portal.jobportalproject.repo.*;

@Service
public class ApplicationService {
	@Autowired
    private ApplicationRepository appRepo;

    public boolean hasAlreadyApplied(User user, Job job) {
        return appRepo.findByApplicantAndJob(user, job).isPresent();
    }

    public boolean apply(User user, Job job) {
        if (hasAlreadyApplied(user, job)) {
            return false; // already applied
        }

        Application app = new Application();
        app.setApplicant(user);
        app.setJob(job);
        app.setStatus("Applied");
        appRepo.save(app);
        return true;
    }

    public List<Application> findByApplicant(User user) {
        return appRepo.findByApplicant(user);
    }
    
    public List<Application> findByJob(Job job) {
        return appRepo.findByJob(job);
    }
    
    public Application getById(Long id) {
        return appRepo.findById(id).orElse(null);
    }

    public void save(Application app) {
        appRepo.save(app);
    }


}
