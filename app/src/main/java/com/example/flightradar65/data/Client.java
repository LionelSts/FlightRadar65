package com.example.flightradar65.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "ip",
        "geo",
        "connection",
        "device",
        "agent",
        "karma"
})
public class Client {

    @JsonProperty("ip")
    private String ip;
    @JsonProperty("geo")
    private Geo geo;
    @JsonProperty("connection")
    private Connection connection;
    @JsonProperty("device")
    private Device device;
    @JsonProperty("agent")
    private Agent agent;
    @JsonProperty("karma")
    private Karma karma;

    @JsonProperty("ip")
    public String getIp() {
        return ip;
    }

    @JsonProperty("ip")
    public void setIp(String ip) {
        this.ip = ip;
    }

    @JsonProperty("geo")
    public Geo getGeo() {
        return geo;
    }

    @JsonProperty("geo")
    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    @JsonProperty("connection")
    public Connection getConnection() {
        return connection;
    }

    @JsonProperty("connection")
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @JsonProperty("device")
    public Device getDevice() {
        return device;
    }

    @JsonProperty("device")
    public void setDevice(Device device) {
        this.device = device;
    }

    @JsonProperty("agent")
    public Agent getAgent() {
        return agent;
    }

    @JsonProperty("agent")
    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    @JsonProperty("karma")
    public Karma getKarma() {
        return karma;
    }

    @JsonProperty("karma")
    public void setKarma(Karma karma) {
        this.karma = karma;
    }

}
