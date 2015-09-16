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



package grafix.telas;

import grafix.auxiliar.*;
import grafix.graficos.*;
import grafix.graficos.eixos.*;
import grafix.principal.*;
import javax.swing.*;
import javax.swing.event.*;
import org.jfree.chart.axis.*;

/**
 * Teste
 * @author marcos
 */

public class JanelaGraficos extends JInternalFrame{
    
    private TelaGrafix tela;
    private Acao acao;
    private ConstrutorGrafico construtorGrafico = new ConstrutorGrafico(this);
    private PanelGraficos panelGraficos;
    private PanelMolduras panelMolduras;
    private JLayeredPane layersPane;
    private ConfiguracoesJanela configuracoes;
    private IntervaloExibicao intervalo;
    
    public JanelaGraficos(TelaGrafix tela, Acao acao, ConfiguracoesJanela confJanela) {
        try {
            this.setTela(tela);
            this.setConfiguracoesJanela(confJanela);
            this.setAcao(acao);
            this.iniciarIntervaloExibicao();
            this.setPanelGraficos(new PanelGraficos(this, construtorGrafico.criarJFreeChart()));
            this.setPanelMolduras(new PanelMolduras(this));
            configurarJanela();
        } catch(Exception e) {
            System.out.println("Impossivel abrir janela para acao: " + acao.getCodAcao());
            e.printStackTrace();
            Controle.reiniciarModoSeguro();
        }
    }
    
    // Janelas nova incluida pelo usuário
    public JanelaGraficos(TelaGrafix tela, Acao acao) {
        this(tela, acao, new ConfiguracoesJanela());
    }
    
    // Janelas criadas a partir de Configuracoes de Janela lidas de arquivos
    public JanelaGraficos(TelaGrafix tela, ConfiguracoesJanela confJanela) {
        this(tela, Controle.getCarteira().getAcao(confJanela.getCodAcao()), confJanela);
    }

    private void configurarJanela() {
        this.setBorder(BorderFactory.createEtchedBorder());
        this.setClosable(true);
        this.setIconifiable(false);
        this.setMaximizable(true);
        this.setResizable(true);
        this.setBounds(0, 0, 500, 500);
        this.setVisible(true);
        this.setTitle(getAcao().getCodAcao() + " - " + getAcao().getNomeAcao());
        this.setFrameIcon(new ImageIcon("resource/icones/grafix3.gif"));
        layersPane = new JLayeredPane();
        layersPane.setLayout(new OverlayLayout(layersPane));
        layersPane.add(getPanelGraficos(), JLayeredPane.FRAME_CONTENT_LAYER);
        layersPane.add(getPanelMolduras(), JLayeredPane.DRAG_LAYER);
        this.add(layersPane);
        this.addInternalFrameListener(new InternalFrameListener() {
            public void internalFrameActivated(InternalFrameEvent evt) {
                janelaAtivada();
            }
            public void internalFrameClosed(InternalFrameEvent evt) {
                janelaFechada();
            }
            public void internalFrameClosing(InternalFrameEvent evt) {}
            public void internalFrameDeactivated(InternalFrameEvent evt) {}
            public void internalFrameDeiconified(InternalFrameEvent evt) {}
            public void internalFrameIconified(InternalFrameEvent evt) {}
            public void internalFrameOpened(InternalFrameEvent evt) {}
        });
    }

    public void iniciarIntervaloExibicao() {
        this.setIntervaloExibicao(new IntervaloExibicao(this));
    }
    
    private void janelaAtivada() {
//        tela.getComboAcoes().setSelectedItem(getAcao());
        tela.getComboAcoes().alterarSelecaoSemDispararTroca(getAcao());
    }
    
    private void janelaFechada() {
        tela.excluirJanela(this);
        tela.ativarPrimeiraJanela();
    }
    
    public void atualizarPainel() {
        panelGraficos.setCursorExtendido(Controle.getConfiguracoesUsuario().isCursorExtendido());
        panelGraficos.setChart(construtorGrafico.criarJFreeChart());
        panelMolduras.reiniciarMolduras();
    }
    
    public void trocarAcao(Acao novaAcao) {
        if(novaAcao.getCodAcao().startsWith("*")) {
            Controle.exibirErro("Não existe arquivo de dados deste papel!");
            return;
        }
        salvarAnaliseAcao();
        Acao acaoAntiga = getAcao();
        this.setAcao(novaAcao);
        Controle.apagarRegistros(acaoAntiga);
        this.setTitle(getAcao().getCodAcao() + " - " + getAcao().getNomeAcao());
        getIntervaloExibicao().atualizarIntervaloExibicaoAposTrocaDeAcao();
        this.getPanelMolduras().reiniciarMolduras();
    }
    
    public TelaGrafix getTela() {
        return tela;
    }
    
    public void setTela(TelaGrafix tela) {
        this.tela = tela;
    }
    
    public Acao getAcao() {
        return acao;
    }
    
    public void setAcao(Acao acao) {
        this.acao = acao;
    }
    
    public DateAxis getDomainAxis() {
        return getPanelGraficos().getDomainAxis();
    }
    
    public ValueAxis getValueAxisCandles() {
        return getPanelGraficos().getValueAxisCandles();
    }
    
    public PanelGraficos getPanelGraficos() {
        return panelGraficos;
    }
    
    public void setPanelGraficos(PanelGraficos panelGraficos) {
        this.panelGraficos = panelGraficos;
    }
    
    public void salvarAnaliseAcao() {
        LeitorArquivoAnalise.getInstance().salvar(getAcao().getAnalise());
    }
    
    public ConfiguracoesJanela getConfiguracoesJanela() {
        return configuracoes;
    }
    
    public void setConfiguracoesJanela(ConfiguracoesJanela configuracoes) {
        this.configuracoes = configuracoes;
    }
    
    public PanelMolduras getPanelMolduras() {
        return panelMolduras;
    }
    
    public void setPanelMolduras(PanelMolduras panelMolduras) {
        this.panelMolduras = panelMolduras;
    }
    
    public void adicionarEixoExtra(EixoExtra eixoExtra) {
        getConfiguracoesJanela().getEixosExtras().add(eixoExtra);
        atualizarPainel();
    }
    
    public void removerEixoExtra(EixoExtra eixoExtra) {
        getConfiguracoesJanela().getEixosExtras().remove(eixoExtra);
        atualizarPainel();
    }
    
    public void apagarTodosOsElementosGraficos() {
        getAcao().getAnalise().apagarObjetosAnalise();
        getTela().atualizarJanelasGraficos();
    }

    public IntervaloExibicao getIntervaloExibicao() {
        return intervalo;
    }

    public void setIntervaloExibicao(IntervaloExibicao intervalo) {
        this.intervalo = intervalo;
    }
}
