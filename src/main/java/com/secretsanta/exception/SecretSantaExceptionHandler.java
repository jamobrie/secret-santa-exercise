package com.secretsanta.exception;

import com.secretsanta.model.v1.Descriptor;
import com.secretsanta.model.v1.Descriptors;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@AllArgsConstructor
public class SecretSantaExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecretSantaExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(InvalidTeammateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Descriptors handleInvalidTeammateException(InvalidTeammateException ex){
        LOGGER.error("Error occurred due to bad data in relation to request body");

        Descriptor descriptor = new Descriptor();
        descriptor.setCode("BAD DATA ERROR");
        descriptor.setMessage(ex.getMessage());
        descriptor.setHelpUrl("www.siteToVisitForMoreInfo");
        descriptor.setTraceId("");

        Descriptors descriptors = new Descriptors();
        descriptors.add(descriptor);

        return descriptors;
    }

    @ResponseBody
    @ExceptionHandler(InvalidAuthorization.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Descriptors handleInvalidTeammateException(InvalidAuthorization ex){
        LOGGER.error("Error occurred during authorization of request");

        Descriptor descriptor = new Descriptor();
        descriptor.setCode("Authorization ERROR");
        descriptor.setMessage(ex.getMessage());
        descriptor.setHelpUrl("www.siteToVisitForMoreInfo");
        descriptor.setTraceId("");

        Descriptors descriptors = new Descriptors();
        descriptors.add(descriptor);

        return descriptors;
    }

    @ResponseBody
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Descriptors handleDefaultException(Exception ex){
        LOGGER.error("Error occurred during SecretSanta process");

        Descriptor descriptor = new Descriptor();
        descriptor.setCode("SECRET SANATA SERVICE ERROR");
        descriptor.setMessage(ex.getMessage());
        descriptor.setHelpUrl("www.siteToVisitForMoreInfo");
        descriptor.setTraceId("");

        Descriptors descriptors = new Descriptors();
        descriptors.add(descriptor);

        return descriptors;
    }

}
