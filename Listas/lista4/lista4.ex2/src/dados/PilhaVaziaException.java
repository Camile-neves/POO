package dados;

public class PilhaVaziaException extends Exception {
    public PilhaVaziaException() {
        super("Pilha está vazia");
    }
}
