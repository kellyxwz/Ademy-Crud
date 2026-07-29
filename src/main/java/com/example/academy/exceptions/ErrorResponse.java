package com.example.academy.exceptions;

import java.time.Instant;

public record ErrorResponse(
        Instant timeStamp,
        String error,
        Integer status,
        String path
) {
}
