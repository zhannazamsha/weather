package weather.domain;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Weather {

    private String main;
    private String temp;
    private String pressure;
    private String humidity;
    private String wind;


}
