package com.gschoudhary.domain;


import com.amazonaws.services.batch.model.JobStatus;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class JobSchedulerEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String jobName;
    private Long jobInstanceId;
    private LocalDateTime startDate;
    private LocalDateTime launchDate;
    private BatchJobType batchJobType;
    private JobStatus status;
}
