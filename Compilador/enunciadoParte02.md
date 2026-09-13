# Trabalho Final – Parte 2: Implementação do Analisador Léxico

Implementar o analisador léxico de forma que identifique, nos programas escritos na linguagem **2026.2**, os tokens corretos, levando em consideração as especificações/correções feitas no trabalho nº1, assim como as palavras reservadas e os símbolos especiais relacionados a seguir.

Deve-se implementar também tratamento de **erros léxicos**, quais sejam:
- símbolos que não fazem parte da linguagem em questão;
- sequências de símbolos que não obedecem às regras de formação dos tokens especificados.

O analisador léxico (para Windows 8 ou superior) deve ser implementado em **Java**, utilizando o **GALS** (ferramenta para geração de analisadores léxicos e sintáticos).

## Entrada e saída

**Entrada:** um conjunto de caracteres, isto é, o programa fonte do editor do compilador.

**Saída:** ao pressionar o botão compilar (ou a tecla de atalho correspondente), deve-se executar a análise léxica do programa fonte e apresentar a saída, em uma das duas situações abaixo.

### 1ª situação: programa compilado com sucesso

- Apagar o conteúdo da área reservada para mensagens.
- Apresentar a lista de tokens na área de mensagens, contendo, para cada token reconhecido, **nessa ordem**: linha onde se encontra, classe (por extenso) e lexema.
- Apresentar a mensagem **"programa compilado com sucesso"**, indicando que o programa não apresenta erros.

**Classes possíveis para os tokens:**
- símbolo especial
- palavra reservada
- identificador (de int, de float, de string, de bool — todos classificados como "identificador")
- constante_int
- constante_float
- constante_string

### 2ª situação: programa apresenta erros

- Apagar o conteúdo da área reservada para mensagens.
- Apresentar a mensagem indicando que o programa apresenta erro, informando a linha onde ocorreu o erro e a descrição do erro:

| Tipo de erro | Mensagem apresentada |
|---|---|
| Símbolo inválido | mensagem "símbolo inválido" + linha + o símbolo não reconhecido |
| Palavra reservada inválida | mensagem "palavra reservada inválida" + linha + a palavra não reconhecida |
| Identificador inválido | mensagem "identificador inválido" + linha (sem mostrar a sequência) |
| Constante string inválida | mensagem "constante_string inválida" + linha (sem mostrar a sequência) |
| Comentário inválido | mensagem "comentário inválido ou não finalizado" + linha **onde inicia o comentário** |

## Observações da linguagem 2026.2

**Palavras reservadas:**
`and` `false` `if` `in` `isFalseDo` `isTrueDo` `module` `not` `or` `out` `true` `while`

As palavras reservadas devem ser definidas como casos especiais de `palavra_reservada` (ou outro nome), conforme especificado pela equipe no trabalho nº1. Sequências de símbolos que seguem o padrão de formação das palavras reservadas, mas diferem das relacionadas acima, constituem erro léxico.

**Símbolos especiais:**
`,` `:` `;` `[` `]` `(` `)` `{` `}` `+` `-` `*` `/` `<-` `=` `<>` `<` `>`

Símbolos diferentes dos relacionados constituem erro léxico.

**Comentários e formatação:**
- Comentários de bloco e caracteres de formatação (espaços em branco, quebra de linha, tabulação) devem ser reconhecidos, porém **ignorados** (não aparecem na saída). Isso deve ser especificado no arquivo `.gals`.
- Comentários que não seguem o padrão de formação especificado no trabalho nº1 devem ser diagnosticados como erro léxico.

**Formato das mensagens de erro** (exemplos):
```
linha 1: @ símbolo inválido
linha 1: iftruedo palavra reservada inválida
linha 1: identificador inválido
linha 1: constante_string inválida
linha 1: comentário inválido ou não finalizado
```

> No caso de identificador, constante_string e comentário inválidos, a sequência não reconhecida **não** deve ser apresentada na mensagem — só a linha.

## Importante sobre o trabalho nº1

- As especificações do trabalho nº1 devem ser **adaptadas à notação do GALS**.
- Um analisador léxico com especificações diferentes das elaboradas pela própria equipe no trabalho nº1 (com as devidas adaptações) recebe **nota 0,0**.
- O trabalho nº1 deve ser devolvido à professora na aula de **31/08**; caso contrário, o analisador léxico recebe **nota 0,0**.

## Entrega

- Disponibilizar no **AVA**, na tarefa "parte 2 - analisador léxico".
- Arquivo compactado com o nome `lexico` + número da equipe, contendo: projeto completo (código fonte, `.jar`, arquivo `.gals` com as especificações léxicas, e demais recursos).
- Basta um integrante da equipe postar o trabalho.
- **Se não forem postados todos os arquivos solicitados, ou não for possível rodar o `.jar`, nota 0,0.**
- Avaliação considera: a correta adaptação dos tokens (definidos no trabalho nº1) à notação do GALS; a qualidade das mensagens de erro.

## Data de entrega

**Até às 23h do dia 18/09/2026.** Só serão aceitas entregas via AVA até a data e hora determinadas.

## Exemplos de entrada/saída

### Exemplo 1 — sem erro léxico

**Entrada:**
```
1: #
2: isso é um comentário de bloco
3: especificado pela professora
4: #
5: in i_area (
6: 1,00
```

**Saída (área de mensagens):**

| linha | classe | lexema |
|---|---|---|
| 6 | palavra reservada | in |
| 6 | identificador | i_area |
| 6 | símbolo especial | ( |
| 8 | constante_float | 1,00 |

```
programa compilado com sucesso
```

### Exemplo 2 — com erro léxico

**Entrada:**
```
1: #
2: isso é um comentário de bloco
3: especificado pela professora
4: #
5: in i_area ( @
6: 1,00
```

**Saída (área de mensagens):**
```
linha 6: @ símbolo inválido
```