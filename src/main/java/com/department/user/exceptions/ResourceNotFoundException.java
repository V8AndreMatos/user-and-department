package com.department.user.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(Object id) {

        super(" Id numero " +id+ " não encontrado");

    }
}
