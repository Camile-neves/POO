package apresentacao;

import java.util.List;
import dados.Imobiliaria;
import dados.Imovel;

public class Principal {
    public static void main(String[] args) {
        Imobiliaria imobiliaria = new Imobiliaria("Minha Casa");
        
        imobiliaria.adicionarImovel(new Imovel(5, 10, 200000));
        imobiliaria.adicionarImovel(new Imovel(6, 12, 250000));
        imobiliaria.adicionarImovel(new Imovel(4, 8, 150000));
        
        System.out.println(imobiliaria);
        
        System.out.println("\nImóveis com área maior ou igual a 50m², ordenados por preço:");
        List<Imovel> filtrados = imobiliaria.filtrarPorArea(50);
        for (Imovel imovel : filtrados) {
            System.out.println(imovel);
        }
    }
}
