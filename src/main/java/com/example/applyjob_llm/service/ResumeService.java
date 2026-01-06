package com.example.applyjob_llm.service;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ResumeService {

    public String updateResume(String resumePath, String updatedResumePath, List<String> points) {
        try (FileInputStream fis = new FileInputStream(resumePath);
             XWPFDocument document = new XWPFDocument(fis)) {

            List<XWPFParagraph> paragraphs = document.getParagraphs();
            boolean summaryFound = false;

            for (int i = 0; i < paragraphs.size(); i++) {
                XWPFParagraph para = paragraphs.get(i);
                String text = para.getText().trim();

                if (text.equalsIgnoreCase("Professional Summary")) {
                    summaryFound = true;

                    // Remove old points (next 5 paragraphs)
                    for (int j = 0; j < 5; j++) {
                        if (i + 1 < paragraphs.size()) {
                            document.removeBodyElement(document.getPosOfParagraph(paragraphs.get(i + 1)));
                        }
                    }

                    // Add new points
                    for (String point : points) {
                        XWPFParagraph newPara = document.insertNewParagraph(paragraphs.get(i).getCTP().newCursor());
                        XWPFRun run = newPara.createRun();
                        run.setText("• " + point);
                    }
                    break;
                }
            }

            if (!summaryFound) {
                return "Professional Summary section not found!";
            } else {
                try (FileOutputStream fos = new FileOutputStream(updatedResumePath)) {
                    document.write(fos);
                    return "Resume updated successfully at: " + updatedResumePath;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "Error updating resume: " + e.getMessage();
        }
    }
}