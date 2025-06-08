package apresentacao;

import java.util.ArrayList;
import java.util.List;

import dados.Pessoa;
import dados.Sorteador;

public class Principal{
    public static void main(String[] args) {
        List<Pessoa> listaPessoas = new ArrayList<>();
        listaPessoas.add(new Pessoa("Ana"));
        listaPessoas.add(new Pessoa("Carlos"));
        listaPessoas.add(new Pessoa("Beatriz"));

        Sorteador sorteador = new Sorteador(listaPessoas);

        System.out.println("Sorteio de Pessoas:");
        for (int i = 0; i < 3; i++) {
            Pessoa sorteada = sorteador.sortearProximo();
            System.out.println("Pessoa sorteada: " + sorteada);
        }
    }
}