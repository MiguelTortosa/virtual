package com.miguelet.miguelet.core.exceptions.specific_exception;

import com.miguelet.miguelet.core.exceptions.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Value
public class BadRequestException  extends GenericException {

  public BadRequestException(@NonNull String textDescription) {
    super(textDescription);
  }
}
