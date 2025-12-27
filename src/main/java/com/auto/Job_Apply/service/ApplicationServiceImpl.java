package com.auto.Job_Apply.service;

import com.auto.Job_Apply.automation.flow.JobApplyFlow;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final JobApplyFlow jobApplyFlow;

    public ApplicationServiceImpl(JobApplyFlow jobApplyFlow) {
        this.jobApplyFlow = jobApplyFlow;
    }

    @Override
    public void runAutomation() {
        jobApplyFlow.start();
    }
}