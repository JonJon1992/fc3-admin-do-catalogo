package com.fullcycle.admin.catalogo.domain.validation.handler;

import java.util.List;
import java.util.ArrayList;

import com.fullcycle.admin.catalogo.domain.exceptions.DomainException;
import com.fullcycle.admin.catalogo.domain.validation.Error;
import com.fullcycle.admin.catalogo.domain.validation.ValidationHandler;

public class Notification implements ValidationHandler {
    private List<Error> errors;

    private Notification(final List<Error> errors) {
        this.errors = errors;
    }

    public static Notification create() {
        return new Notification(new ArrayList<>());
    }

    public static Notification create(final Error aError) {
        return new Notification(new ArrayList<>()).append(aError);
    }

    @Override
    public Notification append(final Error aError) {

        this.errors.add(aError);
        return this;
    }

    @Override
    public Notification append(ValidationHandler aHandler) {
        this.errors.addAll(aHandler.getErrors());
        return this;
    }

    @Override
    public <T> T validate(final Validation<T> aValidation) {
        try {
            return aValidation.validate();

        } catch (final DomainException exception) {
            this.errors.addAll(exception.getErrors());
        } catch (final Throwable t) {
            this.errors.add(new Error(t.getMessage()));
        }
        return null;
    }

    @Override
    public List<Error> getErrors() {

        return this.errors;
    }

}
