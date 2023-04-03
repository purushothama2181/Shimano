package com.incture.shimano.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@Entity
@Data
@NoArgsConstructor
@Table(name = "P44_COMMON_SHIPMENT_TRACKING")
public class PrdCommonShipmentTracking {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "TRANSACTION_ID")
    private UUID transactionId;

    @Column(name = "MBL")
    private String mbl;

    @Column(name = "TRANSACTION_DATE")
    private String transactionDate;

    @Column(name = "P44_STATUS")
    private String p44Status;

    @Column(name = "CLOSED_FLAG")
    private String closedFlag;

    @Column(name = "SCAC")
    private String scac;

    @Column(name = "CONTAINER_ID")
    private String containerID;

    @Column(name = "AIRWAY_BILL_NUMBER")
    private String airwayBillNumber;

    @Column(name = "INVOICE_NUMBER")
    private String invoiceNumber;

    @Column(name = "PRODUCT_TYPE")
    private String productType;

    @Column(name = "NOTE")
    private String note;

    @Column(name = "PORT_OF_LOADING")
    private String portOfLoading;

    @Column(name = "POL_ACTUAL_TIME_OF_DEPARTURE")
    private String polActualTimeOfDeparture;

//    @Column(name = "POL_ESTIMATED_TIME_OF_DEPARTURE")
//    private String polEstimatedTimeOfDeparture;

    @Column(name = "POL_VESSEL_NAME")
    private String polVesselName;

    @Column(name = "PORT_OF_DISCHARGE")
    private String portOfDischarge;


//    @Column(name = "POD_ESTIMATED_TIME_OF_ARRIVAL")
//    private String podEstimatedTimeOfArrival;


    @Column(name = "POD_ACTUAL_TIME_OF_ARRIVAL")
    private String podActualTimeOfArrival;

    @Column(name = "POD_VESSEL_NAME")
    private String podVesselName;

    @Column(name = "POD_CARRIER_ETA")
    private String podCarrierEta;

    @Column(name = "POD_P44_ETA")
    private String podP44Eta;

    @Column(name = "P44_SHIPMENT_ID")
    private String p44ShipmentId;

    @Column(name = "P44_STATUS_DATE")
    private String p44StatusDate;


}
