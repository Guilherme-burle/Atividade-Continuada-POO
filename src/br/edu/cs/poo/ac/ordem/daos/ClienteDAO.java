package br.edu.cs.poo.ac.ordem.daos;

import br.edu.cs.poo.ac.ordem.entidades.Cliente;
import br.edu.cs.poo.ac.utils.Registro;

public class ClienteDAO extends DAOGenerico {

    @Override
    public Class<?> getClasseEntidade() {
        return Cliente.class;
    }

    public Cliente buscar(String cpfCnpj) {
        return (Cliente) super.buscar(cpfCnpj);
    }

    public boolean incluir(Cliente cliente) {
        return super.incluir(cliente);
    }

    public boolean alterar(Cliente cliente) {
        return super.alterar(cliente);
    }

    public boolean excluir(String cpfCnpj) {
        return super.excluir(cpfCnpj);
    }

    public Cliente[] buscarTodos() {
        Registro[] registros = super.buscarTodos();
        Cliente[] clientes = new Cliente[registros.length];
        for (int i = 0; i < registros.length; i++) {
            clientes[i] = (Cliente) registros[i];
        }
        return clientes;
    }
}
