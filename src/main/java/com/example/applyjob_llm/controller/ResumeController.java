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

    @GetMapping("/update")
    public String updateResume() {

        // ✅ Hardcoded resume paths (update if needed)
        String resumePath = "/Users/kadiyamdilipkumar/Documents/DILIP_KUMAR_K_Java_Developer.docx";
        String updatedResumePath = "/Users/kadiyamdilipkumar/Documents/updated_resume.docx";

        // ✅ Hardcoded points
        List<String> points = List.of(
                "Strong experience in Java and Spring Boot",
                "Hands-on with REST APIs and Microservices",
                "Experience with Maven and Git",
                "Understanding of CI/CD pipelines"
        );

        return resumeService.updateResume(
                resumePath,
                updatedResumePath,
                points
        );
    }

    @Data
    public static class UpdateRequest {
        private String resumePath;
        private String updatedResumePath;
        private List<String> points; // Points from LLM
    }
}
