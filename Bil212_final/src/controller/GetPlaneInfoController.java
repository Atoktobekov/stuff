package controller;

import dto.PlaneDTO;
import service.IParsePlaneInfoJsonService;

public class GetPlaneInfoController {
    private final IParsePlaneInfoJsonService parseService;

    public GetPlaneInfoController(IParsePlaneInfoJsonService parseService) {
        this.parseService = parseService;
    }

    public PlaneDTO getPlaneInfoFromFile(String filename) {
        return parseService.parsePlaneInfo(filename);
    }
}

