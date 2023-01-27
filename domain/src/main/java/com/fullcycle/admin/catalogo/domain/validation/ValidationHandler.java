package com.fullcycle.admin.catalogo.domain.validation;

import java.util.List;

public interface ValidationHandler {
  ValidationHandler append(Error aError);
  ValidationHandler append(ValidationHandler aHandler);
  ValidationHandler validate(Validation aValidation);
  List<Error> getErrors();

  default boolean hasError() {
    return getErrors() != null && !(getErrors().size() == 0);
  }

  interface Validation {
    void validate();
  }
}
