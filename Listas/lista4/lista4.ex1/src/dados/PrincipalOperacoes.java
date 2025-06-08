package dados;

// PrincipalOperacoes.java
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PrincipalOperacoes {
    public static void main(String[] args) {
        Random random = new Random();
        List<IOperacaoInteira> operacoes = new ArrayList<>();
        
        operacoes.add(new Soma());
        operacoes.add(new Multiplicacao());
        operacoes.add(new MDC());
        operacoes.add(new Mod());

        for (IOperacaoInteira op : operacoes) {
            int v1 = random.nextInt(100) + 1; // 1 a 100
            int v2 = random.nextInt(99) + 1;  // 1 a 99 (evitar divisão por zero)
            System.out.println(op.getClass().getSimpleName() + " de " + v1 + " e " + v2 + " = " + op.executar(v1, v2));
        }
    }
}

