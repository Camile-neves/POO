package dados;

import java.util.ArrayList;
import java.util.List;

public class Serie {
    private long id;
    private String titulo;
    private String genero;
    private int duracao;
    private int anoLanc;
    private List<Temporada> temporadas; // Lista de temporadas
    private List<Ator> elencoPrincipal= new ArrayList<>();
    private List<Ator> elencoSecundario= new ArrayList<>();

    public Serie() {
        this.temporadas = new ArrayList<>();
    }
    
    // Métodos getters e setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public int getDuracao() {
        return this.duracao;
    }

    public void setAnoLanc(int anoLanc) {
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

    public List<Temporada> getTemporadas() {
        return temporadas; // Retorna a lista de temporadas
    }

    public void setTemporadas(List<Temporada> temporadas) {
        this.temporadas = temporadas; // Define a lista de temporadas
    }

    @Override
    public String toString() {
        return "\nTítulo: " + this.titulo + "\nGênero: " + this.genero + "\nAno de Lançamento: " + this.anoLanc + "\nTemporadas: " + this.temporadas + "\nID: " + this.id;
    }
    public void adicionarAtorElencoPrincipal(Ator ator) {
        elencoPrincipal.add(ator);
    }

    // método para adicionar ator ao elenco secundário
    public void adicionarAtorElencoSecundario(Ator ator) {
        elencoSecundario.add(ator);
    }
}
