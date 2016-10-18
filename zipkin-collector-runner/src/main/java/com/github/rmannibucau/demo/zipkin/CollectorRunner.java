package com.github.rmannibucau.demo.zipkin;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

// configure an execution with org.springframework.boot.loader.JarLauncher class and args:
// --server.port=7070
//
// ex: java -jar dependency/zipkin-server-1.12.1-exec.jar --server.port=7070
@NoArgsConstructor(access = PRIVATE)
public class CollectorRunner {
    public static void main(final String[] args) throws Exception {
        throw new IllegalArgumentException("See comment");
    }

}

