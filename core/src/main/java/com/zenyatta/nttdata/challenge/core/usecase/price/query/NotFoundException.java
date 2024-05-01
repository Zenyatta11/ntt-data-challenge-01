package com.zenyatta.nttdata.challenge.core.usecase.price.query;

import java.io.Serial;

public class NotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 8045915852147114261L;

    public NotFoundException(final String message) {
        super(message);
    }
}
