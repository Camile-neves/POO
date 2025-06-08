package dados;

public class Musica extends Arquivo {
    private int duracao; // em segundos

    public Musica(String nome, int duracao) throws NomeInvalidoException {
        super(nome);
        this.duracao = duracao;
        this.extensao = ".mp3";
    }

    @Override
    public String toString() {
        return nome + extensao + "\nDuração: " + duracao;
    }
}
