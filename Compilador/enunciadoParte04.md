# Trabalho Final – Parte 4: Implementação do Analisador Semântico e do Gerador de Código Intermediário

Implementar as ações semânticas que constituem o **analisador semântico** e o **gerador de código intermediário**, gerando o código objeto IL, de acordo com o esquema de tradução (`esquema.pdf`) disponibilizado no AVA. Na implementação deve ser usada a classe `Semantico.java`, também disponibilizada no AVA.

Deve-se implementar **dois tratamentos de erros** semânticos: um especificado no esquema de tradução e outro específico para cada equipe.

O analisador semântico e o gerador de código intermediário (para Windows 8 ou superior) devem ser implementados em **Java**, conforme especificado abaixo. Na implementação deve ser utilizado o **GALS**, ferramenta para geração de analisadores léxicos e sintáticos.

## Entrada e saída

**Entrada:** um conjunto de caracteres, isto é, o programa fonte do editor do compilador.

**Saída:** caso o botão **compilar** seja pressionado, a ação deve ser: executar as análises **léxica, sintática e semântica** do programa fonte. Um programa pode ser compilado com sucesso ou apresentar erros, em uma das duas situações abaixo.

### 1ª situação: programa compilado com sucesso

- **Mensagem** *"programa compilado com sucesso"*, na área reservada para mensagens, indicando que o programa não apresenta erros.
- **Código objeto** em *Microsoft Intermediate Language*, correspondente ao programa fonte compilado:
  - deve ser gerado na **MESMA PASTA** do programa fonte que está sendo compilado;
  - deve estar em um arquivo texto com **extensão `.il`** e **nome igual** ao nome do arquivo que contém o programa compilado.
  - Exemplo: se o programa compilado for `teste01.txt` da pasta `c:\temp\`, deve ser gerado na pasta `c:\temp\` o arquivo `teste01.il`.
  - Considere que só serão compilados arquivos já salvos.

### 2ª situação: programa apresenta erros

- **Mensagem**, na área reservada para mensagens, indicando que o programa apresenta erro. O erro pode ser **léxico**, **sintático** ou **semântico**, com as mensagens descritas abaixo.

## Observações

- O tipo do analisador sintático a ser gerado é **LL(1)**.
- Formato das mensagens de erro:

| Tipo de erro | O que apresentar | Exemplo |
|---|---|---|
| Léxico | a linha onde ocorreu o erro e a descrição *erro léxico* | `linha 4: erro léxico` |
| Sintático | a linha onde ocorreu o erro, o **lexema encontrado** e a descrição *erro sintático* | `linha 5: encontrado i_lado, erro sintático` |
| Semântico (os dois) | a linha onde ocorreu o erro e a descrição do erro, conforme especificada no esquema de tradução | `linha 7: i_i identificador declarado, não utilizado` |

- As mensagens de erro semântico devem ser geradas **durante a execução das ações semânticas**. Ao ser emitida uma mensagem de erro semântico, o **processo de análise deve ser encerrado**.
- No **esquema de tradução** disponibilizado (`esquema.pdf`), a gramática possui a numeração das ações semânticas. A equipe deve colocar a numeração das ações semânticas na gramática usada para a implementação do analisador sintático.

## Requisitos para avaliação

Para que o analisador semântico e o gerador de código intermediário sejam **avaliados**, devem ser atendidos os seguintes requisitos — caso contrário, será atribuída **nota 0,0** ao analisador semântico e gerador de código intermediário:

1. O arquivo com as especificações léxicas e sintáticas deve ser **o mesmo** utilizado pela equipe na implementação do analisador sintático, incluindo a numeração das ações semânticas. Se a equipe achar necessário, **pode incluir outras ações semânticas**. Se a equipe necessitar fazer alguma alteração na BNF, deve conversar com a professora.
2. Todos os arquivos solicitados devem ser postados no AVA.
3. Deve ser possível executar o arquivo `.jar`.
4. O código objeto deve ser gerado no formato especificado, em um arquivo texto com extensão `.il`, com nome igual ao nome do arquivo que contém o programa fonte compilado e na **MESMA PASTA** do programa fonte compilado (ex.: `c:\temp\teste01.txt` → `c:\temp\teste01.il`).

## Entrega

- A implementação do analisador semântico e do gerador de código intermediário, juntamente com a interface do compilador e os analisadores léxico e sintático, deve ser disponibilizada no **AVA**, na tarefa "**parte 4 - analisador semântico e gerador de código**".
- Deve ser disponibilizado **um arquivo compactado** (com o nome `compilador` seguido do número da equipe), contendo o projeto completo, incluindo: **código fonte**; **arquivo `.jar`**; **arquivo com as especificações léxica e sintática** e a numeração das ações semânticas (arquivo `.gals`); e demais recursos.
- Basta um integrante da equipe postar o trabalho.
- Na avaliação serão levadas em consideração: a correta adequação da gramática com a inclusão das ações semânticas; a correta implementação das ações semânticas (as ações **NÃO** terão peso igual na avaliação); a qualidade das mensagens de erro.

## Data de entrega

**Até às 23h do dia 01/12/2026.** Só serão aceitas entregas via AVA até a data e hora determinadas.

## Exemplos de entrada/saída

### Exemplo 1 — com erro léxico

**Entrada:**
```
1: module
2: i_lado, i_i
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
2: i_lado, i_i
3: {
4:   out ("digite um valor para lado: ")
5:   in  i_lado)
6:   out ("o valor digitado foi: ", i_lado)
7: }
```

**Saída (área de mensagens):**
```
linha 5: encontrado i_lado, erro sintático
```

### Exemplo 3 — com erro semântico

**Entrada:**
```
1: module
2: i_lado, i_i
3: {
4:   out ("digite um valor para lado: ")
5:   in  (i_lado)
6:   out ("o valor digitado foi: ", i_lado)
7: }
```

**Saída (área de mensagens):**
```
linha 7: i_i identificador declarado, não utilizado
```

### Exemplo 4 — sem erro (mensagem na área de mensagens e arquivo `.il` gerado na MESMA pasta do programa fonte)

**Entrada:** programa fonte — arquivo `teste_01.txt`
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

**Saída (na mesma pasta do programa fonte):** programa objeto — arquivo `teste_01.il`
```
// cabeçalho
.assembly extern mscorlib {}
.assembly _codigo_objeto{}
.module _codigo_objeto.exe

.class public _codigo_objeto{
  .method static public void _principal(){
     .entrypoint
// cabeçalho

.locals(int64 i_lado)
ldstr "digite um valor para lado: "
call void [mscorlib]System.Console::Write(string)
call string [mscorlib]System.Console::ReadLine()
call int64 [mscorlib]System.Int64::Parse(string)
stloc i_lado
ldstr "o valor digitado foi: "
call void [mscorlib]System.Console::Write(string)
ldloc i_lado
conv.r8
conv.i8
call void [mscorlib]System.Console::Write(int64)

// fim de programa
ret
}
}
```
