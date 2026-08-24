# Compilador - BCC FURB

Este projeto consiste na implementação da interface gráfica (IDE/Editor de texto) e motor de compilação desenvolvidos como parte da disciplina de **Compiladores** do curso de Bacharelado em Ciências da Computação na **FURB** (Universidade Regional de Blumenau).

A aplicação foi desenvolvida em Java (Swing) utilizando conceitos modernos de engenharia de software para separação de responsabilidades e reutilização de código.

---

## 🏗️ Arquitetura e Estrutura MVC

O projeto adota o padrão **MVC (Model-View-Controller)** para separar a lógica de negócio e manipulação de arquivos das definições e renderização visuais:

### 1. View (`bcc.furb.br.view`)
- **`CompilerFrame.java`**: Representa a janela principal do compilador. É construída de forma 100% manual e programática. Ela não gerencia o estado do arquivo aberto nem faz operações de leitura/escrita; em vez disso, expõe métodos semânticos públicos (como `getEditorText()`, `setConsoleMessage()`, `setStatusMessage()`) e delega todas as ações do usuário (clique nos botões, atalhos do teclado) para o Controller.
- **`NumberedBorder.java`**: Um componente auxiliar do Swing que atua como uma borda personalizada no campo de edição de código. Ela calcula e desenha dinamicamente os números de linhas na margem esquerda da caixa de texto, garantindo que o número de linhas esteja sempre alinhado com o texto editado de forma automática e eficiente, eliminando a necessidade de componentes de sincronização manual.

### 2. Controller (`bcc.furb.br.controller`)
- **`CompilerController.java`**: Centraliza a lógica de controle da IDE. Ele gerencia o estado da aplicação (como a referência ao arquivo atualmente aberto - `currentFile`), manipula o sistema de arquivos através de caixas de diálogo do `JFileChooser` (com codificação UTF-8) e coordena a execução de tarefas como "Novo", "Abrir", "Salvar", "Compilar" e exibição da "Equipe".

### 3. Ponto de Entrada (`bcc.furb.br`)
- **`Main.java`**: Configura o Look and Feel padrão do sistema operacional, instancia a View (`CompilerFrame`) e o Controller (`CompilerController`), injeta a dependência do Controller na View e exibe a janela principal para o usuário.

---

## 🚀 Como Executar o Projeto

Você pode compilar e executar o projeto diretamente por linha de comando ou gerar um arquivo empacotado executável `.jar`.

### Pré-requisitos
- **Java JDK 21** ou superior instalado e configurado no PATH.
- **Apache Maven** instalado e configurado no PATH (para comandos `mvn`).

---

### Opção A: Executando por Comando (Maven ou Java)

#### 1. Via Maven Wrapper/Maven Global:
Para baixar as dependências, limpar e compilar o código fonte:
```bash
mvn clean compile
```

Para executar a aplicação diretamente:
```bash
mvn exec:java -Dexec.mainClass="bcc.furb.br.Main"
```

#### 2. Via Java CLI Direto (se não possuir Maven instalado):
Se o diretório já contiver as classes compiladas ou se você preferir compilar manualmente os arquivos `.java`:
```bash
# Criar diretório de saída
mkdir target\classes

# Compilar todas as classes do projeto
javac -d target/classes -sourcepath src/main/java src/main/java/bcc/furb/br/Main.java src/main/java/bcc/furb/br/view/CompilerFrame.java src/main/java/bcc/furb/br/view/NumberedBorder.java src/main/java/bcc/furb/br/controller/CompilerController.java

# Executar a aplicação apontando para a classe Main
java -cp target/classes bcc.furb.br.Main
```

---

### Opção B: Criando e Executando o arquivo `.jar`

#### 1. Gerar o executável `.jar`:
Você pode usar o Maven para empacotar a aplicação em um único arquivo JAR executável autossuficiente:
```bash
mvn clean package
```
O arquivo JAR será gerado na pasta `target/` com o nome `Compilador-1.0-SNAPSHOT.jar` (conforme definido no `pom.xml`).

#### 2. Executar o arquivo `.jar`:
Navegue até o diretório do projeto e execute:
```bash
java -jar target/Compilador-1.0-SNAPSHOT.jar
```
*(Ou execute o script em lote `run.bat` que automatiza essa inicialização).*
