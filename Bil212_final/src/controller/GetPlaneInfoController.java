package controller;

import dto.PlaneDTO;
import service.ParsePlaneInfoJsonService;

public class GetPlaneInfoController {
    private final ParsePlaneInfoJsonService parseService;

    public GetPlaneInfoController(ParsePlaneInfoJsonService parseService) {
        this.parseService = parseService;
    }

    public PlaneDTO getPlaneInfoFromFile(String filename) {
        return parseService.parsePlaneInfo(filename);
    }
}
