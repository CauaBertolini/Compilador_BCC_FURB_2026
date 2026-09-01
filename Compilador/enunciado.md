1a parte da aula: ESPECIFICAÇÃO... usando o GALS 

especificar, se for o caso, as definições regulares auxiliares do trabalho no.1, adaptando à notação do GALS exemplos de definições regulares auxiliares e como especificá-las: //no "campo" Definições Regulares no GALS 

mai: [A-Z] min: [a-z] letra: {mai}|{min} 

bloco: #[^#]*# 

como usar as definições regulares auxiliares especificadas? ver tutorial e ajuda em https://gals.sourceforge.net/ 

especificar tokens do trabalho no.1 (palavra reservada, identificador de int, identificador de float, identificador de string, identificador de bool, constante_int, constante_float, constante_string) adaptando à notação do GALS exemplos de tokens e como especificá-los: //no "campo" Tokens no GALS palavra:! {min} ({mai}? {min})* 

incluir a definição de todas as palavras reservadas da linguagem (ver quais no enunciado da parte 2) 

incluir a definição dos símbolos especiais (ver quais no enunciado da parte 2) 

incluir definição para reconhecer e ignorar caracteres de formatação 

incluir definição para reconhecer e ignorar comentários 

2a PARTE da aula: IMPLEMENTAÇÃO 

verificar opções (em Ferramentas > Opções): Forma de Entrada: String Implementação do Autômato: Tabela Compactada (Java) Diferenciar maiúsculas/minúsculas ... 

gerar código (em Ferramentas > Gerar Código): escolher pasta, gerar todos os arquivos 

para usar o código gerado, deve ser chamado a partir da ação associada ao botão compilar 

Lexico lexico = new Lexico(); lexico.setInput( /* texto do editor de textos */ ); try { Token t = null; while ( (t = lexico.nextToken()) != null ) { System.out.println(t.getLexeme());  

// só escreve o lexema, necessário escrever t.getId, t.getPosition() // t.getId () - retorna o identificador da classe (ver Constants.java) // necessário adaptar, pois deve ser apresentada a classe por extenso // t.getPosition () - retorna a posição inicial do lexema no editor // necessário adaptar para mostrar a linha 

 // esse código apresenta os tokens enquanto não ocorrer erro // no entanto, os tokens devem ser apresentados SÓ se não ocorrer erro, // necessário adaptar para atender o que foi solicitado 

 } } catch ( LexicalError e ) { // tratamento de erros System.out.println(e.getMessage() + " em " + e.getPosition()); 

// e.getMessage() - retorna a mensagem de erro de SCANNER_ERRO (ver ScannerConstants.java) // necessário adaptar conforme o enunciado da parte 2 

// e.getPosition() - retorna a posição inicial do erro // necessário adaptar para mostrar a linha 

 } 