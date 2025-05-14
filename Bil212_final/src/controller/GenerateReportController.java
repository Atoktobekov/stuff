package controller;

import dto.PlaneDTO;
import service.IPrepareReportService;

public class GenerateReportController {
    private final IPrepareReportService reportService;

    public GenerateReportController(IPrepareReportService reportService) {
        this.reportService = reportService;
    }

    public void generateReport(PlaneDTO planeDTO, String groupBy) {
        reportService.prepareReport(planeDTO, groupBy);
    }
}

