package com.auto.Job_Apply.automation.step;

public class AutomationStep {

    private final StepType type;
    private final String selector;
    private final String value;

    public AutomationStep(StepType type, String selector, String value) {
        this.type = type;
        this.selector = selector;
        this.value = value;
    }

    public StepType getType() {
        return type;
    }

    public String getSelector() {
        return selector;
    }

    public String getValue() {
        return value;
    }
}