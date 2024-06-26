package com.zenyatta.nttdata.challenge.rest;

import static org.springframework.http.HttpStatus.OK;

import com.zenyatta.nttdata.challenge.core.domain.Price;
import com.zenyatta.nttdata.challenge.core.usecase.price.query.QueryPriceUseCase;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@ApiController
public class QueryPricesController {
    private final QueryPriceUseCase getPriceUseCase;

    @Autowired
    public QueryPricesController(final QueryPriceUseCase getPriceUseCase) {
        this.getPriceUseCase = getPriceUseCase;
    }

    @GetMapping("/get/{brandId}/{productId}/{date}")
    public ResponseEntity<Price> getPrice(
            @PathVariable("brandId") final Integer brandId, 
            @PathVariable("productId") final Long productId,
            @PathVariable("date") final LocalDateTime date
    ) {
        log.info("Request: /api/get/{}/{}/{}", brandId, productId, date);
        return new ResponseEntity<>(getPriceUseCase.getPrice(brandId, productId, date), OK);
    }

    @GetMapping("/healthZ")
    public ResponseEntity<String> healthCheck() {
        return new ResponseEntity<>("Alive!", OK);
    }
}
