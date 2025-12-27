package com.auto.Job_Apply.automation.flow;
import com.auto.Job_Apply.automation.definition.DiceStepDefinition;
import com.auto.Job_Apply.automation.engine.PlaywrightFactory;
import com.auto.Job_Apply.automation.step.AutomationStep;
import com.auto.Job_Apply.automation.step.StepExecutor;
import com.microsoft.playwright.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FixedJobApplyFlow {

    private final PlaywrightFactory factory;
    private final StepExecutor executor;

    public FixedJobApplyFlow(PlaywrightFactory factory, StepExecutor executor) {
        this.factory = factory;
        this.executor = executor;
    }

    public void runDiceFlow() {
        Page page = factory.createPage();

        List<AutomationStep> steps = DiceStepDefinition.steps();

        for (AutomationStep step : steps) {
            executor.execute(page, step);
        }

        page.context().close();
    }
}