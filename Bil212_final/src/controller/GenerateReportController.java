package controller;

import dto.PlaneDTO;
import service.PrepareReportService;

public class GenerateReportController {
    private final PrepareReportService reportService;

    public GenerateReportController(PrepareReportService reportService) {
        this.reportService = reportService;
    }

    public void generateReport(PlaneDTO planeDTO, String groupBy) {
        reportService.prepareReport(planeDTO, groupBy);
    }
}
