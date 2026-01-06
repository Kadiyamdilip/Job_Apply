package com.auto.Job_Apply.service;

import com.auto.Job_Apply.automation.flow.FixedJobApplyFlow;
import com.auto.Job_Apply.automation.flow.JobApplyFlow;
import com.auto.Job_Apply.automation.flow.LinkedInLoginFlow;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final JobApplyFlow jobApplyFlow;
    private final FixedJobApplyFlow fixedJobApplyFlow;
    private final LinkedInLoginFlow loginFlow;

    public ApplicationServiceImpl(JobApplyFlow jobApplyFlow, FixedJobApplyFlow fixedJobApplyFlow, LinkedInLoginFlow loginFlow) {
        this.jobApplyFlow = jobApplyFlow;
        this.fixedJobApplyFlow = fixedJobApplyFlow;
        this.loginFlow = loginFlow;
    }

    @Override
    public void runAutomation() {
        jobApplyFlow.start();
    }

    @Override
    public void runFixedJobFlow() {
        fixedJobApplyFlow.runDiceFlow();
    }
    @Override
    public void execute(String email, String password) {
        loginFlow.login(email, password);
        // later → job search & apply
    }
}