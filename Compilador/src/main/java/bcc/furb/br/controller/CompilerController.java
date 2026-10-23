package bcc.furb.br.controller;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import bcc.furb.br.services.gals.Constants;
import bcc.furb.br.services.gals.LexicalError;
import bcc.furb.br.services.gals.Lexico;
import bcc.furb.br.services.gals.SemanticError;
import bcc.furb.br.services.gals.Semantico;
import bcc.furb.br.services.gals.Sintatico;
import bcc.furb.br.services.gals.SyntaticError;
import bcc.furb.br.services.gals.Token;
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
        Sintatico sintatico = new Sintatico();
        Semantico semantico = new Semantico();

        lexico.setInput(new StringReader(input));

        try {
            sintatico.parse(lexico, semantico);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\nprograma compilado com sucesso");
            writeInConsole(stringBuilder.toString()); // tradução dirigida pela sintaxe
            // mensagem: programa compilado com sucesso - na área reservada para mensagens

        } catch (LexicalError e) {
            // tratar erros léxicos: apresentar linha onde o erro foi detectado e mensagem
            // "erro léxico"
            String msg = e.getMessage();
            String textoErro;
            int linha = calcularLinha(input, e.getPosition());

            textoErro = "linha " + linha + ": " + e.getMessage();

            writeInConsole(textoErro);

        } catch (SyntaticError e) {

            String msg = e.getMessage();
            String textoErro;
            int linha = calcularLinha(input, e.getPosition());
            Token encontrado = buscarToken(input, e.getPosition());

            textoErro = ("linha " + linha + ": encontrado " + encontrado + " esperado " + e.getMessage());
            
            writeInConsole(textoErro);
            // SERÁ EXPLICADO NA AULA DE 08/10

            // e.getMessage() - são os símbolos esperados
            // e.getMessage() - retorna a mensagem de erro de PARSER_ERROR
            // (ver ParserConstants.java)
            // necessário adaptar conforme o enunciado da parte 3

            // e.getPosition() - retorna a posição inicial do erro
            // necessário adaptar para mostrar a linha

            // necessário mostrar também o símbolo encontrado
        } catch (SemanticError e) {
        }
    }

    // O Sintatico gerado não expõe o token atual (e não pode ser alterado), então
    // relê a entrada até o token que começa na posição do erro. null = fim de arquivo ($)
    private Token buscarToken(String input, int posicao) {

        Lexico lexico = new Lexico();
        lexico.setInput(new StringReader(input));

        try {
            Token t;
            while ((t = lexico.nextToken()) != null && t.getPosition() <= posicao) {
                if (t.getPosition() == posicao) {
                    return t;
                }
            }
        } catch (LexicalError e) {
            // não ocorre antes da posição do erro: o parser já leu esses tokens sem erro léxico
        }
        return null;
    }

    private int calcularLinha(String input, int posicao) {
        int linha = 1;
        for (int i = 0; i < posicao && i < input.length(); i++) {
            if (input.charAt(i) == '\n') {
                linha++;
            }
        }
        return linha;
    }

    public void handleTeam() {
        writeInConsole(
                "Equipe de desenvolvimento:\n- Cauã Bertolini\n- Cesar Augusto Floriani\n- Eduarda Mazureck");
    }

    private void writeInConsole(String mensagem) {
        view.setConsoleMessage(mensagem);
    }

}
