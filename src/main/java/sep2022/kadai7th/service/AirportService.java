package sep2022.kadai7th.service;

import sep2022.kadai7th.entity.AirportEntity;
import sep2022.kadai7th.exceptionhandler.DuplicateAirportCodeException;

import java.util.List;

public interface AirportService {

    public AirportEntity getAirport(String airportCode);

    public List<AirportEntity> getAllAirports();

    public AirportEntity createAirport(String airportCode, String airportName, String country) throws DuplicateAirportCodeException;

    public AirportEntity updateAirport(String airportCode, String airportName, String country);

    public void deleteAirport(String airportCode);

}
