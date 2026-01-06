package com.example.applyjob_llm.controller;

import com.example.applyjob_llm.service.ResumeService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @PostMapping("/update")
    public String updateResume(@RequestBody UpdateRequest request) {
        return resumeService.updateResume(
                request.getResumePath(),
                request.getUpdatedResumePath(),
                request.getPoints()
        );
    }

    @Data
    public static class UpdateRequest {
        private String resumePath;
        private String updatedResumePath;
        private List<String> points; // Points from LLM
    }
}
