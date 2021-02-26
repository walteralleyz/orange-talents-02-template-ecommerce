package br.com.zup.MercadoLivre.validation;

import br.com.zup.MercadoLivre.exception.CategoryNotFoundException;
import br.com.zup.MercadoLivre.exception.GenericException;
import br.com.zup.MercadoLivre.exception.NotTheSameOwnerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ValidationHandler {

    private final MessageSource ms;

    @Autowired
    public ValidationHandler(MessageSource ms) {
        this.ms = ms;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(GenericException.class)
    public ValidationSimpleMessage handleCategoryException(GenericException e) {
        return new ValidationSimpleMessage(
            e.getField(),
            e.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public List<ValidationSimpleMessage> handleBindException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();

        return bindingResult.getFieldErrors().stream().map(error ->
            new ValidationSimpleMessage(error.getField(), getMessage(error))
        ).collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ValidationSimpleMessage> handleMethodException(MethodArgumentNotValidException e) {
        return handleBindException(e);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(NotTheSameOwnerException.class)
    public ValidationSimpleMessage handleNotOwnerException(NotTheSameOwnerException e) {
        return new ValidationSimpleMessage(
            e.getField(),
            e.getMessage()
        );
    }

    public String getMessage(ObjectError e) {
        return ms.getMessage(e, LocaleContextHolder.getLocale());
    }
}
