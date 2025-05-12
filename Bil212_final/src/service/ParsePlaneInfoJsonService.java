package service;

import dto.PlaneDTO;
import repository.PlaneRepository;

public class ParsePlaneInfoJsonService {
    private final PlaneRepository planeRepository;

    public ParsePlaneInfoJsonService(PlaneRepository planeRepository) {
        this.planeRepository = planeRepository;
    }

    public PlaneDTO parsePlaneInfo(String filename) {
        return planeRepository.readPlaneInfo(filename);
    }
}
