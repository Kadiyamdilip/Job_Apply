package com.auto.Job_Apply.controller;

import com.auto.Job_Apply.service.ApplicationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/automation")
public class ApplicationController {

    private final ApplicationService service;

    public ApplicationController(ApplicationService service) {
        this.service = service;
    }

    @PostMapping("/dice/apply")
    public String applyDiceJobs() {
        service.runAutomation();
        return "Dice job automation started";
    }

    @GetMapping("/")
    public String FixedJobFlow() {
        service.runFixedJobFlow();
        return "Fixed job automation started";
    }
}