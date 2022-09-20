package com.miguelet.miguelet.core.exceptions.specific_exception;

import com.miguelet.miguelet.core.exceptions.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Value
public class NoContentRequestException extends GenericException {
  private static final long serialVersionUID = -3562065104846263240L;

  public NoContentRequestException(@NonNull String textDescription) {
    super(textDescription);
  }
}
