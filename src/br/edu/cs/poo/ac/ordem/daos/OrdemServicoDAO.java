package br.edu.cs.poo.ac.ordem.daos;

import br.edu.cs.poo.ac.ordem.entidades.OrdemServico;
import java.io.Serializable;

public class OrdemServicoDAO extends DAOGenerico {

    public OrdemServicoDAO() {
        super(OrdemServico.class);
    }

    public OrdemServico buscar(String numero) {
        return (OrdemServico) cadastroObjetos.buscar(numero);
    }

    public boolean incluir(OrdemServico ordemServico) {
        String numero = ordemServico.getNumero();
        if (buscar(numero) == null) {
            cadastroObjetos.incluir(ordemServico, numero);
            return true;
        } else {
            return false;
        }
    }

    public boolean alterar(OrdemServico ordemServico) {
        String numero = ordemServico.getNumero();
        if (buscar(numero) != null) {
            cadastroObjetos.alterar(ordemServico, numero);
            return true;
        } else {
            return false;
        }
    }

    public boolean excluir(String numero) {
        if (buscar(numero) != null) {
            cadastroObjetos.excluir(numero);
            return true;
        } else {
            return false;
        }
    }

    public OrdemServico[] buscarTodos() {
        Serializable[] ret = cadastroObjetos.buscarTodos();
        OrdemServico[] ordens;
        if (ret != null && ret.length > 0) {
            ordens = new OrdemServico[ret.length];
            for (int i = 0; i < ret.length; i++) {
                ordens[i] = (OrdemServico) ret[i];
            }
        } else {
            ordens = new OrdemServico[0];
        }
        return ordens;
    }
}