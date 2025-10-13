package br.edu.cs.poo.ac.ordem.daos;

import br.edu.cs.poo.ac.ordem.entidades.FechamentoOrdemServico;
import java.io.Serializable;

public class FechamentoOrdemServicoDAO extends DAOGenerico {

    public FechamentoOrdemServicoDAO() {
        super(FechamentoOrdemServico.class);
    }

    public FechamentoOrdemServico buscar(String numeroOrdemServico) {
        return (FechamentoOrdemServico) cadastroObjetos.buscar(numeroOrdemServico);
    }

    public boolean incluir(FechamentoOrdemServico fechamento) {
        String id = fechamento.getNumeroOrdemServico();
        if (buscar(id) == null) {
            cadastroObjetos.incluir(fechamento, id);
            return true;
        } else {
            return false;
        }
    }

    public boolean alterar(FechamentoOrdemServico fechamento) {
        String id = fechamento.getNumeroOrdemServico();
        if (buscar(id) != null) {
            cadastroObjetos.alterar(fechamento, id);
            return true;
        } else {
            return false;
        }
    }

    public boolean excluir(String numeroOrdemServico) {
        if (buscar(numeroOrdemServico) != null) {
            cadastroObjetos.excluir(numeroOrdemServico);
            return true;
        } else {
            return false;
        }
    }

    public FechamentoOrdemServico[] buscarTodos() {
        Serializable[] ret = cadastroObjetos.buscarTodos();
        FechamentoOrdemServico[] fechamentos;
        if (ret != null && ret.length > 0) {
            fechamentos = new FechamentoOrdemServico[ret.length];
            for (int i = 0; i < ret.length; i++) {
                fechamentos[i] = (FechamentoOrdemServico) ret[i];
            }
        } else {
            fechamentos = new FechamentoOrdemServico[0];
        }
        return fechamentos;
    }
}