package bcc.furb.br.services;

import bcc.furb.br.services.gals.SemanticError;
import bcc.furb.br.services.gals.Token;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

// O Sintatico gerado pelo GALS espera um bcc.furb.br.services.gals.Semantico;
// esta classe estende o stub gerado e implementa as ações semânticas.
public class Semantico extends bcc.furb.br.services.gals.Semantico {

   //registros semanticos
   private StringBuilder  codigoObjeto = new StringBuilder();
   private Stack<String>  pilhaTipos = new Stack<>();
   private String         identificador = "";

   private String operadorRelacional = "";
   private Stack<String> pilhaRotulos = new Stack<>();
   private int contadorRotulos = 0;
   private Map<String, Integer> tabelaSimbolos = new HashMap<>();

   private String getTipo(String id) {
      if (id.startsWith("i_")) return "int64";
      if (id.startsWith("f_")) return "float64";
      if (id.startsWith("s_")) return "string";
      return "bool";
   }

   private Stack<String> pilhaVetores = new Stack<>();

   private String novoRotulo() {
      return "L" + (++contadorRotulos);
   }

   private String getTipoCLR(String tipo) {
      return switch (tipo) {
         case "int64"   -> "Int64";
         case "float64" -> "Double";
         case "string"  -> "String";
         default        -> "Boolean";
      };
   }

   private String getLdelem(String tipo) {
      return switch (tipo) {
         case "int64"   -> "ldelem.i8";
         case "float64" -> "ldelem.r8";
         case "string"  -> "ldelem.ref";
         default        -> "ldelem.i1";
      };
   }

   private String getStelem(String tipo) {
      return switch (tipo) {
         case "int64"   -> "stelem.i8";
         case "float64" -> "stelem.r8";
         case "string"  -> "stelem.ref";
         default        -> "stelem.i1";
      };
   }


   public void executeAction(int action, Token token) throws SemanticError {
      System.out.println("Acao #"+action+", Token: "+token);

      /*
      1 : 129; 130; 116; 117; 119; 121; 122;

      2: 103; 104; 106; 108; 127

      3: 110; 111; 112; 113; 114; 115

      4: 105; 107; 134; 106 (ajusatar); 108 (ajustar); 127 (ajustar); 133


       */
      switch (action) {
         case 101: acao101(); break;
         case 102: acao102(); break;
         case 103: acao103(token); break;
         case 104: acao104(); break;
         case 105: acao105(token); break;
         case 106: acao106(); break;
         case 107: acao107(); break;
         case 108: acao108(); break;
         case 109: acao109(); break;
         case 110: acao110(token); break;
         case 111: acao111(); break;
         case 112: acao112(); break;
         case 113: acao113(); break;
         case 114: acao114(token); break;
         case 115: acao115(); break;
         case 116: acao116(); break;
         case 117: acao117(); break;
         case 118: acao118(); break;
         case 119: acao119(); break;
         case 120: acao120(); break;
         case 121: acao121(token); break;
         case 122: acao122(); break;
         case 123: acao123(); break;
         case 124: acao124(); break;
         case 125: acao125(); break;
         case 126: acao126(); break;
         case 127: acao127(token); break;
         case 128: acao128(token); break;
         case 129: acao129(token); break;
         case 130: acao130(token); break;
         case 131: acao131(); break;
         case 132: acao132(); break;
         case 133: acao133(); break;
         case 134: acao134(); break;
         default:  throw new SemanticError("Acao semantica nao implementada: " + action);
      }
   }


   private void acao101() {
      codigoObjeto.append(".assembly extern mscorlib {}\n");
      codigoObjeto.append(".assembly _codigo_objeto{}\n");
      codigoObjeto.append(".module _codigo_objeto.exe\n");
      codigoObjeto.append("\n");
      codigoObjeto.append(".class public _codigo_objeto{\n");
      codigoObjeto.append(".method static public void _principal(){\n");
      codigoObjeto.append(".entrypoint\n");
}

   private void acao102() {
      codigoObjeto.append("ret\n");
      codigoObjeto.append("}\n");
      codigoObjeto.append("}");
   }

   private void acao103(Token token) {
      identificador = token.getLexeme();
   }

   private void acao104(){
      tabelaSimbolos.put(identificador, 0);
      String tipo = getTipo(identificador);
      codigoObjeto.append(".locals (" + tipo + " " + identificador + ") \n");
   }

