
package negocio;

import dados.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Sistema {
    private List<Filme> filmes = new ArrayList<>();
    private List<Serie> series = new ArrayList<>();
    private List<Ator> atores = new ArrayList<>();
// metodos para usuario 
    private List<Usuario> usuarios = new ArrayList<>();
    private Usuario usuarioLogado;

    public void cadastrarUsuario(Usuario u) {
        usuarios.add(u);
    }

    public Usuario buscarUsuarioPorNome(String nome) {
        for (Usuario u : usuarios) {
            if (u.getNome().equalsIgnoreCase(nome)) {
                return u;
            }
        }
        return null;
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


    
    // Métodos de filmes
    public void adicionarFilme(Filme f) {
        if (f.getDuracao() <= 0) {
            System.out.println("Erro: A duração do filme deve ser maior que zero.");
            return;
        }
        filmes.add(f);
    }

    public boolean removerFilme(long id) {
        return filmes.removeIf(f -> f.getId() == id);
    }

    public Filme getFilmePorId(long id) {
        for (Filme f : filmes) {
            if (f.getId() == id) return f;
        }
        return null;
    }

    public Filme buscarFilmePorId(long id) {
        for (Filme f : filmes) {
            if (f.getId() == id) return f;
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
                    System.out.println("O ator " + ator.getNome() + " já está no elenco secundário. Deseja movê-lo para o elenco principal? (s/n)");
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
                    System.out.println("O ator " + ator.getNome() + " já está no elenco principal. Deseja movê-lo para o elenco secundário? (s/n)");
                    continue;
                }
                if (!f.getElencoSecundario().contains(ator)) {
                    f.getElencoSecundario().add(ator);
                }
            }
        }
    }
    public Set<String> getGenerosFilmesDisponiveis() {
        Set<String> generos = new HashSet<>();
        for (Filme f : filmes) {
            generos.add(f.getGenero());
        }
        return generos;
    }

    // Métodos de séries
    public void adicionarSerie(Serie s) {
        if (s.getDuracao() <= 0) {
            System.out.println("Erro: A duração da série deve ser maior que zero.");
            return;
        }
        series.add(s);
    }

    public boolean removerSerie(long id) {
        return series.removeIf(s -> s.getId() == id);
    }

    public Serie getSeriePorId(long id) {
        for (Serie s : series) {
            if (s.getId() == id) return s;
        }
        return null;
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

    public void adicionarTemporada(long idSerie, int numero) {
        Serie s = buscarSeriePorId(idSerie);
        if (s != null) {
            for (Temporada t : s.getTemporadas()) {
                if (t.getNumero() == numero) {
                    System.out.println("Erro: Temporada já existe.");
                    return;
                }
            }
            Temporada nova = new Temporada();
            nova.setNumero(numero);
            nova.setEpisodios(new ArrayList<>());
            s.getTemporadas().add(nova);
        } else {
            System.out.println("Série com ID " + idSerie + " não encontrada.");
        }
    }

    public void adicionarEpisodio(long idSerie, Episodio ep) {
        Serie s = buscarSeriePorId(idSerie);
        if (s != null) {
            for (Temporada t : s.getTemporadas()) {
                if (t.getNumero() == ep.getNumTemp()) {
                    for (Episodio existente : t.getEpisodios()) {
                        if (existente.getTitulo().equalsIgnoreCase(ep.getTitulo())) {
                            System.out.println("Erro: Episódio com o mesmo nome já existe na temporada.");
                            return;
                        }
                    }
                    t.getEpisodios().add(ep);
                    return;
                }
            }
            System.out.println("Temporada não encontrada na série.");
        } else {
            System.out.println("Série não encontrada.");
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
                    System.out.println("O ator " + ator.getNome() + " já está no elenco secundário. Deseja movê-lo para o elenco principal? (s/n)");
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
                    System.out.println("O ator " + ator.getNome() + " já está no elenco principal. Deseja movê-lo para o elenco secundário? (s/n)");
                    // Aqui você pode implementar a lógica para ler a entrada do usuário
                    // e mover o ator conforme a escolha.
                    continue;
                }
                if (!s.getElencoSecundario().contains(ator)) {
                    s.getElencoSecundario().add(ator);
                }
            }
        }
    }

    // Métodos de atores
    public void adicionarAtor(Ator a) {
        if (a.getSexo() != null && a.getSexo().matches("\\d+")) {
            System.out.println("Erro: O sexo não pode ser um número. Volte ao menu e forneça um valor válido.");
            return; // Retorna para o menu
        }
        int ano = a.getDataNasc();// Supondo que dataNasc é um int com o ano
        int anoAtual = LocalDate.now().getYear();
        if (ano < 1905 || ano > anoAtual) {
            System.out.println("Erro: O ator deve ter nascido entre 1905 e o ano atual.");
            return;
        }
    
        if (a.getDataNasc() <= 0) {
            System.out.println("Erro: A data de nascimento do ator não pode ser nula.");
            return;
        }
        if (a.getNome() == null || a.getNome().trim().isEmpty() || a.getNome().matches(".*\\d.*")) {
            System.out.println("Erro: O nome do ator não pode ser vazio ou conter números.");
            return;
        }
        if (a.getSexo() == null || a.getSexo().trim().isEmpty() || a.getSexo().matches(".*\\d.*")) {
            System.out.println("Erro: O sexo do ator não pode ser vazio ou conter números.");
            return;
        }
        atores.add(a);
    }

    public List<Ator> getAtores() {
        return atores;
    }

    // Verifica se um ator já está em algum elenco
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

    // Remove um ator de todos os elencos que ele participa
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

    // Verifica se o episódio já existe na temporada
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
    
    public Set<String> getGenerosSeriesDisponiveis() {
    Set<String> generos = new HashSet<>();
    for (Serie s : series) {
        generos.add(s.getGenero());
    }
    return generos;
    }
    public Ator buscarAtorPorId(int id) {
        for (Ator ator : atores) {
            if (ator.getId() == id) {
                return ator;
            }
        }
        return null; 
    }
    
    
}
