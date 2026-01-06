package com.auto.Job_Apply.automation.flow;
import com.auto.Job_Apply.automation.engine.PlaywrightFactory;
import com.microsoft.playwright.Page;
import org.springframework.stereotype.Component;

@Component
public class LinkedInLoginFlow {

    private final PlaywrightFactory factory;

    public LinkedInLoginFlow(PlaywrightFactory factory) {
        this.factory = factory;
    }

    public void login(String email, String password) {
        Page page = factory.createPage();

        // 1. Open LinkedIn
        page.navigate("https://www.linkedin.com");

        // 2. Navigate to login page
        page.navigate("https://www.linkedin.com/login");

        // 3. Fill credentials
        page.waitForSelector("input#username");
        page.fill("input#username", email);
        page.fill("input#password", password);

        // 4. Submit login
        page.click("button[type='submit']");

        // 5. Handle security checkpoint (manual)
        waitForSecurityCheckpoint(page);

        // 6. Verify login
        verifyLogin(page);
    }

    private void waitForSecurityCheckpoint(Page page) {
        try {
            page.waitForURL(
                    url -> url.contains("checkpoint"),
                    new Page.WaitForURLOptions().setTimeout(30_000)
            );
            System.out.println("Security checkpoint detected. Complete manually.");

            page.waitForURL(
                    url -> url.contains("/feed"),
                    new Page.WaitForURLOptions().setTimeout(300_000)
            );
        } catch (Exception ignored) {
        }
    }

    private void verifyLogin(Page page) {
        page.navigate("https://www.linkedin.com/feed");
        page.waitForSelector("div.share-box-feed-entry__trigger");
        System.out.println("LinkedIn login successful");
    }
}