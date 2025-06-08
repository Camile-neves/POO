package dados;

public class Quadrados extends Gerador {

    @Override
    public void gerar(int n) {
        sequencia.clear();
        for (int i = 0; i < n; i++) {
            sequencia.add(i * i);
        }
    }
}
