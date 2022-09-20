package com.miguelet.miguelet.core.exceptions.specific_exception;

import com.miguelet.miguelet.core.exceptions.*;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Value
public class UnauthorizedException extends GenericException {
  private static final long serialVersionUID = -3094643482577564466L;
  public UnauthorizedException(@NonNull String textDescription) {
    super(textDescription);
  }
}
