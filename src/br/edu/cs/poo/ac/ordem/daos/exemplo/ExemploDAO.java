package br.edu.cs.poo.ac.ordem.daos.exemplo;

import br.edu.cs.poo.ac.utils.Registro;
import java.io.Serializable;

import br.edu.cs.poo.ac.ordem.daos.DAOGenerico;
/**
 *
 * O identificador único por objeto de Exemplo é o seu código, que será usado nos métodos
 * para verificar a existência no cadastro de um objeto com um dado identificador único.
 *
 */
public class ExemploDAO extends DAOGenerico {

    @Override
    public Class<?> getClasseEntidade() {
        return Exemplo.class;
    }

    public Exemplo buscar(String codigo) {
        return (Exemplo) super.buscar(codigo);
    }

    public boolean incluir(Exemplo exemplo) {
        return super.incluir(exemplo);
    }

    public boolean alterar(Exemplo exemplo) {
        return super.alterar(exemplo);
    }

    public boolean excluir(String codigo) {
        return super.excluir(codigo);
    }

    public Exemplo[] buscarTodos() {
        Registro[] registros = super.buscarTodos();
        Exemplo[] retorno = new Exemplo[registros.length];
        for (int i = 0; i < registros.length; i++) {
            retorno[i] = (Exemplo) registros[i];
        }
        return retorno;
    }
}
