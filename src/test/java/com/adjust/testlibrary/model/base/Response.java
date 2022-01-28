package com.adjust.testlibrary.model.base;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;

@JsonPropertyOrder({"success", "data", "error"})
public class Response {

    private boolean success = true;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private JsonNode data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ResponseError error;

    public Response() {}

    private void markInvalid() {
        this.success = false;
    }

    public ResponseError getError() {
        return this.error;
    }

    public void setError(ResponseError error) {
        markInvalid();
        this.error = error;
    }

    public JsonNode getData() {
        return this.data;
    }

    public void setData(JsonNode data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
