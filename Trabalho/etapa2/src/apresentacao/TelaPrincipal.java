package apresentacao;

import javax.swing.*;
import java.awt.*;
import dados.*;
import negocio.Sistema;


import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class TelaPrincipal extends JFrame {

    private static Sistema sistema = new Sistema();
    private static Scanner scanner = new Scanner(System.in);


    public TelaPrincipal(Sistema sistema) {
        this.sistema = sistema;

        setTitle("Sistema de Streaming");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelBotoes = new JPanel(new GridLayout(0, 2, 5, 5)); 

        JButton btnAddFilme = new JButton("Adicionar Filme");
        btnAddFilme.addActionListener(this::adicionarFilmeGUI);

        JButton btnAddSerie = new JButton("Adicionar Série");
        btnAddSerie.addActionListener(this::adicionarSerieGUI);

        JButton btnAddAtor = new JButton("Adicionar Ator");
        btnAddAtor.addActionListener(this::adicionarAtor);

        JButton btnAddTemporada = new JButton("Adicionar Temporada à Série");
        btnAddTemporada.addActionListener(this::adicionarTemporadaGUI);

        JButton btnAddEpisodio = new JButton("Adicionar Episódio");
        btnAddEpisodio.addActionListener(this::adicionarEpisodio);

        JButton btnListarFilmes = new JButton("Listar Filmes por Gênero");
        btnListarFilmes.addActionListener(this::listarFilmesPorGeneroGUI);

        JButton btnListarSeries = new JButton("Listar Séries por Gênero");
        btnListarSeries.addActionListener(this::listarSeriesPorGeneroGUI);

        JButton btnVerElencoFilme = new JButton("Ver Elenco Principal de Filme");
        btnVerElencoFilme.addActionListener(this::verElencoPrincipalFilmeGUI);

        JButton btnVerElencoSerie = new JButton("Ver Elenco Principal de Série");
        btnVerElencoSerie.addActionListener(this::verElencoPrincipalSerieGUI);

        JButton btnAdicionarAtorElenco = new JButton("Adicionar Ator ao Elenco");
        btnAdicionarAtorElenco.addActionListener(this::adicionarAtorElencoGUI);

        JButton btnRemoverFilme = new JButton("Remover Filme");
        btnRemoverFilme.addActionListener(this::removerFilme);

        JButton btnRemoverSerie = new JButton("Remover Série");
        btnRemoverSerie.addActionListener(this::removerSerie);


        panelBotoes.add(btnAddFilme);
        panelBotoes.add(btnAddSerie);
        panelBotoes.add(btnAddAtor);
        panelBotoes.add(btnAddTemporada);
        panelBotoes.add(btnAddEpisodio);
        panelBotoes.add(btnListarFilmes);
        panelBotoes.add(btnListarSeries);
        panelBotoes.add(btnVerElencoFilme);
        panelBotoes.add(btnVerElencoSerie);
        panelBotoes.add(btnAdicionarAtorElenco);
        panelBotoes.add(btnRemoverFilme);
        panelBotoes.add(btnRemoverSerie);
        JButton btnLogout = new JButton("Logout");
        btnLogout.setPreferredSize(new Dimension(600, 100)); // largura 600, altura 100
        btnLogout.setBackground(new Color(255, 102, 102));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFocusPainted(false);
        btnLogout.setOpaque(true);
        btnLogout.setBorderPainted(false);
        btnLogout.addActionListener(e -> {
            sistema.setUsuarioLogado(null);
            dispose();
            new TelaLogin(sistema);
        });
    
        JPanel painelLogout = new JPanel(); // esse painel vai no sul
        painelLogout.setLayout(new FlowLayout(FlowLayout.CENTER));
        painelLogout.add(btnLogout);
    
        // Adiciona os painéis à janela principal
        add(panelBotoes, BorderLayout.CENTER);
        add(painelLogout, BorderLayout.SOUTH);
    
        setVisible(true);
    }
    ///////////////////////////////////////
    //_______________FILME_______________//
    ///////////////////////////////////////

    private void adicionarFilmeGUI(ActionEvent e) {
        JTextField tituloField = new JTextField();
        JTextField anoField = new JTextField();
        JTextField generoField = new JTextField();
        JTextField duracaoField = new JTextField();
    
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Título:"));
        panel.add(tituloField);
        panel.add(new JLabel("Ano de lançamento:"));
        panel.add(anoField);
        panel.add(new JLabel("Gênero:"));
        panel.add(generoField);
        panel.add(new JLabel("Duração (min):"));
        panel.add(duracaoField);
    
        int result = JOptionPane.showConfirmDialog(null, panel, "Adicionar Filme",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    
        if (result == JOptionPane.OK_OPTION) {
            try {
                String titulo = tituloField.getText().trim();
                int ano = Integer.parseInt(anoField.getText().trim());
                String genero = generoField.getText().trim();
                int duracao = Integer.parseInt(duracaoField.getText().trim());
    
                Filme filme = new Filme();
                filme.setId(System.currentTimeMillis());
                filme.setTitulo(titulo);
                filme.setAnoLanc(ano);
                filme.setGenero(genero);
                filme.setDuracao(duracao);
    
                sistema.adicionarFilme(filme);
                JOptionPane.showMessageDialog(null, "Filme adicionado com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
            }
        }
    }
    private void removerFilme(ActionEvent e) {
        List<Filme> filmes = sistema.getTodosFilmes();
        if (filmes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum filme disponível para remoção.");
            return;
        }
    
        String[] opcoes = filmes.stream()
            .map(f -> "ID: " + f.getId() + " | Título: " + f.getTitulo())
            .toArray(String[]::new);
    
        String selecao = (String) JOptionPane.showInputDialog(
            this,
            "Escolha um filme para remover:",
            "Remover Filme",
            JOptionPane.PLAIN_MESSAGE,
            null,
            opcoes,
            opcoes[0]);
    
        if (selecao == null) return;
    
        long id = Long.parseLong(selecao.split(" ")[1]);
    
        boolean removido = sistema.removerFilme(id);
        if (removido) {
            JOptionPane.showMessageDialog(this, "Filme removido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao remover filme.");
        }
    }

    private void listarFilmesPorGeneroGUI(ActionEvent e) {
        Set<String> generos = sistema.getGenerosFilmes();
        if (generos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum gênero disponível.");
            return;
        }
    
        String genero = (String) JOptionPane.showInputDialog(this, "Selecione o gênero:", "Gêneros", JOptionPane.PLAIN_MESSAGE, null, generos.toArray(), null);
        if (genero == null) return;
    
        List<Filme> filmes = sistema.getFilmesPorGenero(genero);
        if (filmes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum filme encontrado.");
            return;
        }
    
        String[] colunas = {"ID", "Título", "Ano", "Gênero", "Elenco Principal", "Elenco Secundário"};
        Object[][] dados = new Object[filmes.size()][6];
    
        for (int i = 0; i < filmes.size(); i++) {
            Filme f = filmes.get(i);
            dados[i][0] = f.getId();
            dados[i][1] = f.getTitulo();
            dados[i][2] = f.getAnoLanc();
            dados[i][3] = f.getGenero();
            dados[i][4] = f.getElencoPrincipal().stream().map(Ator::getNome).collect(Collectors.toList());
            dados[i][5] = f.getElencoSecundario().stream().map(Ator::getNome).collect(Collectors.toList());
        }
    
        JTable tabela = new JTable(dados, colunas);
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setPreferredSize(new Dimension(600, 250));
        JOptionPane.showMessageDialog(this, scrollPane, "Filmes do Gênero: " + genero, JOptionPane.PLAIN_MESSAGE);
    }
    
    private void verElencoPrincipalFilmeGUI(ActionEvent e) {
        List<Filme> filmes = sistema.getTodosFilmes();
        if (filmes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum filme disponível.");
            return;
        }
    
        String[] opcoes = new String[filmes.size()];
        for (int i = 0; i < filmes.size(); i++) {
            opcoes[i] = "ID: " + filmes.get(i).getId() + " | " + filmes.get(i).getTitulo();
        }
        String escolha = (String) JOptionPane.showInputDialog(this, "Escolha um filme:", "Filmes", JOptionPane.PLAIN_MESSAGE, null, opcoes, null);
        if (escolha == null) return;
        long idFilme = Long.parseLong(escolha.split(" ")[1]);
    
        Filme filme = sistema.buscarFilmePorId(idFilme);
        List<Ator> elenco = filme.getElencoPrincipal();
        if (elenco == null || elenco.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum ator principal cadastrado.");
            return;
        }
    
        StringBuilder elencoStr = new StringBuilder("Elenco principal:\n");
        for (Ator ator : elenco) {
            elencoStr.append("ID: ").append(ator.getId()).append(" | Nome: ").append(ator.getNome()).append("\n");
        }
        JOptionPane.showMessageDialog(this, elencoStr.toString());
    }
    ///////////////////////////////////////
    //_______________SERIE_______________//
    ///////////////////////////////////////
    private void adicionarSerieGUI(ActionEvent e) {
        JTextField tituloField = new JTextField();
        JTextField anoField = new JTextField();
        JTextField generoField = new JTextField();
        JTextField duracaoField = new JTextField();
    
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Título:"));
        panel.add(tituloField);
        panel.add(new JLabel("Ano de lançamento:"));
        panel.add(anoField);
        panel.add(new JLabel("Gênero:"));
        panel.add(generoField);
        panel.add(new JLabel("Duração média dos episódios (min):"));
        panel.add(duracaoField);
    
        int result = JOptionPane.showConfirmDialog(null, panel, "Adicionar Serie",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    
        if (result == JOptionPane.OK_OPTION) {
            try {
                String titulo = tituloField.getText().trim();
                int ano = Integer.parseInt(anoField.getText().trim());
                String genero = generoField.getText().trim();
                int duracao = Integer.parseInt(duracaoField.getText().trim());
    
                Serie serie = new Serie();
                serie.setId(System.currentTimeMillis());
                serie.setTitulo(titulo);
                serie.setAnoLanc(ano);
                serie.setGenero(genero);
                serie.setDuracao(duracao);
    
                sistema.adicionarSerie(serie);
                JOptionPane.showMessageDialog(null, "Serie adicionada com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
            }
        }
    }

    private void removerSerie(ActionEvent e) {
        List<Serie> series = sistema.getTodasSeries();
        if (series.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhuma série disponível para remoção.");
            return;
        }
    
        String[] opcoes = series.stream()
            .map(s -> "ID: " + s.getId() + " | Título: " + s.getTitulo())
            .toArray(String[]::new);
    
        String selecao = (String) JOptionPane.showInputDialog(
            this,
            "Escolha uma série para remover:",
            "Remover Série",
            JOptionPane.PLAIN_MESSAGE,
            null,
            opcoes,
            opcoes[0]);
    
        if (selecao == null) return;
    
        long id = Long.parseLong(selecao.split(" ")[1]);
    
        boolean removido = sistema.removerSerie(id);
        if (removido) {
            JOptionPane.showMessageDialog(this, "Série removida com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao remover série.");
        }
    }
    

    private void adicionarTemporadaGUI(ActionEvent e) {
        List<Serie> series = sistema.getTodasSeries();
        if (series.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhuma série disponível.");
            return;
        }
    
        // Lista de séries formatada para exibição
        String[] opcoesSeries = series.stream()
                .map(s -> "ID: " + s.getId() + " | Título: " + s.getTitulo())
                .toArray(String[]::new);
    
        String serieSelecionada = (String) JOptionPane.showInputDialog(
            this,
            "Escolha uma série:",
            "Selecionar Série",
            JOptionPane.PLAIN_MESSAGE,
            null,
            opcoesSeries,
            opcoesSeries[0]);
    
        if (serieSelecionada == null) return;
    
        // Extrai o ID da série selecionada
        long idSerie = Long.parseLong(serieSelecionada.split(" ")[1]);
        Serie serie = sistema.buscarSeriePorId(idSerie);
    
        if (serie == null) {
            JOptionPane.showMessageDialog(this, "Série não encontrada.");
            return;
        }
    
        // Cria uma nova temporada com número automático
        Temporada novaTemporada = new Temporada();
        int proximoNumero = serie.getTemporadas().size() + 1;
        novaTemporada.setNumero(proximoNumero);
        novaTemporada.setEpisodios(new ArrayList<>());
    
        sistema.adicionarTemporada(idSerie, novaTemporada);
        JOptionPane.showMessageDialog(this, "Temporada " + proximoNumero + " adicionada à série \"" + serie.getTitulo() + "\" com sucesso!");
    }
    

    private void adicionarEpisodio(ActionEvent e) {
        List<Serie> series = sistema.getTodasSeries();
        if (series.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhuma série disponível.");
            return;
        }
    
        // Monta a lista de séries para o usuário escolher
        String[] opcoesSeries = series.stream()
                .map(s -> "ID: " + s.getId() + " | Título: " + s.getTitulo())
                .toArray(String[]::new);
    
        String serieSelecionada = (String) JOptionPane.showInputDialog(
            this,
            "Escolha uma série:",
            "Selecionar Série",
            JOptionPane.PLAIN_MESSAGE,
            null,
            opcoesSeries,
            opcoesSeries[0]);
    
        if (serieSelecionada == null) return;
    
        long idSerie = Long.parseLong(serieSelecionada.split(" ")[1]);
        Serie serie = sistema.buscarSeriePorId(idSerie);
    
        if (serie.getTemporadas().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Essa série não possui temporadas.");
            return;
        }
    
        // Monta a lista de temporadas para o usuário escolher
        String[] opcoesTemporadas = serie.getTemporadas().stream()
                .map(t -> "Temporada " + t.getNumero())
                .toArray(String[]::new);
    
        String temporadaSelecionada = (String) JOptionPane.showInputDialog(
            this,
            "Escolha a temporada:",
            "Selecionar Temporada",
            JOptionPane.PLAIN_MESSAGE,
            null,
            opcoesTemporadas,
            opcoesTemporadas[0]);
    
        if (temporadaSelecionada == null) return;
    
        int numeroTemporada = Integer.parseInt(temporadaSelecionada.replaceAll("\\D+", ""));
        Temporada temporada = serie.getTemporadas().stream()
                .filter(t -> t.getNumero() == numeroTemporada)
                .findFirst()
                .orElse(null);
    
        if (temporada == null) {
            JOptionPane.showMessageDialog(this, "Temporada não encontrada.");
            return;
        }
    
        // Coleta os dados do episódio
        JTextField tituloField = new JTextField();
        JTextField duracaoField = new JTextField();
    
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Título do Episódio:"));
        panel.add(tituloField);
        panel.add(new JLabel("Duração (min):"));
        panel.add(duracaoField);
    
        int result = JOptionPane.showConfirmDialog(null, panel, "Adicionar Episódio",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    
        if (result == JOptionPane.OK_OPTION) {
            try {
                String titulo = tituloField.getText().trim();
                int duracao = Integer.parseInt(duracaoField.getText().trim());
    
                Episodio ep = new Episodio();
                ep.setTitulo(titulo);
                ep.setDuracao(duracao);
    
                temporada.getEpisodios().add(ep);
                JOptionPane.showMessageDialog(this, "Episódio adicionado com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        }
    }
    
    private void listarSeriesPorGeneroGUI(ActionEvent e) {
        Set<String> generos = sistema.getGenerosSeries();
        if (generos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum gênero disponível.");
            return;
        }
    
        String genero = (String) JOptionPane.showInputDialog(this, "Selecione o gênero:", "Gêneros", JOptionPane.PLAIN_MESSAGE, null, generos.toArray(), null);
        if (genero == null) return;
    
        List<Serie> series = sistema.getSeriesPorGenero(genero);
        if (series.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhuma série encontrada.");
            return;
        }
    
        String[] colunas = {"ID", "Título", "Ano", "Gênero", "Elenco Principal", "Elenco Secundário", "Temporadas"};
        Object[][] dados = new Object[series.size()][7];
    
        for (int i = 0; i < series.size(); i++) {
            Serie s = series.get(i);
            dados[i][0] = s.getId();
            dados[i][1] = s.getTitulo();
            dados[i][2] = s.getAnoLanc();
            dados[i][3] = s.getGenero();
            dados[i][4] = s.getElencoPrincipal().toString();
            dados[i][5] = s.getElencoSecundario().toString();
            dados[i][6] = s.getTemporadas().size();
        }
    
        JTable tabela = new JTable(dados, colunas);
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setPreferredSize(new Dimension(600, 250));
        JOptionPane.showMessageDialog(this, scrollPane, "Séries do Gênero: " + genero, JOptionPane.PLAIN_MESSAGE);
    }
    
    
    private void verElencoPrincipalSerieGUI(ActionEvent e) {
        List<Serie> series = sistema.getTodasSeries();
        if (series.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhuma série cadastrada.");
            return;
        }

        String[] opcoes = new String[series.size()];
        for (int i = 0; i < series.size(); i++) {
            opcoes[i] = "ID: " + series.get(i).getId() + " | " + series.get(i).getTitulo();
        }
        String escolha = (String) JOptionPane.showInputDialog(this, "Escolha uma série:", "Séries", JOptionPane.PLAIN_MESSAGE, null, opcoes, null);
        if (escolha == null) return;
        long idSerie = Long.parseLong(escolha.split(" ")[1]);

        Serie serie = sistema.buscarSeriePorId(idSerie);
        List<Ator> elenco = serie.getElencoPrincipal();
        if (elenco == null || elenco.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum ator principal cadastrado.");
            return;
        }

        StringBuilder elencoStr = new StringBuilder("Elenco principal:\n");
        for (Ator ator : elenco) {
            elencoStr.append("ID: ").append(ator.getId()).append(" | Nome: ").append(ator.getNome()).append("\n");
        }
        JOptionPane.showMessageDialog(this, elencoStr.toString());
    }

    ////////////////////////////////////////
    //_______________ATORES_______________//
    ////////////////////////////////////////
    private void adicionarAtor(ActionEvent e) {
        JTextField nomeField = new JTextField();
        JTextField dataNascField = new JTextField();
    
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Nome:"));
        panel.add(nomeField);
        panel.add(new JLabel("Ano de nascimento (ex: 1990):"));
        panel.add(dataNascField);
    
        int result = JOptionPane.showConfirmDialog(null, panel, "Adicionar Ator",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    
        if (result == JOptionPane.OK_OPTION) {
            try {
                String nome = nomeField.getText().trim();
                int dataNasc = Integer.parseInt(dataNascField.getText().trim());
    
                // Verifica se já existe ator com esse nome
                boolean existe = sistema.getTodosAtores().stream()
                    .anyMatch(a -> a.getNome().equalsIgnoreCase(nome));
    
                if (existe) {
                    JOptionPane.showMessageDialog(null, "Ator já cadastrado com esse nome!");
                    return;
                }
    
                Ator ator = new Ator();
                ator.setId(System.currentTimeMillis());
                ator.setNome(nome);
                ator.setDataNasc(dataNasc);
    
                sistema.adicionarAtor(ator);
                JOptionPane.showMessageDialog(null, "Ator adicionado com sucesso!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ano de nascimento inválido.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
            }
        }
    }
    
    private void adicionarAtorElencoGUI(ActionEvent e) {
        List<Ator> atores = sistema.getTodosAtores();
        if (atores.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum ator cadastrado.");
            return;
        }
    
        // Selecionar ator pela lista
        String[] opcoesAtores = atores.stream()
            .map(a -> "ID: " + a.getId() + " | Nome: " + a.getNome())
            .toArray(String[]::new);
    
        String escolhaAtor = (String) JOptionPane.showInputDialog(
            this,
            "Escolha um ator:",
            "Atores",
            JOptionPane.PLAIN_MESSAGE,
            null,
            opcoesAtores,
            null);
    
        if (escolhaAtor == null) return;
    
        long idAtor = Long.parseLong(escolhaAtor.split(" ")[1]);
        Ator atorSelecionado = sistema.buscarAtorPorId(idAtor);
    
        // Escolher tipo: Filme ou Série
        String[] opcoesTipo = {"Filme", "Série"};
        String escolhaTipo = (String) JOptionPane.showInputDialog(
            this,
            "Adicionar ator a:",
            "Tipo",
            JOptionPane.PLAIN_MESSAGE,
            null,
            opcoesTipo,
            null);
    
        if (escolhaTipo == null) return;
    
        if (escolhaTipo.equals("Filme")) {
            List<Filme> filmes = sistema.getTodosFilmes();
            if (filmes.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhum filme disponível.");
                return;
            }
    
            String[] opcoesFilmes = filmes.stream()
                .map(f -> "ID: " + f.getId() + " | " + f.getTitulo())
                .toArray(String[]::new);
    
            String escolhaFilme = (String) JOptionPane.showInputDialog(
                this,
                "Escolha um filme:",
                "Filmes",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcoesFilmes,
                null);
    
            if (escolhaFilme == null) return;
    
            long idFilme = Long.parseLong(escolhaFilme.split(" ")[1]);
            Filme filmeSelecionado = sistema.buscarFilmePorId(idFilme);
    
            // Escolher elenco: Principal ou Secundário
            String[] opcoesElenco = {"Principal", "Secundário"};
            String escolhaElenco = (String) JOptionPane.showInputDialog(
                this,
                "Adicionar ao elenco:",
                "Elenco",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcoesElenco,
                null);
    
            if (escolhaElenco == null) return;
    
            // Verifica se o ator já está em algum elenco do filme selecionado
            boolean atorNoFilme = filmeSelecionado.getElencoPrincipal().contains(atorSelecionado)
                    || filmeSelecionado.getElencoSecundario().contains(atorSelecionado);
    
            if (atorNoFilme) {
                JOptionPane.showMessageDialog(this,
                    "Este ator já pertence a um elenco deste filme e não pode ser adicionado novamente.");
                return;
            }
    
            // Adiciona no elenco escolhido
            if (escolhaElenco.equals("Principal")) {
                filmeSelecionado.getElencoPrincipal().add(atorSelecionado);
            } else {
                filmeSelecionado.getElencoSecundario().add(atorSelecionado);
            }
    
            JOptionPane.showMessageDialog(this,
                "Ator adicionado com sucesso ao elenco " + escolhaElenco + " do filme " + filmeSelecionado.getTitulo());
    
        } else { // Série
            List<Serie> series = sistema.getTodasSeries();
            if (series.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhuma série disponível.");
                return;
            }
    
            String[] opcoesSeries = series.stream()
                .map(s -> "ID: " + s.getId() + " | " + s.getTitulo())
                .toArray(String[]::new);
    
            String escolhaSerie = (String) JOptionPane.showInputDialog(
                this,
                "Escolha uma série:",
                "Séries",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcoesSeries,
                null);
    
            if (escolhaSerie == null) return;
    
            long idSerie = Long.parseLong(escolhaSerie.split(" ")[1]);
            Serie serieSelecionada = sistema.buscarSeriePorId(idSerie);
    
            String[] opcoesElenco = {"Principal", "Secundário"};
            String escolhaElenco = (String) JOptionPane.showInputDialog(
                this,
                "Adicionar ao elenco:",
                "Elenco",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcoesElenco,
                null);
    
            if (escolhaElenco == null) return;
    
            // Verifica se o ator já está em algum elenco da série selecionada
            boolean atorNaSerie = serieSelecionada.getElencoPrincipal().contains(atorSelecionado)
                    || serieSelecionada.getElencoSecundario().contains(atorSelecionado);
    
            if (atorNaSerie) {
                JOptionPane.showMessageDialog(this,
                    "Este ator já pertence a um elenco desta série e não pode ser adicionado novamente.");
                return;
            }
    
            // Adiciona no elenco escolhido
            if (escolhaElenco.equals("Principal")) {
                serieSelecionada.getElencoPrincipal().add(atorSelecionado);
            } else {
                serieSelecionada.getElencoSecundario().add(atorSelecionado);
            }
    
            JOptionPane.showMessageDialog(this,
                "Ator adicionado com sucesso ao elenco " + escolhaElenco + " da série " + serieSelecionada.getTitulo());
        }
    }
    
    
    

    
    
    
    

}
