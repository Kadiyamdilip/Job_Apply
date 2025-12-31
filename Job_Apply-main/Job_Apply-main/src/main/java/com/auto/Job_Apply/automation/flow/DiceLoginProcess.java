package com.auto.Job_Apply.automation.flow;

import com.auto.Job_Apply.automation.engine.PlaywrightFactory;
import com.auto.Job_Apply.constants.ApplicationConstants;
import com.microsoft.playwright.Page;
import org.springframework.stereotype.Component;

@Component
public class DiceLoginProcess {

    private final PlaywrightFactory factory;

    public DiceLoginProcess(PlaywrightFactory factory) {
        this.factory = factory;
    }

    public Page login() {
        Page page = factory.createPage();

        try {
            page.navigate(ApplicationConstants.DICE_LOGIN_URL);

            page.fill("input[placeholder='Please enter your email']", ApplicationConstants.EMAIL);

            page.click("button[data-testid='sign-in-button']");

            page.fill("input[placeholder='Enter Password']", ApplicationConstants.PASSWORD);

            page.click("button[data-testid='submit-password']");

            page.waitForTimeout(7000);

            System.out.println("✅ Dice login successful");
            return page;

        } catch (Exception e) {
            System.err.println("❌ Dice login failed: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}