package com.example.flightradar65.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "is_blocked",
        "is_crawler",
        "is_bot",
        "is_friend",
        "is_regular"
})

public class Karma implements Serializable {

    @JsonProperty("is_blocked")
    private boolean isBlocked;
    @JsonProperty("is_crawler")
    private boolean isCrawler;
    @JsonProperty("is_bot")
    private boolean isBot;
    @JsonProperty("is_friend")
    private boolean isFriend;
    @JsonProperty("is_regular")
    private boolean isRegular;

    @JsonProperty("is_blocked")
    public boolean isIsBlocked() {
        return isBlocked;
    }

    @JsonProperty("is_blocked")
    public void setIsBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    @JsonProperty("is_crawler")
    public boolean isIsCrawler() {
        return isCrawler;
    }

    @JsonProperty("is_crawler")
    public void setIsCrawler(boolean isCrawler) {
        this.isCrawler = isCrawler;
    }

    @JsonProperty("is_bot")
    public boolean isIsBot() {
        return isBot;
    }

    @JsonProperty("is_bot")
    public void setIsBot(boolean isBot) {
        this.isBot = isBot;
    }

    @JsonProperty("is_friend")
    public boolean isIsFriend() {
        return isFriend;
    }

    @JsonProperty("is_friend")
    public void setIsFriend(boolean isFriend) {
        this.isFriend = isFriend;
    }

    @JsonProperty("is_regular")
    public boolean isIsRegular() {
        return isRegular;
    }

    @JsonProperty("is_regular")
    public void setIsRegular(boolean isRegular) {
        this.isRegular = isRegular;
    }

}
