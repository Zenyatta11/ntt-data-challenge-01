package com.zenyatta.nttdata.challenge.core.usecase.price.query;

import com.zenyatta.nttdata.challenge.core.domain.Price;
import java.time.LocalDateTime;

@FunctionalInterface
public interface QueryPriceUseCase {
    Price getPrice(final Integer brandId, final Long productId, final LocalDateTime date);
}
