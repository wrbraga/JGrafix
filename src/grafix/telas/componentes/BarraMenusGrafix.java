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


package grafix.telas.componentes;

import grafix.basedados.Cotacoes;
import grafix.principal.*;
import grafix.principal.Comandos;
import grafix.telas.*;
import grafix.telas.secundarias.*;
import java.awt.event.ActionListener;
import javax.swing.*;


public class BarraMenusGrafix extends JMenuBar {
    
    // Possible Look & Feels
//    private static final String mac =     "com.sun.java.swing.plaf.mac.MacLookAndFeel";
    private static final String METAL =   "javax.swing.plaf.metal.MetalLookAndFeel";
    private static final String MOTIF =   "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
    private static final String WINDOWS = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
    private static final String GTK =     "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
    // The current Look & Feel
//    private static String currentLookAndFeel = metal;
    private ButtonGroup lafMenuGroup = new ButtonGroup();
    private ButtonGroup periodicidadeMenuGroup = new ButtonGroup();
    
    
    private JMenu mnAjuda = new JMenu();
    private JMenu mnArquivo = new JMenu();
    private JMenu mnFerramentas = new JMenu();
    private JMenu mnGraficos = new JMenu();
    private JMenu mnJanelas = new JMenu();
    private JMenu mnExibir = new JMenu();
    private JMenu mnLookFeel = new JMenu();
    private JMenu mnPeriodicidade = new JMenu();
    
    private TelaGrafix tela;
    
