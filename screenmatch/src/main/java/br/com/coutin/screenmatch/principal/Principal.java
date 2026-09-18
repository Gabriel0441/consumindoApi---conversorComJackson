package br.com.coutin.screenmatch.principal;

import br.com.coutin.screenmatch.model.DadosSerie;
import br.com.coutin.screenmatch.model.DadosTemporada;
import br.com.coutin.screenmatch.service.ConsumoApi;
import br.com.coutin.screenmatch.service.converteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner sc = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=161602d9";
    private converteDados conversor = new converteDados();

    public void exibeMenu(){
        System.out.println("Digite o nome da serie para busca");
        var nomeSerie =  sc.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") +"&season=" + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);

        List<DadosTemporada> temporadas = new ArrayList<>();

		for (int i = 1; i <= dados.totalTemporadas(); i++){
			json = consumo.obterDados("https://www.omdbapi.com/?i=gilmore+girls&season=" + i + "&apikey=161602d9");
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);

		}
		temporadas.forEach(System.out::println);

    }
}
