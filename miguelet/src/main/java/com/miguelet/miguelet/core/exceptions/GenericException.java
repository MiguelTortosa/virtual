package com.miguelet.miguelet.core.exceptions;

import lombok.*;

/** @Author miguelet (migueltortosa@flit2go.com) */
@RequiredArgsConstructor
@Data
public class GenericException extends Exception {
  @NonNull
  private final String textDescription;
}
