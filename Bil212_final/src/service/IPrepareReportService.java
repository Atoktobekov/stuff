package service;

import dto.PlaneDTO;

public interface IPrepareReportService {
    void prepareReport(PlaneDTO planeDTO, String groupBy);
}
