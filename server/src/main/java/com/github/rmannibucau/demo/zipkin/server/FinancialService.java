package com.github.rmannibucau.demo.zipkin.server;

import com.github.rmannibucau.demo.zipkin.common.Rate;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Collection;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("financial")
@ApplicationScoped
public class FinancialService {
    @GET
    @Path("rates")
    @Produces(APPLICATION_JSON)
    public Collection<Rate> findRates() {
        return Stream.of(
                new Rate("USD", "EUR", 0.99),
                new Rate("PLN", "EUR", 3)
        ).collect(toList());
    }
}
