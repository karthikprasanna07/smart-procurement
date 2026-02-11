package com.procurement.smart_procurement.service.report;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * ReportService
 * --------------------------------------------------
 * Central service responsible for generating
 * PDF reports using JasperReports (.jrxml).
 *
 * Key responsibilities:
 * - Load JRXML templates from resources
 * - Compile JRXML into JasperReport
 * - Bind Java data to report fields
 * - Export final report as PDF
 *
 * This service is reusable for:
 * Vendor, Purchase Request, Purchase Order reports.
 */
@Service
public class ReportService {

    /**
     * Generates a PDF report from a given JRXML template.
     *
     * @param jrxmlPath Path to JRXML file (inside resources folder)
     *                  Example: "/reports/vendor_report.jrxml"
     *
     * @param data      List of DTO objects that act as
     *                  the data source for the report table
     *
     * @param params    Parameters passed to JRXML
     *                  (accessed using $P{} in JRXML)
     *
     * @return byte[]   Generated PDF file as byte array
     *
     * @throws JRException if report compilation or
     *                     generation fails
     */
    public byte[] generateReport(
            String jrxmlPath,
            List<?> data,
            Map<String, Object> params
    ) throws JRException {

        /*
         * STEP 1: Load JRXML file from classpath
         * -------------------------------------
         * getResourceAsStream() loads the file
         * from src/main/resources directory.
         */
        InputStream inputStream =
                getClass().getResourceAsStream(jrxmlPath);

        // Defensive check to avoid NullPointerException
        if (inputStream == null) {
            throw new JRException(
                    "JRXML file not found at path: " + jrxmlPath
            );
        }

        /*
         * STEP 2: Compile JRXML into JasperReport
         * ---------------------------------------
         * Compilation converts XML template
         * into a binary JasperReport object
         * that can be executed.
         */
        JasperReport jasperReport =
                JasperCompileManager.compileReport(inputStream);

        /*
         * STEP 3: Prepare data source
         * ----------------------------
         * JRBeanCollectionDataSource allows Jasper
         * to read Java object properties using getters.
         */
        JRBeanCollectionDataSource dataSource =
                new JRBeanCollectionDataSource(data);

        /*
         * STEP 4: Fill the report
         * ------------------------
         * This binds:
         * - Compiled report
         * - Parameters
         * - Data source
         * into a printable JasperPrint object.
         */
        JasperPrint jasperPrint =
                JasperFillManager.fillReport(
                        jasperReport,
                        params,
                        dataSource
                );

        /*
         * STEP 5: Export to PDF
         * ----------------------
         * JasperPrint is converted into
         * a PDF byte array.
         */
        return JasperExportManager
                .exportReportToPdf(jasperPrint);
    }
}
