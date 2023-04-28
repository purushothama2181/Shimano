package com.incture.shimano.ServiceImpl;

import com.incture.shimano.Dto.PrdCommonDto;
import com.incture.shimano.Dto.PrdCommonShipmentTrackingDto;
import com.incture.shimano.Dto.PrdDto;
import com.incture.shimano.Entity.PrdCommonShipmentTracking;
import com.incture.shimano.Repository.PrdCommonShipmentTrackingRepository;
import com.incture.shimano.Service.PrdCommonShipmentTrackingService;
import com.incture.shimano.Util.ResponseMessage;
import com.incture.shimano.Util.ShimanoConstants;
import org.apache.http.client.utils.DateUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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
            LOGGER.info("started saving the CustomerLogInfo into db");
            PrdCommonShipmentTracking prdCommonShipmentTracking = this.dtoToEntity(prdCommonShipmentTrackingDto);
            try {
                PrdCommonShipmentTracking prdCommonShipmentTracking1 = prdCommonShipmentTrackingRepository.save(prdCommonShipmentTracking);
                LOGGER.info("PrdCommonShipmentTracking saved in DB " + prdCommonShipmentTracking1);
                responseMessage.setResponseStatus("Successfully Saved ChangeLogInfo", ShimanoConstants.SUCCESS, ShimanoConstants.CODE_SUCCESS, prdCommonShipmentTracking.getTransactionId());
            } catch (Exception e) {
                LOGGER.error("Save PrdCommonShipmentTracking Failed: " + e.getMessage());
                responseMessage.setResponseStatus("Save PrdCommonShipmentTracking Failed: " + e.getMessage(), ShimanoConstants.FAILURE, ShimanoConstants.CODE_FAILURE, null);
                e.getMessage();
            }
        } else {
            LOGGER.info("unable to save PrdCommonShipmentTracking because prdCommonShipmentTrackingDto is null");
            responseMessage.setResponseStatus("Save PrdCommonShipmentTracking Failed", ShimanoConstants.FAILURE, ShimanoConstants.CODE_FAILURE, null);
        }
        return responseMessage;
    }

    @Override
    public ResponseMessage updatePrdCommonShipment(PrdCommonShipmentTrackingDto prdCommonShipmentTrackingDto) {
        LOGGER.info("Started fetching the PrdCommonShipmentTracking to prdCommonShipmentTrackingDto: " + prdCommonShipmentTrackingDto.toString() + "Where the transactionId is: " + prdCommonShipmentTrackingDto.getTransactionId());
        ResponseMessage responseMessage = new ResponseMessage();
        PrdCommonShipmentTracking prdCommonShipmentTrackingLocal = prdCommonShipmentTrackingRepository.findByTransactionId(prdCommonShipmentTrackingDto.getTransactionId());
        PrdCommonShipmentTracking prdCommonShipmentTracking = new PrdCommonShipmentTracking();
        if(prdCommonShipmentTracking!= null) {
            try {
                prdCommonShipmentTracking = this.dtoToEntity(prdCommonShipmentTrackingDto);
                prdCommonShipmentTracking.setTransactionId(prdCommonShipmentTrackingLocal.getTransactionId());
                prdCommonShipmentTrackingRepository.save(prdCommonShipmentTracking);
                LOGGER.info("Successfully updated PrdCommonShipmentTracking into DB");
                responseMessage.setResponseStatus("Successfully Updated PrdCommonShipmentTracking", ShimanoConstants.SUCCESS, ShimanoConstants.CODE_SUCCESS, prdCommonShipmentTracking.getTransactionId());

            } catch (Exception e) {
                LOGGER.error(" failed to update the PrdCommonShipmentTracking from db " + e.getMessage());
                responseMessage.setResponseStatus("Failed to update PrdCommonShipmentTracking" + e.getMessage(), ShimanoConstants.FAILURE, ShimanoConstants.CODE_FAILURE, null);
                e.getMessage();
            }
        }else {
            LOGGER.info("failed to update PrdCommonShipmentTracking because of null object");
            responseMessage.setResponseStatus("failed to update PrdCommonShipmentTracking because of null object", ShimanoConstants.FAILURE, ShimanoConstants.CODE_FAILURE, null);
        }
        return responseMessage;
    }

    @Override
    public PrdDto getPrdCommonShipmentByEta( PrdCommonShipmentTrackingDto PrdCommonShipmentTrackingDto) {
        PrdDto prdDto = new PrdDto();
        Timestamp createdOnTimeStamp = null;
        Timestamp currentTime= null;
        System.out.println(PrdCommonShipmentTrackingDto.toString());
        if(PrdCommonShipmentTrackingDto.getTransactionDate()!=null) {
            TimeZone.setDefault(TimeZone.getTimeZone("America/Los_Angeles"));
            Date date = new Date();
            currentTime = new Timestamp(date.getTime());
            String pattern = "yyyy-MM-dd";
            DateFormat df = new SimpleDateFormat(pattern);
            String todayAsString = df.format(PrdCommonShipmentTrackingDto.getTransactionDate()) + " 00:00:00";
            Date fromDate;
            SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            try {
                fromDate = parser.parse(todayAsString);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            createdOnTimeStamp = new Timestamp(fromDate.getTime()+ 1 * 24 * 60 * 60 * 1000);
        }
        List<PrdCommonShipmentTracking> prdCommonShipmentTrackingList = prdCommonShipmentTrackingRepository.filter(
                currentTime,
                createdOnTimeStamp,
                PrdCommonShipmentTrackingDto.getP44Status(),
                PrdCommonShipmentTrackingDto.getClosedFlag(),
                PrdCommonShipmentTrackingDto.getTransactionId()
        );
        prdDto.setPrdCommonShipmentTrackingList(prdCommonShipmentTrackingList);
        return prdDto;
    }

//    @Override
//    public List<String> getInvoiceNumber(String containerID, String scac) {
//        List<String> list = new ArrayList<>();
//        try {
//            List<PrdCommonShipmentTracking> entities = prdCommonShipmentTrackingRepository.findByContainerIDAndScac(containerID,scac);
//            list = entities.stream().map(PrdCommonShipmentTracking::getInvoiceNumber).collect(Collectors.toList());
//        }catch(Exception e) {
//            e.getMessage();
//        }
//        if(list.size()==0){
//            List<String> newArrList = new ArrayList<>();
//            newArrList.add(null);
//           return newArrList;
//        }else {
//            return list;
//        }
//    }

    @Override
    public PrdCommonDto getInvoiceNumber1(String containerID, String scac) {
        PrdCommonDto prdCommonDto = new PrdCommonDto();
        List<PrdCommonShipmentTracking> AuditLogs = prdCommonShipmentTrackingRepository.findByContainerIDAndScac(containerID, scac);

        List<PrdCommonShipmentTrackingDto> cmAuditLogDtos = new ArrayList<>();
        if (AuditLogs != null) {
            for (PrdCommonShipmentTracking entity : AuditLogs) {
                cmAuditLogDtos.add(this.entityToDto(entity));
            }
            prdCommonDto.setPrdCommonShipmentTracking(cmAuditLogDtos);
            if (cmAuditLogDtos.size() == 0) {
                cmAuditLogDtos.add(null);
                prdCommonDto.setResponse("noRecords");
                return prdCommonDto;
            }
        }
        return prdCommonDto;
    }

//    @Override
//    public PrdDto getPrdCommonShipmentByActualAndEstimatedTimeArrival(String podEstimatedTimeOfArrival, String podActualTimeOfArrival) throws ParseException {
//
//        PrdDto prdDto = new PrdDto();
//      //  String ed = podEstimatedTimeOfArrival +" 00:00:00";
//     //   String ad = podActualTimeOfArrival +" 00:00:00"
//
////        Date date1 = dateFormat.parse(podEstimatedTimeOfArrival);
////        Date date2 = dateFormat.parse(podActualTimeOfArrival);
//        prdDto.setPrdCommonShipmentTrackingList(prdCommonShipmentTrackingRepository.findByPodEstimatedTimeOfArrivalAndPodActualTimeOfArrival(podEstimatedTimeOfArrival, podActualTimeOfArrival));
//
//       return prdDto;
//    }

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
