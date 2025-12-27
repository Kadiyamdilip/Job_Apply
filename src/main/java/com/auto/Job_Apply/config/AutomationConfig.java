package com.auto.Job_Apply.config;
import com.microsoft.playwright.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutomationConfig {

    @Bean
    public Playwright playwright() {
        return Playwright.create();
    }

    @Bean
    public Browser browser(Playwright playwright) {
        return playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setArgs(java.util.List.of("--start-maximized"))
        );
    }
}