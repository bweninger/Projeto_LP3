package br.mack.dao.usuario;

import org.apache.commons.lang.exception.*;

public class UsuarioNaoEncontradoException extends NestableException {

    public UsuarioNaoEncontradoException(String msg) {
        super(msg);
    }

    public UsuarioNaoEncontradoException(Throwable t) {
        super(t);
    }

    public UsuarioNaoEncontradoException(String msg, Throwable t) {
        super(msg, t);
    }
}
