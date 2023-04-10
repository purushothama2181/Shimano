package com.incture.shimano.Repository;

import com.incture.shimano.Entity.PrdCommonShipmentTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface PrdCommonShipmentTrackingRepository extends JpaRepository<PrdCommonShipmentTracking, UUID> {
    PrdCommonShipmentTracking findByTransactionId(Integer transactionId);

    @Query(value="from PrdCommonShipmentTracking c "
            + "where (:transactionDate is null or c.transactionDate between :transactionDate and :currentTime)"
            + "and (:p44Status is null or c.p44Status = :p44Status) "
            + "and (:closedFlag is null or c.closedFlag = :closedFlag) "
            + "and (:transactionId is null or c.transactionId = :transactionId) ")
    List<PrdCommonShipmentTracking> filter( @Param("currentTime")Timestamp currentTime,
                                            @Param("transactionDate")Timestamp createdOnTimeStamp,
                                            @Param("p44Status") String p44Status,
                                            @Param("closedFlag") String closedFlag,
                                            @Param("transactionId") Integer transactionId
                                            );


//    List<PrdCommonShipmentTracking> findByTransactionDate(Date transactionDate);


//    List<PrdCommonShipmentTracking> findByEtaDateBetween(Date date1, Date date2);
//
//    List<PrdCommonShipmentTracking> findByTransactionDateBetween(Date date1, Date date2);

//    List<PrdCommonShipmentTracking> findByPodEstimatedTimeOfArrivalAndPodActualTimeOfArrival(String podEstimatedTimeOfArrival, String PodActualTimeOfArrival);
}
