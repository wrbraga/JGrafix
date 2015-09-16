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

import grafix.auxiliar.ManipuladorArquivos;
import grafix.graficos.*;
import grafix.graficos.elementos.*;
import grafix.principal.*;
import grafix.principal.Comandos;
import grafix.telas.componentes.*;

import java.awt.*;
import java.util.Vector;
import javax.swing.*;

public class TelaGrafix extends JFrame {
    
    private static boolean programaFinalizado = false;
    static public final Color COR_NOVOS_ELEMENTOS_DEFAULT = Color.BLUE;
    static public final int ESPACAMENTO_HORIZONTAL_JANELAS = 1;
    static public final int ESPACAMENTO_VERTICAL_JANELAS = 1;
    private Vector<JanelaGraficos> janelasGraficos;
    private JDesktopPane areaGraficos;
    private BarraDeFerramentasLateral barraLateralBotoes;
    private BarraDeFerramentasSuperior barraSuperiorBotoes;
    private BarraStatus2 barraStatus;
    private ComboAcoes comboAcoes;
    private ComboConfiguracoes comboConfiguracoes;
    private BarraMenusGrafix menuBar;
    private Color corNovosElementos = COR_NOVOS_ELEMENTOS_DEFAULT;
    private ElementoGrafico novoElemento = null;
    private GridLayout gridLayoutJanelas;
    
    public TelaGrafix() {
        construirTela();
    }
    
    public void construirTela() {
        initComponents();
        menuBar.iniciarLookFeel(Controle.getConfiguracoesGrafix().getLookAndFeel());
        incluirJanelas();
        atualizarJanelasGraficos();
    }
    
