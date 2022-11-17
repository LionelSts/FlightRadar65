package com.example.flightradar65.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "hex",
        "reg_number",
        "flag",
        "lat",
        "lng",
        "alt",
        "dir",
        "speed",
        "v_speed",
        "squawk",
        "flight_number",
        "flight_icao",
        "flight_iata",
        "dep_icao",
        "dep_iata",
        "arr_icao",
        "arr_iata",
        "airline_icao",
        "airline_iata",
        "aircraft_icao",
        "updated",
        "status"
})

public class ApiResponse implements Serializable {

    @JsonProperty("hex")
    private String hex;
    @JsonProperty("reg_number")
    private String regNumber;
    @JsonProperty("flag")
    private String flag;
    @JsonProperty("lat")
    private float lat;
    @JsonProperty("lng")
    private float lng;
    @JsonProperty("alt")
    private int alt;
    @JsonProperty("dir")
    private int dir;
    @JsonProperty("speed")
    private int speed;
    @JsonProperty("v_speed")
    private float vSpeed;
    @JsonProperty("squawk")
    private String squawk;
    @JsonProperty("flight_number")
    private String flightNumber;
    @JsonProperty("flight_icao")
    private String flightIcao;
    @JsonProperty("flight_iata")
    private String flightIata;
    @JsonProperty("dep_icao")
    private String depIcao;
    @JsonProperty("dep_iata")
    private String depIata;
    @JsonProperty("arr_icao")
    private String arrIcao;
    @JsonProperty("arr_iata")
    private String arrIata;
    @JsonProperty("airline_icao")
    private String airlineIcao;
    @JsonProperty("airline_iata")
    private String airlineIata;
    @JsonProperty("aircraft_icao")
    private String aircraftIcao;
    @JsonProperty("updated")
    private int updated;
    @JsonProperty("status")
    private String status;

    @JsonProperty("hex")
    public String getHex() {
        return hex;
    }

    @JsonProperty("hex")
    public void setHex(String hex) {
        this.hex = hex;
    }

    @JsonProperty("reg_number")
    public String getRegNumber() {
        return regNumber;
    }

    @JsonProperty("reg_number")
    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    @JsonProperty("flag")
    public String getFlag() {
        return flag;
    }

    @JsonProperty("flag")
    public void setFlag(String flag) {
        this.flag = flag;
    }

    @JsonProperty("lat")
    public float getLat() {
        return lat;
    }

    @JsonProperty("lat")
    public void setLat(float lat) {
        this.lat = lat;
    }

    @JsonProperty("lng")
    public float getLng() {
        return lng;
    }

    @JsonProperty("lng")
    public void setLng(float lng) {
        this.lng = lng;
    }

    @JsonProperty("alt")
    public int getAlt() {
        return alt;
    }

    @JsonProperty("alt")
    public void setAlt(int alt) {
        this.alt = alt;
    }

    @JsonProperty("dir")
    public int getDir() {
        return dir;
    }

    @JsonProperty("dir")
    public void setDir(int dir) {
        this.dir = dir;
    }

    @JsonProperty("speed")
    public int getSpeed() {
        return speed;
    }

    @JsonProperty("speed")
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @JsonProperty("v_speed")
    public float getvSpeed() {
        return vSpeed;
    }

    @JsonProperty("v_speed")
    public void setvSpeed(float vSpeed) {
        this.vSpeed = vSpeed;
    }

    @JsonProperty("squawk")
    public String getSquawk() {
        return squawk;
    }

    @JsonProperty("squawk")
    public void setSquawk(String squawk) {
        this.squawk = squawk;
    }

    @JsonProperty("flight_number")
    public String getFlightNumber() {
        return flightNumber;
    }

    @JsonProperty("flight_number")
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    @JsonProperty("flight_icao")
    public String getFlightIcao() {
        return flightIcao;
    }

    @JsonProperty("flight_icao")
    public void setFlightIcao(String flightIcao) {
        this.flightIcao = flightIcao;
    }

    @JsonProperty("flight_iata")
    public String getFlightIata() {
        return flightIata;
    }

    @JsonProperty("flight_iata")
    public void setFlightIata(String flightIata) {
        this.flightIata = flightIata;
    }

    @JsonProperty("dep_icao")
    public String getDepIcao() {
        return depIcao;
    }

    @JsonProperty("dep_icao")
    public void setDepIcao(String depIcao) {
        this.depIcao = depIcao;
    }

    @JsonProperty("dep_iata")
    public String getDepIata() {
        return depIata;
    }

    @JsonProperty("dep_iata")
    public void setDepIata(String depIata) {
        this.depIata = depIata;
    }

    @JsonProperty("arr_icao")
    public String getArrIcao() {
        return arrIcao;
    }

    @JsonProperty("arr_icao")
    public void setArrIcao(String arrIcao) {
        this.arrIcao = arrIcao;
    }

    @JsonProperty("arr_iata")
    public String getArrIata() {
        return arrIata;
    }

    @JsonProperty("arr_iata")
    public void setArrIata(String arrIata) {
        this.arrIata = arrIata;
    }

    @JsonProperty("airline_icao")
    public String getAirlineIcao() {
        return airlineIcao;
    }

    @JsonProperty("airline_icao")
    public void setAirlineIcao(String airlineIcao) {
        this.airlineIcao = airlineIcao;
    }

    @JsonProperty("airline_iata")
    public String getAirlineIata() {
        return airlineIata;
    }

    @JsonProperty("airline_iata")
    public void setAirlineIata(String airlineIata) {
        this.airlineIata = airlineIata;
    }

    @JsonProperty("aircraft_icao")
    public String getAircraftIcao() {
        return aircraftIcao;
    }

    @JsonProperty("aircraft_icao")
    public void setAircraftIcao(String aircraftIcao) {
        this.aircraftIcao = aircraftIcao;
    }

    @JsonProperty("updated")
    public int getUpdated() {
        return updated;
    }

    @JsonProperty("updated")
    public void setUpdated(int updated) {
        this.updated = updated;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

}