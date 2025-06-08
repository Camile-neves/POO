package dados;

public class Main {
    public static void main(String[] args) {
        SistemaProcessos sistema = new SistemaProcessos();

        // Cria 3 juízes com capacidade de 5 processos cada
        sistema.adicionarPilha(new Pilha(5));
        sistema.adicionarPilha(new Pilha(5));
        sistema.adicionarPilha(new Pilha(5));

        // Cria e cadastra 18 processos
        for (int i = 1; i <= 18; i++) {
            try {
                Processo processo = new Processo("Processo_" + i);
                sistema.adicionarProcesso(processo);
                System.out.println("Cadastrado: " + processo.getNome());
            } catch (ProcessoInvalidoException e) {
                System.out.println("Erro ao cadastrar processo: " + e.getMessage());
            }
        }

        // Distribui os processos entre as pilhas
        try {
            sistema.distribuirProcessos();
            System.out.println("Distribuição de processos concluída com sucesso.");
        } catch (PilhaVaziaException | PilhaCheiaException e) {
            System.out.println("Erro ao distribuir processos: " + e.getMessage());
        }
    }
}
