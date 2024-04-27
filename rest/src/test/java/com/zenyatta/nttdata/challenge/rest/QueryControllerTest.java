package com.zenyatta.nttdata.challenge.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.zenyatta.nttdata.challenge.core.domain.Currency;
import com.zenyatta.nttdata.challenge.core.domain.Price;
import com.zenyatta.nttdata.challenge.core.usecase.price.get.GetPriceUseCase;
import com.zenyatta.nttdata.challenge.core.usecase.price.get.NotFoundException;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class QueryControllerTest {

    @Mock
    private GetPriceUseCase getPriceUseCase;

    @InjectMocks
    private QueryController queryController;

    @Test
    public void testGetPrice() {
        Long productId = 1L;
        Long brandId = 2L;
        LocalDateTime date = LocalDateTime.now();

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
        Long productId = 1L;
        Long brandId = 2L;
        LocalDateTime date = LocalDateTime.now();

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
