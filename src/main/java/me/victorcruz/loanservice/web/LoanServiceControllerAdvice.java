package me.victorcruz.loanservice.web;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import me.victorcruz.loanservice.web.responses.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import me.victorcruz.loanservice.domain.exceptions.LoanNotFoundException;


/**
 * Loan Service controller advice
 *
 * Central class for handling Domain Exceptions
 */
@ControllerAdvice
public class LoanServiceControllerAdvice {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = {LoanNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleLoanNotFound(LoanNotFoundException ex) {
        ErrorResponse response = new ErrorResponse("Not Found", HttpStatus.NOT_FOUND.value());

        this.logger.info(ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handleLoanNotFound(MethodArgumentNotValidException ex) {
        List<String> validationErrors = this.parseValidationErrorsToList(ex);

        ErrorResponse response = new ErrorResponse(
                "Unprocessable Entity",
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                validationErrors
        );

        this.logger.info(ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private List<String> parseValidationErrorsToList(MethodArgumentNotValidException validException) {
        return validException.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::buildValidationError)
                .collect(Collectors.toList());
    }

    private String buildValidationError(FieldError fieldError) {
        return String.format("Attribute `%s`, %s", fieldError.getField(), fieldError.getDefaultMessage());
    }
}
