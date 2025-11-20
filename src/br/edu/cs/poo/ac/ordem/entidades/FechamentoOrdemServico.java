package br.edu.cs.poo.ac.ordem.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import br.edu.cs.poo.ac.utils.Registro;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class FechamentoOrdemServico implements Registro {

    private String numeroOrdemServico;
    private LocalDate dataFechamento;
    private boolean pago;
    private String relatorioFinal;

    @Override
    public String getId() {
        return numeroOrdemServico;
    }

}
