package br.edu.cs.poo.ac.ordem.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import br.edu.cs.poo.ac.utils.Registro;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class OrdemServico implements Registro {

    private Cliente cliente;
    private PrecoBase precoBase;
    private Notebook notebook;
    private Desktop desktop;
    private LocalDateTime dataHoraAbertura;
    private int prazoEmDias;
    private double valor;

    public LocalDate getDataEstimadaEntrega() {
        return this.dataHoraAbertura.toLocalDate().plusDays(this.prazoEmDias);
    }

    public String getNumero() {
        String tipoEquipamento;
        if (this.notebook != null) {
            tipoEquipamento = this.notebook.getIdTipo();
        } else if (this.desktop != null) {
            tipoEquipamento = this.desktop.getIdTipo();
        } else {
            tipoEquipamento = "ND";
        }

        String ano = String.valueOf(this.dataHoraAbertura.getYear());
        String mes = String.format("%02d", this.dataHoraAbertura.getMonthValue());
        String dia = String.format("%02d", this.dataHoraAbertura.getDayOfMonth());
        String hora = String.format("%02d", this.dataHoraAbertura.getHour());
        String minuto = String.format("%02d", this.dataHoraAbertura.getMinute());

        String documentoCliente = this.cliente.getCpfCnpj();

        if (documentoCliente.length() > 11) {
            return tipoEquipamento + ano + mes + dia + hora + minuto + documentoCliente;
        } else {
            return tipoEquipamento + ano + mes + dia + hora + minuto + "000" + documentoCliente;
        }
    }

    @Override
    public String getId() {
        return getNumero();
    }
}
