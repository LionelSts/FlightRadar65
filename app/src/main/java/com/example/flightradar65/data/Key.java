package com.example.flightradar65.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "api_key",
        "type",
        "expired",
        "registered",
        "limits_by_hour",
        "limits_by_minute",
        "limits_by_month",
        "limits_total"
})

public class Key implements Serializable {

    @JsonProperty("id")
    private int id;
    @JsonProperty("api_key")
    private String apiKey;
    @JsonProperty("type")
    private String type;
    @JsonProperty("expired")
    private String expired;
    @JsonProperty("registered")
    private String registered;
    @JsonProperty("limits_by_hour")
    private int limitsByHour;
    @JsonProperty("limits_by_minute")
    private int limitsByMinute;
    @JsonProperty("limits_by_month")
    private int limitsByMonth;
    @JsonProperty("limits_total")
    private int limitsTotal;

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("api_key")
    public String getApiKey() {
        return apiKey;
    }

    @JsonProperty("api_key")
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("expired")
    public String getExpired() {
        return expired;
    }

    @JsonProperty("expired")
    public void setExpired(String expired) {
        this.expired = expired;
    }

    @JsonProperty("registered")
    public String getRegistered() {
        return registered;
    }

    @JsonProperty("registered")
    public void setRegistered(String registered) {
        this.registered = registered;
    }

    @JsonProperty("limits_by_hour")
    public int getLimitsByHour() {
        return limitsByHour;
    }

    @JsonProperty("limits_by_hour")
    public void setLimitsByHour(int limitsByHour) {
        this.limitsByHour = limitsByHour;
    }

    @JsonProperty("limits_by_minute")
    public int getLimitsByMinute() {
        return limitsByMinute;
    }

    @JsonProperty("limits_by_minute")
    public void setLimitsByMinute(int limitsByMinute) {
        this.limitsByMinute = limitsByMinute;
    }

    @JsonProperty("limits_by_month")
    public int getLimitsByMonth() {
        return limitsByMonth;
    }

    @JsonProperty("limits_by_month")
    public void setLimitsByMonth(int limitsByMonth) {
        this.limitsByMonth = limitsByMonth;
    }

    @JsonProperty("limits_total")
    public int getLimitsTotal() {
        return limitsTotal;
    }

    @JsonProperty("limits_total")
    public void setLimitsTotal(int limitsTotal) {
        this.limitsTotal = limitsTotal;
    }

}
