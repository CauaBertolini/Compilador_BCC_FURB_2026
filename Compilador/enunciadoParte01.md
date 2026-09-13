# Trabalho Final – Parte 1: Implementação da Interface do Compilador

A interface do compilador (para Windows 8 ou superior) deve ser implementada em **Java**, conforme especificado abaixo.

## Especificação por item

### Geral

1. **(0,6 pontos)** A interface do compilador deve ter tamanho fixo **1500x800**, ou seja, não deve ser possível alterar o tamanho da janela. No entanto, deve ser possível minimizar e fechar a janela.
2. **(0,8 pontos)** A interface do compilador deve ter os seguintes componentes:
   - uma **barra de ferramentas** (tamanho 150 x n, onde n é o tamanho máximo para que o componente fique totalmente visível na vertical);
   - uma área para edição de programas – **editor** (tamanho suficiente para ficar totalmente visível);
   - uma área para visualização das **mensagens** (tamanho suficiente para ficar totalmente visível);
   - uma **barra de status** (tamanho m x 25, onde m é o tamanho máximo para que o componente fique totalmente visível na horizontal);

   dispostos e visualizados conforme a Figura 1.
3. **(0,6 pontos)** Deve ser possível alterar o tamanho vertical do editor e da área para mensagens movendo a barra de divisão existente entre eles.

### Editor

4. **(1,0 ponto)** Deve apresentar o número da linha, à esquerda, iniciando em 1. Não deve ser possível alterar o número da linha.
5. **(0,6 pontos)** Deve possuir barras de rolagem (horizontal e vertical), que devem ser visualizadas mesmo sem texto editado.

### Área para mensagens

6. **(0,2 pontos)** Não deve ser possível editar texto na área para mensagens.
7. **(0,6 pontos)** Deve possuir barras de rolagem (horizontal e vertical), que devem ser visualizadas mesmo sem mensagens.

### Barra de status

8. **(0,6 pontos)** Deve mostrar a pasta e o nome do arquivo aberto.

### Barra de ferramentas

9. **(0,8 pontos)** Deve possuir botões, todos com o mesmo tamanho, com ações para:
   - manipulação de arquivos (novo, abrir, salvar);
   - edição de textos (copiar, colar, recortar);
   - compilação de programas (compilar);
   - informações sobre o compilador (equipe).

   Os botões devem estar dispostos na ordem descrita acima. Cada botão deve possuir um ícone, um nome e a indicação da tecla de atalho associada. Nenhum nome deve ser abreviado.

   | Botão | Atalho |
   |---|---|
   | novo | Ctrl+N |
   | abrir | Ctrl+O |
   | salvar | Ctrl+S |
   | copiar | Ctrl+C |
   | colar | Ctrl+V |
   | recortar | Ctrl+X |
   | compilar | F7 |
   | equipe | F1 |

10. **(0,6 pontos)** Botão **novo**: ao ser pressionado (ou tecla de atalho), deve: limpar o editor¹, limpar a área para mensagens² e limpar a barra de status³.

11. **(1,3 pontos)** Botão **abrir**: ao ser pressionado (ou tecla de atalho), deve: possibilitar a seleção de pasta/arquivo, carregar o arquivo selecionado no editor, limpar a área para mensagens² e atualizar a barra de status⁴.
    - Caso nenhum arquivo seja selecionado, o estado anterior da interface deve ser mantido (editor mantém o texto, mensagens não são limpas, status não é atualizado).
    - Devem ser abertos arquivos texto (extensão `.txt`) compatíveis com o Notepad.

12. **(1,3 pontos)** Botão **salvar**: ao ser pressionado (ou tecla de atalho):
    - se o arquivo for novo: possibilitar a seleção de pasta/nome do arquivo, salvar o conteúdo do editor, manter o texto no editor, limpar a área para mensagens² e atualizar a barra de status⁴;
    - caso contrário: salvar as alterações do arquivo editado, manter o texto no editor, limpar a área para mensagens² e manter a barra de status⁵.
    - Devem ser salvos arquivos texto (extensão `.txt`) compatíveis com o Notepad.

13. **(0,6 pontos)** As ações dos botões de edição (copiar, colar, recortar) e suas teclas de atalho devem funcionar como em editores de texto convencionais.

14. **(0,2 pontos)** Botão **compilar**: ao ser pressionado (ou tecla de atalho), deve apresentar a mensagem *"compilação de programas ainda não foi implementada"* na área para mensagens. Somente essa mensagem deve ser exibida.

15. **(0,2 pontos)** Botão **equipe**: ao ser pressionado (ou tecla de atalho), deve apresentar os nomes dos integrantes da equipe na área para mensagens. Somente essa mensagem deve ser exibida.

---

¹ *Limpar o editor*: apagar seu conteúdo.
² *Limpar a área para mensagens*: apagar seu conteúdo.
³ *Limpar a barra de status*: apagar seu conteúdo e "esquecer" a pasta e o nome do arquivo editado.
⁴ *Atualizar a barra de status*: mostrar o nome da pasta e do arquivo aberto.
⁵ *Manter a barra de status*: continuar mostrando o nome da pasta e do arquivo editado.

## Observações

- O compilador poderá ser desenvolvido em equipes com **no mínimo dois e no máximo três estudantes**.
- A implementação da interface deve ser disponibilizada no **AVA**, na tarefa "parte 1 - interface".
- Deve ser disponibilizado **um arquivo compactado** (nome: `interface` + número da equipe) contendo: projeto completo (código fonte, `.jar` e demais recursos). Basta um integrante postar o trabalho.
- **Caso não sejam postados todos os arquivos solicitados, ou não seja possível rodar o `.jar`, a interface recebe nota 0,0 (zero).**
- Na avaliação será considerada a correta (e completa) implementação de cada item.

## Data de entrega

**Até às 23h do dia 28/08/2026.** Só serão aceitas entregas via AVA até a data e hora determinadas.

## Figura 1: Interface solicitada

Layout geral da janela (1500x800):

```
┌────────────────────────────────────────────────────────┐
│ [ícone]                                              X  │
├──────────────┬───────────────────────────────────────┤
│              │ 3                                        │
│ novo [ctrl-n]│ 4   ← editor (numeração de linha à       │
│ abrir[ctrl-o]│ 5     esquerda, rolagem H/V)              │
│ salvar[ctrl-s]│...                                       │
│              │                                           │
│ copiar[ctrl-c]├── barra de divisão (redimensiona editor  │
│ colar [ctrl-v]│    e área de mensagens) ─────────────────┤
│ recortar[ctrl-x]│                                        │
│              │  ← área para mensagens (rolagem H/V)      │
│ compilar[F7] │                                           │
│ equipe [F1]  │                                           │
├──────────────┴───────────────────────────────────────┤
│ pasta\nome do arquivo                     (barra status) │
└────────────────────────────────────────────────────────┘
```

- **Barra de ferramentas**: coluna à esquerda com os 8 botões.
- **Editor**: área principal à direita, com numeração de linhas.
- **Barra de divisão**: entre editor e área de mensagens, redimensionável.
- **Área para mensagens**: abaixo do editor.
- **Barra de status**: rodapé, mostra `pasta\nome do arquivo`.