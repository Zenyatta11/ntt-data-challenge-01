package com.zenyatta.nttdata.challenge.database.h2.usecase.price.query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.zenyatta.nttdata.challenge.core.domain.Currency;
import com.zenyatta.nttdata.challenge.core.domain.Price;
import com.zenyatta.nttdata.challenge.core.usecase.price.query.NotFoundException;
import com.zenyatta.nttdata.challenge.database.h2.domain.PriceEntity;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class QueryPriceAdapterTest {
    private Long productId = 1L;
    private Integer brandId = 2;
    private LocalDateTime date = LocalDateTime.now();

    @Mock
    private QueryPriceRepository getPriceRepository;

    @InjectMocks
    private QueryPriceAdapter getPriceAdapter;

    @Test
    public void testGetPrice_Success() {
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
        when(getPriceRepository.findHighestPriorityPrice(anyInt(), anyLong(), any())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> getPriceAdapter.getPrice(brandId, productId, date));
    }
}
