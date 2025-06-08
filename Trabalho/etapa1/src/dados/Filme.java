package dados;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Filme {
    private long id;
    private String titulo;
    private String genero;
    private int duracao;
    private int anoLanc;
    private List<Ator> elencoPrincipal = new ArrayList<>();
    private List<Ator> elencoSecundario = new ArrayList<>();

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("O título não pode ser nulo ou vazio.");
        }
        this.titulo = titulo;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setGenero(String genero) {
        if (genero == null || genero.trim().isEmpty()) {
            throw new IllegalArgumentException("O gênero não pode ser nulo ou vazio.");
        }
        this.genero = genero;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setDuracao(int duracao) {
        if (duracao <= 0) {
            throw new IllegalArgumentException("A duração deve ser maior que zero.");
        }
        this.duracao = duracao;
    }

    public int getDuracao() {
        return this.duracao;
    }

    public void setAnoLanc(int anoLanc) {
        int anoAtual = LocalDate.now().getYear();
        if (anoLanc < 1888) {
            throw new IllegalArgumentException("O primeiro filme lançado foi em 1888, então cadastre um filme a partir desta data.");
        }
        if (anoLanc > anoAtual) {
            throw new IllegalArgumentException("O ano de lançamento não pode ser posterior ao ano atual.");
        }
        this.anoLanc = anoLanc;
    }

    public int getAnoLanc() {
        return this.anoLanc;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public List<Ator> getElencoPrincipal() {
        return elencoPrincipal;
    }

    public void setElencoPrincipal(List<Ator> elencoPrincipal) {
        this.elencoPrincipal = elencoPrincipal;
    }

    public List<Ator> getElencoSecundario() {
        return elencoSecundario;
    }

    public void setElencoSecundario(List<Ator> elencoSecundario) {
        this.elencoSecundario = elencoSecundario;
    }

    @Override
    public String toString() {
        return "\nTítulo: " + this.titulo +
               "\nGênero: " + this.genero +
               "\nAno de lançamento: " + this.anoLanc +
               "\nDuração: " + this.duracao + " minutos" +
               "\nID: " + this.id +
               "\nElenco Principal: " + formatarElenco(elencoPrincipal) +
               "\nElenco Secundário: " + formatarElenco(elencoSecundario);
    }

    private String formatarElenco(List<Ator> elenco) {
        if (elenco == null || elenco.isEmpty()) return "Nenhum";
        StringBuilder sb = new StringBuilder();
        for (Ator ator : elenco) {
            sb.append(ator.getNome()).append(", ");
        }
        return sb.substring(0, sb.length() - 2); // Remove última vírgula e espaço
    }
}