    private void initComponents() {
        janelasGraficos = new Vector<JanelaGraficos>();
        areaGraficos = new JDesktopPane();
        barraLateralBotoes = new BarraDeFerramentasLateral(this);
        barraSuperiorBotoes = new BarraDeFerramentasSuperior(this);
        barraStatus = new BarraStatus2(this);
        comboAcoes = new ComboAcoes();
        comboAcoes.setEditable(true);
        this.add(comboAcoes);
        setComboConfiguracoes(new ComboConfiguracoes(this));
        menuBar = new BarraMenusGrafix(this);
        
        Image loadedImage = Toolkit.getDefaultToolkit().getImage("resource/icones/grafix3.gif");
        this.setIconImage(loadedImage);
        this.setTitle("GRAFIX " + ConfiguracoesGrafix.VERSAO);
        setSize(800, 600);
        this.setExtendedState(MAXIMIZED_BOTH); /////////////////////////////////////////////////////
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                finalizarPrograma();
            }
        });
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        // menuBar.iniciarLookFeel(Controle.getConfiguracoesGrafix().getLookAndFeel());
        barraSuperiorBotoes.criarBotoesBarra();
        barraLateralBotoes.criarBotoesBarra();
        
        getAreaGraficos().setBackground(new Color(58, 110, 165));
        //getAreaGraficos().setBackground(java.awt.SystemColor.desktop);
        getAreaGraficos().setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
        setJMenuBar(menuBar);
        if (Controle.getConfiguracoesGrafix().isLocalizacaoStatusSuperior()) {
            JPanel cabecalho = new JPanel();
            cabecalho.setLayout(new java.awt.BorderLayout());
            cabecalho.add(barraSuperiorBotoes, BorderLayout.NORTH);
            cabecalho.add(barraStatus, BorderLayout.BEFORE_LINE_BEGINS);
            getContentPane().add(cabecalho, java.awt.BorderLayout.NORTH);
        } else {
            getContentPane().add(barraSuperiorBotoes, java.awt.BorderLayout.NORTH);           
            getContentPane().add(barraStatus, java.awt.BorderLayout.SOUTH);
        }
        getContentPane().add(barraLateralBotoes, java.awt.BorderLayout.WEST);
        getContentPane().add(getAreaGraficos(), java.awt.BorderLayout.CENTER);
    }
    
    private void incluirJanelas() {
        setGridLayoutJanelas(new GridLayout(Controle.getConfiguracoesUsuario().getNumLinhasLayout(),Controle.getConfiguracoesUsuario().getNumColunasLayout(),
                ESPACAMENTO_HORIZONTAL_JANELAS, ESPACAMENTO_VERTICAL_JANELAS));
        getAreaGraficos().setLayout(getGridLayoutJanelas());
        Vector<ConfiguracoesJanela> confJanelas = Controle.getConfiguracoesUsuario().getConfiguracoesJanelas();
        if (confJanelas.size() != 0) {
            if (Controle.getCarteira().getAcoes().size() != 0) {
                for (ConfiguracoesJanela elem : confJanelas) {
                    janelasGraficos.add(new JanelaGraficos(this, elem));
                }
            }
        }
    }
    
    public void atualizarJanelasGraficos() {
        cancelarAcoesIniciadasDoUsuario();
        getAreaGraficos().removeAll();
        getAreaGraficos().setLayout(getGridLayoutJanelas());
        for (int i = 0; i < janelasGraficos.size(); i++) {
            JanelaGraficos j = janelasGraficos.get(i);
            j.atualizarPainel();
            getAreaGraficos().add(j, i);
        }
    }
    
    public JanelaGraficos getJanelaAtiva() {
        return (JanelaGraficos) getAreaGraficos().getSelectedFrame();
    }
    
    public Vector<JanelaGraficos> getJanelasGraficos() {
        return janelasGraficos;
    }
    
    public void setJanelasGraficos(Vector<JanelaGraficos> janelasGraficos) {
        this.janelasGraficos = janelasGraficos;
    }
    
    public ElementoGrafico getNovoElemento() {
        return novoElemento;
    }
    
    public void setNovoElemento(ElementoGrafico novoElemento) {
        this.novoElemento = novoElemento;
    }
    
    public Color getCorNovosElementos() {
        return corNovosElementos;
    }
    
    public void setCorNovosElementos(Color corNovosElementos) {
        this.corNovosElementos = corNovosElementos;
        barraLateralBotoes.atualizarCor();
    }
    
    public void iniciarSelecaoNovoElemento(ElementoGrafico elem) {
        setNovoElemento(elem);
        getJanelaAtiva().getPanelMolduras().habilitarEventos();
    }
    
    public void finalizarSelecaoNovoElemento(String botao) {
        setNovoElemento(null);
        if (botao != null) {
            barraLateralBotoes.alterarBotao(botao, false);
        }
        for (JanelaGraficos j : janelasGraficos) {
            j.getPanelMolduras().desabilitarEventos();
        }
    }
    
    public void finalizarPrograma() {
        if (!programaFinalizado) {
            programaFinalizado = true;
            for (JanelaGraficos janela : janelasGraficos) {
                janela.salvarAnaliseAcao();
            }
            Controle.finalizarPrograma();
        }
    }
    
    public void excluirJanela(JanelaGraficos janelaGraficos) {
        janelasGraficos.remove(janelaGraficos);
        atualizarJanelasGraficos();
    }
    
    public ComboAcoes getComboAcoes() {
        return comboAcoes;
    }
    
    public void setComboAcoes(ComboAcoes comboAcoes) {
        this.comboAcoes = comboAcoes;
    }
    
    public void exibir() {
        this.setVisible(true);
        acoesIniciais();
    }
    
    public JDesktopPane getAreaGraficos() {
        return areaGraficos;
    }
    
    public GridLayout getGridLayoutJanelas() {
        return gridLayoutJanelas;
    }
    
    public void setGridLayoutJanelas(GridLayout gridLayoutJanelas) {
        this.gridLayoutJanelas = gridLayoutJanelas;
    }
    
    public void organizarLayoutJanela(int lin, int col) {
        setGridLayoutJanelas(new GridLayout(lin, col, ESPACAMENTO_HORIZONTAL_JANELAS, ESPACAMENTO_VERTICAL_JANELAS));
        atualizarJanelasGraficos();
    }
    
    private void cancelarAcoesIniciadasDoUsuario() {
        barraLateralBotoes.abortarSelecoesIniciadas(null);
        finalizarSelecaoNovoElemento(null);
    }
    
    public void adicionarMarcaGrafica(final MarcaGrafica marca) {
        this.getJanelaAtiva().getAcao().getAnalise().getMarcas().add(marca);
        this.atualizarJanelasGraficos();
    }
    
    public void setCursorExtendido(boolean b) {
        Controle.getConfiguracoesUsuario().setCursorExtendido(b);
        for (JanelaGraficos janelaGraficos : janelasGraficos) {
            janelaGraficos.getPanelGraficos().setCursorExtendido(b);
            janelaGraficos.getPanelGraficos().repaint();
        }
    }
    
    public void reconstruirTela() { // Gambiarra
        getContentPane().removeAll();
        construirTela();
        exibir();
    }
    
    public void alterarBotao(String botao, boolean selected) {
        //Se nï¿½o for uma tenta a outra
        if (!barraSuperiorBotoes.alterarBotao(botao, selected)) {
            barraLateralBotoes.alterarBotao(botao, selected);
        }
    }
    
    public ComboConfiguracoes getComboConfiguracoes() {
        return comboConfiguracoes;
    }
    
    public void setComboConfiguracoes(ComboConfiguracoes comboConfiguracoes) {
        this.comboConfiguracoes = comboConfiguracoes;
    }
    
    public void acoesIniciais() {
        
        // testa se ï¿½ necessï¿½rio fazer o download da base de dados
        if(ManipuladorArquivos.existeArquivo("atualizaBase")) {
            String vendor = Controle.getConfiguracoesVolateis().getVendor();
           
            String base = Controle.getConfiguracoesGrafix().getPathBaseDados();
            //Controle.getConfiguracoesGrafix().setPathBaseDados(pastaDados);
            Controle.getTela().getComboAcoes().alterarCarteira();
        } else {
            // Testa se a lista ï¿½ null. Neste caso todas as aï¿½ï¿½es foram carregadas no combo.
            if (Controle.getConfiguracoesGrafix().getListaAcoes() == null) {
                AjudaGrafix.exibirMensagem(AjudaGrafix.LISTA_INEXISTENTE);
            }
            // Testa se nï¿½o foram carregadas aï¿½ï¿½es.
            if (Controle.getCarteira().getAcoes().size() == 0) {
                AjudaGrafix.exibirMensagem(AjudaGrafix.CARTEIRA_VAZIA);
                //Comandos.cmdOpcoes();
            } else {
                // Testa se nï¿½o existem janelas iniciadas
                if (janelasGraficos.size() == 0) {
                    Comandos.cmdAddJanela();
                } else {
                    ativarPrimeiraJanela();
                }
            }
        }
    }
    
    public void ativarPrimeiraJanela() {
        try {
            janelasGraficos.get(0).setSelected(true);
        } catch (Exception ex) {
        }
    }
    
    public void ativarUltimaJanela() {
        try {
            janelasGraficos.get(janelasGraficos.size() - 1).setSelected(true);
        } catch (Exception ex) {
        }
    }
    
    public void atualizarBarraStatus(RegistroDiario reg, double atual) {
        barraStatus.atualizar(reg, atual);
    }
    
}

