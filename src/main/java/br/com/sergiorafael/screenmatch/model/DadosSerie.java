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
    String avalia;

    public String getTitulo() {
        return titulo;
    }

    public Integer getTotalTemporadas() {
        return totalTemporadas;
    }

    public String getAvalia() {
        return avalia;
    }

    @Override
    public String toString() {
        return "DadosSerie{" +
                "titulo='" + titulo + '\'' +
                ", totalTemporadas=" + totalTemporadas +
                ", avalia='" + avalia + '\'' +
                '}';
    }
}
