/**
 * Copyright (C) Estafet Ltd
 */
package com.estafet.qapractices.exceptions;

/**
 * Created by Pesho on 15-Sep-17.
 */

/**
 * A custom test exception class for our framework.
 * I encourage you to throw this exception in our code. This will indicate that there is something wrong in
 * out implementation.
 */
public class TestException extends RuntimeException {

    public TestException(final String string) {
        super(string);
    }

    public TestException(final Throwable cause) {
        super(cause);
    }

    public TestException(final String string, final Throwable cause) {
        super(string, cause);
    }
}
