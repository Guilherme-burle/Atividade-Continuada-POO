package br.edu.cs.poo.ac.ordem.daos;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.edu.cs.poo.ac.utils.Registro;
import java.io.Serializable;

public abstract class DAOGenerico {
    protected CadastroObjetos cadastroObjetos;

    public DAOGenerico() {
        cadastroObjetos = new CadastroObjetos(getClasseEntidade());
    }

    public abstract Class<?> getClasseEntidade();

    public boolean incluir(Registro registro) {
        if (buscar(registro.getId()) == null) {
            cadastroObjetos.incluir(registro, registro.getId());
            return true;
        } else {
            return false;
        }
    }

    public boolean alterar(Registro registro) {
        if (buscar(registro.getId()) != null) {
            cadastroObjetos.alterar(registro, registro.getId());
            return true;
        } else {
            return false;
        }
    }

    public boolean excluir(String id) {
        if (buscar(id) != null) {
            cadastroObjetos.excluir(id);
            return true;
        } else {
            return false;
        }
    }

    public Registro buscar(String id) {
        return (Registro) cadastroObjetos.buscar(id);
    }

    public Registro[] buscarTodos() {
        Serializable[] ret = cadastroObjetos.buscarTodos();
        if (ret == null || ret.length == 0) {
            return new Registro[0];
        }
        Registro[] registros = new Registro[ret.length];
        for (int i = 0; i < ret.length; i++) {
            registros[i] = (Registro) ret[i];
        }
        return registros;
    }
}
