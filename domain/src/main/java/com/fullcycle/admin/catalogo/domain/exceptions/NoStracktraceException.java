package com.fullcycle.admin.catalogo.domain.exceptions;

public class NoStracktraceException extends RuntimeException {
    public NoStracktraceException(final String message) {
        this(message, null);
    }

    public NoStracktraceException(final String message, final Throwable cause) {
        super(message, cause, true, false);
    }
}
