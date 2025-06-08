package dados;


public class Ator {
    private String nome;
    private long id;
    private int dataNasc;
    private String sexo;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public void setDataNasc(int dataNasc) {
        this.dataNasc = dataNasc;
    }

    public int getDataNasc() {
        return this.dataNasc;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSexo() {
        return this.sexo;
    }

    @Override
    public String toString() {
        return "\nNome: " + this.nome +
               "\nData de nascimento: " + this.dataNasc +
               "\nId: " + this.id +
               "\nSexo: " + this.sexo;
    }
}
