# Trabalho Final – Parte 3: Implementação do Analisador Sintático

Implementar o **analisador sintático** de forma que indique quais programas escritos na linguagem **2026.2** estão sintaticamente corretos.

Deve-se implementar também tratamento de **erros sintáticos**, quais sejam: *tokens* que não estão na sequência definida na gramática.

Se necessário, efetuar as correções/alterações nas especificações dos *tokens* e na BNF, **conforme indicado na avaliação do trabalho nº3**. A não correção/alteração pode implicar em não reconhecimento de programas sintaticamente corretos.

O analisador sintático (para Windows 8 ou superior) deve ser implementado em **Java**, utilizando o **GALS** (ferramenta para geração de analisadores léxicos e sintáticos).

## Entrada e saída

**Entrada:** um conjunto de caracteres, isto é, o programa fonte do editor do compilador.

**Saída:** ao pressionar o botão **compilar** (ou a tecla de atalho correspondente), deve-se executar as análises **léxica e sintática** do programa fonte e apresentar a saída, em uma das duas situações abaixo.

### 1ª situação: programa compilado com sucesso

- Apagar o conteúdo da área reservada para mensagens.
- Apresentar a mensagem **"programa compilado com sucesso"**, indicando que o programa não apresenta erros.

> A **lista de tokens não deve mais** ser mostrada na área reservada para mensagens.

### 2ª situação: programa apresenta erros

- Apagar o conteúdo da área reservada para mensagens.
- Apresentar a mensagem indicando que o programa apresenta erro. O erro pode ser **léxico** ou **sintático**, com as mensagens descritas abaixo.

## Observações

- O tipo do analisador sintático a ser gerado é **LL(1)**.
- **Erros léxicos:** a mensagem deve indicar a linha onde ocorreu o erro e a descrição `erro léxico`.
- **Erros sintáticos:** a mensagem deve indicar a linha onde ocorreu, o *lexema* encontrado e o(s) símbolo(s) esperado(s). Exemplos:
  ```
  linha 1: encontrado EOF esperado expressão
  linha 1: encontrado area esperado (
  ```

> Em todo o enunciado, `...` representa o(s) símbolo(s) esperado(s) ou o símbolo encontrado.

### Símbolo encontrado

| Situação | Mensagem |
|---|---|
| Encontrada uma `constante_string` (ex.: `"olá mundo"`) | `encontrado constante_string esperado ...` |
| Encontrado `$` (fim de arquivo) | `encontrado EOF esperado ...` |
| Encontrado símbolo especial, palavra reservada, identificador (de int, float, string, bool — todos "identificador"), `constante_int` ou `constante_float` | mostrar o **lexema**: `encontrado { esperado ...`; `encontrado module esperado ...`; `encontrado f_area esperado ...`; `encontrado 123 esperado ...`; `encontrado 12,3 esperado ...` |

### Símbolo esperado

| Situação | Mensagem |
|---|---|
| Esperado fim de programa | `encontrado ... esperado EOF` |
| Esperado identificador (de int, float, string, bool) | `encontrado ... esperado identificador` |
| Esperado `constante_int` | `encontrado ... esperado constante_int` |
| Esperado `constante_float` | `encontrado ... esperado constante_float` |
| Esperado `constante_string` | `encontrado ... esperado constante_string` |
| Esperada palavra reservada ou símbolo especial | escrever **exatamente** como o programador deve escrevê-los no programa |

Podem ser esperados outros símbolos além desses; nesse caso, a mensagem de erro deve ser complementada.

### Mensagens por não-terminal

| Não-terminal | Mensagem |
|---|---|
| `<lista_de_expressoes>` (ou outro nome), usado no comando `out` conforme trabalho nº3 | `encontrado ... esperado expressao` |
| Não-terminal para `<expressao>` opcional entre colchetes, ex.: `<expressao_colchetes> ::= î \| "[" <expressao> "]"` | `encontrado ... esperado expressao` |
| `<expressao>`, `<expressao_>`, `<termo_logico>`, `<relacional>`, `<relacional_>`, `<aritmetica>`, `<aritmetica_>`, `<termo_aritmetico>`, `<termo_aritmetico_>`, `<fator>`, `<vetor>` | `encontrado ... esperado expressao` |
| Demais não-terminais | `encontrado ... esperado símbolo1 símbolo2 ... símboloN`, conforme a tabela de análise sintática |

