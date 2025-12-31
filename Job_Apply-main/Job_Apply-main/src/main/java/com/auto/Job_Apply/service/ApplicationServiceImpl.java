package com.auto.Job_Apply.service;

import com.auto.Job_Apply.automation.flow.FixedJobApplyFlow;
import com.auto.Job_Apply.automation.flow.JobApplyFlow;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final JobApplyFlow jobApplyFlow;
    private final FixedJobApplyFlow fixedJobApplyFlow;

    public ApplicationServiceImpl(JobApplyFlow jobApplyFlow, FixedJobApplyFlow fixedJobApplyFlow) {
        this.jobApplyFlow = jobApplyFlow;
        this.fixedJobApplyFlow = fixedJobApplyFlow;
    }

    @Override
    public void runAutomation() {
        jobApplyFlow.start();
    }

    @Override
    public void runFixedJobFlow() {
        fixedJobApplyFlow.runDiceFlow();
    }
}