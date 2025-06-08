package dados;

// Grafo.java
public class Grafo extends GrafoBase {
    @Override
    public void adicionarAresta(int origem, int destino) {
        matrizAdjacencia[origem][destino] = 1;
        matrizAdjacencia[destino][origem] = 1; // Grafo: matriz simétrica
    }
}
