package com.incture.shimano.Controller;

import com.incture.shimano.Dto.PrdCommonDto;
import com.incture.shimano.Dto.PrdCommonShipmentTrackingDto;
import com.incture.shimano.Dto.PrdDto;
import com.incture.shimano.Service.PrdCommonShipmentTrackingService;
import com.incture.shimano.Util.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@Slf4j
public class PrdCommonShipmentTrackingController {

    @Autowired
    private PrdCommonShipmentTrackingService prdCommonShipmentTrackingService;

    Logger logger = LoggerFactory.getLogger(PrdCommonShipmentTrackingController.class);


    /*
     * Create for savePrdCommonShipment
     * */
    @PostMapping("/savePrdCommonShipment")
    public ResponseMessage savePrdCommonShipment(@RequestBody PrdCommonShipmentTrackingDto prdCommonShipmentTrackingDto) {
        logger.info("Started fetching the PrdCommonShipmentTrackingDto to prdCommonShipmentTrackingDto: " + prdCommonShipmentTrackingDto.toString());
        return prdCommonShipmentTrackingService.savePrdCommonShipment(prdCommonShipmentTrackingDto);
    }

    /*
     * Update for savePrdCommonShipment
     * */
    @PutMapping("/updatePrdCommonShipment")
    public ResponseMessage updatePrdCommonShipment(@RequestBody PrdCommonShipmentTrackingDto prdCommonShipmentTrackingDto) {
        logger.info("Started fetching the PrdCommonShipmentTrackingDto to prdCommonShipmentTrackingDto: " + prdCommonShipmentTrackingDto.toString());
        return prdCommonShipmentTrackingService.updatePrdCommonShipment(prdCommonShipmentTrackingDto);
    }


    /*
     * Get ETA BETWEEN DATES
     * */
    @GetMapping(value = "/getPrdCommonShipmentByEta")
    public PrdDto getPrdCommonShipmentByEta(@RequestBody PrdCommonShipmentTrackingDto prdCommonShipmentTrackingDto)  {
     return prdCommonShipmentTrackingService.getPrdCommonShipmentByEta(prdCommonShipmentTrackingDto);
    }

    /*
     * Get method for fetching AuditLogInfo By requestId
     * */
    @GetMapping(value = "/getInvoiceNumber")
    public PrdCommonDto getInvoiceNumber1(@RequestParam String containerID, @RequestParam String scac) {
        return prdCommonShipmentTrackingService.getInvoiceNumber1(containerID,scac);
    }


//    /*
//     * get CustomerCountry using containerID and scac
//     * */
//    @GetMapping(value = "/getInvoiceNumber")
//    public List<String> getInvoiceNumber(@RequestParam String containerID, @RequestParam String scac){
//        return prdCommonShipmentTrackingService.getInvoiceNumber(containerID,scac);
//    }

    //    @GetMapping(value = "/getPrdCommonShipmentByActualEstimatedTime")
//    public PrdDto getPrdCommonShipmentByActualAndEstimatedTimeArrival(@RequestParam("podEstimatedTimeOfArrival") String podEstimatedTimeOfArrival , @RequestParam("podActualTimeOfArrival") String podActualTimeOfArrival) throws ParseException {
//        return prdCommonShipmentTrackingService.getPrdCommonShipmentByActualAndEstimatedTimeArrival(podEstimatedTimeOfArrival,podActualTimeOfArrival);
//    }

//    @GetMapping(value = "/getPrdCommonShipmentByEta")
//    public List<PrdCommonShipmentTrackingDto> getPrdCommonShipmentByEta(@RequestParam("etaDate") String etaDate) throws ParseException {
//        return prdCommonShipmentTrackingService.getPrdCommonShipmentByEta(etaDate);
//    }
//
//    @GetMapping(value = "/getPrdCommonShipmentByActualDate")
//    public List<PrdCommonShipmentTrackingDto> getPrdCommonShipmentByActualDate(@RequestParam("transactionDate") String transactionDate) throws ParseException{
//        return prdCommonShipmentTrackingService.getPrdCommonShipmentByActualDate(transactionDate);
//    }

}
