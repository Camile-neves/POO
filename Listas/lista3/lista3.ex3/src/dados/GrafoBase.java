package dados;
// GrafoBase.java
public abstract class GrafoBase {
    protected int[][] matrizAdjacencia;

    public GrafoBase() {
        matrizAdjacencia = new int[0][0];
    }

    public void adicionarVertice() {
        int tamanho = matrizAdjacencia.length;
        int[][] novaMatriz = new int[tamanho + 1][tamanho + 1];

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                novaMatriz[i][j] = matrizAdjacencia[i][j];
            }
        }

        matrizAdjacencia = novaMatriz;
    }

    public abstract void adicionarAresta(int origem, int destino);

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Matriz de Adjacência:\n");
        for (int i = 0; i < matrizAdjacencia.length; i++) {
            for (int j = 0; j < matrizAdjacencia[i].length; j++) {
                sb.append(matrizAdjacencia[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

