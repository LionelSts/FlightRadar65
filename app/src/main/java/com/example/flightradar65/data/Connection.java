package com.example.flightradar65.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "type",
        "isp_code"
})

public class Connection {

    @JsonProperty("type")
    private String type;

    @JsonProperty("isp_code")
    private String isp_code;

    @JsonProperty("isp_name")
    private String isp_name;

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("isp_code")
    public String getIsp_code() {
        return isp_code;
    }

    @JsonProperty("isp_code")
    public void setIsp_code(String isp_code) {
        this.isp_code = isp_code;
    }

    @JsonProperty("isp_name")
    public String getIsp_name() {
        return isp_name;
    }

    @JsonProperty("isp_name")
    public void setIsp_name(String isp_name) {
        this.isp_name = isp_name;
    }

}
