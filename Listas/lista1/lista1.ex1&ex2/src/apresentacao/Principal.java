package apresentacao;

import dados.Agencia;
import dados.Aluguel;
import dados.Cliente;
import dados.Funcionario;
import dados.Veiculo;

public class Principal {
    public static void main(String[] args) {
        Veiculo carro1 = new Veiculo("Toyota Corolla", "ABC-1234", 100.0);
        Veiculo carro2 = new Veiculo("Honda Civic", "XYZ-5678", 120.0);
        
        Cliente cliente1 = new Cliente("João Silva", "123.456.789-00");
        Cliente cliente2 = new Cliente("Maria Souza", "987.654.321-00");
        
        Funcionario funcionario1 = new Funcionario("Carlos Mendes", "Gerente", 5000.0);
        Agencia agencia1 = new Agencia("Locadora XPTO", "Rua das Flores, 123");
        
        Aluguel aluguel1 = new Aluguel(carro1, cliente1, 5);
        Aluguel aluguel2 = new Aluguel(carro2, cliente2, 3);
        
        System.out.println(agencia1);
        System.out.println("\n");
        System.out.println(funcionario1);
        System.out.println("\n");
        System.out.println(aluguel1);
        System.out.println("\n");
        System.out.println(aluguel2);
        System.out.println("\n");
    }
}
