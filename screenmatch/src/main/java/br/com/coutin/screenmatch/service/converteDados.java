package br.com.coutin.screenmatch.service;

import tools.jackson.databind.ObjectMapper;

public class converteDados implements IConverteDados {
    private ObjectMapper mapper =  new ObjectMapper();


    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        return mapper.readValue(json, classe);

    }
}
