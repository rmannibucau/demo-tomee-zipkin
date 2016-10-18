package com.github.rmannibucau.demo.zipkin.client;

import lombok.NoArgsConstructor;
import org.apache.tomee.embedded.FatApp;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ClientRunner {
    public static void main(final String[] args) {
        FatApp.main(new String[] {
                "--port=8080",
                "--property=openejb.additional.exclude=brave"
        });
    }
}
