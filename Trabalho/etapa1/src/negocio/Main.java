package negocio;

import dados.*;
import java.util.*;
import java.time.LocalDate;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Sistema sistema = new Sistema();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Sistema sistema = new Sistema();

        // === MENU DE LOGIN/CADASTRO ===
        while (!sistema.estaLogado()) {
            System.out.println("\n=== SISTEMA DE STREAMING ===");
            System.out.println("1 - Cadastrar novo usuário");
            System.out.println("2 - Fazer login");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // consumir quebra de linha

            switch (opcao) {
                case 1:
                    System.out.print("Nome de usuário: ");
                    String nome = scanner.nextLine();

                    System.out.print("Senha: ");
                    String senha = scanner.nextLine();
                    int nascimento;
                    int anoAtual = java.time.Year.now().getValue();

                    while (true) {
                        System.out.print("Ano de nascimento (entre 1905 e " + anoAtual + "): ");
                        nascimento = scanner.nextInt();
                        scanner.nextLine(); // consumir quebra de linha
                
                        if (nascimento >= 1905 && nascimento <= anoAtual) {
                            break;
                        } else {
                            System.out.println("Ano inválido! Tente novamente.");
                        }
                    }
                    Usuario novoUsuario = new Usuario();
                    novoUsuario.setId(System.currentTimeMillis()); // ID simples
                    novoUsuario.setNome(nome);
                    novoUsuario.setSenha(senha);
                    novoUsuario.setDataNasc(nascimento);

                    sistema.cadastrarUsuario(novoUsuario);
                    break;

                case 2:
                    System.out.print("Nome de usuário: ");
                    String login = scanner.nextLine();
                
                    System.out.print("Senha: ");
                    String senhaLogin = scanner.nextLine();
                
                    sistema.autenticarUsuario(login, senhaLogin); 
                
                    break;
                

                case 0:
                    System.out.println("Encerrando o sistema...");
                    return;

                default:
                    System.out.println("Opção inválida!");
            }
        }
        int opcao;
        do {
            System.out.println("\n\n===== MENU STREAMING =====");
            System.out.println("1.Adicionar Filme");
            System.out.println("2.Adicionar Série");
            System.out.println("3.Remover Filme");
            System.out.println("4.Remover Série");
            System.out.println("5.Listar Filmes por Gênero");
            System.out.println("6.Listar Séries por Gênero");
            System.out.println("7.Ver Detalhes de um Filme");
            System.out.println("8.Ver Detalhes de uma Série");
            System.out.println("9.Ver Atores Principais de um Filme");
            System.out.println("10.Ver Atores Principais de uma Série");
            System.out.println("11.Adicionar Ator");
            System.out.println("12.Adicionar Temporada à Série");
            System.out.println("13.Adicionar Episódio à Temporada");
            System.out.println("14.Adicionar Elenco Principal/Secundário");
            System.out.println("0.Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1: adicionarFilme(); break;
                case 2: adicionarSerie(); break;
                case 3: removerFilme(); break;
                case 4: removerSerie(); break;
                case 5: listarFilmesPorGenero(); break;
                case 6: listarSeriesPorGenero(); break;
                case 7: verDetalhesFilme(); break;
                case 8: verDetalhesSerie(); break;
                case 9: verElencoPrincipalFilme(); break;
                case 10: verElencoPrincipalSerie(); break;
                case 11: adicionarAtor(); break;
                case 12: adicionarTemporada(); break;
                case 13: adicionarEpisodio(); break;
                case 14: adicionarElenco(); break;
                case 0: System.out.println("Encerrando..."); break;
                default: System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
        scanner.close();
    }
    private static void adicionarFilme() {
        Random random = new Random();
        long id = random.nextInt(1000);
        Filme f = new Filme();
        f.setId(id);
        System.out.println("ID gerado automaticamente: " + id);
    
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        if (titulo.isEmpty()) {
            System.out.println("Entrada vazia. Voltando ao menu...");
            return;
        }
        f.setTitulo(titulo);

        System.out.print("Gênero: ");
        String genero = scanner.nextLine().trim();
        if (genero.isEmpty()) {
            System.out.println("Entrada vazia. Voltando ao menu...");
            return;
        }
        if (genero.matches(".*\\d.*")) {
            System.out.println("Erro: O gênero não pode conter números. Voltando ao menu...");
            return;
        }
        f.setGenero(genero);
    

        System.out.print("Duração (hora): ");
        String duracaoStr = scanner.nextLine().trim();
        if (duracaoStr.isEmpty()) {
            System.out.println("Entrada vazia. Voltando ao menu...");
            return;
        }
    
        try {
            int duracao = Integer.parseInt(duracaoStr);
            if (duracao <= 0) {
                System.out.println("Erro: A duração deve ser um número positivo.");
                return;
            }
            f.setDuracao(duracao);
        } catch (NumberFormatException e) {
            System.out.println("Erro: Digite um número válido para a duração.");
            return;
        }
    
        System.out.print("Ano de lançamento: ");
        String anoStr = scanner.nextLine();
        if (anoStr.isEmpty()) {
            System.out.println("Entrada vazia. Voltando ao menu...");
            return;
        }
        try {
            int ano = Integer.parseInt(anoStr);
            int anoAtual = LocalDate.now().getYear();
            if (ano < 1888 || ano > anoAtual) {
                System.out.println("Ano inválido. Voltando ao menu...");
                return;
            }
            f.setAnoLanc(ano);
        } catch (NumberFormatException e) {
            System.out.println("Erro: Digite um número válido. Voltando ao menu...");
            return;
        }
    
        f.setElencoPrincipal(new ArrayList<>());
        f.setElencoSecundario(new ArrayList<>());
    
        sistema.adicionarFilme(f);
        System.out.println("Filme adicionado!");
    }
    

    private static void adicionarSerie() {
        Random random = new Random();
        long id = random.nextInt(1000);
    
        Serie s = new Serie();
        s.setId(id);
        System.out.println("ID gerado automaticamente: " + id);
    
        System.out.print("Título (se tiver número, escreva por extenso): ");
        String titulo = scanner.nextLine().trim();
        if (titulo.isEmpty()) {
            System.out.println("Entrada vazia. Voltando ao menu...");
            return;
        }
        s.setTitulo(titulo);
    
        System.out.print("Gênero: ");
        String genero = scanner.nextLine().trim();
        if (genero.isEmpty()) {
            System.out.println("Entrada vazia. Voltando ao menu...");
            return;
        }
        if (genero.matches(".*\\d.*")) {
            System.out.println("Erro: O gênero não pode conter números. Voltando ao menu...");
            return;
        }
        s.setGenero(genero);
    
        System.out.print("Duração (hora): ");
        String duracaoStr = scanner.nextLine().trim();
        if (duracaoStr.isEmpty()) {
            System.out.println("Entrada vazia. Voltando ao menu...");
            return;
        }
    
        try {
            int duracao = Integer.parseInt(duracaoStr);
            if (duracao <= 0) {
                System.out.println("Erro: A duração deve ser um número positivo.");
                return;
            }
            s.setDuracao(duracao);
        } catch (NumberFormatException e) {
            System.out.println("Erro: Digite um número válido para a duração.");
            return;
        }
    
        System.out.print("Ano de lançamento: ");
        String anoStr = scanner.nextLine().trim();
        if (anoStr.isEmpty()) {
            System.out.println("Erro: O ano de lançamento não pode estar vazio.");
            return;
        }
    
        try {
            int ano = Integer.parseInt(anoStr);
            int anoAtual = java.time.LocalDate.now().getYear();
            if (ano < 1888) {
                System.out.println("Erro: O ano de lançamento não pode ser anterior a 1888.");
                return;
            }
            if (ano > anoAtual) {
                System.out.println("Erro: O ano de lançamento não pode ser posterior ao ano atual (" + anoAtual + ").");
                return;
            }
    
            s.setAnoLanc(ano);
        } catch (NumberFormatException e) {
            System.out.println("Erro: Digite um número válido para o ano de lançamento.");
            return;
        }
    
        s.setElencoPrincipal(new ArrayList<>());
        s.setElencoSecundario(new ArrayList<>());
        s.setTemporadas(new ArrayList<>());
    
        sistema.adicionarSerie(s);
        System.out.println("Série adicionada!");
    }
    

    private static void removerFilme() {
        List<Filme> filmes = sistema.getTodosFilmes();
        if (filmes.isEmpty()) {
            System.out.println("Nenhum filme disponível para remoção.");
            return;
        }
    
        System.out.println("Filmes disponíveis:");
        for (Filme f : filmes) {
            System.out.println("ID: " + f.getId() + " | Título: " + f.getTitulo());
        }
    
        System.out.print("ID do filme a remover: ");
        long id = scanner.nextLong();
        boolean ok = sistema.removerFilme(id);
        System.out.println(ok ? "Filme removido." : "Filme não encontrado.");
    }
    

    private static void removerSerie() {
        List<Serie> series = sistema.getTodasSeries();
        if (series.isEmpty()) {
            System.out.println("Nenhuma série disponível para remoção.");
            return;
        }
    
        System.out.println("Séries disponíveis:");
        for (Serie s : series) {
            System.out.println("ID: " + s.getId() + " | Título: " + s.getTitulo());
        }
    
        System.out.print("ID da série a remover: ");
        long id = scanner.nextLong();
        boolean ok = sistema.removerSerie(id);
        System.out.println(ok ? "Série removida." : "Série não encontrada.");
    }
    

    private static void listarFilmesPorGenero() {
        Set<String> generos = sistema.getGenerosFilmesDisponiveis();
        if (generos.isEmpty()) {
            System.out.println("Nenhum gênero disponível.");
            return;
        }
        System.out.println("Gêneros disponíveis:");
        generos.forEach(g -> System.out.println("- " + g));
        System.out.print("Digite o gênero desejado: ");
        String genero = scanner.nextLine();
        List<Filme> filmes = sistema.getFilmesPorGenero(genero);
        if (filmes.isEmpty()) {
            System.out.println("Nenhum filme encontrado.");
        } else {
            for (Filme f : filmes) {
                System.out.println(f);
            }
        }
    }
    
    private static void listarSeriesPorGenero() {
        Set<String> generos = sistema.getGenerosSeriesDisponiveis();
        if (generos.isEmpty()) {
            System.out.println("Nenhum gênero disponível.");
            return;
        }
        System.out.println("Gêneros disponíveis:");
        generos.forEach(g -> System.out.println("- " + g));
        System.out.print("Digite o gênero desejado: ");
        String genero = scanner.nextLine();
        List<Serie> series = sistema.getSeriesPorGenero(genero);
        if (series.isEmpty()) {
            System.out.println("Nenhuma série encontrada.");
        } else {
            for (Serie s : series) {
                System.out.println(s);
            }
        }
    }
    

    private static void verDetalhesFilme() {
        List<Filme> filmes = sistema.getTodosFilmes();
        if (filmes.isEmpty()) {
            System.out.println("Nenhum filme disponível.");
            return;
        }
        System.out.println("Filmes disponíveis:");
        for (Filme f : filmes) {
            System.out.println("ID: " + f.getId() + " | Título: " + f.getTitulo());
        }
        System.out.print("ID do filme: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        Filme f = sistema.getFilmePorId(id);
        System.out.println((f != null) ? f : "Filme não encontrado.");
    }
    

    private static void verDetalhesSerie() {
        List<Serie> series = sistema.getTodasSeries();
        if (series.isEmpty()) {
            System.out.println("Nenhuma série disponível.");
            return;
        }
        System.out.println("Séries disponíveis:");
        for (Serie s : series) {
            System.out.println("ID: " + s.getId() + " | Título: " + s.getTitulo());
        }
        System.out.print("ID da série: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        Serie s = sistema.getSeriePorId(id);
        System.out.println((s != null) ? s : "Série não encontrada.");
    }
    

    private static void verElencoPrincipalFilme() {
        List<Filme> filmes = sistema.getTodosFilmes();
    
        if (filmes.isEmpty()) {
            System.out.println("Nenhum filme disponível.");
            return;
        }
    
        System.out.println("Filmes disponíveis:");
        for (Filme f : filmes) {
            System.out.println("ID: " + f.getId() + " | Título: " + f.getTitulo());
        }
    
        System.out.print("ID do filme: ");
        int idFilme = Integer.parseInt(scanner.nextLine());
        Filme filme = sistema.buscarFilmePorId(idFilme);
    
        if (filme == null) {
            System.out.println("Filme não encontrado.");
            return;
        }
    
        List<Ator> elenco = filme.getElencoPrincipal();
        if (elenco == null || elenco.isEmpty()) {
            System.out.println("Nenhum ator principal cadastrado.");
            return;
        }
    
        System.out.println("Elenco principal:");
        for (Ator ator : elenco) {
            System.out.println(ator.getNome());
        }
    }
    
    
    

    private static void verElencoPrincipalSerie() {
        List<Serie> series = sistema.getTodasSeries();
    
        if (series.isEmpty()) {
            System.out.println("Nenhuma série cadastrada.");
            return;
        }
    
        System.out.println("Séries disponíveis:");
        for (Serie s : series) {
            System.out.println("ID: " + s.getId() + " | Título: " + s.getTitulo());
        }
    
        System.out.print("ID da série: ");
        int id = Integer.parseInt(scanner.nextLine());
        Serie serie = sistema.buscarSeriePorId(id);
    
        if (serie == null) {
            System.out.println("Série não encontrada.");
            return;
        }
    
        List<Ator> elencoPrincipal = serie.getElencoPrincipal();
    
        if (elencoPrincipal.isEmpty()) {
            System.out.println("Não há atores principais cadastrados.");
            return;
        }
    
        System.out.println("Elenco principal:");
        for (Ator ator : elencoPrincipal) {
            System.out.println("ID: " + ator.getId() + " | Nome: " + ator.getNome());
        }
    }
    

    private static void adicionarAtor() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();
        if (nome.isEmpty()) {
            System.out.println("Entrada vazia. Voltando ao menu...");
            return;
        }
        if (nome.matches(".*\\d.*")) {
            System.out.println("O nome não pode conter números. Voltando ao menu...");
            return;
        }
    
        System.out.print("Data de nascimento (ano): ");
        String entradaAno = scanner.nextLine().trim();
        if (entradaAno.isEmpty()) {
            System.out.println("Entrada vazia. Voltando ao menu...");
            return;
        }
    
        int ano;
        try {
            ano = Integer.parseInt(entradaAno);
            int anoAtual = java.time.Year.now().getValue();
            if (ano < 1904 || ano > anoAtual) {
                System.out.println("Ano inválido. Deve estar entre 1904 e " + anoAtual + ". Voltando ao menu...");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Ano inválido. Voltando ao menu...");
            return;
        }
    
        for (Ator existente : sistema.getTodosAtores()) {
            if (existente.getNome().equalsIgnoreCase(nome) && existente.getDataNasc() == ano) {
                System.out.println("Ator já cadastrado.");
                return;
            }
        }
    
        System.out.print("Sexo: ");
        String sexo = scanner.nextLine().trim();
        if (sexo.isEmpty()) {
            System.out.println("Entrada vazia. Voltando ao menu...");
            return;
        }
        if (sexo.matches(".*\\d.*")) {
            System.out.println("O sexo não pode conter números. Voltando ao menu...");
            return;
        }
    
        Random random = new Random();
        long id = random.nextInt(1000);
        Ator a = new Ator();
        a.setId(id);
        a.setNome(nome);
        a.setDataNasc(ano);
        a.setSexo(sexo);
    
        sistema.adicionarAtor(a);
        System.out.println("Ator adicionado!");
    }

    private static void adicionarTemporada() {
        List<Serie> series = sistema.getTodasSeries();
        if (series.isEmpty()) {
            System.out.println("Nenhuma série cadastrada.");
            return;
        }
    
        System.out.println("Séries disponíveis:");
        for (Serie s : series) {
            System.out.println("ID: " + s.getId() + " - " + s.getTitulo());
        }
    
        System.out.print("ID da série: ");
        int idSerie;
        try {
            idSerie = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }
    
        Serie s = sistema.buscarSeriePorId(idSerie);
        if (s == null) {
            System.out.println("Série não encontrada.");
            return;
        }
    
        int novoNumero = s.getTemporadas().size() + 1;
        sistema.adicionarTemporada(idSerie, novoNumero);
        System.out.println("Temporada " + novoNumero + " adicionada!");
    }    
    

    private static void adicionarEpisodio() {
        System.out.println("Séries com temporadas disponíveis:");
    
        List<Serie> series = sistema.getTodasSeries();
        boolean encontrou = false;
    
        for (Serie s : series) {
            List<Temporada> temporadas = s.getTemporadas();
            if (temporadas != null && !temporadas.isEmpty()) {
                encontrou = true;
                System.out.println("ID da Série: " + s.getId() + " - " + s.getTitulo());
                System.out.print("Temporadas: ");
                for (Temporada t : temporadas) {
                    System.out.print("[" + t.getNumero() + "] ");
                }
                System.out.println();
            }
        }
    
        if (!encontrou) {
            System.out.println("Nenhuma série com temporadas encontrada.");
            return;
        }
    
        System.out.print("ID da série: ");
        int idSerie;
        try {
            idSerie = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Somente número, digite novamente.");
            return;
        }
    
        System.out.print("Número da temporada: ");
        String entradaTemporada = scanner.nextLine();
        if (entradaTemporada.isEmpty()) {
            System.out.println("Entrada vazia. Voltando ao menu...");
            return;
        }
        int numTemporada;
        try {
            numTemporada = Integer.parseInt(entradaTemporada);
        } catch (NumberFormatException e) {
            System.out.println("Somente número, voltando ao menu...");
            return;
        }
        
        Episodio ep = new Episodio();
        ep.setNumTemp(numTemporada);
        
        System.out.print("Título do episódio: ");
        String titulo = scanner.nextLine();
        if (titulo.isEmpty()) {
            System.out.println("Entrada vazia. Voltando ao menu...");
            return;
        }
        ep.setTitulo(titulo);
        
        System.out.print("Duração (min): ");
        String entradaDuracao = scanner.nextLine();
        if (entradaDuracao.isEmpty()) {
            System.out.println("Entrada vazia. Voltando ao menu...");
            return;
        }
        int duracao;
        try {
            duracao = Integer.parseInt(entradaDuracao);
        } catch (NumberFormatException e) {
            System.out.println("Somente número, voltando ao menu...");
            return;
        }
        ep.setDuracao(duracao);
        
        sistema.adicionarEpisodio(idSerie, ep);
        System.out.println("Episódio adicionado!");
    }        
    
    private static void adicionarElenco() {
        List<Ator> atoresDisponiveis = sistema.getTodosAtores();
    
        if (atoresDisponiveis.isEmpty()) {
            System.out.println("Nenhum ator cadastrado.");
            return;
        }
    
        System.out.print("1 - Filme | 2 - Série: ");
        String entradaTipo = scanner.nextLine();
        if (entradaTipo.isEmpty()) {
            System.out.println("Entrada vazia. Voltando ao menu...");
            return;
        }
    
        int tipo;
        try {
            tipo = Integer.parseInt(entradaTipo);
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Voltando ao menu...");
            return;
        }
    
        if (tipo == 1) {
            List<Filme> filmes = sistema.getTodosFilmes();
            if (filmes.isEmpty()) {
                System.out.println("Não há filme cadastrado.");
                return;
            }
            System.out.println("Filmes disponíveis:");
            for (Filme f : filmes) {
                System.out.println("ID: " + f.getId() + " | Título: " + f.getTitulo());
            }
        } else if (tipo == 2) {
            List<Serie> series = sistema.getTodasSeries();
            if (series.isEmpty()) {
                System.out.println("Não há série cadastrada.");
                return;
            }
            System.out.println("Séries disponíveis:");
            for (Serie s : series) {
                System.out.println("ID: " + s.getId() + " | Título: " + s.getTitulo());
            }
        } else {
            System.out.println("Tipo inválido.");
            return;
        }
    
        System.out.print("ID da mídia: ");
        String entradaIdMidia = scanner.nextLine();
        if (entradaIdMidia.isEmpty()) {
            System.out.println("Entrada vazia. Voltando ao menu...");
            return;
        }
    
        int idMidia;
        try {
            idMidia = Integer.parseInt(entradaIdMidia);
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Voltando ao menu...");
            return;
        }
    
        System.out.println("Atores disponíveis:");
        for (Ator a : atoresDisponiveis) {
            System.out.println("ID: " + a.getId() + " | Nome: " + a.getNome());
        }
    
        System.out.print("ID do ator para adicionar: ");
        String entradaIdAtor = scanner.nextLine();
        if (entradaIdAtor.isEmpty()) {
            System.out.println("Entrada vazia. Voltando ao menu...");
            return;
        }
    
        int idAtor;
        try {
            idAtor = Integer.parseInt(entradaIdAtor);
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Voltando ao menu...");
            return;
        }
    
        Ator ator = sistema.buscarAtorPorId(idAtor);
        if (ator == null) {
            System.out.println("Ator não encontrado.");
            return;
        }
    
        System.out.print("Adicionar ao elenco: 1 - Principal | 2 - Secundário: ");
        String entradaTipoElenco = scanner.nextLine();
        if (entradaTipoElenco.isEmpty()) {
            System.out.println("Entrada vazia. Voltando ao menu...");
            return;
        }
    
        int tipoElenco;
        try {
            tipoElenco = Integer.parseInt(entradaTipoElenco);
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Voltando ao menu...");
            return;
        }
    
        if (tipo == 1) {
            Filme filme = sistema.buscarFilmePorId(idMidia);
            if (filme == null) {
                System.out.println("Filme não encontrado.");
                return;
            }
    
            List<Ator> elencoPrincipal = filme.getElencoPrincipal();
            List<Ator> elencoSecundario = filme.getElencoSecundario();
    
            if (elencoPrincipal.contains(ator) || elencoSecundario.contains(ator)) {
                System.out.println("Este ator já está no elenco " + (elencoPrincipal.contains(ator) ? "principal" : "secundário") + ".");
                System.out.print("Deseja mover o ator para o novo elenco? (s/n): ");
                String opcao = scanner.nextLine().trim().toLowerCase();
                if (opcao.equals("s")) {
                    elencoPrincipal.remove(ator);
                    elencoSecundario.remove(ator);
                    if (tipoElenco == 1) {
                        elencoPrincipal.add(ator);
                    } else {
                        elencoSecundario.add(ator);
                    }
                    System.out.println("Ator movido para o novo elenco com sucesso!");
                } else {
                    System.out.println("Ator mantido no elenco original.");
                }
            } else {
                if (tipoElenco == 1) {
                    elencoPrincipal.add(ator);
                } else {
                    elencoSecundario.add(ator);
                }
                System.out.println("Ator adicionado ao elenco com sucesso!");
            }
    
        } else {
            Serie serie = sistema.buscarSeriePorId(idMidia);
            if (serie == null) {
                System.out.println("Série não encontrada.");
                return;
            }
    
            List<Ator> elencoPrincipal = serie.getElencoPrincipal();
            List<Ator> elencoSecundario = serie.getElencoSecundario();
    
            if (elencoPrincipal.contains(ator) || elencoSecundario.contains(ator)) {
                System.out.println("Este ator já está no elenco " + (elencoPrincipal.contains(ator) ? "principal" : "secundário") + ".");
                System.out.print("Deseja mover o ator para o novo elenco? (s/n): ");
                String opcao = scanner.nextLine().trim().toLowerCase();
                if (opcao.equals("s")) {
                    elencoPrincipal.remove(ator);
                    elencoSecundario.remove(ator);
                    if (tipoElenco == 1) {
                        elencoPrincipal.add(ator);
                    } else {
                        elencoSecundario.add(ator);
                    }
                    System.out.println("Ator movido para o novo elenco com sucesso!");
                } else {
                    System.out.println("Ator mantido no elenco original.");
                }
            } else {
                if (tipoElenco == 1) {
                    elencoPrincipal.add(ator);
                } else {
                    elencoSecundario.add(ator);
                }
                System.out.println("Ator adicionado ao elenco com sucesso!");
            }
        }
    }
    
}