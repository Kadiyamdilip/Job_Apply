package com.auto.jobapply.automation.flow;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import org.springframework.stereotype.Component;

@Component
public class DiceLoginProcess {

    private final Page page;

    // Locators
    private final Locator usernameField;
    private final Locator passwordField;
    private final Locator signInButton;
    private final Locator submitPasswordButton;

    public DiceLoginProcess(Page page) {
        this.page = page;
        // Based on your previous steps, using the specific data-testids
        this.usernameField = page.locator("input[placeholder='Please enter your email']");
        this.signInButton = page.locator("button[data-testid='sign-in-button']");
        this.passwordField = page.locator("input[placeholder='Enter Password']");
        this.submitPasswordButton = page.locator("button[data-testid='submit-password']");
    }

    public void navigateToLogin(String url) {
        try {
            page.navigate(url);
            System.out.println("Successfully navigated to: " + url);
        } catch (PlaywrightException e) {
            System.err.println("Failed to navigate to login page: " + e.getMessage());
            throw e; // Re-throw if you want the process to stop here
        }
    }

    public void login(String username, String password) {
        try {
            // Step 1: Fill Email
            usernameField.fill(username);
            signInButton.click();
            System.out.println("Email submitted for user: " + username);

            // Step 2: Fill Password
            passwordField.fill(password);
            submitPasswordButton.click();
            System.out.println("Password submitted.");

            // Optional: Wait for navigation to confirm success
            page.waitForURL("**/dashboard**");
            System.out.println("Login successful.");

        } catch (PlaywrightException e) {
            // This catches timeouts, element not found, or click failures
            System.err.println("Login failed due to automation error: " + e.getMessage());
            // You could take a screenshot here for debugging
            page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get("login-failure.png")));
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}