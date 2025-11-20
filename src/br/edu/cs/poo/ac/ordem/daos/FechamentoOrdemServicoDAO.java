package br.edu.cs.poo.ac.ordem.daos;

import br.edu.cs.poo.ac.ordem.entidades.FechamentoOrdemServico;
import br.edu.cs.poo.ac.utils.Registro;

public class FechamentoOrdemServicoDAO extends DAOGenerico {

    @Override
    public Class<?> getClasseEntidade() {
        return FechamentoOrdemServico.class;
    }

    public FechamentoOrdemServico buscar(String numeroOrdemServico) {
        return (FechamentoOrdemServico) super.buscar(numeroOrdemServico);
    }

    public boolean incluir(FechamentoOrdemServico fechamento) {
        return super.incluir(fechamento);
    }

    public boolean alterar(FechamentoOrdemServico fechamento) {
        return super.alterar(fechamento);
    }

    public boolean excluir(String numeroOrdemServico) {
        return super.excluir(numeroOrdemServico);
    }

    public FechamentoOrdemServico[] buscarTodos() {
        Registro[] registros = super.buscarTodos();
        FechamentoOrdemServico[] fechamentos = new FechamentoOrdemServico[registros.length];
        for (int i = 0; i < registros.length; i++) {
            fechamentos[i] = (FechamentoOrdemServico) registros[i];
        }
        return fechamentos;
    }
}
