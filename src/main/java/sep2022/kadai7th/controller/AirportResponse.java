package sep2022.kadai7th.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import sep2022.kadai7th.entity.AirportEntity;

@AllArgsConstructor
@Data
public class AirportResponse {

    private String message;
    private AirportEntity airportEntity;

}
