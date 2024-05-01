package com.zenyatta.nttdata.challenge.core.service.price.get;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.zenyatta.nttdata.challenge.core.domain.Currency;
import com.zenyatta.nttdata.challenge.core.domain.Price;
import com.zenyatta.nttdata.challenge.core.service.price.query.QueryPriceService;
import com.zenyatta.nttdata.challenge.core.usecase.price.query.NotFoundException;
import com.zenyatta.nttdata.challenge.core.usecase.price.query.QueryPricePort;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class QueryPriceServiceTest {
    private Long productId = 1L;
    private Integer brandId = 2;
    private LocalDateTime date = LocalDateTime.now();

    @Mock
    private QueryPricePort getPricePort;

    @InjectMocks
    private QueryPriceService getPriceService;

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
                Currency.EUR
        );

        when(getPricePort.getPrice(brandId, productId, date)).thenReturn(mockPrice);

        Price actualPrice = getPriceService.getPrice(brandId, productId, date);

        assertEquals(mockPrice, actualPrice);
    }

    @Test
    public void testGetPriceNotFOund() {
        when(getPricePort.getPrice(brandId, productId, date)).thenThrow(new NotFoundException("Not found"));

        assertThrows(NotFoundException.class, () -> getPriceService.getPrice(brandId, productId, date));
    }
}
