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

   private void acao104(){}

   private void acao105(){}

   private void acao106(){}

   private void acao107(){}

   private void acao108(){}


   private void acao109() {
      String tipo = pilhaTipos.pop();
      if ("int64".equals(tipo)) {
         codigoObjeto.append("conv.i8\n");
      }
      codigoObjeto.append("call void [mscorlib]System.Console::Write(" + tipo + ")\n");
    }

   private void acao110(){}

   private void acao111(){}

   private void acao112(){}

   private void acao113(){}

   private void acao114(){}

   private void acao115(){}

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


   private void acao122(){}

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


   private void acao127(){}

   private void acao128(Token token) {
     pilhaTipos.push("int64");
     codigoObjeto.append("ldc.i8 " + token.getLexeme() + "\n");
     codigoObjeto.append("conv.r8\n");
   }


   private void acao129(Token token) {
      pilhaTipos.push("float64");
      String valor = token.getLexeme().replace(',', '.');
      codigoObjeto.append("ldc.r8" + valor + "\n");

   }

   private void acao130(Token token){
      pilhaTipos.push("string");
      codigoObjeto.append("ldstr" + token.getLexeme() + "\n");
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

   private void acao133(){}

   private void acao134(){}


   public String getCodigoObjeto() {
      return codigoObjeto.toString();
   }
}
