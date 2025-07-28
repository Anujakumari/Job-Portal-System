package com.portal.jobportalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.portal.jobportalproject.model.Job;
import com.portal.jobportalproject.model.User;
import com.portal.jobportalproject.service.*;

@Controller
@RequestMapping("/applicant")
public class ApplicantController {

    @Autowired 
    private JobService jobService;
    @Autowired 
    private ApplicationService appService;
    @Autowired 
    private UserService userService;
    
    @GetMapping("/browse-jobs")
    public String browseJobs(@RequestParam(required = false) String keyword, Model model) {
        model.addAttribute("jobs", jobService.searchJobs(keyword));
        model.addAttribute("keyword", keyword); // so we can retain search value in input
        return "applicant/browse-jobs";
    }


    @PostMapping("/apply/{jobId}")
    public String applyJob(@PathVariable Long jobId, Authentication auth, RedirectAttributes redirectAttributes) {
        User user = userService.findByUsername(auth.getName());
        Job job = jobService.getById(jobId);

        boolean applied = appService.apply(user, job);
        if (!applied) {
            redirectAttributes.addFlashAttribute("message", "You have already applied for this job.");
        } else {
            redirectAttributes.addFlashAttribute("message", "Application submitted successfully.");
        }

        return "redirect:/applicant/my-apps";
    }

    @GetMapping("/my-apps")
    public String myApplications(Authentication auth, Model model) {
        User user = userService.findByUsername(auth.getName());
        model.addAttribute("applications", appService.findByApplicant(user));
        return "applicant/my-apps";
    }
}
