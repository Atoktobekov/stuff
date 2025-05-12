package dto;

import java.util.List;

public class SectionDTO {
    private String sectionName;
    private int maxPassengersNumber;
    private List<PassengerDTO> passengers;

    public SectionDTO() {}

    public SectionDTO(String sectionName, int maxPassengersNumber, List<PassengerDTO> passengers) {
        this.sectionName = sectionName;
        this.maxPassengersNumber = maxPassengersNumber;
        this.passengers = passengers;
    }

    public String getSectionName() { return sectionName; }
    public int getMaxPassengersNumber() { return maxPassengersNumber; }
    public List<PassengerDTO> getPassengers() { return passengers; }

    public void setSectionName(String sectionName) { this.sectionName = sectionName; }
    public void setMaxPassengersNumber(int maxPassengersNumber) { this.maxPassengersNumber = maxPassengersNumber; }
    public void setPassengers(List<PassengerDTO> passengers) { this.passengers = passengers; }

    @Override
    public String toString() {
        return sectionName + " (" + passengers.size() + "/" + maxPassengersNumber + ")";
    }
}
