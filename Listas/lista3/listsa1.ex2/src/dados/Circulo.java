package dados;

// Circulo.java
public class Circulo extends FormaGeometrica {
    public void setRaio(double raio) {
        this.medida1 = raio;
        this.medida2 = raio * 2;
    }

    @Override
    public double calcularArea() {
        return Math.PI * medida1 * medida1;
    }

    @Override
    public double calcularPerimetro() {
        return 2 * Math.PI * medida1;
    }

    @Override
    public String toString() {
        return "Círculo [raio=" + medida1 + 
               ", diâmetro=" + medida2 +
               ", área=" + calcularArea() + 
               ", perímetro=" + calcularPerimetro() + "]";
    }
}