- **Todas** as mensagens de erro geradas pelo GALS devem ser mantidas (em **comentário de linha** ao lado da mensagem alterada, no arquivo `ParserConstants.java`), **mas** devem ser alteradas, seguindo as orientações dadas em aula.

### Exemplo com a tabela de análise sintática

Considerando o seguinte trecho da tabela de análise sintática (no GALS, menu `Documentação > Tabela de Análise Sintática`):

| | id_int | id_float | id_string | id_bool | if | in | module | out | while | "{" |
|---|---|---|---|---|---|---|---|---|---|---|
| `<programa>` | - | - | - | - | - | - | 0 | - | - | - |
| `<variaveis>` | 2 | 2 | 2 | 2 | - | - | - | - | - | 1 |
| `<lista_variaveis>` | 3 | 3 | 3 | 3 | - | - | - | - | - | - |
| `<lista_comandos>` | 12 | 12 | 12 | 12 | 12 | 12 | - | 12 | 12 | - |

As mensagens de erro para os não-terminais relacionados devem ser:

| Não-terminal | Mensagem |
|---|---|
| `<programa>` | `encontrado ... esperado module` |
| `<variaveis>` | `encontrado ... esperado identificador {` |
| `<lista_variaveis>` | `encontrado ... esperado identificador` |
| `<lista_comandos>` | `encontrado ... esperado identificador if in out while` |
| `<expressao>` | `encontrado ... esperado expressao` |
| `<expressao_colchetes>` | `encontrado ... esperado expressao` |

## Importante sobre o trabalho nº3

As especificações feitas no trabalho nº3 (com as devidas correções) devem ser usadas para implementação do analisador sintático. Recebe **nota 0,0** o analisador sintático quando:

1. não forem efetuadas todas as alterações/correções indicadas na avaliação do trabalho nº3;
2. for desenvolvido usando especificações diferentes das elaboradas pela equipe no trabalho nº3 (com as devidas correções);
3. o trabalho nº3 não for devolvido à professora na aula de **05/10**;
4. forem alteradas classes geradas pelo GALS além da constante `PARSER_ERROR` no arquivo `ParserConstants.java` (**única alteração permitida**);
5. nem **todas** as mensagens de erro sintático geradas pelo GALS forem alteradas conforme especificado e mantidas em comentário de linha ao lado da mensagem alterada (`ParserConstants.java`) — o "símbolo esperado" só pode ser informado a partir desse código.

## Entrega

- Disponibilizar no **AVA**, na tarefa "parte 3 - analisador sintático", a implementação do analisador sintático, da interface do compilador e do analisador léxico.
- Arquivo compactado com o nome `sintatico` + número da equipe, contendo o projeto completo: **código fonte**, **arquivo `.jar`**, **arquivo com as especificações léxica e sintática (`.gals`)** e demais recursos.
- Basta um integrante da equipe postar o trabalho.
- **Se não forem postados todos os arquivos solicitados, ou não for possível rodar o `.jar`, nota 0,0.**
- Avaliação considera: a correta especificação do arquivo `.gals`, conforme as correções/alterações do trabalho nº3; a qualidade das mensagens de erro, conforme descrito acima.

## Data de entrega

**Até às 23h do dia 23/10/2026.** Só serão aceitas entregas via AVA até a data e hora determinadas.

## Exemplos de entrada/saída

### Exemplo 1 — com erro léxico

**Entrada:**
```
1: module
2: i_lado
3: {
4:   out ("digite um valor para lado: )
5:   in  i_lado)
6:   out ("o valor digitado foi: ", i_lado)
7: }
```

**Saída (área de mensagens):**
```
linha 4: erro léxico
```

### Exemplo 2 — com erro sintático

**Entrada:**
```
1: module
2: i_lado
3: {
4:   out ("digite um valor para lado: ")
5:   in  i_lado)
6:   out ("o valor digitado foi: ", i_lado)
7: }
```

**Saída (área de mensagens):**
```
linha 5: encontrado i_lado esperado (
```

### Exemplo 3 — sem erro

**Entrada:**
```
1: module
2: i_lado
3: {
4:   out ("digite um valor para lado: ")
5:   in  (i_lado)
6:   out ("o valor digitado foi: ", i_lado)
7: }
```

**Saída (área de mensagens):**
```
programa compilado com sucesso
```
