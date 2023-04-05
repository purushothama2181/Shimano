package com.incture.shimano.Util;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class ResponseMessage {

    private String responseMessage;

    private String status;

    private String statusCode;

    private Integer transactionId;

    public void setResponseStatus(String msg, String status, String code, Integer transactionId) {
        this.responseMessage = msg ;
        this.status = status ;
        this.statusCode = code ;
        this.transactionId = transactionId ;
    }
}
