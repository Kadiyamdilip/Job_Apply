package com.auto.jobapply.config;

import com.microsoft.playwright.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlaywrightConfig {

    @Bean
    public Playwright playwright() {
        return Playwright.create();
    }

    @Bean
    public Browser browser(Playwright playwright) {
        // Set headless(false) so you can see the browser during automation
        return playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @Bean
    public BrowserContext browserContext(Browser browser) {
        return browser.newContext();
    }

    @Bean
    public Page page(BrowserContext context) {
        return context.newPage();
    }
}