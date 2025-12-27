package com.auto.Job_Apply.automation.step;
import com.microsoft.playwright.Page;
import org.springframework.stereotype.Component;

@Component
public class StepExecutor {

    public void execute(Page page, AutomationStep step) {

        switch (step.getType()) {

            case NAVIGATE -> page.navigate(step.getValue());

            case CLICK -> page.click(step.getSelector());

            case FILL -> page.fill(step.getSelector(), step.getValue());

            case WAIT -> page.waitForTimeout(Long.parseLong(step.getValue()));

            case OPEN_NEW_TAB -> {
                Page newPage = page.context().newPage();
                newPage.navigate(step.getValue());
            }
            case APPLY_EASY_JOBS -> applyEasyJobs(page);
        }
    }
    private void applyEasyJobs(Page page) {

        // Filters
        page.click("text=All filters");
        page.click("label:has-text('Easy apply')");
        page.click("text=Apply filters");

        page.waitForSelector("a[data-testid='job-search-job-card-link']");

        var jobs = page.locator("a[data-testid='job-search-job-card-link']");
        int count = jobs.count();

        System.out.println("Found jobs: " + count);

        for (int i = 0; i < count; i++) {
            String jobUrl = jobs.nth(i).getAttribute("href");

            Page jobPage = page.context().newPage();
            jobPage.navigate(jobUrl);

            try {
                jobPage.click(
                        "button:has-text('Easy apply')",
                        new Page.ClickOptions().setTimeout(8000)
                );
                System.out.println("Applied: " + jobUrl);
            } catch (Exception e) {
                System.out.println("Easy apply not available: " + jobUrl);
            }

            jobPage.close();
        }
    }
}