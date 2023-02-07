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
import java.util.HashMap;

public class InformationReport {

    public static void showReportMenu() throws JRException, ClassNotFoundException, SQLException {

        HashMap hm = new HashMap();

        //hm.put("tipo", tipo);

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

        //hm.put("tipo", tipo);

        JasperReport report = JasperCompileManager.compileReport("src/main/resources/reports/product-tables.jrxml");

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

    public static void showReportRequestBetweenTwoDates(String tipo) throws JRException, ClassNotFoundException, SQLException {

        HashMap hm = new HashMap();

        //hm.put("tipo", tipo);

        JasperReport report = JasperCompileManager.compileReport("src/main/resources/jasper_reports/product-tables.jrxml");

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

    public static void pdfReport(String tipo) throws JRException, ClassNotFoundException, SQLException {

        HashMap hm = new HashMap();

        hm.put("tipo", tipo);

        String report = "Pokemon.jasper";

        JasperPrint jasperPrint = JasperFillManager.fillReport(
                report,
                hm,
                JDBCUtils.getConnection()
        );

        JRPdfExporter exp = new JRPdfExporter();
        exp.setExporterInput(new SimpleExporterInput(jasperPrint));
        exp.setExporterOutput(new SimpleOutputStreamExporterOutput("pokeball.pdf"));
        SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
        exp.setConfiguration(conf);
        exp.exportReport();

        System.out.print("Done!");
    }
}
