package com.bytecoder.OtherKnowledge.JavaRMI.ClientService;

import com.bytecoder.OtherKnowledge.JavaRMI.common.Report;
import com.bytecoder.OtherKnowledge.JavaRMI.common.ReportService;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class ReportClient {
    private static final String SERVER_HOST = "localhost"; // Change this to actual server host in production
    private static final int SERVER_PORT = 1099;
    private static final String SERVICE_NAME = "ReportService";

    public static void main(String[] args) {
        try {
            // Get the RMI registry from the server
            Registry registry = LocateRegistry.getRegistry(SERVER_HOST, SERVER_PORT);
            
            // Look up the remote object
            ReportService reportService = (ReportService) registry.lookup(SERVICE_NAME);
            
            // Call remote methods
            System.out.println("Client: Fetching specific report...");
            Report report = reportService.getReport("R001");
            System.out.println("Client: Received report: " + report);
            
            System.out.println("\nClient: Fetching all reports...");
            List<Report> allReports = reportService.getAllReports();
            allReports.forEach(r -> System.out.println("Client: Report: " + r));
            
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
