package br.edu.cs.poo.ac.ordem.daos;

import br.edu.cs.poo.ac.ordem.entidades.Desktop;
import java.io.Serializable;

public class DesktopDAO extends DAOGenerico {

    public DesktopDAO() {
        super(Desktop.class);
    }

    private String getIdentificador(Desktop desktop) {
        return desktop.getIdTipo() + desktop.getSerial();
    }

    public Desktop buscar(String identificador) {
        return (Desktop) cadastroObjetos.buscar(identificador);
    }

    public boolean incluir(Desktop desktop) {
        String id = getIdentificador(desktop);
        if (buscar(id) == null) {
            cadastroObjetos.incluir(desktop, id);
            return true;
        } else {
            return false;
        }
    }

    public boolean alterar(Desktop desktop) {
        String id = getIdentificador(desktop);
        if (buscar(id) != null) {
            cadastroObjetos.alterar(desktop, id);
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

    public Desktop[] buscarTodos() {
        Serializable[] ret = cadastroObjetos.buscarTodos();
        Desktop[] desktops;
        if (ret != null && ret.length > 0) {
            desktops = new Desktop[ret.length];
            for (int i = 0; i < ret.length; i++) {
                desktops[i] = (Desktop) ret[i];
            }
        } else {
            desktops = new Desktop[0];
        }
        return desktops;
    }
}