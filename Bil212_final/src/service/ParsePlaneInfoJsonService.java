package service;

import dto.PlaneDTO;
import repository.IPlaneRepository;

public class ParsePlaneInfoJsonService implements IParsePlaneInfoJsonService {
    private final IPlaneRepository planeRepository;

    public ParsePlaneInfoJsonService(IPlaneRepository planeRepository) {
        this.planeRepository = planeRepository;
    }

    public PlaneDTO parsePlaneInfo(String filename) {
        return planeRepository.readPlaneInfo(filename);
    }
}