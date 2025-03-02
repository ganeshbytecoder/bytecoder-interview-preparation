package com.bytecoder.OtherKnowledge.JavaRMI.ClientService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ReportService extends Remote {
    Report getReport(String reportId) throws RemoteException;
    List<Report> getAllReports() throws RemoteException;
}
