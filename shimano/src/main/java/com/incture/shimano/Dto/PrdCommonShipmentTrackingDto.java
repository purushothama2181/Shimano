package com.incture.shimano.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.UUID;

@Data
@ToString
@NoArgsConstructor
public class PrdCommonShipmentTrackingDto {

    private UUID transactionId;
    private String transactionDate;
    private String invoiceNumber;
    private String scac;
    private String containerID;
    private String airwayBillNumber;
    private String pooEstimatedTimeOfDeparture;
    private String p44Status;
    private String closedFlag;
    private String mbl;
    private String note;
    private String productType;
    private String portOfOrigin;
    private String pooActualTimeOfDeparture;
    private String pooVesselName;
    private String portOfDischarge;
    private String podEstimatedTimeOfArrival;
    private String podActualTimeOfArrival;
    private String podVesselName;
}
