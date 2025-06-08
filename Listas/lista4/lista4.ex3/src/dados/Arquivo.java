package dados;

public abstract class Arquivo {
    protected String nome;
    protected String extensao;

    public Arquivo(String nome) throws NomeInvalidoException {
        validarNome(nome);
        this.nome = nome;
    }

    private void validarNome(String nome) throws NomeInvalidoException {
        if (nome.contains("\n") || nome.contains("[") || nome.contains("]") ||
            nome.contains("(") || nome.contains(")") ||
            nome.contains("\"") || nome.contains("'") ||
            nome.length() < 10 || nome.length() > 256) {
            throw new NomeInvalidoException("Nome de arquivo inválido: " + nome);
        }
    }

    public abstract String toString();
}
