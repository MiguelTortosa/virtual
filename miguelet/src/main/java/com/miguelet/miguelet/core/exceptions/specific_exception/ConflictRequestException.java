package com.miguelet.miguelet.core.exceptions.specific_exception;

import com.miguelet.miguelet.core.exceptions.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Value
public class ConflictRequestException extends GenericException {

  private static final long serialVersionUID = 810157209575535193L;

  public ConflictRequestException(@NonNull String textDescription) {
    super(textDescription);
  }
}
