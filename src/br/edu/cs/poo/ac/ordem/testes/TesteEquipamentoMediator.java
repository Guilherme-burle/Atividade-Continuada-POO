 }


    @Test
    public void testeExcluirNotebookNaoSucesso() {
        Notebook note = new Notebook("NXXX112", "THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG", false, 3.0, false);
        String id = note.getIdTipo() + note.getSerial();
        cadastro.incluir(note, id);
        ResultadoMediator res = mediator.excluirNotebook(null);
        assertionsExclusaoIdSerialVazio(res, Notebook.class);
        Assertions.assertEquals(ID_DO_TIPO_SERIAL_DO_NOTEBOOK_NAO_INFORMADO, res.getMensagensErro().buscar(0));
        res = mediator.excluirNotebook(STR_VAZIA);
        assertionsExclusaoIdSerialVazio(res, Notebook.class);
        Assertions.assertEquals(ID_DO_TIPO_SERIAL_DO_NOTEBOOK_NAO_INFORMADO, res.getMensagensErro().buscar(0));
        res = mediator.excluirNotebook("NO0000");
        Assertions.assertNotNull(res);
        Assertions.assertTrue(res.isValidado());
        Assertions.assertFalse(res.isOperacaoRealizada());
        Assertions.assertNotNull(res.getMensagensErro());
        Assertions.assertEquals(1, res.getMensagensErro().tamanho());
        Assertions.assertEquals(1, obterQuantidadeRegistros());
        Assertions.assertEquals(SERIAL_DO_NOTEBOOK_NAO_EXISTENTE, res.getMensagensErro().buscar(0));
    }

    @Test
    public void testeExcluirNotebookSucesso() {
        String serial = "NXXX112";
        Notebook note = new Notebook(serial, "THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG", false, 3.0, false);
        String id = note.getIdTipo() + note.getSerial();
        cadastro.incluir(note, id);
        ResultadoMediator res = mediator.excluirNotebook(id);
        Assertions.assertNotNull(res);
        Assertions.assertTrue(res.isValidado());
        Assertions.assertTrue(res.isOperacaoRealizada());
        Assertions.assertNotNull(res.getMensagensErro());
        Assertions.assertEquals(0, res.getMensagensErro().tamanho());
        Assertions.assertEquals(0, obterQuantidadeRegistros());
    }

    @Test
    public void testeExcluirDesktopNaoSucesso() {
        Desktop des = new Desktop("NXXX112", "THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG", false, 3.0, false);
        String id = des.getIdTipo() + des.getSerial();
        cadastroDesktop.incluir(des, id);
        ResultadoMediator res = mediator.excluirDesktop(null);
        assertionsExclusaoIdSerialVazio(res, Desktop.class);
        Assertions.assertEquals(ID_DO_TIPO_SERIAL_DO_DESKTOP_NAO_INFORMADO, res.getMensagensErro().buscar(0));
        res = mediator.excluirDesktop(STR_VAZIA);
        assertionsExclusaoIdSerialVazio(res, Desktop.class);
        Assertions.assertEquals(ID_DO_TIPO_SERIAL_DO_DESKTOP_NAO_INFORMADO, res.getMensagensErro().buscar(0));
        res = mediator.excluirDesktop("NO0000");
        Assertions.assertNotNull(res);
        Assertions.assertTrue(res.isValidado());
        Assertions.assertFalse(res.isOperacaoRealizada());
        Assertions.assertNotNull(res.getMensagensErro());
        Assertions.assertEquals(1, res.getMensagensErro().tamanho());
        Assertions.assertEquals(1, obterQuantidadeRegistrosPorTipo(Desktop.class));
        Assertions.assertEquals(SERIAL_DO_DESKTOP_NAO_EXISTENTE, res.getMensagensErro().buscar(0));
    }

    @Test
    public void testeExcluirDesktopSucesso() {
        String serial = "NXXX112";
        Desktop des = new Desktop(serial, "THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG", false, 3.0, false);
        String id = des.getIdTipo() + des.getSerial();
        cadastroDesktop.incluir(des, id);
        ResultadoMediator res = mediator.excluirDesktop(id);
        Assertions.assertNotNull(res);
        Assertions.assertTrue(res.isValidado());
        Assertions.assertTrue(res.isOperacaoRealizada());
        Assertions.assertNotNull(res.getMensagensErro());
        Assertions.assertEquals(0, res.getMensagensErro().tamanho());
        Assertions.assertEquals(0, obterQuantidadeRegistros());
    }

    @Test
    public void testeBuscarDesktopInexistente() {
        String serial = "NXXX112";

        Desktop note = new Desktop(serial, "THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG", false, 3.0, false);
        String id = note.getIdTipo() + note.getSerial();
        cadastroDesktop.incluir(note, id);
        Desktop desBus = mediator.buscarDesktop("NO000000");
        Assertions.assertNull(desBus);
        Assertions.assertEquals(1, obterQuantidadeRegistrosPorTipo(Desktop.class));
        desBus = mediator.buscarDesktop(null);
        Assertions.assertNull(desBus);
        Assertions.assertEquals(1, obterQuantidadeRegistrosPorTipo(Desktop.class));
        desBus = mediator.buscarDesktop(STR_VAZIA);
        Assertions.assertNull(desBus);
        Assertions.assertEquals(1, obterQuantidadeRegistrosPorTipo(Desktop.class));
    }

    @Test
    public void testeBuscarNotebookInexistente() {
        String serial = "NXXX112";

        Notebook note = new Notebook(serial, "THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG", false, 3.0, false);
        String id = note.getIdTipo() + note.getSerial();
        cadastro.incluir(note, id);
        Notebook noteBus = mediator.buscarNotebook("NO000000");
        Assertions.assertNull(noteBus);
        Assertions.assertEquals(1, obterQuantidadeRegistros());
        noteBus = mediator.buscarNotebook(null);
        Assertions.assertNull(noteBus);
        Assertions.assertEquals(1, obterQuantidadeRegistros());
        noteBus = mediator.buscarNotebook(STR_VAZIA);
        Assertions.assertNull(noteBus);
        Assertions.assertEquals(1, obterQuantidadeRegistros());
    }
    @Test
    public void testeBuscarDesktopSucesso() {
        String id = "NO11111";
        Desktop des = new Desktop(id, "THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG", false, 3.0, false);
        cadastroDesktop.incluir(des, id);
        Desktop desBuscado = mediator.buscarDesktop(id);
        Assertions.assertNotNull(desBuscado);
        Assertions.assertEquals(1, obterQuantidadeRegistrosPorTipo(Desktop.class));
        Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(desBuscado, des));
    }
    private void assertionsResultadoTudoNuloBranco(ResultadoMediator res) {
        ListaString mensagens = res.getMensagensErro();
        Assertions.assertEquals(2, mensagens.tamanho());
        Assertions.assertEquals(DESCRICAO_NAO_INFORMADA, mensagens.buscar(0));
        Assertions.assertEquals(SERIAL_NAO_INFORMADO, mensagens.buscar(1));
    }
    private void wrapTesteValidarEquipamento01(Function<DadosEquipamento, ResultadoMediator> conversor) {
        ResultadoMediator res = mediator.validar(null);
        assertionsResultadoMediatorNaoValidado(res);
        ListaString mensagens = res.getMensagensErro();
        Assertions.assertEquals(1, mensagens.tamanho());
        Assertions.assertEquals("Dados básicos do equipamento não informados", mensagens.buscar(0));
        DadosEquipamento dadosEquip = new DadosEquipamento(null, null, false, 1.0);
        res = conversor.apply(dadosEquip);
        assertionsResultadoMediatorNaoValidado(res);
        assertionsResultadoTudoNuloBranco(res);
        dadosEquip = new DadosEquipamento("                      ", " ", false, 2.0);
        res = conversor.apply(dadosEquip);
        assertionsResultadoMediatorNaoValidado(res);
        assertionsResultadoTudoNuloBranco(res);
    }
    private void wrapTesteValidarEquipamento02(Function<DadosEquipamento, ResultadoMediator> conversor) {
        DadosEquipamento dadosEquip = new DadosEquipamento("DE12345", "A".repeat(152), false, 3.0);
        ResultadoMediator res =  conversor.apply(dadosEquip);
        assertionsResultadoMediatorNaoValidado(res);
        ListaString mensagens = res.getMensagensErro();
        Assertions.assertEquals(1, mensagens.tamanho());
        Assertions.assertEquals(DESCRICAO_TEM_MAIS_DE_150_CARACTERES, mensagens.buscar(0));
        dadosEquip = new DadosEquipamento("NO12345", "ABCDEF", false, 4.0);
        res =  conversor.apply(dadosEquip);
        assertionsResultadoMediatorNaoValidado(res);
        mensagens = res.getMensagensErro();
        Assertions.assertEquals(1, mensagens.tamanho());
        Assertions.assertEquals(DESCRICAO_TEM_MENOS_DE_10_CARACTERES, mensagens.buscar(0));
    }
    private void wrapTesteValidarEquipamento03(Function<DadosEquipamento, ResultadoMediator> conversor) {
        DadosEquipamento dadosEquip = new DadosEquipamento(STR_VAZIA, null, true, 0.0);
        ResultadoMediator res =  conversor.apply(dadosEquip);
        assertionsResultadoMediatorNaoValidado(res);
        ListaString mensagens = res.getMensagensErro();
        Assertions.assertEquals(3, mensagens.tamanho());
        Assertions.assertEquals(DESCRICAO_NAO_INFORMADA, mensagens.buscar(0));
        Assertions.assertEquals(SERIAL_NAO_INFORMADO, mensagens.buscar(1));
        Assertions.assertEquals(VALOR_ESTIMADO_MENOR_OU_IGUAL_A_ZERO, mensagens.buscar(2));
        dadosEquip = new DadosEquipamento(STR_VAZIA, "ABCDEFGHIJKL", false, -2.0);
        res =  conversor.apply(dadosEquip);
        mensagens = res.getMensagensErro();
        Assertions.assertEquals(2, mensagens.tamanho());
        Assertions.assertEquals(SERIAL_NAO_INFORMADO, mensagens.buscar(0));
