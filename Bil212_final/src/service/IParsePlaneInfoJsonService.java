package service;

import dto.PlaneDTO;

public interface IParsePlaneInfoJsonService {
    PlaneDTO parsePlaneInfo(String filename);
}
