package service;

import dto.PassengerDTO;
import dto.PlaneDTO;
import repository.IPassengerReportSaver;
import dto.SectionDTO;
import repository.PassengerRepository;

import java.util.*;
import java.util.stream.Collectors;

public class PrepareReportService implements IPrepareReportService {
    private final IPassengerReportSaver passengerRepository;

    public PrepareReportService(IPassengerReportSaver passengerRepository) {
        this.passengerRepository = passengerRepository;
    }
    public void prepareReport(PlaneDTO planeInfo, String groupBy) {
        List<PassengerDTO> allPassengers = planeInfo.getSections().stream()
                .flatMap(section -> section.getPassengers().stream())
                .collect(Collectors.toList());

        Map<String, List<PassengerDTO>> grouped;

        if (groupBy.equalsIgnoreCase("nationality")) {
            grouped = allPassengers.stream().collect(Collectors.groupingBy(PassengerDTO::getNationality));
        } else {
            grouped = allPassengers.stream().collect(Collectors.groupingBy(PassengerDTO::getGender));
        }

        StringBuilder report = new StringBuilder();

        for (Map.Entry<String, List<PassengerDTO>> entry : grouped.entrySet()) {
            String key = entry.getKey();
            List<PassengerDTO> passengers = entry.getValue();

            report.append(key).append("\n");
            for (PassengerDTO p : passengers) {
                if (groupBy.equalsIgnoreCase("nationality")) {
                    report.append("● ").append(p.getName())
                            .append(" / ").append(p.getGender())
                            .append(" / ").append(p.getAge()).append(" жашта\n");
                } else {
                    report.append("● ").append(p.getName())
                            .append(" / ").append(p.getNationality())
                            .append(" / ").append(p.getAge()).append(" жашта\n");
                }
            }

            double avgAge = passengers.stream().mapToInt(PassengerDTO::getAge).average().orElse(0);
            report.append("\nорточо жашы ").append(String.format("%.2f", avgAge)).append("\n");
            report.append("______________________________________\n");
        }

        passengerRepository.saveReport(report.toString());
    }
}
