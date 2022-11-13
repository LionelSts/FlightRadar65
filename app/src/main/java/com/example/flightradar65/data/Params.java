package com.example.flightradar65.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "hex",
        "reg_number",
        "airline_icao",
        "flag",
        "flight_icao",
        "flight_number",
        "dep_icao",
        "arr_icao",
        "lang"
})

public class Params {

    @JsonProperty("hex")
    private String hex;
    @JsonProperty("reg_number")
    private String reg_number;
    @JsonProperty("airline_icao")
    private String airline_icao;
    @JsonProperty("flag")
    private String flag;
    @JsonProperty("flight_icao")
    private String flight_icao;
    @JsonProperty("flight_number")
    private String flight_number;
    @JsonProperty("dep_icao")
    private String dep_icao;
    @JsonProperty("arr_icao")
    private String arr_icao;
    @JsonProperty("lang")
    private String lang;

    @JsonProperty("hex")
    public String getHex() {
        return hex;
    }

    @JsonProperty("hex")
    public void setHex(String hex) {
        this.hex = hex;
    }

    @JsonProperty("reg_number")
    public String getReg_number() {
        return reg_number;
    }

    @JsonProperty("reg_number")
    public void setReg_number(String reg_number) {
        this.reg_number = reg_number;
    }

    @JsonProperty("airline_icao")
    public String getAirline_icao() {
        return airline_icao;
    }

    @JsonProperty("airline_icao")
    public void setAirline_icao(String airline_icao) {
        this.airline_icao = airline_icao;
    }

    @JsonProperty("flag")
    public String getFlag() {
        return flag;
    }

    @JsonProperty("flag")
    public void setFlag(String flag) {
        this.flag = flag;
    }

    @JsonProperty("flight_icao")
    public String getFlight_icao() {
        return flight_icao;
    }

    @JsonProperty("flight_icao")
    public void setFlight_icao(String flight_icao) {
        this.flight_icao = flight_icao;
    }

    @JsonProperty("flight_number")
    public String getFlight_number() {
        return flight_number;
    }

    @JsonProperty("flight_number")
    public void setFlight_number(String flight_number) {
        this.flight_number = flight_number;
    }

    @JsonProperty("dep_icao")
    public String getDep_icao() {
        return dep_icao;
    }

    @JsonProperty("dep_icao")
    public void setDep_icao(String dep_icao) {
        this.dep_icao = dep_icao;
    }

    @JsonProperty("arr_icao")
    public String getArr_icao() {
        return arr_icao;
    }

    @JsonProperty("arr_icao")
    public void setArr_icao(String arr_icao) {
        this.arr_icao = arr_icao;
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
