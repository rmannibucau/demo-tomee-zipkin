package com.github.rmannibucau.demo.zipkin.client;

import com.github.kristofa.brave.Brave;
import com.github.kristofa.brave.EmptySpanCollectorMetricsHandler;
import com.github.kristofa.brave.http.DefaultSpanNameProvider;
import com.github.kristofa.brave.http.HttpSpanCollector;
import com.github.kristofa.brave.jaxrs2.BraveClientRequestFilter;
import com.github.kristofa.brave.jaxrs2.BraveClientResponseFilter;
import com.github.rmannibucau.demo.zipkin.common.Rate;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import java.util.Collection;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;

@Path("rates")
@ApplicationScoped
public class RateProxy {
    private Client client;

    @PostConstruct
    private void start() {
        final Brave brave = new Brave.Builder("rate-client")
                .spanCollector(HttpSpanCollector.create(
                        "http://localhost:7070",
                        HttpSpanCollector.Config.builder().flushInterval(1).build(),
                        new EmptySpanCollectorMetricsHandler()))
                .build();
        client = ClientBuilder.newClient()
                .register(new BraveClientRequestFilter(new DefaultSpanNameProvider(), brave.clientRequestInterceptor()))
                .register(new BraveClientResponseFilter(brave.clientResponseInterceptor()));
    }

    @GET
    @Produces(APPLICATION_JSON)
    public Collection<Rate> findAll() {
        return client.target("http://localhost:9090")
                .path("api/financial/rates")
                .request(APPLICATION_JSON_TYPE)
                .get(new GenericType<Collection<Rate>>() {
                });
    }

    @PreDestroy
    private void stop() {
        client.close();
    }
}
