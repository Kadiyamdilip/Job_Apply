package com.auto.Job_Apply.automation.flow;

import com.auto.Job_Apply.automation.strategy.JobApplyStrategy;
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