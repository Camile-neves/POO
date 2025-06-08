package dados;

// PrincipalGrafo.java
public class PrincipalGrafo {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();
        grafo.adicionarVertice();
        grafo.adicionarVertice();
        grafo.adicionarVertice();
        grafo.adicionarVertice();

        grafo.adicionarAresta(0, 1);
        grafo.adicionarAresta(1, 2);
        grafo.adicionarAresta(2, 3);

        System.out.println("Grafo:");
        System.out.println(grafo);

        Digrafo digrafo = new Digrafo();
        digrafo.adicionarVertice();
        digrafo.adicionarVertice();
        digrafo.adicionarVertice();

        digrafo.adicionarAresta(0, 1);
        digrafo.adicionarAresta(1, 2);

        System.out.println("Dígrafo:");
        System.out.println(digrafo);
    }
}
