package br.edu.cs.poo.ac.ordem.mediators;

import br.edu.cs.poo.ac.ordem.daos.DesktopDAO;
import br.edu.cs.poo.ac.ordem.daos.NotebookDAO;
import br.edu.cs.poo.ac.ordem.entidades.Desktop;
import br.edu.cs.poo.ac.ordem.entidades.Notebook;
import br.edu.cs.poo.ac.utils.ListaString;
import br.edu.cs.poo.ac.utils.StringUtils;

public class EquipamentoMediator {

    private static EquipamentoMediator instancia;
    private DesktopDAO desktopDAO = new DesktopDAO();
    private NotebookDAO notebookDAO = new NotebookDAO();

    private EquipamentoMediator() {}

    public static EquipamentoMediator getInstancia() {
        if (instancia == null) {
            instancia = new EquipamentoMediator();
        }
        return instancia;
    }

    public ResultadoMediator validar(DadosEquipamento equip) {
        ListaString mensagensErro = new ListaString();
        if (equip == null) {
            mensagensErro.adicionar("Dados básicos do equipamento não informados");
            return new ResultadoMediator(false, false, mensagensErro);
        }

        if (StringUtils.estaVazia(equip.getDescricao())) {
            mensagensErro.adicionar("Descrição não informada");
        } else {
            if (StringUtils.tamanhoMenor(equip.getDescricao(), 10)) {
                mensagensErro.adicionar("Descrição tem menos de 10 caracteres");
            }
            if (StringUtils.tamanhoExcedido(equip.getDescricao(), 150)) {
                mensagensErro.adicionar("Descrição tem mais de 150 caracteres");
            }
        }

        if (StringUtils.estaVazia(equip.getSerial())) {
            mensagensErro.adicionar("Serial não informado");
        }

        if (equip.getValorEstimado() <= 0) {
            mensagensErro.adicionar("Valor estimado menor ou igual a zero");
        }

        boolean validado = mensagensErro.tamanho() == 0;
        return new ResultadoMediator(validado, false, mensagensErro);
    }

    public ResultadoMediator validarNotebook(Notebook note) {
        if (note == null) {
            ListaString mensagensErro = new ListaString();
            mensagensErro.adicionar("Notebook não informado");
            return new ResultadoMediator(false, false, mensagensErro);
        }
        DadosEquipamento dados = new DadosEquipamento(note.getSerial(), note.getDescricao(), note.isEhNovo(), note.getValorEstimado());
        return validar(dados);
    }

    public ResultadoMediator validarDesktop(Desktop desk) {
        if (desk == null) {
            ListaString mensagensErro = new ListaString();
            mensagensErro.adicionar("Desktop não informado");
            return new ResultadoMediator(false, false, mensagensErro);
        }
        DadosEquipamento dados = new DadosEquipamento(desk.getSerial(), desk.getDescricao(), desk.isEhNovo(), desk.getValorEstimado());
        return validar(dados);
    }

    public ResultadoMediator incluirNotebook(Notebook note) {
        ResultadoMediator resValidacao = validarNotebook(note);
        if (!resValidacao.isValidado()) {
            return resValidacao;
        }

        String id = note.getIdTipo() + note.getSerial();
        if (notebookDAO.buscar(id) != null) {
            ListaString erros = new ListaString();
            erros.adicionar("Serial do notebook já existente");
            return new ResultadoMediator(true, false, erros);
        }

        notebookDAO.incluir(note);
        return new ResultadoMediator(true, true, new ListaString());
    }

    public ResultadoMediator alterarNotebook(Notebook note) {
        ResultadoMediator resValidacao = validarNotebook(note);
        if (!resValidacao.isValidado()) {
            return resValidacao;
        }

        String id = note.getIdTipo() + note.getSerial();
        if (notebookDAO.buscar(id) == null) {
            ListaString erros = new ListaString();
            erros.adicionar("Serial do notebook não existente");
            return new ResultadoMediator(true, false, erros);
        }

        notebookDAO.alterar(note);
        return new ResultadoMediator(true, true, new ListaString());
    }

    public Notebook buscarNotebook(String idTipoSerial) {
        if (StringUtils.estaVazia(idTipoSerial)) {
            return null;
        }
        return notebookDAO.buscar(idTipoSerial);
    }

    public ResultadoMediator excluirNotebook(String idTipoSerial) {
        if (StringUtils.estaVazia(idTipoSerial)) {
            ListaString erros = new ListaString();
            erros.adicionar("Id do tipo + serial do notebook não informado");
            return new ResultadoMediator(false, false, erros);
        }

        if (notebookDAO.buscar(idTipoSerial) == null) {
            ListaString erros = new ListaString();
            erros.adicionar("Serial do notebook não existente");
            return new ResultadoMediator(true, false, erros);
        }

        notebookDAO.excluir(idTipoSerial);
        return new ResultadoMediator(true, true, new ListaString());
    }

    public ResultadoMediator incluirDesktop(Desktop desk) {
        ResultadoMediator resValidacao = validarDesktop(desk);
        if (!resValidacao.isValidado()) {
            return resValidacao;
        }

        String id = desk.getIdTipo() + desk.getSerial();
        if (desktopDAO.buscar(id) != null) {
            ListaString erros = new ListaString();
            erros.adicionar("Serial do desktop já existente");
            return new ResultadoMediator(true, false, erros);
        }

        desktopDAO.incluir(desk);
        return new ResultadoMediator(true, true, new ListaString());
    }

    public ResultadoMediator alterarDesktop(Desktop desk) {
        ResultadoMediator resValidacao = validarDesktop(desk);
        if (!resValidacao.isValidado()) {
            return resValidacao;
        }

        String id = desk.getIdTipo() + desk.getSerial();
        if (desktopDAO.buscar(id) == null) {
            ListaString erros = new ListaString();
            erros.adicionar("Serial do desktop não existente");
            return new ResultadoMediator(true, false, erros);
        }

        desktopDAO.alterar(desk);
        return new ResultadoMediator(true, true, new ListaString());
    }

    public Desktop buscarDesktop(String idTipoSerial) {
        if (StringUtils.estaVazia(idTipoSerial)) {
            return null;
        }
        return desktopDAO.buscar(idTipoSerial);
    }

    public ResultadoMediator excluirDesktop(String idTipoSerial) {
        if (StringUtils.estaVazia(idTipoSerial)) {
            ListaString erros = new ListaString();
            erros.adicionar("Id do tipo + serial do desktop não informado");
            return new ResultadoMediator(false, false, erros);
        }

        if (desktopDAO.buscar(idTipoSerial) == null) {
            ListaString erros = new ListaString();
            erros.adicionar("Serial do desktop não existente");
            return new ResultadoMediator(true, false, erros);
        }

        desktopDAO.excluir(idTipoSerial);
        return new ResultadoMediator(true, true, new ListaString());
    }
}
