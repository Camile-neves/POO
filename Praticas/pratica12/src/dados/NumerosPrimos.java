package dados;

public class NumerosPrimos extends Gerador {

    @Override
    public void gerar(int n) {
        sequencia.clear();
        int quant = 0;
        int num = 2;
        while (quant < n) {
            if (primo(num)) {
                sequencia.add(num);
                quant++;
            }
            num++;
        }
    }

    private boolean primo(int v) {
        for (int i = 2; i < v; i++) {
            if (v % i == 0) return false;
        }
        return true;
    }
}

