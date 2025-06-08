package dados;

// Multiplicacao.java
public class Multiplicacao implements IOperacaoInteira {
    @Override
    public int executar(int valor1, int valor2) {
        int resultado = 0;
        for (int i = 0; i < Math.abs(valor2); i++) {
            resultado += valor1;
        }
        if (valor2 < 0) {
            resultado = -resultado;
        }
        return resultado;
    }
}
