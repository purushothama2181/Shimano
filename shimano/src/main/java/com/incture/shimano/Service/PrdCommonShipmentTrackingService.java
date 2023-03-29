package com.incture.shimano.Service;

import com.incture.shimano.Dto.PrdCommonShipmentTrackingDto;
import com.incture.shimano.Entity.PrdCommonShipmentTracking;
import com.incture.shimano.Util.ResponseMessage;

import java.text.ParseException;
import java.util.List;

public interface PrdCommonShipmentTrackingService {

    public ResponseMessage savePrdCommonShipment(PrdCommonShipmentTrackingDto prdCommonShipmentTrackingDto);

    public ResponseMessage updatePrdCommonShipment(PrdCommonShipmentTrackingDto prdCommonShipmentTrackingDto);

//    List<PrdCommonShipmentTrackingDto> getPrdCommonShipmentByEta(String etaDate) throws ParseException;
//
//
//    List<PrdCommonShipmentTrackingDto> getPrdCommonShipmentByActualDate(String transactionDate) throws ParseException;

      public List<PrdCommonShipmentTracking> getPrdCommonShipmentByActualAndEstimatedTimeArrival(String podEstimatedTimeOfArrival , String podActualTimeOfArrival) throws ParseException;

}
