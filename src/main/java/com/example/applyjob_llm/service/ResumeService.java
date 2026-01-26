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

                if (text.equalsIgnoreCase("Professional Summary:")) {
                    summaryFound = true;

                    // Remove old points until the next header (paragraph ending with ':') or end of doc
                    int removeIndex = i + 1;
                    while (removeIndex < document.getParagraphs().size()) {
                        XWPFParagraph nextPara = document.getParagraphs().get(removeIndex);
                        if (nextPara.getText().trim().endsWith(":")) {
                            break; // next section header reached
                        }
                        document.removeBodyElement(document.getPosOfParagraph(nextPara));
                        // do not increment removeIndex because list shrinks
                    }

                    // Add new bullet points below the summary
                    int summaryIndex = document.getPosOfParagraph(paragraphs.get(i));

                    for (int j = 0; j < points.size(); j++) {
                        XWPFParagraph newPara = document.createParagraph();

                        // Line spacing = 1.0 (single spacing)
                        newPara.setSpacingBetween(0);

                        XWPFRun run = newPara.createRun();
                        run.setText("• " + points.get(j));

                        // Font style
                        run.setFontFamily("Calibri");
                        run.setFontSize(12);

                        // Insert at correct position
                        document.setParagraph(newPara, summaryIndex + 1 + j);
                    }

                    break; // summary processed, exit loop
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