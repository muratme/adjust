package com.adjust.testlibrary.model.base;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
@JsonPropertyOrder({"code", "name", "description", "message", "trace", "clientMessage"})
public class ResponseError {
    private int code;

    private String name;

    private String description;

    private String message;

    private String trace;

    private String clientMessage;

    public ResponseError() {}

    public ResponseError(String message, String description) {
        this.description = description;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTrace() {
        return this.trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

    public String getClientMessage() {
        return this.clientMessage;
    }

    public void setClientMessage(String clientMessage) {
        this.clientMessage = clientMessage;
    }
}
