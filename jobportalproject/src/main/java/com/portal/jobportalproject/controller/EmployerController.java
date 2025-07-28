package com.portal.jobportalproject.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.portal.jobportalproject.model.Application;
import com.portal.jobportalproject.model.Job;
import com.portal.jobportalproject.model.User;
import com.portal.jobportalproject.service.*;

@Controller
@RequestMapping("/employer")
public class EmployerController {

    @Autowired private JobService jobService;
    @Autowired private UserService userService;
    @Autowired private ApplicationService appService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication auth) {
        User user = userService.findByUsername(auth.getName());
        model.addAttribute("jobs", jobService.getAllJobs().stream()
                .filter(j -> j.getPostedBy().getId().equals(user.getId()))
                .toList());
        return "employer/dashboard";
    }

    @GetMapping("/post-job")
    public String postJobForm(Model model) {
        model.addAttribute("job", new Job());
        return "employer/post-job";
    }

    @PostMapping("/post-job")
    public String postJob(@ModelAttribute Job job, Authentication auth) {
        User user = userService.findByUsername(auth.getName());
        job.setPostedBy(user);
        jobService.save(job);
        return "redirect:/employer/dashboard";
    }
    
    @GetMapping("/applications/{jobId}")
    public String viewApplications(@PathVariable Long jobId, Model model) {
        Job job = jobService.getById(jobId);
        List<Application> applications = appService.findByJob(job);
        model.addAttribute("job", job);
        model.addAttribute("applications", applications);
        return "employer/applications";
    }

    
    @PostMapping("/update-status/{appId}")
    public String updateStatus(@PathVariable Long appId, @RequestParam String status) {
        Application app = appService.getById(appId);
        app.setStatus(status);
        appService.save(app);
        return "redirect:/employer/applications/" + app.getJob().getId();
    }



}
