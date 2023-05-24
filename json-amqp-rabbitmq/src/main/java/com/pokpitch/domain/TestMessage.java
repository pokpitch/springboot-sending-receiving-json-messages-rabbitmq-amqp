package com.pokpitch.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class TestMessage {

    @JsonProperty("request_id")
    private String requestId;

    @JsonProperty("reference_id")
    private String referenceId;

    @JsonProperty("status")
    private String status;

    @JsonProperty("closed")
    private boolean closed;

    @JsonCreator
    public TestMessage(
            @JsonProperty("request_id")
            String requestId,
            @JsonProperty("reference_id")
            String referenceId,
            @JsonProperty("status")
            String status,
            @JsonProperty("closed")
            boolean closed
    ) {
        this.requestId = requestId;
        this.referenceId = referenceId;
        this.status = status;
        this.closed = closed;
    }
}
