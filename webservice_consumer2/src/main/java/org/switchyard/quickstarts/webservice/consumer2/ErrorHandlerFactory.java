package org.switchyard.quickstarts.webservice.consumer2;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.apache.camel.builder.ErrorHandlerBuilder;
import org.apache.camel.builder.LoggingErrorHandlerBuilder;

public class ErrorHandlerFactory {
    
    @Produces
    @Named("loggingErrorHandler")
    public ErrorHandlerBuilder createErrorHandler() {
        return new LoggingErrorHandlerBuilder();
    }
    
}