   private void acao105(Token token){
      int tamanho = Integer.parseInt(token.getLexeme());
      tabelaSimbolos.put(identificador, tamanho);
      String tipo = getTipo(identificador);

      codigoObjeto.append(".locals (" + tipo + "[] " + identificador + ") \n");
      codigoObjeto.append("ldc.i4 " + tamanho + "\n");
      codigoObjeto.append("newarr [mscorlib]System." + getTipoCLR(tipo) + "\n");
      codigoObjeto.append("stloc" + identificador + "\n");


   }

   private void acao106(){
      String tipo = pilhaTipos.pop();
      if ("int64".equals((tipo))) {
         codigoObjeto.append("conv.i8\n");
      }
   }

   private void acao107(){
      codigoObjeto.append("ldloc " + identificador + "\n");
   }

   private void acao108(){
      String tipo = getTipo(identificador);
      codigoObjeto.append("call string [mscorlib]SYstem.Int64::Parse(string)\n");

      if ("int64".equals(tipo)) {
         codigoObjeto.append("call int64 [mscorlib]SYstem.Int64::Parse(string)\n");
      } else if("float64".equals(tipo)) {
         codigoObjeto.append("call float64 [mscorlib]SYstem.Double::Parse(string)\n");
      } else if ("bool".equals(tipo)) {
         codigoObjeto.append("call bool [mscorlib]SYstem.Boolean::Parse(string)\n");
      }

      int tamanho = tabelaSimbolos.getOrDefault(identificador, 0);
      if (tamanho == 0) {
         codigoObjeto.append("stloc " + identificador + "\n");
      } else {
         codigoObjeto.append(getStelem(tipo) + "\n");
      }
   }


   private void acao109() {
      String tipo = pilhaTipos.pop();
      if ("int64".equals(tipo)) {
         codigoObjeto.append("conv.i8\n");
      }
      codigoObjeto.append("call void [mscorlib]System.Console::Write(" + tipo + ")\n");
    }

   private void acao110(Token token){
      pilhaTipos.pop();
      String rotulo = novoRotulo();
      pilhaRotulos.push(rotulo);

      if ("isTrueDo".equals(token.getLexeme())) {
         codigoObjeto.append("brfalse" + rotulo + "\n");
      } else {
         codigoObjeto.append("brtrue" + rotulo + "\n");
      }
   }

   private void acao111(){
      String rotulo = pilhaRotulos.pop();
      codigoObjeto.append(rotulo + ":\n");
   }

   private void acao112(){
      String rotuloFim = novoRotulo();
      codigoObjeto.append("br " + rotuloFim + "\n");

      String rotuloElse = pilhaRotulos.pop();
      codigoObjeto.append(rotuloElse + ":\n");

      pilhaRotulos.push(rotuloFim);
   }

   private void acao113(){
      String rotuloInicio = novoRotulo();
      pilhaRotulos.push(rotuloInicio);
      codigoObjeto.append(rotuloInicio + ":\n");
   }

   private void acao114(Token token){
      pilhaTipos.pop();
      String rotuloFim = novoRotulo();
      pilhaRotulos.push(rotuloFim);

      if ("isTrueDo".equals(token.getLexeme())) {
         codigoObjeto.append("brfalse" + rotuloFim + "\n");
      } else {
         codigoObjeto.append("brtrue" + rotuloFim + "\n");
      }
   }

   private void acao115(){
      String rotuloFim = pilhaRotulos.pop();
      String rotuloInicio = pilhaRotulos.pop();

      codigoObjeto.append("br" + rotuloInicio + "\n");
      codigoObjeto.append(rotuloFim + ":\n");
   }

   private void acao116(){
      pilhaTipos.pop();
      pilhaTipos.pop();
      pilhaTipos.push("bool");
      codigoObjeto.append("and \n");
   }

   private void acao117(){
      pilhaTipos.pop();
      pilhaTipos.pop();
      pilhaTipos.push("bool");
      codigoObjeto.append("or \n");
   }

   private void acao118() {
      pilhaTipos.push("bool");
      codigoObjeto.append("ldc.i4.1\n");
   }

   private void acao119() {
      pilhaTipos.push("bool");
      codigoObjeto.append("ldc.i4.0 \n");
   }

   private void acao120() {
      String tipo = pilhaTipos.pop();
      pilhaTipos.push("bool");
      codigoObjeto.append("ldc.i4.1\n");
      codigoObjeto.append("xor\n");
   }

   private void acao121(Token token){
      operadorRelacional = token.getLexeme();
   }


