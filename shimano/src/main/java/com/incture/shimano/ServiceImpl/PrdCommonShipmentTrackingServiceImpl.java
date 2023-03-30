package com.incture.shimano.ServiceImpl;

import com.incture.shimano.Dto.PrdCommonShipmentTrackingDto;
import com.incture.shimano.Entity.PrdCommonShipmentTracking;
import com.incture.shimano.Repository.PrdCommonShipmentTrackingRepository;
import com.incture.shimano.Service.PrdCommonShipmentTrackingService;
import com.incture.shimano.Util.ResponseMessage;
import com.incture.shimano.Util.ShimanoConstants;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
public class PrdCommonShipmentTrackingServiceImpl implements PrdCommonShipmentTrackingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrdCommonShipmentTrackingServiceImpl.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PrdCommonShipmentTrackingRepository prdCommonShipmentTrackingRepository;

    /*
     * Method to Covert PrdCommonShipmentTracking entity to PrdCommonShipmentTrackingDto
     * */
    public PrdCommonShipmentTrackingDto entityToDto(PrdCommonShipmentTracking prdCommonShipmentTracking) {
        PrdCommonShipmentTrackingDto prdCommonShipmentTrackingDto = this.modelMapper.map(prdCommonShipmentTracking, PrdCommonShipmentTrackingDto.class);
        LOGGER.info("After converting the PrdCommonShipmentTracking entity to PrdCommonShipmentTrackingDto :" + prdCommonShipmentTrackingDto.toString());
        return prdCommonShipmentTrackingDto;
    }

    /*
     * Method to Convert PrdCommonShipmentTrackingDto to PrdCommonShipmentTracking entity
     * */
    public PrdCommonShipmentTracking dtoToEntity(PrdCommonShipmentTrackingDto prdCommonShipmentTrackingDto) {
        PrdCommonShipmentTracking prdCommonShipmentTracking = this.modelMapper.map(prdCommonShipmentTrackingDto, PrdCommonShipmentTracking.class);
        LOGGER.info("After converting the CMAuditLogDto to CMAuditLog entity :" + prdCommonShipmentTracking.toString());
        return prdCommonShipmentTracking;
    }

    @Override
    public ResponseMessage savePrdCommonShipment(PrdCommonShipmentTrackingDto prdCommonShipmentTrackingDto) {
        ResponseMessage responseMessage = new ResponseMessage();
        if (prdCommonShipmentTrackingDto != null ) {
            if(prdCommonShipmentTrackingDto.getPodEstimatedTimeOfArrival() != null && prdCommonShipmentTrackingDto.getPodActualTimeOfArrival() != null) {
                prdCommonShipmentTrackingDto.setP44Status("Completed");
            } else {
                prdCommonShipmentTrackingDto.setP44Status("In Progress");
            }
            LOGGER.info("started saving the CustomerLogInfo into db");
 //           prdCommonShipmentTrackingDto.setEtaDate(ts);
            PrdCommonShipmentTracking prdCommonShipmentTracking = this.dtoToEntity(prdCommonShipmentTrackingDto);
            try {
                PrdCommonShipmentTracking prdCommonShipmentTracking1 = prdCommonShipmentTrackingRepository.save(prdCommonShipmentTracking);
                LOGGER.info("PrdCommonShipmentTracking saved in DB " + prdCommonShipmentTracking1);
                responseMessage.setResponseStatus("Successfully Saved ChangeLogInfo", ShimanoConstants.SUCCESS, ShimanoConstants.CODE_SUCCESS, prdCommonShipmentTracking.getTransactionId());
            } catch (Exception e) {
                LOGGER.error("Save PrdCommonShipmentTracking Failed: " + e.getMessage());
                responseMessage.setResponseStatus("Save PrdCommonShipmentTracking Failed: " + e.getMessage(), ShimanoConstants.FAILURE, ShimanoConstants.CODE_FAILURE, UUID.fromString(""));
                e.getMessage();
            }
        } else {
            LOGGER.info("unable to save PrdCommonShipmentTracking because prdCommonShipmentTrackingDto is null");
            responseMessage.setResponseStatus("Save PrdCommonShipmentTracking Failed", ShimanoConstants.FAILURE, ShimanoConstants.CODE_FAILURE, UUID.fromString(""));
        }
        return responseMessage;
    }

    @Override
    public ResponseMessage updatePrdCommonShipment(PrdCommonShipmentTrackingDto prdCommonShipmentTrackingDto) {
        LOGGER.info("Started fetching the PrdCommonShipmentTracking to prdCommonShipmentTrackingDto: " + prdCommonShipmentTrackingDto.toString() + "Where the transactionId is: " + prdCommonShipmentTrackingDto.getTransactionId());
        ResponseMessage responseMessage = new ResponseMessage();
        PrdCommonShipmentTracking prdCommonShipmentTracking = prdCommonShipmentTrackingRepository.findByTransactionId(prdCommonShipmentTrackingDto.getTransactionId());
        if(prdCommonShipmentTracking!= null) {
            try {
                prdCommonShipmentTracking.setInvoiceNumber(prdCommonShipmentTrackingDto.getInvoiceNumber());
                prdCommonShipmentTracking.setScac(prdCommonShipmentTrackingDto.getScac());
                prdCommonShipmentTracking.setContainerID(prdCommonShipmentTrackingDto.getContainerID());
                prdCommonShipmentTracking.setAirwayBillNumber(prdCommonShipmentTrackingDto.getAirwayBillNumber());
                prdCommonShipmentTracking.setP44Status(prdCommonShipmentTrackingDto.getP44Status());
                prdCommonShipmentTracking.setClosedFlag(prdCommonShipmentTrackingDto.getClosedFlag());
                prdCommonShipmentTracking.setAirwayBillNumber(prdCommonShipmentTrackingDto.getAirwayBillNumber());
                prdCommonShipmentTracking.setPodVesselName(prdCommonShipmentTrackingDto.getPodVesselName());
                prdCommonShipmentTracking.setNote(prdCommonShipmentTrackingDto.getNote());
                prdCommonShipmentTracking.setMbl(prdCommonShipmentTrackingDto.getMbl());
                prdCommonShipmentTracking.setPodVesselName(prdCommonShipmentTrackingDto.getPolVesselName());
                prdCommonShipmentTracking.setPortOfDischarge(prdCommonShipmentTrackingDto.getPortOfDischarge());
                prdCommonShipmentTracking.setPortOfLoading(prdCommonShipmentTrackingDto.getPortOfLoading());
                prdCommonShipmentTracking.setProductType(prdCommonShipmentTrackingDto.getProductType());
                prdCommonShipmentTrackingRepository.save(prdCommonShipmentTracking);
                LOGGER.info("Successfully updated PrdCommonShipmentTracking into DB");
                responseMessage.setResponseStatus("Successfully Updated PrdCommonShipmentTracking", ShimanoConstants.SUCCESS, ShimanoConstants.CODE_SUCCESS, prdCommonShipmentTracking.getTransactionId());

            } catch (Exception e) {
                LOGGER.error(" failed to update the PrdCommonShipmentTracking from db " + e.getMessage());
                responseMessage.setResponseStatus("Failed to update PrdCommonShipmentTracking" + e.getMessage(), ShimanoConstants.FAILURE, ShimanoConstants.CODE_FAILURE, UUID.fromString(""));
                e.getMessage();
            }
        }else {
            LOGGER.info("failed to update PrdCommonShipmentTracking because of null object");
            responseMessage.setResponseStatus("failed to update PrdCommonShipmentTracking because of null object", ShimanoConstants.FAILURE, ShimanoConstants.CODE_FAILURE, UUID.fromString(""));
        }
        return responseMessage;
    }

    @Override
    public List<PrdCommonShipmentTracking> getPrdCommonShipmentByActualAndEstimatedTimeArrival(String podEstimatedTimeOfArrival, String podActualTimeOfArrival) throws ParseException {
      //  String ed = podEstimatedTimeOfArrival +" 00:00:00";
     //   String ad = podActualTimeOfArrival +" 00:00:00"

//        Date date1 = dateFormat.parse(podEstimatedTimeOfArrival);
//        Date date2 = dateFormat.parse(podActualTimeOfArrival);
        return prdCommonShipmentTrackingRepository.findByPodEstimatedTimeOfArrivalAndPodActualTimeOfArrival(podEstimatedTimeOfArrival, podActualTimeOfArrival);
    }

    //   @Override
