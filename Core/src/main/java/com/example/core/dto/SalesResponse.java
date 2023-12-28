package com.example.core.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Month;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalesResponse {
    String userOrStoreName;
    int month;
    int sales;
    @JsonCreator
    public SalesResponse(@JsonProperty("userOrStoreName") String userOrStoreName,
                         @JsonProperty("month") int month,
                         @JsonProperty("sales") int sales) {
        this.userOrStoreName = userOrStoreName;
        this.month = month;
        this.sales = sales;
    }
}

