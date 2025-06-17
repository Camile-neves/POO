package negocio;

import dados.*;

import java.time.LocalDate;
import java.util.*;

public class Sistema {
    private List<Filme> filmes = new ArrayList<>();
    private List<Serie> series = new ArrayList<>();
    private List<Ator> atores = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();
    private Usuario usuarioLogado;

    private long proximoIdFilme = 1;
    private long proximoIdSerie = 1;
    private int proximoIdAtor = 1;
    private long ultimoIdFilme = 0;

    public long gerarIdFilme() {
        return ++ultimoIdFilme;
    }
    private long ultimoIdSerie = 0;
    private long ultimoIdAtor = 0;
    private long ultimoIdEpisodio = 0;

    public long gerarIdSerie() {
        return ++ultimoIdSerie;
    }

    public long gerarIdAtor() {
        return ++ultimoIdAtor;
    }

    public long gerarIdEpisodio() {
        return ++ultimoIdEpisodio;
    }  


    public void cadastrarUsuario(Usuario u) {
        usuarios.add(u);
    }
    
    public List<Ator> getAtores() {
        return atores;
    }

    public void adicionarAtor(Ator ator) {
        atores.add(ator);
    }
    public Usuario buscarUsuarioPorNome(String nome) {
        for (Usuario u : usuarios) {
            if (u.getNome().equalsIgnoreCase(nome)) {
                return u;
            }
        }
        return null;
    }

    public void deslogarUsuario() {
        usuarioLogado = null;
    }

