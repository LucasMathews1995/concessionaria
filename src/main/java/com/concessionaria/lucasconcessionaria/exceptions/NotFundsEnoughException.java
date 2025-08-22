package com.concessionaria.lucasconcessionaria.exceptions;

public class NotFundsEnoughException extends RuntimeException{
    public NotFundsEnoughException(String message)
    {
        super(message);
    }

}
