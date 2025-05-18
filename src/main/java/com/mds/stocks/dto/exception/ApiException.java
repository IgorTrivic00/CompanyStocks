package com.mds.stocks.dto.exception;

import java.time.LocalDateTime;

public record ApiException(String message,
                           String errorCode,
                           int httpStatus,
                           LocalDateTime timestamp) {
}
