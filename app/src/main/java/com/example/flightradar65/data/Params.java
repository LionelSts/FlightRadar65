package com.example.flightradar65.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "flight_icao",
        "lang"
})

public class Params {

    @JsonProperty("flight_icao")
    private String flightIcao;
    @JsonProperty("lang")
    private String lang;

    @JsonProperty("flight_icao")
    public String getFlightIcao() {
        return flightIcao;
    }

    @JsonProperty("flight_icao")
    public void setFlightIcao(String flightIcao) {
        this.flightIcao = flightIcao;
    }

    @JsonProperty("lang")
    public String getLang() {
        return lang;
    }

    @JsonProperty("lang")
    public void setLang(String lang) {
        this.lang = lang;
    }

}
