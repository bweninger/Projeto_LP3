package br.mack.dao.exception;

import org.apache.commons.lang.exception.*;

public class DAORuntimeException extends NestableRuntimeException {

    public DAORuntimeException(final Throwable cause) {
        super(cause);
    }

    public DAORuntimeException(final String msg, final Throwable cause) {
        super(msg, cause);
    }

    public DAORuntimeException(final String msg) {
        super(msg);
    }
}
