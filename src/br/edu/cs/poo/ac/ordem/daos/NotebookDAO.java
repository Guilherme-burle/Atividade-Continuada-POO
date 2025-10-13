package br.edu.cs.poo.ac.ordem.daos;

import br.edu.cs.poo.ac.ordem.entidades.Notebook;
import java.io.Serializable;

public class NotebookDAO extends DAOGenerico {

    public NotebookDAO() {
        super(Notebook.class);
    }

    private String getIdentificador(Notebook notebook) {
        return notebook.getIdTipo() + notebook.getSerial();
    }

    public Notebook buscar(String identificador) {
        return (Notebook) cadastroObjetos.buscar(identificador);
    }

    public boolean incluir(Notebook notebook) {
        String id = getIdentificador(notebook);
        if (buscar(id) == null) {
            cadastroObjetos.incluir(notebook, id);
            return true;
        } else {
            return false;
        }
    }

    public boolean alterar(Notebook notebook) {
        String id = getIdentificador(notebook);
        if (buscar(id) != null) {
            cadastroObjetos.alterar(notebook, id);
            return true;
        } else {
            return false;
        }
    }

    public boolean excluir(String identificador) {
        if (buscar(identificador) != null) {
            cadastroObjetos.excluir(identificador);
            return true;
        } else {
            return false;
        }
    }

    public Notebook[] buscarTodos() {
        Serializable[] ret = cadastroObjetos.buscarTodos();
        Notebook[] notebooks;
        if (ret != null && ret.length > 0) {
            notebooks = new Notebook[ret.length];
            for (int i = 0; i < ret.length; i++) {
                notebooks[i] = (Notebook) ret[i];
            }
        } else {
            notebooks = new Notebook[0];
        }
        return notebooks;
    }
}