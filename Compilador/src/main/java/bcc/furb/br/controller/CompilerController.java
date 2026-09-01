package bcc.furb.br.controller;

import bcc.furb.br.services.Token;
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
        Lexico lexico = new Lexico();
        lexico.setInput(view.getEditorText());
        
        try { 
            Token t = null;

            while ( (t = lexico.nextToken()) != null ) { 
                StringBuilder stringBuilder= new StringBuilder();

                stringBuilder.append((t.getLexeme() + " - " + t.getId() + " - " + t.getPosition()));

            } 
        }  catch ( LexicalError e ) { 
            
        }
    }

    public void handleTeam() {
        view.setConsoleMessage("Equipe de desenvolvimento:\n- Cauã Bertolini\n- Cesar Augusto Floriani\n- Eduarda Mazureck");
    }


}
