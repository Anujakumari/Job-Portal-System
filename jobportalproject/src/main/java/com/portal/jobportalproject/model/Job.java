package com.portal.jobportalproject.model;

import java.util.Optional;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor
public class Job {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String location;

    @ManyToOne
    private User postedBy;
}
