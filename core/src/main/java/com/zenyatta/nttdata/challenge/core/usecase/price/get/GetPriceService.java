package com.zenyatta.nttdata.challenge.core.usecase.price.get;

import com.zenyatta.nttdata.challenge.core.domain.Price;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetPriceService implements GetPriceUseCase {
    private final GetPricePort getPricePort;

    @Autowired
    public GetPriceService(final GetPricePort getPricePort) {
        this.getPricePort = getPricePort;
    }

    @Override
    public Price getPrice(final Long brandId, final Long productId, final LocalDateTime date) {
        return getPricePort.getPrice(brandId, productId, date);
    }
}
