package bcc.furb.br.controller;

import bcc.furb.br.services.*;
import java.util.ArrayList;
import java.util.List;
import bcc.furb.br.view.CompilerFrame;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CompilerController {

    private final CompilerFrame view;
    private File currentFile = null;

    public CompilerController(CompilerFrame view) {
        this.view = view;
    }

    public void handleNew() {
        currentFile = null;
        view.setEditorText("");
        view.setConsoleMessage("");
        view.setStatusMessage(" ");
        view.requestEditorFocus();
    }

    public void handleOpen() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Abrir Arquivo de Texto");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Arquivos de Texto (*.txt)", "txt"));

        int result = fileChooser.showOpenDialog(view);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                byte[] bytes = Files.readAllBytes(selectedFile.toPath());
                String content = new String(bytes, StandardCharsets.UTF_8);

                view.setEditorText(content);
                currentFile = selectedFile;
                view.setConsoleMessage("");
                view.setStatusMessage(currentFile.getParent() + File.separator + currentFile.getName());
                view.requestEditorFocus();
            } catch (IOException ex) {
                view.showErrorDialog("Erro de I/O", "Erro ao abrir o arquivo:\n" + ex.getMessage());
            }
        }
    }

    public void handleSave() {
        if (currentFile == null) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Salvar Arquivo Como");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Arquivos de Texto (*.txt)", "txt"));

            int result = fileChooser.showSaveDialog(view);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                if (!selectedFile.getName().toLowerCase().endsWith(".txt")) {
                    selectedFile = new File(selectedFile.getParentFile(), selectedFile.getName() + ".txt");
                }

                try {
                    Files.write(selectedFile.toPath(), view.getEditorText().getBytes(StandardCharsets.UTF_8));
                    currentFile = selectedFile;
                    view.setConsoleMessage("");
                    view.setStatusMessage(currentFile.getParent() + File.separator + currentFile.getName());
                    view.requestEditorFocus();
                } catch (IOException ex) {
                    view.showErrorDialog("Erro de I/O", "Erro ao salvar o arquivo:\n" + ex.getMessage());
                }
            }
        } else {
            try {
                Files.write(currentFile.toPath(), view.getEditorText().getBytes(StandardCharsets.UTF_8));
                view.setConsoleMessage("");
                view.requestEditorFocus();
            } catch (IOException ex) {
                view.showErrorDialog("Erro de I/O", "Erro ao salvar o arquivo:\n" + ex.getMessage());
            }
        }
    }

    public void handleCompile() {
        String input = view.getEditorText();
        Lexico lexico = new Lexico();
        getTokens(input, lexico);
    }

    public void handleTeam() {
        writeInConsole(
                "Equipe de desenvolvimento:\n- Cauã Bertolini\n- Cesar Augusto Floriani\n- Eduarda Mazureck");
    }

    public void getTokens(String input, Lexico lexico) {
        lexico.setInput(input);

        List<Token> tokens = new ArrayList<>();

        try {
            Token t = null;
            while ((t = lexico.nextToken()) != null) {
                tokens.add(t);
            }

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(String.format("%-10s | %-25s | %s\n", "LINHA", "CLASSE", "LEXEMA"));
            stringBuilder.append("----------------------------------------------------------------\n");
            for (Token token : tokens) {
                int linha = calcularLinha(input, token.getPosition());
                String classe = getNomeClasse(token.getId());
                stringBuilder.append(String.format("%-10d | %-25s | %s\n", linha, classe, token.getLexeme()));
            }
            stringBuilder.append("\nPrograma compilado com sucesso!");
            writeInConsole(stringBuilder.toString());

        } catch (LexicalError e) {
            int linhaErro = calcularLinha(input, e.getPosition());
            String mensagem = e.getMessage();
            if (mensagem == null || mensagem.trim().isEmpty()) {
                mensagem = "erro léxico";
            }
            writeInConsole("linha " + linhaErro + ": " + mensagem);
        }
    }

    private int calcularLinha(String texto, int posicao) {
        int linha = 1;
        for (int i = 0; i < posicao && i < texto.length(); i++) {
            if (texto.charAt(i) == '\n') {
                linha++;
            }
        }
        return linha;
    }

    private String getNomeClasse(int id) {
        if (id == Constants.t_palavraReservada || (id >= Constants.t_and && id <= Constants.t_while)) {
            return "Palavra reservada";
        }
        return switch (id) {
            case Constants.t_idInt -> "Identificador de int";
            case Constants.t_idFloat -> "Identificador de float";
            case Constants.t_idString -> "Identificador de string";
            case Constants.t_idBool -> "Identificador de bool";
            case Constants.t_constInt -> "Constante int";
            case Constants.t_constFloat -> "Constante float";
            case Constants.t_constString -> "Constante string";
            default -> {
                if (id >= Constants.t_TOKEN_22 && id <= Constants.t_TOKEN_39) {
                    yield "Símbolo especial";
                }
                yield "Desconhecido";
            }
        };
    }

    private void writeInConsole(String mensagem) {
        view.setConsoleMessage(mensagem);
    }

}