   // #122: os dois operandos já estão na pilha da IL; gera a comparação
   // guardada pela #121. O resultado é sempre bool (TABELA DE TIPOS).
   private void acao122(){
      String tipo2 = pilhaTipos.pop();   // operando da direita
      String tipo1 = pilhaTipos.pop();   // operando da esquerda
      pilhaTipos.push("bool");

      // ceq em string compara referências; String::Compare devolve <0, 0 ou >0,
      // que é então comparado com 0 usando o mesmo operador relacional
      if ("string".equals(tipo1) && "string".equals(tipo2)) {
         codigoObjeto.append("call int32 [mscorlib]System.String::Compare(string, string)\n");
         codigoObjeto.append("ldc.i4.0\n");
      }

      switch (operadorRelacional) {
         case "="  -> codigoObjeto.append("ceq\n");
         case ">"  -> codigoObjeto.append("cgt\n");
         case "<"  -> codigoObjeto.append("clt\n");
         case "<>" -> {
            // IL não tem "diferente": compara igualdade e inverte o resultado
            codigoObjeto.append("ceq\n");
            codigoObjeto.append("ldc.i4.0\n");
            codigoObjeto.append("ceq\n");
         }
      }
   }

   private void acao123() {
      String tipo1 = pilhaTipos.pop();
      String tipo2 = pilhaTipos.pop();
      if ("int64".equals(tipo1) && "int64".equals(tipo2)) {
         pilhaTipos.push("int64");
      } else {
         pilhaTipos.push("float64");
      }
      codigoObjeto.append("add\n");
   }

   private void acao124() {
      String tipo1 = pilhaTipos.pop();
      String tipo2 = pilhaTipos.pop();
      if ("int64".equals(tipo1) && "int64".equals(tipo2)) {
         pilhaTipos.push("int64");
      } else {
         pilhaTipos.push("float64");
      }
      codigoObjeto.append("sub\n");
   }

   private void acao125() {
      String tipo1 = pilhaTipos.pop();
      String tipo2 = pilhaTipos.pop();
      if ("int64".equals(tipo1) && "int64".equals(tipo2)) {
         pilhaTipos.push("int64");
      } else {
         pilhaTipos.push("float64");
      }
      codigoObjeto.append("mul\n");
   }

   private void acao126() {
      String tipo1 = pilhaTipos.pop();
      String tipo2 = pilhaTipos.pop();
      pilhaTipos.push("float64");
      codigoObjeto.append("div\n");
   }


   private void acao127(Token token){
      String id = token.getLexeme();
      String tipo = getTipo(id);
      int tamanho = tabelaSimbolos.getOrDefault(id, 0);

      if (tamanho == 0) {
         pilhaTipos.push(tipo);
         codigoObjeto.append("ldloc" + id + "\n");
         if ("int64".equals(tipo)) {
            codigoObjeto.append("conv.r8\n");
         }
      } else {
         pilhaVetores.push(id);
         codigoObjeto.append("ldloc" + id + "\n");
      }
   }

   private void acao128(Token token) {
     pilhaTipos.push("int64");
     codigoObjeto.append("ldc.i8 " + token.getLexeme() + "\n");
     codigoObjeto.append("conv.r8\n");
   }


   private void acao129(Token token) {
      pilhaTipos.push("float64");
      String valor = token.getLexeme().replace(',', '.');
      codigoObjeto.append("ldc.r8 " + valor + "\n");

   }

   private void acao130(Token token){
      pilhaTipos.push("string");
      codigoObjeto.append("ldstr " + token.getLexeme() + "\n");
   }


   private void acao131() {
      String tipo = pilhaTipos.pop();
      if ("int64".equals(tipo)) {
         pilhaTipos.push("int64");
      } else {
         pilhaTipos.push("float64");
      }
   }

   private void acao132() {
      String tipo = pilhaTipos.pop();
      if ("int64".equals(tipo)) {
         pilhaTipos.push("int64");
      } else {
         pilhaTipos.push("float64");
      }
      codigoObjeto.append("ldc.i8 -1\n");
      codigoObjeto.append("conv.r8\n");
      codigoObjeto.append("mul\n");
   }

   private void acao133(){
      pilhaTipos.pop();
      codigoObjeto.append("conv.i4\n");

      String id = pilhaVetores.pop();
      String tipo = getTipo(id);
      pilhaTipos.push(tipo);
      codigoObjeto.append(getLdelem(tipo) + "\n");

      if ("int64".equals(tipo)) {
         codigoObjeto.append("conv.r8\n");
      }
   }

   private void acao134(){
      pilhaTipos.pop();
      codigoObjeto.append("conv.i4\n");
   }


   public String getCodigoObjeto() {
      return codigoObjeto.toString();
   }
}
