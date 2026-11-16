# Trabalho Final – Parte 2: Implementação do Analisador Léxico

Implementar o **analisador léxico** de forma que identifique, nos programas escritos na linguagem **2026.2**, os *tokens* corretos, levando em consideração as especificações/correções feitas no trabalho nº1, assim como as palavras reservadas e os símbolos especiais relacionados a seguir.

Deve-se implementar também tratamento de **erros léxicos**, quais sejam:
- símbolos que não fazem parte da linguagem em questão;
- sequências de símbolos que não obedecem às regras de formação dos *tokens* especificados.

O analisador léxico (para Windows 8 ou superior) deve ser implementado em **Java**, conforme especificado abaixo. Na implementação do analisador léxico deve ser utilizado o **GALS**, ferramenta para geração de analisadores léxicos e sintáticos.

## Entrada e saída

**Entrada:** um conjunto de caracteres, isto é, o programa fonte do editor do compilador.

**Saída:** caso o botão **compilar** seja pressionado (ou a tecla de atalho correspondente), a ação deve ser: executar a análise léxica do programa fonte e apresentar a saída. Um programa pode ser compilado com sucesso ou apresentar erros, em uma das duas situações abaixo.

### 1ª situação: programa compilado com sucesso

- Apagar o conteúdo da área reservada para mensagens.
- Apresentar a **lista de tokens** na área reservada para mensagens, contendo, para cada *token* reconhecido, **nessa ordem**: a linha onde se encontra, a sua classe (por extenso) e o lexema, conforme exemplo.
- Apresentar a mensagem **"programa compilado com sucesso"**, na área reservada para mensagens, indicando que o programa não apresenta erros.

**Classes possíveis para os tokens:**
- símbolo especial
- palavra reservada
- identificador (de `int`, de `float`, de `string`, de `bool` — todos classificados como "identificador")
- constante_int
- constante_float
- constante_string

### 2ª situação: programa apresenta erros

- Apagar o conteúdo da área reservada para mensagens.
- Apresentar a **mensagem** na área reservada para mensagens, indicando que o programa apresenta erro. Neste caso, indicar a linha onde ocorreu o erro e a descrição do erro, emitindo uma mensagem adequada:

| Tipo de erro | O que apresentar |
|---|---|
| Símbolo inválido | a mensagem *símbolo inválido*, a linha onde ocorreu o erro e o símbolo não reconhecido |
| Palavra reservada inválida | a mensagem *palavra reservada inválida*, a linha onde ocorreu o erro e a palavra não reconhecida |
| Identificador inválido | a mensagem *identificador inválido* e a linha onde ocorreu o erro |
| Constante string inválida | a mensagem *constante_string inválida* e a linha onde ocorreu o erro |
| Comentário inválido | a mensagem *comentário inválido ou não finalizado* e a linha onde ocorreu o erro — nesse caso, a linha **onde inicia** o comentário |

> As mensagens geradas pelo GALS devem ser alteradas conforme as orientações dadas em aula.

## Observações

**Palavras reservadas** da linguagem 2026.2:

`and` `false` `if` `in` `isFalseDo` `isTrueDo` `module` `not` `or` `out` `true` `while`

As palavras reservadas devem ser definidas como **casos especiais** de `palavra_reservada` (ou outro nome), conforme especificado pela equipe no trabalho nº1. Sequências de símbolos que seguem o padrão de formação das palavras reservadas, mas são diferentes das relacionadas acima, constituem erro léxico.

**Símbolos especiais** da linguagem 2026.2:

`,` `:` `;` `[` `]` `(` `)` `{` `}` `+` `-` `*` `/` `<-` `=` `<>` `<` `>`

Símbolos diferentes dos relacionados acima constituem erro léxico.

**Comentários e formatação:**
- Os comentários de bloco e os caracteres de formatação (espaços em branco, quebra de linha, tabulação) devem ser reconhecidos, porém **ignorados**, ou seja, não devem ser apresentados como saída do analisador léxico. Isso deve ser especificado no GALS, no arquivo com as especificações léxicas (arquivo `.gals`).
- Comentários que não seguem o padrão de formação especificado no trabalho nº1 devem ser diagnosticados como erro léxico.

**Formato das mensagens de erro** (exemplos):
```
linha 1: @ símbolo inválido
linha 1: iftruedo palavra reservada inválida
linha 1: identificador inválido
linha 1: constante_string inválida
linha 1: comentário inválido ou não finalizado
```

- No primeiro exemplo, o símbolo `@` não é um símbolo especial da linguagem, portanto caracteriza um erro léxico e deve ser apresentado na mensagem de erro, juntamente com a linha onde o erro foi detectado.
- No segundo exemplo, a palavra `iftruedo` não é uma palavra reservada da linguagem e deve ser apresentada na mensagem de erro, juntamente com a linha onde o erro foi detectado.
- Nos demais exemplos, deve ser apresentada a mensagem de erro juntamente com a linha onde o erro foi detectado, ou seja, a sequência não reconhecida **não** deve ser apresentada na mensagem de erro.

## Importante sobre o trabalho nº1

As especificações feitas no trabalho nº1 (e já corrigidas) devem ser usadas para implementação do analisador léxico. Observa-se que:

1. as especificações feitas no trabalho nº1 devem ser **adaptadas à notação do GALS**;
2. o analisador léxico desenvolvido usando especificações diferentes daquelas elaboradas pela equipe no trabalho nº1 (com as devidas adaptações) receberá **nota 0,0**;
3. o trabalho nº1 deve ser devolvido à professora na aula de **31/08**; caso contrário, o analisador léxico receberá **nota 0,0**.

## Entrega

- A implementação do analisador léxico, bem como da interface do compilador, deve ser disponibilizada no **AVA**, na tarefa "**parte 2 - analisador léxico**".
- Deve ser disponibilizado **um arquivo compactado** (com o nome `lexico` seguido do número da equipe) contendo o projeto completo, incluindo **código fonte**, **arquivo `.jar`**, **arquivo com as especificações léxicas (`.gals`)** e demais recursos.
- Basta um integrante da equipe postar o trabalho.
- **Caso não sejam postados todos os arquivos solicitados ou não seja possível rodar o arquivo `.jar`, será atribuída nota 0,0 (zero) ao analisador léxico.**
- Na avaliação serão levadas em consideração: a correta adaptação dos *tokens*, definidos no trabalho nº1, à notação do GALS; a qualidade das mensagens de erro, conforme descrito acima.

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
5:
6: in i_area  (
7:
8: 1,00
```

**Saída (área de mensagens):**
```
linha   classe              lexema
6       palavra reservada   in
6       identificador       i_area
6       símbolo especial    (
8       constante_float     1,00

        programa compilado com sucesso
```

### Exemplo 2 — com erro léxico

**Entrada:**
```
1: #
2: isso é um comentário de bloco
3: especificado pela professora
4: #
5:
6: in i_area  ( @
7:
8: 1,00
```

**Saída (área de mensagens):**
```
linha 6: @ símbolo inválido
```
