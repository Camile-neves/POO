package dados;
public class Agencia {
    private String nome;
    private String endereco;
    //construtor com parâmetrosv
    public Agencia(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }

    public Agencia() {}//construtor
    //get e set
    public String getNome() {
        return nome; 
    }
    public void setNome(String nome) {
        this.nome = nome; 
    }

    public String getEndereco() {
        return endereco; 
    }
    public void setEndereco(String endereco) { 
        this.endereco = endereco; 
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Agencia agencia = (Agencia) obj;
        return nome.equals(agencia.nome) && endereco.equals(agencia.endereco);
    }

    public String toString() {
        return "Agencia: " + nome + " (Endereco: " + endereco + ")";
    }
}
