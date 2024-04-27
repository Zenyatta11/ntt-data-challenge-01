package com.zenyatta.nttdata.challenge.database.h2.usecase.price.get;

import com.zenyatta.nttdata.challenge.core.domain.Price;
import com.zenyatta.nttdata.challenge.core.usecase.price.get.GetPricePort;
import com.zenyatta.nttdata.challenge.core.usecase.price.get.NotFoundException;
import com.zenyatta.nttdata.challenge.database.h2.domain.PriceEntity;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GetPriceAdapter implements GetPricePort {
    private final GetPriceRepository getPriceRepository;

    @Autowired
    public GetPriceAdapter(final GetPriceRepository getPriceRepository) {
        this.getPriceRepository = getPriceRepository;
    }

    @Override
    public Price getPrice(final Long brandId, final Long productId, final LocalDateTime date) {
        final PriceEntity priceEntity = getPriceRepository.findHighestPriorityPrice(brandId, productId, date)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Price with product ID {0}, brand ID {1}, and application date {2} not found.",
                                new Object[] { brandId, productId, date })));

        if (log.isInfoEnabled()) {
            log.info(String.format("Retrieved price for product ID {0}, brand ID {1}, and application date {2}: {3}",
                    new Object[] { brandId, productId, date,
                            priceEntity }));
        }

        return new Price(
                priceEntity.getEntryId(),
                priceEntity.getBrandId(),
                priceEntity.getDateStart(),
                priceEntity.getDateEnd(),
                priceEntity.getPriceListId(),
                priceEntity.getProductId(),
                priceEntity.getPriority(),
                priceEntity.getPrice(),
                priceEntity.getCurrency());
    }
}
