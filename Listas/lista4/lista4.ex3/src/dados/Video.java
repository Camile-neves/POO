package dados;

public class Video extends Arquivo {
    private Qualidade qualidade;

    public Video(String nome, Qualidade qualidade) throws NomeInvalidoException {
        super(nome);
        this.qualidade = qualidade;
        this.extensao = ".mp4";
    }

    @Override
    public String toString() {
        return nome + extensao + "\nQualidade: " + qualidade;
    }
}
