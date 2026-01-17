package com.auto.Job_Apply.automation.step;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;

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

        for (int i = 0; i < 2; i++) {
            String jobUrl = jobs.nth(i).getAttribute("href");

            Page jobPage = page.context().newPage();
            jobPage.navigate(jobUrl);

            try {
                String jobTitle;
                if (jobPage.locator("[data-cy='jobTitle']").count() > 0) {
                    jobTitle = jobPage.textContent("[data-cy='jobTitle']").trim();
                } else {
                    jobTitle = jobPage.locator("h1").first().textContent().trim();
                }
                System.out.println("Job Title: " + jobTitle);
                Locator easyApply = jobPage.locator("a:has-text(\"Easy Apply\")");
                Locator applybutton = jobPage.locator("[data-testid='apply-button']");

                if (easyApply.count() > 0) {
                    easyApply.first().click();
                } else if(applybutton.count() > 0) {
                    applybutton.first().click();
                }
                jobPage.locator("span:has-text('Next')").click();
//                jobPage.locator("span.flex.items-center.justify-center.gap-2.align-middle").click();
//                jobPage.locator("span:has-text('Replace')").click();
//                jobPage.locator("input[type='file']").nth(0).setInputFiles(
//                                Paths.get("/Users/kadiyamdilipkumar/Downloads/DILIP KUMAR K_ Java Developer.docx")
//                        );
                System.out.println("Cover letter uploaded successfully!");
                Locator nextButton = jobPage.locator("button:has-text('Next')");

                if (nextButton.count()>0) {
                    nextButton.click();
                }
                jobPage.locator("button:has-text('Submit')").click();
                jobPage.waitForTimeout(10000);
                System.out.println("Applied: " + jobUrl);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Easy apply not available: " + jobUrl);
            }

            jobPage.close();
        }
    }
}