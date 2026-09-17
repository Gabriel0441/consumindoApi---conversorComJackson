package br.com.coutin.screenmatch;

import br.com.coutin.screenmatch.model.DadosEpisodio;
import br.com.coutin.screenmatch.model.DadosSerie;
import br.com.coutin.screenmatch.model.DadosTemporada;
import br.com.coutin.screenmatch.service.ConsumoApi;
import br.com.coutin.screenmatch.service.converteDados;
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
		var consumoApi = new ConsumoApi();
		var json = consumoApi.obterDados("https://www.omdbapi.com/?i=gilmore+girls&Season=1&apikey=161602d9");
		System.out.println(json);
        converteDados conversor = new converteDados();
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
		System.out.println(dados);

		json = consumoApi.obterDados("https://www.omdbapi.com/?i=gilmore+girls&season=1&episode=2&apikey=161602d9");
		DadosEpisodio dadosEpisodio = conversor.obterDados(json, DadosEpisodio.class);
		System.out.println(dadosEpisodio);

        List<DadosTemporada> temporadas = new ArrayList<>();

		for (int i = 1; i <= dados.totalTemporadas(); i++){
			json = consumoApi.obterDados("https://www.omdbapi.com/?i=gilmore+girls&season=" + i + "&apikey=161602d9");
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);

		}
		temporadas.forEach(System.out::println);
	}
}
