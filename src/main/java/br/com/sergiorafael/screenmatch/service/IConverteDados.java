package br.com.sergiorafael.screenmatch.service;

public interface IConverteDados {
    <T> T obterDados (String json, Class<T> classe);
}
