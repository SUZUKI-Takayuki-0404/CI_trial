package sep2022.kadai7th.service;

import lombok.AllArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import sep2022.kadai7th.entity.AirportEntity;
import sep2022.kadai7th.exceptionhandler.AirportNotFoundException;
import sep2022.kadai7th.exceptionhandler.DuplicateAirportCodeException;
import sep2022.kadai7th.exceptionhandler.SameAsCurrentAirportException;
import sep2022.kadai7th.mapper.AirportMapper;

import java.util.List;

@AllArgsConstructor
@Service
public class AirportServiceImpl implements AirportService {

    private final AirportMapper airportMapper;

    @Override
    public AirportEntity getAirport(String airportCode) {
        return airportMapper.findById(airportCode).orElseThrow(() -> new AirportNotFoundException(airportCode + " Not Found"));
    }

    @Override
    public List<AirportEntity> getAllAirports() {
        return airportMapper.findAll();
    }

    @Override
    public AirportEntity createAirport(String airportCode, String airportName, String country) throws DuplicateAirportCodeException {
        try {
            airportMapper.create(airportCode, airportName, country);
        } catch (DuplicateKeyException e) {
            throw new DuplicateAirportCodeException("Airport code duplication must be avoided", e);
        }
        return new AirportEntity(airportCode, airportName, country);
    }

    @Override
    public AirportEntity updateAirport(String airportCode, String airportName, String country) {
        AirportEntity airportEntity = airportMapper.findById(airportCode).orElseThrow(() -> new AirportNotFoundException(airportCode + " Not Found"));
        if (airportEntity.getAirportName().equals(airportName) && airportEntity.getCountry().equals(country)) {
            throw new SameAsCurrentAirportException("No change of AirportName and Country");
        } else {
            airportMapper.update(airportCode, airportName, country);
            return new AirportEntity(airportCode, airportName, country);
        }
    }

    @Override
    public void deleteAirport(String airportCode) {
        if (airportMapper.findById(airportCode).isPresent()) {
            airportMapper.deleteById(airportCode);
        } else {
            throw new AirportNotFoundException(airportCode + " Not Found");
        }
    }

}
