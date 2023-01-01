package sep2022.kadai7th.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AirportEntity {

    private final String airportCode;
    private String airportName;
    private String country;

}
