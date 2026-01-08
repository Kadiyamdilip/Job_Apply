package com.auto.jobapply.automation.flow;

import com.auto.jobapply.automation.strategy.JobApplyStrategy;
import org.springframework.stereotype.Component;

@Component
public class JobApplyFlow {

    private final JobApplyStrategy diceApplyStrategy;

    public JobApplyFlow(JobApplyStrategy diceApplyStrategy) {
        this.diceApplyStrategy = diceApplyStrategy;
    }

    public void start() {
        diceApplyStrategy.applyJobs();
    }
}