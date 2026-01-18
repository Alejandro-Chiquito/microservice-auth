package co.com.crediya.model.direction;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import co.com.crediya.model.postalcode.PostalCode;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Direction {
    private Long id;
    private String country;
    private String city;
    private String state;
    private String street;
    private String streetNumber;
    private String neighborhood;
    private String apartment;
    private String additionalInfo;
    private Integer postalCode;
    private Double latitude;
    private Double longitude;
}
