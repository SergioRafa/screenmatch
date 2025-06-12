package br.com.sergiorafael.screenmatch;

import br.com.sergiorafael.screenmatch.model.DadosEpisodio;
import br.com.sergiorafael.screenmatch.model.DadosSerie;
import br.com.sergiorafael.screenmatch.model.DadosTemporada;
import br.com.sergiorafael.screenmatch.service.ConsumoAPI;
import br.com.sergiorafael.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ScreenmatchApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var consumoApi = new ConsumoAPI();
        var conversor = new ConverteDados();
        var apiKey = "9cdafca8"; // Melhorar isso em projetos reais

        // Busca dados da série
        var jsonSerie = consumoApi.obterDados("http://www.omdbapi.com/?t=gilmore+girls&apikey=" + apiKey);
        DadosSerie dadosSerie = conversor.obterDados(jsonSerie, DadosSerie.class);
        System.out.println("Dados da Série: " + dadosSerie);

        // Exemplo de busca de um episódio específico (se necessário)
        var jsonEpisodio = consumoApi.obterDados("https://omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=" + apiKey);
        DadosEpisodio dadosEpisodio = conversor.obterDados(jsonEpisodio, DadosEpisodio.class);
        System.out.println("Dados do Episódio 1x02: " + dadosEpisodio);

        List<DadosTemporada> temporadas = new ArrayList<>();

        // Loop para buscar dados de todas as temporadas
        // Assume que totalTemporadas é um valor confiável da API
        for(int i = 1; i <= dadosSerie.getTotalTemporadas(); i++) {
            try {
                var jsonTemporada = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season=" + i + "&apikey=" + apiKey);
                DadosTemporada dadosTemporada = conversor.obterDados(jsonTemporada, DadosTemporada.class);
                temporadas.add(dadosTemporada);
            } catch (RuntimeException e) {
                System.err.println("Erro ao buscar dados da temporada " + i + ": " + e.getMessage());
                // Continua para a próxima temporada mesmo se houver erro em uma
            }
        }
        System.out.println("\nDados de Todas as Temporadas:");
        temporadas.forEach(System.out::println);
    }
}