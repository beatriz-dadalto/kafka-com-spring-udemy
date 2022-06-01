package com.live.kafka.producer.controller;

import lombok.Builder;
import lombok.Data;

@Data
@Builder // serialize with Lombok?
public class CarDTO {

    private String id;
    private String model;
    private String color;

}
