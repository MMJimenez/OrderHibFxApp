package com.example.orderhibfx;

import com.example.orderhibfx.utils.JDBCUtils;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

public class InformationReport {

    public static void showReportMenu() throws JRException, ClassNotFoundException, SQLException {

        HashMap hm = new HashMap();

        JasperReport report = JasperCompileManager.compileReport("src/main/resources/reports/menu_products.jrxml");

        JasperPrint print = JasperFillManager.fillReport(
                report,
                hm,
                JDBCUtils.getConnection()
        );

        JasperViewer view = new JasperViewer(print, false);
        view.getDefaultCloseOperation();
        view.setVisible(true);

        System.out.print("Done!");
    }

    public static void showReportRequestToday() throws JRException, ClassNotFoundException, SQLException {

        HashMap hm = new HashMap();

        JasperReport report = JasperCompileManager.compileReport("src/main/resources/reports/request_today_report.jrxml");

        JasperPrint print = JasperFillManager.fillReport(
                report,
                hm,
                JDBCUtils.getConnection()
        );

        JasperViewer view = new JasperViewer(print, false);
        view.getDefaultCloseOperation();
        view.setVisible(true);

        System.out.print("Done!");
    }

    public static void showReportRequestBetweenTwoDates(LocalDate date1, LocalDate date2) throws JRException, ClassNotFoundException, SQLException {

        HashMap hm = new HashMap();

        //Pasar las fechas a java.sql.Date
        java.sql.Date date1sql = java.sql.Date.valueOf(date1);
        java.sql.Date date2sql = java.sql.Date.valueOf(date2);


        hm.put("ParameterInicio", date1sql);
        hm.put("ParameterFin", date2sql);

        JasperReport report = JasperCompileManager.compileReport("src/main/resources/reports/request_by_date.jrxml");

        JasperPrint print = JasperFillManager.fillReport(
                report,
                hm,
                JDBCUtils.getConnection()
        );

        JasperViewer view = new JasperViewer(print, false);
        view.getDefaultCloseOperation();
        view.setVisible(true);

        System.out.print("Done!");
    }

    public static void pdfReportProduct() throws JRException, ClassNotFoundException, SQLException {

        HashMap hm = new HashMap();


        JasperReport report = JasperCompileManager.compileReport("src/main/resources/reports/menu_products.jrxml");

        JasperPrint jasperPrint = JasperFillManager.fillReport(
                report,
                hm,
                JDBCUtils.getConnection()
        );

        JRPdfExporter exp = new JRPdfExporter();
        exp.setExporterInput(new SimpleExporterInput(jasperPrint));
        exp.setExporterOutput(new SimpleOutputStreamExporterOutput("Menu.pdf"));
        SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
        exp.setConfiguration(conf);
        exp.exportReport();
    }

    public static void pdfReportRequestToday() throws JRException, ClassNotFoundException, SQLException {

        HashMap hm = new HashMap();


        JasperReport report = JasperCompileManager.compileReport("src/main/resources/reports/request_today_report.jrxml");

        JasperPrint jasperPrint = JasperFillManager.fillReport(
                report,
                hm,
                JDBCUtils.getConnection()
        );

        JRPdfExporter exp = new JRPdfExporter();
        exp.setExporterInput(new SimpleExporterInput(jasperPrint));
        exp.setExporterOutput(new SimpleOutputStreamExporterOutput("ComandasDeHoy.pdf"));
        SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
        exp.setConfiguration(conf);
        exp.exportReport();
    }

    public static void pdfReportRequestbyDate(LocalDate date1, LocalDate date2) throws JRException, ClassNotFoundException, SQLException {

        HashMap hm = new HashMap();


        JasperReport report = JasperCompileManager.compileReport("src/main/resources/reports/request_by_date.jrxml");

        JasperPrint jasperPrint = JasperFillManager.fillReport(
                report,
                hm,
                JDBCUtils.getConnection()
        );

        JRPdfExporter exp = new JRPdfExporter();
        exp.setExporterInput(new SimpleExporterInput(jasperPrint));
        exp.setExporterOutput(new SimpleOutputStreamExporterOutput("ComandasPorFecha.pdf"));
        SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
        exp.setConfiguration(conf);
        exp.exportReport();
    }
}
