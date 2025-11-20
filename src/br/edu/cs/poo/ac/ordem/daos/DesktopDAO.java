package br.edu.cs.poo.ac.ordem.daos;

import br.edu.cs.poo.ac.ordem.entidades.Desktop;
import br.edu.cs.poo.ac.utils.Registro;

public class DesktopDAO extends DAOGenerico {

    @Override
    public Class<?> getClasseEntidade() {
        return Desktop.class;
    }

    public Desktop buscar(String identificador) {
        return (Desktop) super.buscar(identificador);
    }

    public boolean incluir(Desktop desktop) {
        return super.incluir(desktop);
    }

    public boolean alterar(Desktop desktop) {
        return super.alterar(desktop);
    }

    public boolean excluir(String identificador) {
        return super.excluir(identificador);
    }

    public Desktop[] buscarTodos() {
        Registro[] registros = super.buscarTodos();
        Desktop[] desktops = new Desktop[registros.length];
        for (int i = 0; i < registros.length; i++) {
            desktops[i] = (Desktop) registros[i];
        }
        return desktops;
    }
}
