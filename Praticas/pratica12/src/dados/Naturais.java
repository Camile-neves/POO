package dados;

public class Naturais extends Gerador {

    @Override
    public void gerar(int n) {
        sequencia.clear();
        for (int i = 1; i <= n; i++) {
            sequencia.add(i);
        }
    }
}

