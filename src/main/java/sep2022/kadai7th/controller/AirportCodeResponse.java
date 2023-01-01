package sep2022.kadai7th.controller;

import lombok.Data;
import sep2022.kadai7th.entity.AirportEntity;

import java.util.List;

@Data
public class AirportCodeResponse {

    private String message;
    private List<String> airportCodes;

    public AirportCodeResponse(String message, List<AirportEntity> allAirports) {
        this.message = message;
        this.airportCodes = allAirports.stream()
                .map(airportEntity -> airportEntity.getAirportCode())
                .toList();
    }
}
