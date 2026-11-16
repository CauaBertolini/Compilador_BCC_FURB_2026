package bcc.furb.br.services;

import bcc.furb.br.services.gals.Constants;
import bcc.furb.br.services.gals.LexicalError;
import bcc.furb.br.services.gals.Lexico;
import bcc.furb.br.services.gals.Token;

// O Lexico gerado pelo GALS devolve o token base palavraReservada quando a
// sequência segue o padrão de palavra reservada mas não é um dos casos
// especiais (ex.: "ou"). Como as classes geradas não podem ser alteradas,
// a verificação fica nesta subclasse, usada pelo parser.
public class LexicoValidado extends Lexico {

    @Override
    public Token nextToken() throws LexicalError {
        Token token = super.nextToken();
        if (token != null && token.getId() == Constants.t_palavraReservada) {
            throw new LexicalError("erro léxico", token.getPosition());
        }
        return token;
    }
}
