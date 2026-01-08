package com.auto.jobapply.automation.strategy;
import com.auto.jobapply.automation.engine.PlaywrightFactory;
import com.auto.jobapply.automation.flow.DiceLoginProcess;
import com.auto.jobapply.constants.ApplicationConstants;
import com.microsoft.playwright.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DiceApplyStrategy implements JobApplyStrategy {

    private final PlaywrightFactory factory;

    public DiceApplyStrategy(PlaywrightFactory factory) {
        this.factory = factory;
    }

    @Autowired
    private DiceLoginProcess loginProcess;

    @Override
    public void applyJobs() {
        Page page = null;
        if (page == null) {
            System.err.println("Skipping job apply due to login failure.");
            return;
        }

        page = factory.createPage();

        page.navigate(ApplicationConstants.DICE_LOGIN_URL);

        // Cookies (optional)
        try {
            page.locator("#cmpbntyestxt").click(new Locator.ClickOptions().setTimeout(2000));
        } catch (Exception ignored) {}

        // Email
        page.fill("input[placeholder='Please enter your email']", ApplicationConstants.EMAIL);
        page.click("button[data-testid='sign-in-button']");

        // Password
        page.fill("input[placeholder='Enter Password']", ApplicationConstants.PASSWORD);
        page.click("button[data-testid='submit-password']");

        page.waitForTimeout(7000);

        // Job search
        page.fill("input[placeholder='Job title, skill, company, keyword']",
                ApplicationConstants.JOB_TITLE);

        page.click("button[data-testid='job-search-search-bar-search-button']");

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
                jobPage.click("button:has-text('Easy apply')",
                        new Page.ClickOptions().setTimeout(8000));
                System.out.println("Applied: " + jobUrl);
            } catch (Exception e) {
                System.out.println("Easy apply not available: " + jobUrl);
            }

            jobPage.close();
        }

        page.context().close();
    }
}