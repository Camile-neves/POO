package dados;
public class Veiculo{
    private String modelo;
    private String placa;
    private double precoPorDia;
    //construtor com parâmetros
    public Veiculo(String modelo, String placa, double precoPorDia) {
        this.modelo = modelo;
        this.placa = placa;
        this.precoPorDia = precoPorDia;
    }

    public Veiculo() {}//construtor

    public String getModelo() { 
        return modelo; 
    }
    public void setModelo(String modelo) { 
        this.modelo = modelo; 
    }

    public String getPlaca() { 
        return placa; 
    }
    public void setPlaca(String placa) { 
        this.placa = placa; 
    }

    public double getPrecoPorDia() { 
        return precoPorDia; 
    }
    public void setPrecoPorDia(double precoPorDia) { 
        this.precoPorDia = precoPorDia; 
    }
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Veiculo veiculo = (Veiculo) obj;
        return placa.equals(veiculo.placa);
    }
    public String toString() {
        return "Veiculo: " + modelo + " (Placa: " + placa + ", Preco/Dia: " + precoPorDia + ")";
    }
}