//    public List<PrdCommonShipmentTrackingDto> getPrdCommonShipmentByEta(String etaDate) throws ParseException {
//        String st = etaDate+" 00:00:00";
//        String ed = etaDate+" 23:59:00";
//        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        Date date1 = dateFormat.parse(st);
//        Date date2 = dateFormat.parse(ed);
//        List<PrdCommonShipmentTracking> prdCommonShipmentTrackings = prdCommonShipmentTrackingRepository.findByEtaDateBetween(date1,date2);
//
//        List<PrdCommonShipmentTrackingDto> prdCommonShipmentTrackingDtos = new ArrayList<>();
//        if(prdCommonShipmentTrackings !=null){
//            for(PrdCommonShipmentTracking entity : prdCommonShipmentTrackings) {
//                prdCommonShipmentTrackingDtos.add(this.entityToDto(entity));
//            }
//        }
//        return prdCommonShipmentTrackingDtos;
//    }
//
//    @Override
//    public List<PrdCommonShipmentTrackingDto> getPrdCommonShipmentByActualDate(String transactionDate) throws ParseException {
//        String st = transactionDate+" 00:00:00";
//        String ed = transactionDate+" 23:59:00";
//        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        Date date1 = dateFormat.parse(st);
//        Date date2 = dateFormat.parse(ed);
//        List<PrdCommonShipmentTracking> prdCommonShipmentTrackings = prdCommonShipmentTrackingRepository.findByTransactionDateBetween(date1,date2);
//
//        List<PrdCommonShipmentTrackingDto> prdCommonShipmentTrackingDtos = new ArrayList<>();
//        if(prdCommonShipmentTrackings !=null){
//            for(PrdCommonShipmentTracking entity : prdCommonShipmentTrackings) {
//                prdCommonShipmentTrackingDtos.add(this.entityToDto(entity));
//            }
//        }
//        return prdCommonShipmentTrackingDtos;
//    }

}