package dados;

// Digrafo.java
public class Digrafo extends GrafoBase {
    @Override
    public void adicionarAresta(int origem, int destino) {
        matrizAdjacencia[origem][destino] = 1; // Digrafo: só um sentido
    }
}

