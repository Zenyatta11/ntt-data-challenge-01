package com.zenyatta.nttdata.challenge.core.usecase.price.get;

import com.zenyatta.nttdata.challenge.core.domain.Price;
import java.time.LocalDateTime;

@FunctionalInterface
public interface GetPricePort {
    Price getPrice(final Long brandId, final Long productId, final LocalDateTime date);
}
