package org.dbs.biblio.dataprovider.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BusinessObjectAllReadyExist extends RuntimeException {

    public static final String BUSINESS_OBJECT_ALL_READY_EXISTS_WITH_THIS_IDENT_KEY = "Business Object AllReady Exists with this ident key : ";
    private final transient Object businessObject;

    public BusinessObjectAllReadyExist(String message, Object businessObject) {
        super(BUSINESS_OBJECT_ALL_READY_EXISTS_WITH_THIS_IDENT_KEY + message + formatContext(businessObject));
        this.businessObject = businessObject;
        logDebug(message, this.businessObject);
    }

    public BusinessObjectAllReadyExist(String message, Object businessObject, Throwable cause) {
        super(BUSINESS_OBJECT_ALL_READY_EXISTS_WITH_THIS_IDENT_KEY + message + formatContext(businessObject));
        this.businessObject = businessObject;
        logDebug(message, this.businessObject, cause);
    }


    public BusinessObjectAllReadyExist withMessage(String message) {
        return new BusinessObjectAllReadyExist(message, this.businessObject, this);
    }

    public BusinessObjectAllReadyExist withCause(Throwable cause) {
        return new BusinessObjectAllReadyExist(getMessage(), this.businessObject, cause);
    }

    private static String formatContext(Object businessObject) {
        if (log.isDebugEnabled() && businessObject != null) {
            return " [BusinessObject: " + businessObject + "]";
        } else {
            return "";
        }
    }

    private void logDebug(String message, Object businessObject) {
        if (log.isDebugEnabled()) {
            log.debug("{}{}", message, formatContext(businessObject));
        }
    }

    private void logDebug(String message, Object businessObject, Throwable cause) {
        if (log.isDebugEnabled()) {
            log.debug("{}{}", message, formatContext(businessObject), cause);
        }
    }
}
