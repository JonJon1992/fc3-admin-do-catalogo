package com.fullcycle.admin.catalogo.domain.exceptions;

import com.fullcycle.admin.catalogo.domain.validation.Error;

import java.util.List;

public class DomainException extends NoStracktraceException {
    private final List<Error> errors;

    protected DomainException(final String message, final List<Error> aErrors) {
        super(message);
        this.errors = aErrors;
    }

    public static DomainException with(final List<Error> aError) {
        return new DomainException("", aError);
    }

    public List<Error> getErrors() {
        return errors;
    }
}
