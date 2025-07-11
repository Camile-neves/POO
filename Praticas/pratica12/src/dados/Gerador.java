package dados;

import java.util.List;
import java.util.LinkedList;

public abstract class Gerador {

    protected List<Integer> sequencia = new LinkedList<>();

    public abstract void gerar(int n);

    public List<Integer> getSequencia() {
        return this.sequencia;
    }
}

