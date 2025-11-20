package br.edu.cs.poo.ac.ordem.daos;

import br.edu.cs.poo.ac.ordem.entidades.OrdemServico;
import br.edu.cs.poo.ac.utils.Registro;

public class OrdemServicoDAO extends DAOGenerico {

    @Override
    public Class<?> getClasseEntidade() {
        return OrdemServico.class;
    }

    public OrdemServico buscar(String numero) {
        return (OrdemServico) super.buscar(numero);
    }

    public boolean incluir(OrdemServico ordemServico) {
        return super.incluir(ordemServico);
    }

    public boolean alterar(OrdemServico ordemServico) {
        return super.alterar(ordemServico);
    }

    public boolean excluir(String numero) {
        return super.excluir(numero);
    }

    public OrdemServico[] buscarTodos() {
        Registro[] registros = super.buscarTodos();
        OrdemServico[] ordens = new OrdemServico[registros.length];
        for (int i = 0; i < registros.length; i++) {
            ordens[i] = (OrdemServico) registros[i];
        }
        return ordens;
    }
}
