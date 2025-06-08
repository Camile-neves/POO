package dados;

/// id, nome, data nascimento e senha
public class Usuario {

    private long id;
    private int dataNasc;
    private String senha;
    private String nome;

    public void setNome(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return this.nome;
    }  

    public void setSenha(String senha){
        this.senha = senha;
    }
    public String getSenha(){
        return this.senha;
    }  

    public void setDataNasc(int dataNasc){
        this.dataNasc = dataNasc;
    }
    public int getDataNasc(){
        return this.dataNasc;
    }  

    public void setId(long id){
        this.id = id;
    }
    public long getId(){
        return this.id;
    }

    @Override
    public String toString(){
        return "\nNome: " + this.nome +
               "\nData de nascimento: " + this.dataNasc +
               "\nID: " + this.id;
    }       
}
