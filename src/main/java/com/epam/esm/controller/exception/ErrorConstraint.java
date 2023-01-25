package com.epam.esm.controller.exception;

public class ErrorConstraint {

    public static final String INCORRECT_PARAMETER = "exception.incorrect.parameter";

    public static final String RESOURCE_NOT_FOUND = "exception.entity.not.found";

    public static final String CONFLICT = "exception.duplicate.entity";

    private ErrorConstraint() {
    }
}