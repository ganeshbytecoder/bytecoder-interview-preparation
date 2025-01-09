package com.bytecoder.Networking.JavaRMI.ClientService;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Report implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String reportId;
    private String title;
    private String content;
    private LocalDateTime generatedTime;

    public Report() {
        // Required for RMI serialization
    }

    public Report(String reportId, String title, String content) {
        this.reportId = reportId;
        this.title = title;
        this.content = content;
        this.generatedTime = LocalDateTime.now();
    }

    // Getters and setters
    public String getReportId() { return reportId; }
    public void setReportId(String reportId) { this.reportId = reportId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public LocalDateTime getGeneratedTime() { return generatedTime; }
    public void setGeneratedTime(LocalDateTime generatedTime) { this.generatedTime = generatedTime; }

    @Override
    public String toString() {
        return "Report{" +
                "reportId='" + reportId + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", generatedTime=" + generatedTime +
                '}';
    }
}
