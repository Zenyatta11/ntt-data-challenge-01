package com.zenyatta.nttdata.challenge.database.h2.usecase.price.get;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.zenyatta.nttdata.challenge.core.domain.Currency;
import com.zenyatta.nttdata.challenge.core.domain.Price;
import com.zenyatta.nttdata.challenge.core.usecase.price.get.NotFoundException;
import com.zenyatta.nttdata.challenge.database.h2.domain.PriceEntity;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetPriceAdapterTest {

    @Mock
    private GetPriceRepository getPriceRepository;

    @InjectMocks
    private GetPriceAdapter getPriceAdapter;

    @Test
    public void testGetPrice_Success() {
        Long productId = 123L;
        Long brandId = 456L;
        LocalDateTime date = LocalDateTime.now();

        PriceEntity priceEntity = new PriceEntity(
                null,
                brandId,
                date,
                date,
                null,
                productId,
                null,
                12.34d,
                Currency.EUR);

        Price expectedPrice = new Price(
                null,
                brandId,
                date,
                date,
                null,
                productId,
                null,
                12.34d,
                Currency.EUR);

        when(getPriceRepository.findHighestPriorityPrice(brandId, productId, date))
                .thenReturn(Optional.of(priceEntity));

        Price actualPrice = getPriceAdapter.getPrice(brandId, productId, date);

        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void testGetPrice_NotFound() {
        Long productId = 123L;
        Long brandId = 456L;
        LocalDateTime date = LocalDateTime.now();

        when(getPriceRepository.findHighestPriorityPrice(anyLong(), anyLong(), any())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> getPriceAdapter.getPrice(brandId, productId, date));
    }
}
