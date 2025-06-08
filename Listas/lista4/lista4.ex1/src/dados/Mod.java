package dados;

// Mod.java
public class Mod implements IOperacaoInteira {
    @Override
    public int executar(int valor1, int valor2) {
        int quociente = valor1 / valor2;
        int produto = quociente * valor2;
        return valor1 - produto;
    }
}
