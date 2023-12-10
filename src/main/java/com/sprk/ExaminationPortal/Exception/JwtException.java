package com.sprk.ExaminationPortal.Exception;



public class JwtException extends RuntimeException {

    public JwtException() {
        super();
    }

    public JwtException(String message) {
        super(message);
    }
    
}
