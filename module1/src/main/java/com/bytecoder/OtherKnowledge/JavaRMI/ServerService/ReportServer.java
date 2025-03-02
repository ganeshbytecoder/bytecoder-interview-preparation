package com.bytecoder.OtherKnowledge.JavaRMI.ServerService;

import com.bytecoder.OtherKnowledge.JavaRMI.common.ReportService;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ReportServer {
    private static final int PORT = 1099;
    private static final String SERVICE_NAME = "ReportService";

    public static void main(String[] args) {
        try {
            // Create and export the remote service object
            ReportService reportService = new ReportServiceImpl();
            
            // Create the RMI registry on port 1099
            Registry registry = LocateRegistry.createRegistry(PORT);
            
            // Bind the remote object to the registry
            registry.rebind(SERVICE_NAME, reportService);
            
            System.out.println("Report Server is running on port " + PORT);
            System.out.println("Service name: " + SERVICE_NAME);
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
