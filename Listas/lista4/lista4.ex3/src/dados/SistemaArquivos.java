package dados;

import java.util.*;

public class SistemaArquivos {
    private Map<String, List<Arquivo>> diretorios = new HashMap<>();

    public void adicionarArquivo(String diretorio, Arquivo arquivo) throws NomeInvalidoException {
        if (!diretorios.containsKey(diretorio)) {
            diretorios.put(diretorio, new ArrayList<>());
        }
        diretorios.get(diretorio).add(arquivo);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (String dir : diretorios.keySet()) {
            sb.append(dir).append(":\n");
            for (Arquivo arq : diretorios.get(dir)) {
                sb.append(arq.toString()).append("\n\n");
            }
        }

        return sb.toString();
    }
}

