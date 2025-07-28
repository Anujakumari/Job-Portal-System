package com.portal.jobportalproject.model;

import java.util.Optional;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor
public class Application {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User applicant;

    @ManyToOne
    private Job job;

    private String status; // Applied, Shortlisted, etc.

}
