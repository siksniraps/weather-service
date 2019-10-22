package lv.id.siksniraps.weatherservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class ExternalServiceNotAvailableAdvice {

  @SuppressWarnings("unused")
  @ResponseBody
  @ExceptionHandler(ExternalServiceUnavailableException.class)
  @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
  String externalServiceUnavailable(ExternalServiceUnavailableException e) {
    return e.getMessage();
  }
}