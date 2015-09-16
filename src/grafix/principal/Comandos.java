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
import grafix.graficos.*;
import grafix.seguranca.*;
import grafix.telas.*;
import grafix.telas.componentes.*;
import grafix.telas.secundarias.*;
import java.io.*;
import javax.swing.*;
import org.jfree.chart.*;

public class Comandos {
    
    // Comandos -------------------------------------------
    public static void cmdAplicarConfiguracao(ConfiguracoesUsuario conf) {
        conf.restaurarAposCarregamento();
        Controle.setConfiguracoesUsuario(conf);
        Controle.getTela().reconstruirTela();  // Gambiarra
    }
    
    static public void cmdAtualizar() {
        ControleRegistro.alertaRegistro();
        
     
        String fonte="ddddd";
        String arquivo = Controle.getConfiguracoesGrafix().getPathBaseDados()+"/cfg/fonte";
        if(ManipuladorArquivos.existeArquivo(arquivo)) {
            fonte = ManipuladorArquivos.lerConteudoDeArquivo(arquivo);
            fonte = fonte.toLowerCase();
        }
       
            new FormAtualizarPadrao().setVisible(true);
        
    }
    
    public static void cmdCursorExtendido(boolean selected) {
        Controle.getTela().setCursorExtendido(selected);
        //tela.repaint();
    }
    
    static public void cmdListas() {
        ControleRegistro.alertaRegistro();
        new FormListas().setVisible(true);
    }
    
    static public void cmdZoomPrevisao() {
        ControleRegistro.alertaRegistro();
        Controle.getJanelaAtiva().getIntervaloExibicao().aplicarZoomPrevisao();
        atualizarPainel();
    }
    
    static public void cmdZoomNormal() {
        ControleRegistro.alertaRegistro();
        try {
            Controle.getJanelaAtiva().getIntervaloExibicao().aplicarZoomNormal();
            atualizarPainel();
        } catch (Exception e) {
            e.printStackTrace();
            semJanelaAtiva();
        }
    }
    
    static public void cmdZoomHistorico() {
        ControleRegistro.alertaRegistro();
        try {
            Controle.getJanelaAtiva().getIntervaloExibicao().aplicarZoomHistorico();
            atualizarPainel();
        } catch (Exception e) {
            e.printStackTrace();
            semJanelaAtiva();
        }
    }
    
    static public void cmdZoomMais() {
        ControleRegistro.alertaRegistro();
        try {
            Controle.getJanelaAtiva().getIntervaloExibicao().moverMargemEsqADireita();
        } catch (Exception ex) {
            semJanelaAtiva();
        }
        atualizarPainel();
    }
    
    static public void cmdZoomMenos() {
        ControleRegistro.alertaRegistro();
        try {
            Controle.getJanelaAtiva().getIntervaloExibicao().moverMargemEsqAEsquerda();
        } catch (Exception ex) {
            semJanelaAtiva();
        }
        atualizarPainel();
    }
    
    static public void cmdMoveDireita() {
        ControleRegistro.alertaRegistro();
        try {
            Controle.getJanelaAtiva().getIntervaloExibicao().moverADireita();
        } catch (Exception ex) {
            semJanelaAtiva();
        }
        atualizarPainel();
    }
    
    static public void cmdMoveEsquerda() {
        ControleRegistro.alertaRegistro();
        try {
            Controle.getJanelaAtiva().getIntervaloExibicao().moverAEsquerda();
        } catch (Exception ex) {
            semJanelaAtiva();
        }
        atualizarPainel();
    }
    
    static public void cmdHistorico() {
        ControleRegistro.alertaRegistro();
        new FormPlanilha().setVisible(true);
    }
    
    static public void cmdAddJanela() {
        ControleRegistro.alertaRegistro();
        Controle.getTela().getJanelasGraficos().add(new JanelaGraficos(Controle.getTela(), (Acao) Controle.getTela().getComboAcoes().getSelectedItem()));
        Controle.getTela().atualizarJanelasGraficos();
        Controle.getTela().ativarUltimaJanela();
    }
    
    static public void cmdOrganizarJanelas() {
        ControleRegistro.alertaRegistro();
        new FormLayout(Controle.getTela()).setVisible(true);
    }
    
    static public void cmdOpcoes() {
        ControleRegistro.alertaRegistro();
        new FormOpcoes(Controle.getTela()).setVisible(true);
    }
    
