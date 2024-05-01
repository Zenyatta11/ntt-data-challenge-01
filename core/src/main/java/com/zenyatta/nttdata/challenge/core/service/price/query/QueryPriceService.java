package com.zenyatta.nttdata.challenge.core.service.price.query;

import com.zenyatta.nttdata.challenge.core.domain.Price;
import com.zenyatta.nttdata.challenge.core.usecase.price.query.QueryPricePort;
import com.zenyatta.nttdata.challenge.core.usecase.price.query.QueryPriceUseCase;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryPriceService implements QueryPriceUseCase {
    private final QueryPricePort getPricePort;

    @Autowired
    public QueryPriceService(final QueryPricePort getPricePort) {
        this.getPricePort = getPricePort;
    }

    @Override
    public Price getPrice(final Integer brandId, final Long productId, final LocalDateTime date) {
        return getPricePort.getPrice(brandId, productId, date);
    }
}
