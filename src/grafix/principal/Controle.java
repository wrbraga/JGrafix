/*
  Copyright (C) 2001-2012, Joao Medeiros, Paulo Vilela (grafix2.com)
  
  Este arquivo é parte do programa Grafix2.com
  
  Grafix2.com é um software livre; você pode redistribui-lo e/ou 
  modifica-lo dentro dos termos da Licença Pública Geral GNU como 
  publicada pela Fundação do Software Livre (FSF); na versão 2 da 
  Licença.

  Este programa é distribuido na esperança que possa ser útil, 
  mas SEM NENHUMA GARANTIA; sem uma garantia implicita de ADEQUAÇÂO a qualquer
  MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a
  Licença Pública Geral GNU para maiores detalhes.

  Você deve ter recebido uma cópia da Licença Pública Geral GNU
  junto com este programa, se não, veja uma cópia em
  <http://www.gnu.org/licenses/>
  
 */



package grafix.principal;

import grafix.auxiliar.*;
import grafix.seguranca.ControleRegistro;
import grafix.telas.JanelaGraficos;
import grafix.telas.TelaGrafix;

import java.awt.*;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Controle {
    
    private static TelaGrafix telaGrafix;
    private static HistoricoPapeis historicoPapeis;
    private static CarteiraDeAcoes carteira;
    private static ConfiguracoesUsuario configuracoesUsuario;
    private static ConfiguracoesGrafix configuracoesGrafix;
    private static ConfiguracoesVolateis configuracoesVolateis;
    private static GeradorCriptografia criptografia;
    private static FormatadorPadrao formatador;
    
    public Controle() {
        // Registro liberado ***********
        ControleRegistro.setRegistrado(true); // ControleRegistro.avaliarRegistro();
        //******************************
        setConfiguracoesGrafix(ConfiguracoesGrafix.carregar());
        setConfiguracoesUsuario(ConfiguracoesUsuario.carregar());
        setConfiguracoesVolateis(new ConfiguracoesVolateis());
        setHistoricoPapeis(HistoricoPapeis.carregar());
        setFormatador(new FormatadorPadrao());
        checarPrimeiroAcesso();
        checarDefinicaoDaListaDePapeis();
        iniciarTeclasAtalho();
        setCarteira(new CarteiraDeAcoes());
        telaGrafix = new TelaGrafix();
        telaGrafix.exibir();
    }
    
    private void checarPrimeiroAcesso() {
       
        // Testa se usuário completou o processo
//        if(configuracoesGrafix.isPrimeiroAcesso()) {
//
//            Controle.exibirErro("O programa será finalizado pois não completou o processo de configuração inicial da base de dados.");
//            System.exit(1);
//        }
    }
    
    private void iniciarTeclasAtalho() {
        Toolkit.getDefaultToolkit().addAWTEventListener(new TeclasAtalho(),
                AWTEvent.KEY_EVENT_MASK);
    }
    
    private void checarDefinicaoDaListaDePapeis() {
        // A lista definida não existe
       
            getConfiguracoesGrafix().setListaAcoes("basica.lst");
            
         
        
        if (!ManipuladorArquivos.existeArquivo(ConfiguracoesGrafix.PASTA_LISTAS + configuracoesGrafix.getListaAcoes())) {
            System.out.println("Lista de acões '" + configuracoesGrafix.getListaAcoes() + "' não existe!");
            getConfiguracoesGrafix().setListaAcoes("basica.lst");
        }
        if (configuracoesGrafix.getListaAcoes() == null) {
            getConfiguracoesGrafix().setListaAcoes("basica.lst");
        }
    }
    
//    private void prepararAmbienteInicial() {
//        if(getCarteira().getAcoes().size()==0) {
//            AjudaGrafix.exibirMensagem(AjudaGrafix.CARTEIRA_VAZIA);
//        }
//        else {
//            if(telaGrafix.getJanelasGraficos().size()==0) {
//                Comandos.cmdAddJanela();
//            } else {
//                telaGrafix.ativarPrimeiraJanela();
//            }
//        }
//    }
    public static CarteiraDeAcoes getCarteira() {
        return carteira;
    }
    
    public static TelaGrafix getTela() {
        return telaGrafix;
    }
    
    public static JanelaGraficos getJanelaAtiva() {
        return telaGrafix.getJanelaAtiva();
    }
    
    public static ConfiguracoesUsuario getConfiguracoesUsuario() {
        return configuracoesUsuario;
    }
    
    public static void setConfiguracoesUsuario(ConfiguracoesUsuario conf) {
        configuracoesUsuario = conf;
    }
    
    public static void salvarConfiguracoesUsuario(boolean restaurarAposSalvar) {
        //  Atualizar as configuracoesUsuario
        //configuracoesUsuario.setExibeFinaisDeSemana();
        configuracoesUsuario.setNumColunasLayout(telaGrafix.getGridLayoutJanelas().getColumns());
        configuracoesUsuario.setNumLinhasLayout(telaGrafix.getGridLayoutJanelas().getRows());
        configuracoesUsuario.setConfiguracoesJanelas(telaGrafix);
        configuracoesUsuario.salvar(restaurarAposSalvar);
    }
    
    private static void salvarConfiguracoesGrafix() {
        configuracoesGrafix.setLookAndFeel(UIManager.getLookAndFeel().getClass().getName());
        configuracoesGrafix.salvar();
    }
    
    public static void exibirErro(String mensagem) {
        JOptionPane.showMessageDialog(telaGrafix, mensagem, "Erro - Grafix", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void exibirInfo(String mensagem) {
        JOptionPane.showMessageDialog(telaGrafix, mensagem, "Info - Grafix", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void exibirAjuda(String mensagem) {
        JOptionPane.showMessageDialog(telaGrafix, mensagem, "Ajuda - Grafix", JOptionPane.QUESTION_MESSAGE);
    }
    
    public static String exibirInputBox(String mensagem) {
        return JOptionPane.showInputDialog(telaGrafix, mensagem, "Grafix", JOptionPane.QUESTION_MESSAGE);
    }
    
    public static ConfiguracoesGrafix getConfiguracoesGrafix() {
        return configuracoesGrafix;
    }
    
    public static void setConfiguracoesGrafix(ConfiguracoesGrafix aConfiguracoesGrafix) {
        configuracoesGrafix = aConfiguracoesGrafix;
    }
    
    public static void finalizarPrograma() {
        salvarConfiguracoesUsuario(false);
        salvarConfiguracoesGrafix();
    }
    
    public static void setCarteira(CarteiraDeAcoes aCarteira) {
        carteira = aCarteira;
    }
    
    public static void reiniciarModoSeguro() {/////////////////////////////////////////////////////////////////////////
        exibirErro("As configuracoes foram reiniciadas devido a um erro desconhecido. Reinicie a aplicação.");
        //telaGrafix.fecharJanelas();
        configuracoesGrafix = ConfiguracoesGrafix.reiniciar();
        configuracoesUsuario = ConfiguracoesUsuario.reiniciar();
        configuracoesGrafix.salvar();
        configuracoesUsuario.salvar(false);
    }
    
    public static GeradorCriptografia getCriptografia() {
        return criptografia;
    }
    
    public static void setCriptografia(GeradorCriptografia aCriptografia) {
        criptografia = aCriptografia;
    }
    
    public static HistoricoPapeis getHistoricoPapeis() {
        return historicoPapeis;
    }
    
    public static void setHistoricoPapeis(HistoricoPapeis aHistoricoPapeis) {
        historicoPapeis = aHistoricoPapeis;
    }
    
    public static FormatadorPadrao getFormatador() {
        return formatador;
    }
    
    public static void setFormatador(FormatadorPadrao aFormatador) {
        formatador = aFormatador;
    }
    
//    private void criarListaDefault() {
//        throw new UnsupportedOperationException("Not yet implemented");
//    }
    public static void apagarRegistros(Acao acaoAntiga) {
        Vector<JanelaGraficos> janelas = getTela().getJanelasGraficos();
        for (JanelaGraficos j : janelas) {
            if (j.getAcao().getCodAcao().equals(acaoAntiga.getCodAcao())) {
                return; // Existe alguma janela usando esta ação.
            }
        }
        acaoAntiga.apagarRegistros();
    }
    
    public static void apagarTodosOsRegistros() {
        Vector<JanelaGraficos> janelas = Controle.getTela().getJanelasGraficos();
        for (JanelaGraficos j : janelas) {
            j.getAcao().apagarRegistros();
        }
    }
    
    static void reiniciarTodosOsIntervaloExibicao() {
        Vector<JanelaGraficos> janelas = Controle.getTela().getJanelasGraficos();
        for (JanelaGraficos j : janelas) {
            j.iniciarIntervaloExibicao();
        }
    }
    
    public static ConfiguracoesVolateis getConfiguracoesVolateis() {
        return configuracoesVolateis;
    }
    
    public static void setConfiguracoesVolateis(ConfiguracoesVolateis aConfiguracoesVolateis) {
        configuracoesVolateis = aConfiguracoesVolateis;
    }
}
