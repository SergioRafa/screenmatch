package br.com.sergiorafael.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosSerie {
    @JsonAlias("Title")
    String titulo;
    @JsonAlias("totalSeasons")
    Integer totalTemporadas;
    @JsonAlias("imdbRating")
    String avalia; // <-- Voltou para String

    public String getTitulo() {
        return titulo;
    }

    public Integer getTotalTemporadas() {
        return totalTemporadas;
    }

    public String getAvalia() {
        return avalia;
    }

    // Adicione um método para obter a avaliação como Double, tratando "N/A"
    public Double getAvaliaNumerica() {
        try {
            return Double.valueOf(avalia);
        } catch (NumberFormatException e) {
            return 0.0; // Ou null, dependendo de como você quer tratar N/A
        }
    }

    @Override
    public String toString() {
        return "DadosSerie{" +
                "titulo='" + titulo + '\'' +
                ", totalTemporadas=" + totalTemporadas +
                ", avalia='" + avalia + '\'' + // Continua como String para o toString
                '}';
    }
}