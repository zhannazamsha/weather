package weather.domain;

import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Data
public class Location implements Serializable {

    private String city;
    private String country;
    private String countryCode;
    private Float lat;
    private Float lon;
    private String timezone;
    private String zip;

}
