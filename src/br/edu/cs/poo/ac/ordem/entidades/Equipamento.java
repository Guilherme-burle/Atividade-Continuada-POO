package br.edu.cs.poo.ac.ordem.entidades;

import lombok.AllArgsConstructor;
import br.edu.cs.poo.ac.utils.Registro;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public abstract class Equipamento implements Registro {
    private String serial;
    private String descricao;
    private boolean ehNovo;
    private double valorEstimado;

    public abstract String getIdTipo();

    @Override
    public String getId() {
        return getIdTipo() + serial;
    }
}