    public boolean autenticarUsuario(String nome, String senha) {
        Usuario u = buscarUsuarioPorNome(nome);
        if (u != null && u.getSenha().equals(senha)) {
            usuarioLogado = u;
            return true;
        }
        return false;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public boolean estaLogado() {
        return usuarioLogado != null;
    }

    public void cadastrarAtor(String nome, int dataNasc, String sexo) {
        if (sexo != null && sexo.matches("\\d+")) {
            System.out.println("Erro: O sexo não pode ser um número.");
            return;
        }

        int anoAtual = LocalDate.now().getYear();
        if (dataNasc < 1905 || dataNasc > anoAtual) {
            System.out.println("Erro: O ator deve ter nascido entre 1905 e o ano atual.");
            return;
        }

        if (nome == null || nome.trim().isEmpty() || nome.matches(".*\\d.*")) {
            System.out.println("Erro: Nome inválido.");
            return;
        }

        if (sexo == null || sexo.trim().isEmpty() || sexo.matches(".*\\d.*")) {
            System.out.println("Erro: Sexo inválido.");
            return;
        }

        Ator a = new Ator();
        a.setId(proximoIdAtor++);
        a.setNome(nome);
        a.setDataNasc(dataNasc);
        a.setSexo(sexo);
        atores.add(a);
    }

    public void adicionarFilme(Filme f) {
        if (f.getDuracao() <= 0) {
            System.out.println("Erro: A duração do filme deve ser maior que zero.");
            return;
        }
        f.setId(proximoIdFilme++);
        filmes.add(f);
    }

    public void adicionarSerie(Serie s) {
        if (s.getDuracao() <= 0) {
            System.out.println("Erro: A duração da série deve ser maior que zero.");
            return;
        }
        s.setId(proximoIdSerie++);
        series.add(s);
    }

    public Serie buscarSeriePorId(long id) {
        for (Serie s : series) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    public List<Serie> getSeriesPorGenero(String genero) {
        List<Serie> resultado = new ArrayList<>();
        for (Serie s : series) {
            if (s.getGenero().equalsIgnoreCase(genero)) {
                resultado.add(s);
            }
        }
        return resultado;
    }
    public Filme buscarFilmePorId(long id) {
        for (Filme f : filmes) {
            if (f.getId() == id) {
                return f;
            }
        }
        return null; // Se não encontrar, retorna null
    }

    
    

    public Set<String> getGenerosSeriesDisponiveis() {
        Set<String> generos = new HashSet<>();
        for (Serie s : series) {
            generos.add(s.getGenero());
        }
        return generos;
    }
    public void adicionarTemporada(long idSerie, Temporada temporada) {
        Serie s = buscarSeriePorId(idSerie);
        if (s != null) {
            for (Temporada t : s.getTemporadas()) {
                if (t.getNumero() == temporada.getNumero()) {
                    System.out.println("Erro: Temporada já existe.");
                    return;
                }
            }
            temporada.setEpisodios(new ArrayList<>()); // garante que lista não seja null
            s.getTemporadas().add(temporada);
        } else {
            System.out.println("Série com ID " + idSerie + " não encontrada.");
        }
    }
    

    public void adicionarEpisodio(long idSerie, int numTemporada, Episodio ep) {
        Serie s = buscarSeriePorId(idSerie);
        if (s != null) {
            for (Temporada t : s.getTemporadas()) {
                if (t.getNumero() == numTemporada) {
                    t.getEpisodios().add(ep);
                    return;
                }
            }
            System.out.println("Temporada " + numTemporada + " não encontrada.");
        } else {
            System.out.println("Série com ID " + idSerie + " não encontrada.");
        }
    }
    
    public void adicionarElencoPrincipalFilme(long id, List<Ator> elenco) {
        Filme f = buscarFilmePorId(id);
        if (f != null) {
            if (f.getElencoPrincipal() == null)
                f.setElencoPrincipal(new ArrayList<>());
            for (Ator ator : elenco) {
                if (!atores.contains(ator)) {
                    System.out.println("Erro: Ator com ID " + ator.getId() + " não encontrado.");
                    continue;
                }
                if (f.getElencoSecundario() != null && f.getElencoSecundario().contains(ator)) {
                    System.out.println("O ator " + ator.getNome() + " já está no elenco secundário.");
                    continue;
                }
                if (!f.getElencoPrincipal().contains(ator)) {
                    f.getElencoPrincipal().add(ator);
                }
            }
        }
    }

    public void adicionarElencoSecundarioFilme(long id, List<Ator> elenco) {
        Filme f = buscarFilmePorId(id);
        if (f != null) {
            if (f.getElencoSecundario() == null)
                f.setElencoSecundario(new ArrayList<>());
            for (Ator ator : elenco) {
                if (!atores.contains(ator)) {
                    System.out.println("Erro: Ator com ID " + ator.getId() + " não encontrado.");
                    continue;
                }
                if (f.getElencoPrincipal() != null && f.getElencoPrincipal().contains(ator)) {
                    System.out.println("O ator " + ator.getNome() + " já está no elenco principal.");
                    continue;
                }
                if (!f.getElencoSecundario().contains(ator)) {
                    f.getElencoSecundario().add(ator);
                }
            }
        }
    }

    public void adicionarElencoPrincipalSerie(long id, List<Ator> elenco) {
        Serie s = buscarSeriePorId(id);
        if (s != null) {
            if (s.getElencoPrincipal() == null)
                s.setElencoPrincipal(new ArrayList<>());
            for (Ator ator : elenco) {
                if (!atores.contains(ator)) {
                    System.out.println("Erro: Ator com ID " + ator.getId() + " não encontrado.");
                    continue;
                }
                if (s.getElencoSecundario() != null && s.getElencoSecundario().contains(ator)) {
                    System.out.println("O ator " + ator.getNome() + " já está no elenco secundário.");
                    continue;
                }
                if (!s.getElencoPrincipal().contains(ator)) {
                    s.getElencoPrincipal().add(ator);
                }
            }
        }
    }

    public void adicionarElencoSecundarioSerie(long id, List<Ator> elenco) {
        Serie s = buscarSeriePorId(id);
        if (s != null) {
            if (s.getElencoSecundario() == null)
                s.setElencoSecundario(new ArrayList<>());
            for (Ator ator : elenco) {
                if (!atores.contains(ator)) {
                    System.out.println("Erro: Ator com ID " + ator.getId() + " não encontrado.");
                    continue;
                }
                if (s.getElencoPrincipal() != null && s.getElencoPrincipal().contains(ator)) {
                    System.out.println("O ator " + ator.getNome() + " já está no elenco principal.");
                    continue;
                }
                if (!s.getElencoSecundario().contains(ator)) {
                    s.getElencoSecundario().add(ator);
                }
            }
        }
    }
    public boolean atorEstaEmAlgumElenco(Ator ator) {
        for (Filme f : filmes) {
            if ((f.getElencoPrincipal() != null && f.getElencoPrincipal().contains(ator)) ||
                (f.getElencoSecundario() != null && f.getElencoSecundario().contains(ator))) {
                return true;
            }
        }
        for (Serie s : series) {
            if ((s.getElencoPrincipal() != null && s.getElencoPrincipal().contains(ator)) ||
                (s.getElencoSecundario() != null && s.getElencoSecundario().contains(ator))) {
                return true;
            }
        }
        return false;
    }

    public void removerAtorDeTodosOsElencos(Ator ator) {
        for (Filme f : filmes) {
            if (f.getElencoPrincipal() != null) f.getElencoPrincipal().remove(ator);
            if (f.getElencoSecundario() != null) f.getElencoSecundario().remove(ator);
        }
        for (Serie s : series) {
            if (s.getElencoPrincipal() != null) s.getElencoPrincipal().remove(ator);
            if (s.getElencoSecundario() != null) s.getElencoSecundario().remove(ator);
        }
    }

    public boolean episodioJaExiste(Temporada temporada, String nomeEpisodio) {
        for (Episodio e : temporada.getEpisodios()) {
            if (e.getTitulo().equalsIgnoreCase(nomeEpisodio)) {
                return true;
            }
        }
        return false;
    }

    public List<Filme> getTodosFilmes() {
        return filmes;
    }

    public List<Serie> getTodasSeries() {
        return series;
    }

    public List<Ator> getTodosAtores() {
        return new ArrayList<>(atores);
    }

    public void setUsuarioLogado(Usuario usuario) {
        this.usuarioLogado = usuario;
    }

    public Ator buscarAtorPorId(long id) {
        for (Ator ator : atores) {
            if (ator.getId() == id) {
                return ator;
            }
        }
        return null;
    }

    public List<Filme> getFilmesPorGenero(String genero) {
        List<Filme> resultado = new ArrayList<>();
        for (Filme f : filmes) {
            if (f.getGenero().equalsIgnoreCase(genero)) {
                resultado.add(f);
            }
        }
        return resultado;
    }


    public boolean removerFilme(long id) {
        Filme filme = buscarFilmePorId(id);
        if (filme != null) {
            filmes.remove(filme);
            return true;
        }
        return false;
    }


    public boolean removerSerie(long id) {
        Serie serie = buscarSeriePorId(id);
        if (serie != null) {
            series.remove(serie);
            return true;
        }
        return false;
    }






    public Set<String> getGenerosSeries() {
        Set<String> generos = new HashSet<>();
        for (Serie serie : series) {
            generos.add(serie.getGenero());
        }
        return generos;
    }




    
    public Set<String> getGenerosFilmes() {
        Set<String> generos = new HashSet<>();
        for (Filme filme : filmes) {
            generos.add(filme.getGenero());
        }
        return generos;
    }

    
    

}
