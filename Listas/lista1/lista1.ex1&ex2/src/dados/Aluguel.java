package dados;

public class Aluguel {
    private Veiculo veiculo;
    private Cliente cliente;
    private int dias;
    //construtor com parâmetros
    public Aluguel(Veiculo veiculo, Cliente cliente, int dias) {
        this.veiculo = veiculo;
        this.cliente = cliente;
        this.dias = dias;
    }

    public Aluguel() {}//construtor
    //get e set
    public void setVeiculo(Veiculo veiculo){
        this.veiculo=veiculo;
    }
    public Veiculo getVeiculo(){
        return veiculo;
    }


    public void setCliente(Cliente cliente){
        this.cliente=cliente;
    }
    public Cliente getCliente(){
        return cliente;
    }


    public void setDias(int dias){
        this.dias=dias;
    }
    public int getDias(){
        return dias;
    }

    public double calcularTotal() {
        return dias * veiculo.getPrecoPorDia();
    }
   

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Aluguel aluguel = (Aluguel) obj;
        return dias == aluguel.dias && veiculo.equals(aluguel.veiculo) && cliente.equals(aluguel.cliente);
    }
    
    public String toString() {
        return cliente + " alugou " + veiculo + " por " + dias + " dias. Total: " + calcularTotal();
    }
}
