package dados;
// TrianguloEquilatero.java
public class TrianguloEquilatero extends FormaGeometrica {
    public void setLado(double lado) {
        this.medida1 = lado;
    }

    @Override
    public double calcularArea() {
        return (Math.sqrt(3) / 4) * medida1 * medida1;
    }

    @Override
    public double calcularPerimetro() {
        return 3 * medida1;
    }

    @Override
    public String toString() {
        return "Triângulo Equilátero [lado=" + medida1 + 
               ", área=" + calcularArea() + 
               ", perímetro=" + calcularPerimetro() + "]";
    }
}
