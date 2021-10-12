package com.example.clients.rest.exceptions;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class ApiErrors {
    @Getter
    private List<String> _erros;

    public ApiErrors(List<String> _erros) {
        this._erros = _erros;
    }

    public ApiErrors(String message){
        this._erros = Arrays.asList(message);
    }
}
