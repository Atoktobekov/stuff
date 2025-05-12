package dto;

import java.time.LocalDateTime;
import java.util.List;

public class PlaneDTO {
    private LocalDateTime arrivedDateTime;
    private String departureAirport;
    private String destinationAirport;
    private List<SectionDTO> sections;

    public PlaneDTO() {}

    public PlaneDTO(LocalDateTime arrivedDateTime, String departureAirport, String destinationAirport, List<SectionDTO> sections) {
        this.arrivedDateTime = arrivedDateTime;
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
        this.sections = sections;
    }

    public LocalDateTime getArrivedDateTime() { return arrivedDateTime; }
    public String getDepartureAirport() { return departureAirport; }
    public String getDestinationAirport() { return destinationAirport; }
    public List<SectionDTO> getSections() { return sections; }

    public void setArrivedDateTime(LocalDateTime arrivedDateTime) { this.arrivedDateTime = arrivedDateTime; }
    public void setDepartureAirport(String departureAirport) { this.departureAirport = departureAirport; }
    public void setDestinationAirport(String destinationAirport) { this.destinationAirport = destinationAirport; }
    public void setSections(List<SectionDTO> sections) { this.sections = sections; }

    @Override
    public String toString() {
        return departureAirport + " → " + destinationAirport + " @ " + arrivedDateTime;
    }
}
