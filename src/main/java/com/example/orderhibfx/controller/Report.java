package com.example.orderhibfx.controller;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;

public class Report {
    private static JasperReport jasperReport;
    private static JasperViewer jasperViewer;
    private static JasperPrint jasperPrint;

    public static void createReport(Connection connection, Map<String, Object> map, InputStream by) {
        try {
            jasperReport = (JasperReport) JRLoader.loadObject(by);
            jasperPrint = JasperFillManager.fillReport(jasperReport, map, connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showReport() {
        jasperViewer = new JasperViewer(jasperPrint);
        jasperViewer.setVisible(true);
    }
}
