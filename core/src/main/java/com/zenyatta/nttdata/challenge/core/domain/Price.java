package com.zenyatta.nttdata.challenge.core.domain;

import java.time.LocalDateTime;

public record Price(
        Long entryId,
        Long brandId,
        LocalDateTime dateStart,
        LocalDateTime dateEnd,
        Long priceListId,
        Long productId,
        Integer priority,
        Double price,
        Currency currency) {
}
