package com.example.flightradar65.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "request",
        "response",
        "terms"
})

public class Dataset implements Serializable {

    @JsonProperty("request")
    private Request request;
    @JsonProperty("response")
    private List<ApiResponse> response = null;
    @JsonProperty("terms")
    private String terms;

    @JsonProperty("request")
    public Request getRequest() {
        return request;
    }

    @JsonProperty("request")
    public void setRequest(Request request) {
        this.request = request;
    }

    @JsonProperty("response")
    public List<ApiResponse> getResponse() {
        return response;
    }

    @JsonProperty("response")
    public void setResponse(List<ApiResponse> response) {
        this.response = response;
    }

    @JsonProperty("terms")
    public String getTerms() {
        return terms;
    }

    @JsonProperty("terms")
    public void setTerms(String terms) {
        this.terms = terms;
    }

}