    public BarraMenusGrafix(TelaGrafix tela) {
        this.tela = tela;
        this.add(mnArquivo);
        this.add(mnExibir);
        this.add(mnFerramentas);
        this.add(mnGraficos);
        this.add(mnJanelas);
        this.add(mnAjuda);
        
        mnArquivo.setText("Arquivo");
        criarItemMenu(mnArquivo, "Atualizar", "atualizar.gif", new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comandos.cmdAtualizar();
                //new FormAtualizarPadrao().setVisible(true);
            }
        });
        criarItemMenu(mnArquivo, "Intraday", "intraday.gif", new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comandos.cmdIntraday(((AbstractButton)evt.getSource()).isSelected());
            }
        });
        criarItemMenu(mnArquivo, "Configurar Atualização", "prefAtualizar.gif", new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new FormPreferenciasAtualizar(Controle.getTela(), "", true).setVisible(true);
            }
        });
        criarItemMenu(mnArquivo, "Sair sem salvar configurações", "sairSemSalvar.gif", new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0);
            }
        });
        criarItemMenu(mnArquivo, "Sair", "sair.gif", new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Controle.getTela().finalizarPrograma();
                System.exit(0);
            }
        });
        mnExibir.setText("Exibir");
        mnExibir.add(mnLookFeel);
        mnLookFeel.setText("Aparência - Look & Feel");
        criarMenuLookFeel();
        mnGraficos.setText("Graficos");

        mnGraficos.add(mnPeriodicidade);
        mnPeriodicidade.setText("Periodicidade");
        criarMenuPeriodicidade();

        criarItemMenu(mnGraficos, "Configurar Janela", "configurar3.gif", new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comandos.cmdConfigurarJanela();
            }
        });
        criarItemMenu(mnGraficos, "Índices", "indices.gif", new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comandos.cmdIndices();
            }
        });
        criarItemMenu(mnGraficos, "Tabela do Gráfico", "historico.gif", new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comandos.cmdHistorico();
            }
        });
        criarItemMenu(mnGraficos, "Salvar gráfico em JPEG", "fotografia2.gif", new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comandos.cmdSalvarJPEG();
            }
        });
        mnFerramentas.setText("Ferramentas");
        criarItemMenu(mnFerramentas, "Opções", "opcoes.gif", new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comandos.cmdOpcoes();
            }
        });
        criarItemMenu(mnFerramentas, "Listas de Papéis", "lista2.gif", new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comandos.cmdListas();
            }
        });
        mnJanelas.setText("Janelas");
        criarItemMenu(mnJanelas, "Adicionar nova janela", "novaJanela.gif", new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comandos.cmdAddJanela();
            }
        });
        criarItemMenu(mnJanelas, "Organizar Janelas de Gráfico", "distribuir.gif", new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comandos.cmdOrganizarJanelas();
            }
        });
        criarItemMenu(mnJanelas, "Configuração Default", "default2.gif", new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comandos.cmdAplicarDefault();
            }
        });
        mnAjuda.setText("Ajuda");
        criarItemMenu(mnAjuda, "Sobre o Grafix", "help2.gif", new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comandos.cmdSobre();
            }
        });
    }
    
    private void criarItemMenu(JMenu menu, String texto, String icone, ActionListener actionListener) {
        JMenuItem mi = new JMenuItem();
        mi.setText(texto);
        if(icone!=null) {
            mi.setIcon(new javax.swing.ImageIcon(ConfiguracoesGrafix.PASTA_ICONES + icone));
        }
        mi.addActionListener(actionListener);
        menu.add(mi);
    }
    
    private void criarMenuLookFeel() {
        mnLookFeel.setIcon(new javax.swing.ImageIcon(ConfiguracoesGrafix.PASTA_ICONES + "lookandfeel.gif"));
        UIManager.LookAndFeelInfo[] lafInfo = UIManager.
                getInstalledLookAndFeels();
        
        for (int counter = 0; counter < lafInfo.length; counter++) {
            String className = lafInfo[counter].getClassName();
            if (className.equals(METAL)) {
                createLafMenuItem(mnLookFeel, "Metal", METAL);
            } else if (className.equals(MOTIF)) {
                createLafMenuItem(mnLookFeel, "Motif", MOTIF);
            } else if (className.equals(WINDOWS)) {
                createLafMenuItem(mnLookFeel, "Windows", WINDOWS);
            } else if (className.equals(GTK)) {
                createLafMenuItem(mnLookFeel, "GTK", GTK);
            }
        }
        
    }
    
    public JMenuItem createLafMenuItem(JMenu menu, String label, final String laf) {
        JMenuItem mi = (JRadioButtonMenuItem) menu.add(new JRadioButtonMenuItem(label));
        lafMenuGroup.add(mi);
        mi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizarLookFeel(laf);
            }});
            mi.setEnabled(isAvailableLookAndFeel(laf));
            return mi;
    }
    
    protected boolean isAvailableLookAndFeel(String laf) {
        try {
            Class lnfClass = Class.forName(laf);
            LookAndFeel newLAF = (LookAndFeel)(lnfClass.newInstance());
            return newLAF.isSupportedLookAndFeel();
        } catch(Exception e) { // If ANYTHING weird happens, return false
            return false;
        }
    }
    
    private void atualizarLookFeel(String laf) {
        try {
            UIManager.setLookAndFeel(laf);
            SwingUtilities.updateComponentTreeUI(tela);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void iniciarLookFeel(String laf) {
        UIManager.LookAndFeelInfo[] lafInfo = UIManager.getInstalledLookAndFeels();
        boolean lafInstalada = false;
        for (int i = 0; i < lafInfo.length; i++) {
            if(lafInfo[i].getClassName().equals(laf)) {
                lafInstalada = true;
                break;
            }
        }
        if(lafInstalada) {
            atualizarLookFeel(laf);
            if(laf.equals(METAL)) {
                marcarLAF("Metal");
            } else if(laf.equals(MOTIF)) {
                marcarLAF("Motif");
            } else if(laf.equals(WINDOWS)) {
                marcarLAF("Windows");
            } else if(laf.equals(GTK)) {
                marcarLAF("GTK");
            }
        }
    }
    
    private void marcarLAF(String itemMenu) {
        for (int i = 0; i < mnLookFeel.getItemCount(); i++) {
            if(mnLookFeel.getItem(i).getText().equals(itemMenu)) {
                mnLookFeel.getItem(i).setSelected(true);
                break;
            }
        }
    }

    private void setPeriodicidade(int periodicidade) {
        Controle.getConfiguracoesGrafix().setPeriodicidade(periodicidade);
        Comandos.cmdPassarAcao(false);
        Comandos.cmdPassarAcao(true);
    }

    public JMenuItem createMenuItemPeriodicidade(JMenu menu, String label, final int periodicidade) {
        JMenuItem mi = (JRadioButtonMenuItem) menu.add(new JRadioButtonMenuItem(label));
        periodicidadeMenuGroup.add(mi);
        mi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setPeriodicidade(periodicidade);
            }});
            return mi;
    }

    private void criarMenuPeriodicidade() {
        int periodicidade = Controle.getConfiguracoesGrafix().getPeriodicidade();
        createMenuItemPeriodicidade(mnPeriodicidade, "Diário", Cotacoes.PERIODICIDADE_DIARIA);
        createMenuItemPeriodicidade(mnPeriodicidade, "Semanal", Cotacoes.PERIODICIDADE_SEMANAL);
        createMenuItemPeriodicidade(mnPeriodicidade, "Mensal", Cotacoes.PERIODICIDADE_MENSAL);
        mnPeriodicidade.getItem(periodicidade-1).setSelected(true);
    }
    
}
