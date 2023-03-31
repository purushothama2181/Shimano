package com.incture.shimano.Dto;

import com.incture.shimano.Entity.PrdCommonShipmentTracking;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
public class PrdDto {

    private List<PrdCommonShipmentTracking> prdCommonShipmentTrackingList;
}
