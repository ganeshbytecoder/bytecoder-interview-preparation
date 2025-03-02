package com.bytecoder.OtherKnowledge.JavaRMI.ServerService;

import com.bytecoder.OtherKnowledge.JavaRMI.common.Report;
import com.bytecoder.OtherKnowledge.JavaRMI.common.ReportService;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportServiceImpl extends UnicastRemoteObject implements ReportService {
    private static final long serialVersionUID = 1L;
    private Map<String, Report> reports = new HashMap<>();

    protected ReportServiceImpl() throws RemoteException {
        super();
        // Initialize some sample reports
        initializeSampleReports();
    }

    private void initializeSampleReports() {
        reports.put("R001", new Report("R001", "Sales Report", "Monthly sales data for January 2025"));
        reports.put("R002", new Report("R002", "Inventory Report", "Current inventory status as of January 2025"));
        reports.put("R003", new Report("R003", "Employee Report", "Employee performance metrics Q4 2024"));
    }

    @Override
    public Report getReport(String reportId) throws RemoteException {
        System.out.println("Server: Fetching report with ID: " + reportId);
        Report report = reports.get(reportId);
        if (report == null) {
            throw new RemoteException("Report not found with ID: " + reportId);
        }
        return report;
    }

    @Override
    public List<Report> getAllReports() throws RemoteException {
        System.out.println("Server: Fetching all reports");
        return new ArrayList<>(reports.values());
    }
}
