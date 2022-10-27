package service.validation;

import service.exception.ServiceException;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ServiceValidationException extends ServiceException {

    public ServiceValidationException(String message) {
        super(message);
    }

    public ServiceValidationException(Exception e) {
        super(e);
    }

    public ServiceValidationException(String message, Exception e) {
        super(message, e);
    }
//    public service.validation.ServiceValidationException(String message, Map<service.validation.ParameterName, service.validation.ErrorKey> errors) {
//        System.out.println(message);
//        for (Map.Entry <service.validation.ParameterName, service.validation.ErrorKey> entry : errors.entrySet()) {
//            service.validation.ParameterName parameterName = entry.getKey();
//            service.validation.ErrorKey errorKey = entry.getValue();
//            System.out.printf();
//        }
//
//    }

    public ServiceValidationException(String message, Map<ParameterName, ErrorKey> errors) {
        System.out.println(message);
        Set<ParameterName> parameterNameSet = errors.keySet();
        for (Iterator<ParameterName> parameterNameIterator= parameterNameSet.iterator(); parameterNameIterator.hasNext();) {
            ParameterName key = parameterNameIterator.next();
            System.out.println(errors.get(key));
        }
    }

    public ServiceValidationException() {

    }
}
