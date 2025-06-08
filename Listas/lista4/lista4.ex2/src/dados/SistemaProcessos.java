package dados;

import java.util.ArrayList;
import java.util.List;

public class SistemaProcessos {
    private List<Pilha> pilhas;

    public SistemaProcessos() {
        pilhas = new ArrayList<>();
    }

    public void adicionarPilha(Pilha pilha) {
        pilhas.add(pilha);
    }

    public void adicionarProcesso(Processo p) throws ProcessoInvalidoException {
        if (p == null || p.getNome() == null || p.getNome().isEmpty()) {
            throw new ProcessoInvalidoException();
        }

        for (Pilha pilha : pilhas) {
            try {
                pilha.inserir(p);
                return;
            } catch (PilhaCheiaException e) {
                // Continua tentando nas próximas pilhas
            }
        }

        throw new ProcessoInvalidoException(); // Nenhuma pilha pôde receber o processo
    }

    public void distribuirProcessos() throws PilhaCheiaException, PilhaVaziaException {
        for (int i = 0; i < pilhas.size() - 1; i++) {
            Pilha origem = pilhas.get(i);
            Pilha destino = pilhas.get(i + 1);

            if (!origem.isVazia()) {
                Processo p = origem.remover();
                destino.inserir(p);
            }
        }
    }
}
