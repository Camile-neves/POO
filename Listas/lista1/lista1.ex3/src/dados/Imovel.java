package dados;
public class Imovel{
    private double largura;
    private double comprimento;
    private double preco;
    
    public Imovel(double largura, double comprimento, double preco) {
        this.largura = largura;
        this.comprimento = comprimento;
        this.preco = preco;
    }
    
    public double calcularArea() {
        return largura * comprimento;
    }
    
    public double getPreco() {
        return preco;
    }

    public String toString() {
        return "Imovel [Largura: " + largura + ", Comprimento: " + comprimento + ", Preço: R$ " + preco + ", Área: " + calcularArea() + " m²]";
    }
}