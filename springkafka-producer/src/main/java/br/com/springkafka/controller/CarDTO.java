package br.com.springkafka.controller;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CarDTO {

    private String id;
    private String name;
    private String brand;
}
