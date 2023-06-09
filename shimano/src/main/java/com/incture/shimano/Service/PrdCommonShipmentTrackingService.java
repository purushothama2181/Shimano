package com.incture.shimano.Service;

import com.incture.shimano.Dto.PrdCommonDto;
import com.incture.shimano.Dto.PrdCommonShipmentTrackingDto;
import com.incture.shimano.Dto.PrdDto;
import com.incture.shimano.Entity.PrdCommonShipmentTracking;
import com.incture.shimano.Util.ResponseMessage;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface PrdCommonShipmentTrackingService {

    public ResponseMessage savePrdCommonShipment(PrdCommonShipmentTrackingDto prdCommonShipmentTrackingDto);

    public ResponseMessage updatePrdCommonShipment(PrdCommonShipmentTrackingDto prdCommonShipmentTrackingDto);

    PrdDto getPrdCommonShipmentByEta(PrdCommonShipmentTrackingDto prdCommonShipmentTrackingDto);

//    List<String> getInvoiceNumber(String containerID, String scac);

    PrdCommonDto getInvoiceNumber1(String containerID, String scac);

//    List<PrdCommonShipmentTrackingDto> getPrdCommonShipmentByEta(String etaDate) throws ParseException;
//
//
//    List<PrdCommonShipmentTrackingDto> getPrdCommonShipmentByActualDate(String transactionDate) throws ParseException;

  //    public PrdDto getPrdCommonShipmentByActualAndEstimatedTimeArrival(String podEstimatedTimeOfArrival , String podActualTimeOfArrival) throws ParseException;

}
