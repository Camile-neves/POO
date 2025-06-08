package dados;

// Losango.java
public class Losango extends FormaGeometrica {
    public void setDiagonalMaior(double dMaior) {
        this.medida1 = dMaior;
    }

    public void setDiagonalMenor(double dMenor) {
        this.medida2 = dMenor;
    }

    @Override
    public double calcularArea() {
        return (medida1 * medida2) / 2;
    }

    @Override
    public double calcularPerimetro() {
        double lado = Math.sqrt(Math.pow(medida1 / 2, 2) + Math.pow(medida2 / 2, 2));
        return 4 * lado;
    }

    @Override
    public String toString() {
        return "Losango [diagonal maior=" + medida1 + 
               ", diagonal menor=" + medida2 +
               ", área=" + calcularArea() + 
               ", perímetro=" + calcularPerimetro() + "]";
    }
}

