package dados;

public class Main {
    public static void main(String[] args) {
        SistemaArquivos sistema = new SistemaArquivos();

        try {
            Documento doc1 = new Documento("Lista de compras", "Lista contendo itens do mercado");
            Documento doc2 = new Documento("resumo.txt", "Resumo das aulas de POO");
            Musica musica1 = new Musica("Imagine Dragons - Radioactive", 276);
            Video video1 = new Video("HMMV S01E01", Qualidade._1080p);
            Documento doc3 = new Documento("notaSOO.txt", "Notas da disciplina de SOO");

            sistema.adicionarArquivo("Downloads", musica1);
            sistema.adicionarArquivo("Downloads", video1);
            sistema.adicionarArquivo("Downloads", doc1);

            sistema.adicionarArquivo("Área de Trabalho", doc2);
            sistema.adicionarArquivo("Área de Trabalho", doc3);

        } catch (NomeInvalidoException e) {
            System.out.println("Erro ao criar/adicionar arquivo: " + e.getMessage());
        }

        System.out.println(sistema.toString());
    }
}