    static public void cmdConfigurarJanela() {
        ControleRegistro.alertaRegistro();
        try {
            new FormConfiguracaoJanela(Controle.getTela()).setVisible(true);
        } catch (Exception ex) {
            semJanelaAtiva();
        }
    }
    
    static public void cmdIndices() {
        ControleRegistro.alertaRegistro();
        try {
            new FormIndices(Controle.getTela()).setVisible(true);
        } catch (Exception ex) {
            semJanelaAtiva();
        }
    }
    
    static public void cmdLog(boolean b) {
        ControleRegistro.alertaRegistro();
        Controle.getConfiguracoesVolateis().setLog(b);
        Controle.getTela().atualizarJanelasGraficos();
    }
    
    static public void cmdAplicarDefault() {
        ControleRegistro.alertaRegistro();
        try {
            if (JOptionPane.showConfirmDialog(Controle.getTela(), "Deseja desfazer todas as configurações da janela?",
                    "Confirmar ação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                Controle.getJanelaAtiva().setConfiguracoesJanela(new ConfiguracoesJanela());
                Controle.getTela().atualizarJanelasGraficos();
            }
        } catch (Exception ex) {
            semJanelaAtiva();
        }
    }
    
    static public void cmdSalvarJPEG() {
        ControleRegistro.alertaRegistro();
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File(".jpg"));
        int returnVal = chooser.showSaveDialog(Controle.getTela());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                ChartUtilities.saveChartAsJPEG(file,
                        Controle.getJanelaAtiva().getPanelGraficos().getChart(),
                        Controle.getJanelaAtiva().getWidth(),
                        Controle.getJanelaAtiva().getHeight());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    static public void cmdSalvarConfiguracao() {
        ControleRegistro.alertaRegistro();
        JFileChooser chooser = new JFileChooser(new File(ConfiguracoesGrafix.PASTA_TEMPLATES));
        chooser.setSelectedFile(new File(ConfiguracoesGrafix.EXTENSAO_TEMPLATES));
        chooser.setDialogType(JFileChooser.SAVE_DIALOG);
        int returnVal = chooser.showSaveDialog(Controle.getTela());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                Controle.getConfiguracoesUsuario().setNome(file.getName());
                Controle.salvarConfiguracoesUsuario(true);
                LeitorArquivoConfiguracao.getInstance().criarCopia(file);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        Controle.getTela().getComboConfiguracoes().popularCombo();
    }
    
    public static void cmdComparativo() {
        ControleRegistro.alertaRegistro();
        new TelaComparativos().setVisible(true);
    }
    
    public static void cmdIntraday(boolean b) {
        ControleRegistro.alertaRegistro();
        Controle.getConfiguracoesVolateis().setIntraday(b);
        Controle.apagarTodosOsRegistros();
        Controle.reiniciarTodosOsIntervaloExibicao();
        Controle.getTela().atualizarJanelasGraficos();
    }
    
    public static void cmdPassarAcao(boolean acima) {
        try {
            ComboAcoes cb = Controle.getTela().getComboAcoes();
            int atual = cb.getSelectedIndex();
            Acao acao;
            if(acima) {
                if(atual == 0) {
                    acao = (Acao)cb.getItemAt(cb.getItemCount() - 1);
                } else {
                    acao = (Acao)cb.getItemAt(atual - 1);
                }
            } else {
                if(atual == cb.getItemCount() - 1) {
                    acao = (Acao)cb.getItemAt(0);
                } else {
                    acao = (Acao)cb.getItemAt(atual + 1);
                }
            }
            if(acao != null) {
                cb.setSelectedItem(acao);
            }
            Controle.getTela().getJanelaAtiva().grabFocus();
        } catch(Exception e) {}
    }
    
    static public void cmdSobre() {
        new DialogSobre(Controle.getTela(), true).setVisible(true);
    }
    
    public static void cmdTestarBaseDados() {
        ////////////////////////////////////////////////////////////////////////////////////////////////////
        throw new UnsupportedOperationException("Not yet implemented");
        ////////////////////////////////////////////////////////////////////////////////////////////////////
    }
    
    static private void semJanelaAtiva() {
        JOptionPane.showMessageDialog(Controle.getTela(), "Selecione uma janela!");
    }
    
    public static void atualizarPainel() {
        Controle.getJanelaAtiva().atualizarPainel();
    }
}
