package com.auto.Job_Apply.automation.definition;
import com.auto.Job_Apply.automation.step.AutomationStep;
import com.auto.Job_Apply.automation.step.StepType;
import com.auto.Job_Apply.constants.ApplicationConstants;

import java.util.List;

public class DiceStepDefinition {

    public static List<AutomationStep> steps() {
        return List.of(

                new AutomationStep(
                        StepType.NAVIGATE,
                        null,
                        ApplicationConstants.DICE_LOGIN_URL
                ),

                new AutomationStep(
                        StepType.FILL,
                        "input[placeholder='Please enter your email']",
                        ApplicationConstants.EMAIL
                ),

                new AutomationStep(
                        StepType.CLICK,
                        "button[data-testid='sign-in-button']",
                        null
                ),

                new AutomationStep(
                        StepType.FILL,
                        "input[placeholder='Enter Password']",
                        ApplicationConstants.PASSWORD
                ),

                new AutomationStep(
                        StepType.CLICK,
                        "button[data-testid='submit-password']",
                        null
                ),

                new AutomationStep(
                        StepType.WAIT,
                        null,
                        "7000"
                ),

                new AutomationStep(
                        StepType.FILL,
                        "input[placeholder='Job title, skill, company, keyword']",
                        ApplicationConstants.JOB_TITLE
                ),

                new AutomationStep(
                        StepType.CLICK,
                        "button[data-testid='job-search-search-bar-search-button']",
                        null
                ),
                new AutomationStep(
                        StepType.APPLY_EASY_JOBS,
                        null,
                        null
                )
        );


    }
}