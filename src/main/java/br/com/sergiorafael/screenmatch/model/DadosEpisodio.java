package br.com.sergiorafael.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodio(@JsonAlias("Title") String titulo,
                            @JsonAlias("Episode")Integer numero,
                            @JsonAlias("imdbRating") String avaliacao, // <-- Voltou para String
                            @JsonAlias("Released")String anoDeLancamento) {

    // Adicione um método para obter a avaliação como Double, tratando "N/A"
    public Double getAvaliacaoNumerica() {
        try {
            return Double.valueOf(avaliacao);
        } catch (NumberFormatException e) {
            return 0.0; // Ou null
        }
    }
}