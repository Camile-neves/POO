package dados;

import java.util.ArrayList;
import java.util.List;

public class Pilha {
    private int limite;
    private List<Processo> processos;

    public Pilha(int limite) {
        this.limite = limite;
        this.processos = new ArrayList<>();
    }

    public void inserir(Processo p) throws PilhaCheiaException {
        if (processos.size() >= limite) {
            throw new PilhaCheiaException();
        }
        processos.add(p);
    }

    public Processo remover() throws PilhaVaziaException {
        if (processos.isEmpty()) {
            throw new PilhaVaziaException();
        }
        return processos.remove(processos.size() - 1);
    }

    public boolean isVazia() {
        return processos.isEmpty();
    }
}
