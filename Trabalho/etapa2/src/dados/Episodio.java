package dados;
//id titulo duração, numero da temp e serie q pertence
public class Episodio {
    private long id;
    private String titulo;
    private int duracao; // em minutos
    private int numTemp;
    private Serie serie;
 
    public long getId() {
        return id;
    }
 
    public void setId(long id) {
        this.id = id;
    }
 
    public String getTitulo() {
        return titulo;
    }
 
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
 
    public int getDuracao() {
        return duracao;
    }
 
    public void setDuracao(int duracao) {
        if (duracao < 0) {
            throw new IllegalArgumentException("Duração não pode ser negativa.");
        }
        this.duracao = duracao;
    }
    
 
    public int getNumTemp() {
        return numTemp;
    }
 
    public void setNumTemp(int numTemp) {
        this.numTemp = numTemp;
    }
 
    public Serie getSerie() {
        return serie;
    }
 
    public void setSerie(Serie serie) {
        this.serie = serie;
    }
 
    public String toString() {
        return "\nTítulo: " + this.titulo +
               "\nDuração: " + this.duracao + " minutos" +
               "\nID: " + this.id +
               "\nNúmero da Temporada: " + this.numTemp +
               "\nSérie: " + (serie != null ? serie.getTitulo() : "N/A");
    }
    
 }
 
