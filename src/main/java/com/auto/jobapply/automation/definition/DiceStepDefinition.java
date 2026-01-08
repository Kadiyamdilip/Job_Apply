package com.auto.jobapply.automation.definition;

import com.auto.jobapply.automation.step.AutomationStep;
import com.auto.jobapply.automation.step.StepType;
import com.auto.jobapply.constants.ApplicationConstants;
import java.util.ArrayList;
import java.util.List;

public class DiceStepDefinition {

    public static List<AutomationStep> steps() {
        try {
            return List.of(
                    new AutomationStep(
                            StepType.NAVIGATE, null, ApplicationConstants.DICE_LOGIN_URL),
                    new AutomationStep(
                            StepType.FILL, "input[placeholder='Please enter your email']", ApplicationConstants.EMAIL),
                    new AutomationStep(
                            StepType.CLICK, "button[data-testid='sign-in-button']", null),
                    new AutomationStep(
                            StepType.FILL, "input[placeholder='Enter Password']", ApplicationConstants.PASSWORD),
                    new AutomationStep(
                            StepType.CLICK, "button[data-testid='submit-password']", null),
                    new AutomationStep(
                            StepType.WAIT, null, "7000"),
                    new AutomationStep(
                            StepType.FILL, "input[placeholder='Job title, skill, company, keyword']", ApplicationConstants.JOB_TITLE),
                    new AutomationStep(
                            StepType.CLICK, "button[data-testid='job-search-search-bar-search-button']", null),
                    new AutomationStep(
                            StepType.APPLY_EASY_JOBS, null, null)
            );
        } catch (Exception e) {
            System.err.println("Error initializing Dice steps: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}