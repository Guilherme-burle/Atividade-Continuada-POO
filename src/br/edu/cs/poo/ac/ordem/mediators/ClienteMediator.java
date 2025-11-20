package br.edu.cs.poo.ac.ordem.mediators;

import java.time.LocalDate;
import br.edu.cs.poo.ac.ordem.daos.ClienteDAO;
import br.edu.cs.poo.ac.ordem.entidades.Cliente;
import br.edu.cs.poo.ac.ordem.entidades.Contato;
import br.edu.cs.poo.ac.utils.ErroValidacaoCPFCNPJ;
import br.edu.cs.poo.ac.utils.ListaString;
import br.edu.cs.poo.ac.utils.ResultadoValidacaoCPFCNPJ;
import br.edu.cs.poo.ac.utils.StringUtils;
import br.edu.cs.poo.ac.utils.ValidadorCPFCNPJ;

public class ClienteMediator {

    private static ClienteMediator instancia;
    private ClienteDAO clienteDAO = new ClienteDAO();

    private ClienteMediator() {}

    public static ClienteMediator getInstancia() {
        if (instancia == null) {
            instancia = new ClienteMediator();
        }
        return instancia;
    }

    public ResultadoMediator validar(Cliente cliente) {
        ListaString mensagensErro = new ListaString();

        if (cliente == null) {
            mensagensErro.adicionar("Cliente não informado");
            return new ResultadoMediator(false, false, mensagensErro);
        }

        if (StringUtils.estaVazia(cliente.getCpfCnpj())) {
            mensagensErro.adicionar("CPF/CNPJ não informado");
        } else {
            ResultadoValidacaoCPFCNPJ resVal = ValidadorCPFCNPJ.validarCPFCNPJ(cliente.getCpfCnpj());
            if (resVal.getErroValidacao() != null) {
                if (resVal.getErroValidacao() == ErroValidacaoCPFCNPJ.CPF_CNPJ_COM_DV_INVALIDO) {
                    mensagensErro.adicionar("CPF ou CNPJ com dígito verificador inválido");
                } else if (resVal.getErroValidacao() == ErroValidacaoCPFCNPJ.CPF_CNPJ_NAO_E_CPF_NEM_CNPJ) {
                    mensagensErro.adicionar("Não é CPF nem CNJP");
                }
            }
        }

        if (StringUtils.estaVazia(cliente.getNome())) {
            mensagensErro.adicionar("Nome não informado");
        } else if (StringUtils.tamanhoExcedido(cliente.getNome(), 50)) {
            mensagensErro.adicionar("Nome tem mais de 50 caracteres");
        }

        if (cliente.getContato() == null) {
            mensagensErro.adicionar("Contato não informado");
        } else {
            validarContato(cliente.getContato(), mensagensErro);
        }

        if (cliente.getDataCadastro() == null) {
            mensagensErro.adicionar("Data do cadastro não informada");
        } else if (cliente.getDataCadastro().isAfter(LocalDate.now())) {
            mensagensErro.adicionar("Data do cadastro não pode ser posterior à data atual");
        }

        boolean validado = mensagensErro.tamanho() == 0;
        return new ResultadoMediator(validado, false, mensagensErro);
    }

    private void validarContato(Contato contato, ListaString mensagensErro) {
        boolean emailVazio = StringUtils.estaVazia(contato.getEmail());
        boolean celularVazio = StringUtils.estaVazia(contato.getCelular());

        if (emailVazio && celularVazio) {
            mensagensErro.adicionar("Celular e e-mail não foram informados");
            return;
        }

        if (!emailVazio && !StringUtils.emailValido(contato.getEmail())) {
            mensagensErro.adicionar("E-mail está em um formato inválido");
        }

        if (!celularVazio && !StringUtils.telefoneValido(contato.getCelular())) {
            mensagensErro.adicionar("Celular está em um formato inválido");
        }

        if (celularVazio && contato.isEhZap()) {
            mensagensErro.adicionar("Celular não informado e indicador de zap ativo");
        }
    }

    public ResultadoMediator incluir(Cliente cliente) {
        ResultadoMediator resultadoValidacao = validar(cliente);
        if (!resultadoValidacao.isValidado()) {
            return resultadoValidacao;
        }

        if (clienteDAO.buscar(cliente.getCpfCnpj()) != null) {
            ListaString erros = new ListaString();
            erros.adicionar("CPF/CNPJ já existente");
            return new ResultadoMediator(true, false, erros);
        }

        clienteDAO.incluir(cliente);
        return new ResultadoMediator(true, true, new ListaString());
    }

    public ResultadoMediator alterar(Cliente cliente) {
        ResultadoMediator resultadoValidacao = validar(cliente);
        if (!resultadoValidacao.isValidado()) {
            return resultadoValidacao;
        }

        if (clienteDAO.buscar(cliente.getCpfCnpj()) == null) {
            ListaString erros = new ListaString();
            erros.adicionar("CPF/CNPJ inexistente");
            return new ResultadoMediator(true, false, erros);
        }

        clienteDAO.alterar(cliente);
        return new ResultadoMediator(true, true, new ListaString());
    }

    public Cliente buscar(String cpfCnpj) {
        if (StringUtils.estaVazia(cpfCnpj)) {
            return null;
        }
        return clienteDAO.buscar(cpfCnpj);
    }

    public ResultadoMediator excluir(String cpfCnpj) {
        if (StringUtils.estaVazia(cpfCnpj)) {
            ListaString erros = new ListaString();
            erros.adicionar("CPF/CNPJ não informado");
            return new ResultadoMediator(false, false, erros);
        }

        if (clienteDAO.buscar(cpfCnpj) == null) {
            ListaString erros = new ListaString();
            erros.adicionar("CPF/CNPJ inexistente");
            return new ResultadoMediator(true, false, erros);
        }

        clienteDAO.excluir(cpfCnpj);
        return new ResultadoMediator(true, true, new ListaString());
    }
}
