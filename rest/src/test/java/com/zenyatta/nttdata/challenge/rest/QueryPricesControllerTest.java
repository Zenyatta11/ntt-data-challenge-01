package com.zenyatta.nttdata.challenge.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.zenyatta.nttdata.challenge.core.domain.Currency;
import com.zenyatta.nttdata.challenge.core.domain.Price;
import com.zenyatta.nttdata.challenge.core.usecase.price.query.NotFoundException;
import com.zenyatta.nttdata.challenge.core.usecase.price.query.QueryPriceUseCase;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class QueryPricesControllerTest {
    private Long productId = 1L;
    private Integer brandId = 2;
    private LocalDateTime date = LocalDateTime.now();

    @Mock
    private QueryPriceUseCase getPriceUseCase;

    @InjectMocks
    private QueryPricesController queryController;

    @Test
    public void testGetPrice() {
        Price mockPrice = new Price(
                null,
                brandId,
                date,
                date,
                null,
                productId,
                null,
                12.34d,
                Currency.EUR);

        when(getPriceUseCase.getPrice(brandId, productId, date)).thenReturn(mockPrice);

        ResponseEntity<Price> responseEntity = queryController.getPrice(brandId, productId, date);

        verify(getPriceUseCase, times(1)).getPrice(brandId, productId, date);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockPrice, responseEntity.getBody());
    }

    @Test
    public void testGetPriceNotFOund() {
        when(getPriceUseCase.getPrice(brandId, productId, date))
                .thenThrow(new NotFoundException("Test price is not found"));

        assertThrows(NotFoundException.class, () -> queryController.getPrice(brandId, productId, date));
    }

    @Test
    public void testHealthCheck() {
        ResponseEntity<String> responseEntity = queryController.healthCheck();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Alive!", responseEntity.getBody());
    }
}
