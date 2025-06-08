package dados;
public class Funcionario {
    private String nome;
    private String cargo;
    private double salario;
    //construtor com parâmetros
    public Funcionario(String nome, String cargo, double salario) {
        this.nome = nome;
        this.cargo = cargo;
        this.salario = salario;
    }

    public Funcionario() {}//construtor

    public String getNome() {
        return nome; 
    }
    public void setNome(String nome) {
        this.nome = nome; 
    }

    public String getCargo() {
        return cargo; 
    }
    public void setCargo(String cargo) { 
        this.cargo = cargo; 
    }

    public double getSalario() { 
        return salario; 
    }
    public void setSalario(double salario) { 
        this.salario = salario; 
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Funcionario funcionario = (Funcionario) obj;
        return nome.equals(funcionario.nome) && cargo.equals(funcionario.cargo);
    }

    public String toString() {
        return "Funcionario: " + nome + " (Cargo: " + cargo + ", Salario: " + salario + ")";
    }
}
