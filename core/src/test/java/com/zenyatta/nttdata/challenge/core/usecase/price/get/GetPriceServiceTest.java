package com.zenyatta.nttdata.challenge.core.usecase.price.get;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.zenyatta.nttdata.challenge.core.domain.Currency;
import com.zenyatta.nttdata.challenge.core.domain.Price;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetPriceServiceTest {

    @Mock
    private GetPricePort getPricePort;

    @InjectMocks
    private GetPriceService getPriceService;

    @Test
    public void testGetPrice() {
        Long productId = 123L;
        Long brandId = 456L;
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
                Currency.EUR
        );

        when(getPricePort.getPrice(brandId, productId, date)).thenReturn(mockPrice);

        Price actualPrice = getPriceService.getPrice(brandId, productId, date);

        assertEquals(mockPrice, actualPrice);
    }

    @Test
    public void testGetPriceNotFOund() {
        Long productId = 123L;
        Long brandId = 456L;
        LocalDateTime date = LocalDateTime.now();

        when(getPricePort.getPrice(brandId, productId, date)).thenThrow(new NotFoundException("Not found"));

        assertThrows(NotFoundException.class, () -> getPriceService.getPrice(brandId, productId, date));
    }
}
