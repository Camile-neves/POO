package dados;

// MDC.java
public class MDC implements IOperacaoInteira {
    @Override
    public int executar(int valor1, int valor2) {
        int a = Math.abs(valor1);
        int b = Math.abs(valor2);

        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
