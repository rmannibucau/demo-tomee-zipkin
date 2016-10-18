package com.github.rmannibucau.demo.zipkin.server;

import lombok.NoArgsConstructor;
import org.apache.tomee.embedded.FatApp;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ServerRunner {
    public static void main(final String[] args) {
        FatApp.main(new String[] { "--port=9090" });
    }
}
