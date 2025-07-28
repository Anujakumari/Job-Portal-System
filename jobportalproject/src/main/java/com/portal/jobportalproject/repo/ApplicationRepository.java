package com.portal.jobportalproject.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.portal.jobportalproject.model.*;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
	List<Application> findByApplicant(User user);
    Optional<Application> findByApplicantAndJob(User applicant, Job job);
    List<Application> findByJob(Job job);

}
