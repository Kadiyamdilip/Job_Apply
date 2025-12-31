package com.auto.Job_Apply.automation.engine;
import com.microsoft.playwright.*;
import org.springframework.stereotype.Component;

@Component
public class PlaywrightFactory {

    private final Browser browser;

    public PlaywrightFactory(Browser browser) {
        this.browser = browser;
    }

    public BrowserContext createContext() {
        return browser.newContext(
                new Browser.NewContextOptions()
                        .setViewportSize(null)
        );
    }

    public Page createPage() {
        return createContext().newPage();
    }
}