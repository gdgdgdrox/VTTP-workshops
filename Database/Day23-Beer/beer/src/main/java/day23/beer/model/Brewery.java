package day23.beer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Brewery {
    private String name;
    private String address1;
    private String city;
    private String country;
    private String website;
}
