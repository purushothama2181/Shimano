package com.incture.shimano.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
public class PrdCommonDto {

    private List<PrdCommonShipmentTrackingDto> prdCommonShipmentTracking;
}
