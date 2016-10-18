package com.github.rmannibucau.demo.zipkin.common;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Rate {
    private String from;
    private String to;
    private double value;
}
