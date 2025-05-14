package repository;

import dto.PlaneDTO;

public interface IPlaneRepository {
    PlaneDTO readPlaneInfo(String filename);
}
