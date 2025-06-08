package dados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Imobiliaria{
    private String nome;
    private List<Imovel> imoveis;
    
    public Imobiliaria(String nome) {
        this.nome = nome;
        this.imoveis = new ArrayList<>();
    }
    
    public void adicionarImovel(Imovel imovel) {
        imoveis.add(imovel);
    }
    
    public List<Imovel> filtrarPorArea(double x) {
        List<Imovel> filtrados = new ArrayList<>();
        for (Imovel imovel : imoveis) {
            if (imovel.calcularArea() >= x) {
                filtrados.add(imovel);
            }
        }
        filtrados.sort(Comparator.comparingDouble(Imovel::getPreco));
        return filtrados;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Imobiliária " + nome + " - Lista de Imóveis:\n");
        for (Imovel imovel : imoveis) {
            sb.append(imovel.toString()).append("\n");
        }
        return sb.toString();
    }
}