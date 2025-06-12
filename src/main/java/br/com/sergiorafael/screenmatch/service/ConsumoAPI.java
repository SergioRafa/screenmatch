package br.com.sergiorafael.screenmatch.service;

import br.com.sergiorafael.screenmatch.exception.ApiCommunicationException; // <-- ESTE IMPORT DEVE ESTAR AQUI

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {
    public String obterDados(String endereco) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            // Aqui, você USA o nome SIMPLES da classe, pois ela foi importada acima
            throw new ApiCommunicationException("Erro ao comunicar com a API no endereço: " + endereco, e);
        }

        // Verifica o código de status HTTP
        if (response.statusCode() != 200) {
            throw new ApiCommunicationException("Falha na requisição para " + endereco +
                    ". Código de status: " + response.statusCode() +
                    " - Resposta: " + response.body());
        }

        String json = response.body();
        return json;
    }
}