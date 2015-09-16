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


package grafix.telas.secundarias;

import grafix.graficos.*;
import grafix.graficos.eixos.*;
import grafix.principal.*;
import grafix.telas.*;
import java.awt.Color;
import java.awt.Frame;
import java.util.Vector;
import javax.swing.*;

public class FormConfiguracaoJanela extends javax.swing.JDialog {

    private JanelaGraficos janela;
    private ConfiguracoesJanela confJanela;

    /** Creates new form FormConfiguracaoJanela */
    public FormConfiguracaoJanela(Frame frame) {
        super(frame);
        this.janela = Controle.getJanelaAtiva();
        if (janela == null) {
            throw new RuntimeException("Nao ha janela ativa! Abertura da tela abortada!");
        }
        this.confJanela = janela.getConfiguracoesJanela();
        initComponents();
        centralizarFrame();
        iniciarCampos();
    }

    private void centralizarFrame() {
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width - this.getSize().width) / 2,
                (screenSize.height - this.getSize().height) / 2);
    }

    public FormConfiguracaoJanela(Frame frame, int plot) {
        this(frame);
        if (plot < 0) {
            this.jTabbedPane1.setSelectedIndex(0);
        } else if (plot == 0) {
            this.jTabbedPane1.setSelectedIndex(1);
        } else if (plot == 1) {
            this.jTabbedPane1.setSelectedIndex(2);
        } else {
            this.jTabbedPane1.setSelectedIndex(3);
            int numEixoExtra = plot - 2;
            this.cbEixosExtras.setSelectedIndex(numEixoExtra);
        }
    }

    private void iniciarCampos() {
        ConfiguracoesUsuario confUsuario = Controle.getConfiguracoesUsuario();
        ConfiguracoesGrafix confGrafix = Controle.getConfiguracoesGrafix();
        panelConfCandles.iniciarPanel(confJanela.getCandles());
        panelConfVolumes.iniciarPanel(confJanela.getVolume());
        cbExibirSomenteDiasUteis.setSelected(confUsuario.isExibeSomenteDiasUteis());
        cbExibirEixoX.setSelected(confUsuario.isExibeEixoX());
        rbStatusSup.setSelected(confGrafix.isLocalizacaoStatusSuperior());
        rbStatusInf.setSelected(!confGrafix.isLocalizacaoStatusSuperior());
        tfZoomNormal.setText(confGrafix.getZoomNormal() + "");
        tfIncZoom.setText(confGrafix.getZoomIncremento() + "");
        tfEspacoPrevisao.setText(confGrafix.getEspacoPrevisao() + "");
        tfDeslocLateral.setText(confGrafix.getMovimentoIncremento() + "");
        lbCorCandlesAlta.setBackground(confUsuario.getCorCandlesAlta());
        lbCorCandlesBaixa.setBackground(confUsuario.getCorCandlesBaixa());
        lbCorVolume.setBackground(confUsuario.getCorVolume());
        spVolumeEspac.setValue(new Integer(confUsuario.getEspacColunasVolume()));
        popularComboEixosExtras();
        atualizarPanelConfExtras();
    }

    private void popularComboEixosExtras() {
        Vector<EixoExtra> eixos = confJanela.getEixosExtras();
        cbEixosExtras.removeAllItems();
        for (int i = 0; i < eixos.size(); i++) {
            cbEixosExtras.addItem(confJanela.getEixosExtras().get(i));
        }
    }

    private void atualizarPanelConfExtras() {
        panelConfExtras.iniciarPanel((Eixo) cbEixosExtras.getSelectedItem());
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        localizacaoBarraStatus = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        cbExibirSomenteDiasUteis = new javax.swing.JCheckBox();
        cbExibirEixoX = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tfZoomNormal = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfEspacoPrevisao = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tfDeslocLateral = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tfIncZoom = new javax.swing.JTextField();
        btRestaurar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        rbStatusSup = new javax.swing.JRadioButton();
        rbStatusInf = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbCorCandlesAlta = new javax.swing.JLabel();
        lbCorCandlesBaixa = new javax.swing.JLabel();
        panelConfCandles = new grafix.telas.secundarias.PanelConfiguracaoEixo();
        jPanel8 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        lbCorVolume = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        spVolumeEspac = new javax.swing.JSpinner();
        panelConfVolumes = new grafix.telas.secundarias.PanelConfiguracaoEixo();
        jPanel7 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        btNovoEixo = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        cbEixosExtras = new javax.swing.JComboBox();
        panelConfExtras = new grafix.telas.secundarias.PanelConfiguracaoEixo();
        btExcluirEixoExtra = new javax.swing.JButton();
        btOK = new javax.swing.JButton();
        btAplicar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configurações de Janela");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        cbExibirSomenteDiasUteis.setText("Exibir somente dias úteis");
        cbExibirSomenteDiasUteis.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        cbExibirSomenteDiasUteis.setMargin(new java.awt.Insets(0, 0, 0, 0));
        cbExibirSomenteDiasUteis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbExibirSomenteDiasUteisActionPerformed(evt);
            }
        });

        cbExibirEixoX.setSelected(true);
        cbExibirEixoX.setText("Destacar o eixo X");
        cbExibirEixoX.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        cbExibirEixoX.setMargin(new java.awt.Insets(0, 0, 0, 0));
        cbExibirEixoX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbExibirEixoXActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Configurações de Zoom e Visualização"));

        jLabel2.setText("Dias exibidos no zoom NORMAL");

        tfZoomNormal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfZoomNormalFocusLost(evt);
            }
        });

        jLabel3.setText("Espaço para previsões (em dias)");
        jLabel3.setOpaque(true);

        tfEspacoPrevisao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfEspacoPrevisaoFocusLost(evt);
            }
        });

        jLabel6.setText("Deslocamento lateral (em dias)");

        tfDeslocLateral.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfDeslocLateralFocusLost(evt);
            }
        });

        jLabel7.setText("Intensidade do zoom (em dias)");

        tfIncZoom.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfIncZoomFocusLost(evt);
            }
        });

        btRestaurar.setText("Restaurar Valores Padrões");
        btRestaurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRestaurarActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel2)
                    .add(jLabel3)
                    .add(jLabel6)
                    .add(jLabel7))
                .add(17, 17, 17)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(tfIncZoom)
                    .add(tfDeslocLateral)
                    .add(tfEspacoPrevisao)
                    .add(tfZoomNormal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 69, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(58, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(99, Short.MAX_VALUE)
                .add(btRestaurar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(tfZoomNormal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(tfEspacoPrevisao, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel6)
                    .add(tfDeslocLateral, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel7)
                    .add(tfIncZoom, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 34, Short.MAX_VALUE)
                .add(btRestaurar)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Barra de Status"));

        localizacaoBarraStatus.add(rbStatusSup);
        rbStatusSup.setText("Superior");
        rbStatusSup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbStatusSupActionPerformed(evt);
            }
        });

        localizacaoBarraStatus.add(rbStatusInf);
        rbStatusInf.setText("Inferior");
        rbStatusInf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbStatusInfActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(rbStatusSup)
                .add(18, 18, 18)
                .add(rbStatusInf)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(rbStatusSup)
                    .add(rbStatusInf))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout jPanel6Layout = new org.jdesktop.layout.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(cbExibirSomenteDiasUteis)
                    .add(cbExibirEixoX)
                    .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(cbExibirSomenteDiasUteis)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(cbExibirEixoX)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(31, 31, 31)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Geral", jPanel2);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Gráfico de Candles"));

        jLabel4.setText("Cor de alta");

        jLabel5.setText("Cor de baixa");

        lbCorCandlesAlta.setBackground(new java.awt.Color(255, 255, 153));
        lbCorCandlesAlta.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lbCorCandlesAlta.setOpaque(true);
        lbCorCandlesAlta.setPreferredSize(new java.awt.Dimension(30, 15));
        lbCorCandlesAlta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbCorCandlesAltaMouseClicked(evt);
            }
        });

        lbCorCandlesBaixa.setBackground(new java.awt.Color(255, 255, 153));
        lbCorCandlesBaixa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lbCorCandlesBaixa.setOpaque(true);
        lbCorCandlesBaixa.setPreferredSize(new java.awt.Dimension(30, 15));
        lbCorCandlesBaixa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbCorCandlesBaixaMouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel4)
                    .add(jLabel5))
                .add(21, 21, 21)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(lbCorCandlesBaixa, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(lbCorCandlesAlta, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(265, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel4)
                    .add(lbCorCandlesAlta, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel5)
                    .add(lbCorCandlesBaixa, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(33, Short.MAX_VALUE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(panelConfCandles, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(33, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(panelConfCandles, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(76, 76, 76))
        );

        jTabbedPane1.addTab("Eixo Candles", jPanel1);

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("Gráfico de Volumes"));

        lbCorVolume.setBackground(new java.awt.Color(255, 255, 153));
        lbCorVolume.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lbCorVolume.setOpaque(true);
        lbCorVolume.setPreferredSize(new java.awt.Dimension(30, 15));
        lbCorVolume.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbCorVolumeMouseClicked(evt);
            }
        });

        jLabel10.setText("Cor das colunas");

        jLabel1.setText("Espaçamento entre colunas");

        spVolumeEspac.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spVolumeEspacStateChanged(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel15Layout = new org.jdesktop.layout.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel15Layout.createSequentialGroup()
                        .add(jLabel10)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(lbCorVolume, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel15Layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel1)
                        .add(4, 4, 4)
                        .add(spVolumeEspac, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 49, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(157, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel15Layout.createSequentialGroup()
                .add(jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel10)
                    .add(lbCorVolume, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(spVolumeEspac, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout jPanel8Layout = new org.jdesktop.layout.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(panelConfVolumes, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(panelConfVolumes, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Eixo Volumes", jPanel8);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Novos Eixos"));

        btNovoEixo.setText("Adicionar novo eixo...");
        btNovoEixo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovoEixoActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel9Layout = new org.jdesktop.layout.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .add(btNovoEixo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel9Layout.createSequentialGroup()
                .add(btNovoEixo)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Eixos Adicionais"));

        jLabel11.setText("Selecione o eixo:");

        cbEixosExtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEixosExtrasActionPerformed(evt);
            }
        });

        btExcluirEixoExtra.setText("Excluir eixo");
        btExcluirEixoExtra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirEixoExtraActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel10Layout = new org.jdesktop.layout.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel10Layout.createSequentialGroup()
                        .add(jLabel11)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(cbEixosExtras, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 105, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(46, 46, 46)
                        .add(btExcluirEixoExtra))
                    .add(panelConfExtras, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel11)
                    .add(cbEixosExtras, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(btExcluirEixoExtra))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(panelConfExtras, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout jPanel7Layout = new org.jdesktop.layout.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Eixos Adicionais", jPanel7);

        btOK.setText("OK");
        btOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOKActionPerformed(evt);
            }
        });

        btAplicar.setText("Aplicar");
        btAplicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAplicarActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(btAplicar)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btOK, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 490, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btAplicar)
                    .add(btOK))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void btRestaurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRestaurarActionPerformed
        Controle.getConfiguracoesGrafix().setZoomIncremento(ConfiguracoesGrafix.DEFAULT_ZOOM_INCREMENTO);
        Controle.getConfiguracoesGrafix().setMovimentoIncremento(ConfiguracoesGrafix.DEFAULT_MOVIMENTO_INCREMENTO);
        Controle.getConfiguracoesGrafix().setZoomNormal(ConfiguracoesGrafix.DEFAULT_ZOOM_NORMAL);
        tfIncZoom.setText("" + ConfiguracoesGrafix.DEFAULT_ZOOM_INCREMENTO);
        tfEspacoPrevisao.setText("" + ConfiguracoesGrafix.DEFAULT_ESPACO_PREVISAO);
        tfDeslocLateral.setText("" + ConfiguracoesGrafix.DEFAULT_MOVIMENTO_INCREMENTO);
        tfZoomNormal.setText("" + ConfiguracoesGrafix.DEFAULT_ZOOM_NORMAL); 
}//GEN-LAST:event_btRestaurarActionPerformed

    private void tfIncZoomFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfIncZoomFocusLost
        try {
            int incZoom = new Integer(tfIncZoom.getText()).intValue();
            if (incZoom >= 10 && incZoom <= 100) {
                Controle.getConfiguracoesGrafix().setZoomIncremento(incZoom);
            } else {
                Controle.exibirErro(
                        "Configuração inválida! \n" +
                        "O incremento do zoom deve ter entre 10 e 100 dias.");
            }
        } catch (Exception e) {
            System.out.println("[ERRO] Ajuste de 'incZoom' inválido!");
        }
    }//GEN-LAST:event_tfIncZoomFocusLost

    private void tfDeslocLateralFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfDeslocLateralFocusLost
        try {
            int desloc = new Integer(tfDeslocLateral.getText()).intValue();
            if (desloc >= 10 && desloc <= 100) {
                Controle.getConfiguracoesGrafix().setMovimentoIncremento(desloc);
            } else {
                Controle.exibirErro(
                        "Configuração inválida! \n" +
                        "O deslocamento lateral deve ter entre 10 e 100 dias.");
            }
        } catch (Exception e) {
            System.out.println("[ERRO] Ajuste de 'desloc' inválido!");
        }
    }//GEN-LAST:event_tfDeslocLateralFocusLost

    private void tfZoomNormalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfZoomNormalFocusLost
        try {
            int zoomNormal = new Integer(tfZoomNormal.getText()).intValue();
            if (zoomNormal >= 30 && zoomNormal <= 365) {
                Controle.getConfiguracoesGrafix().setZoomNormal(zoomNormal);
            } else {
                Controle.exibirErro(
                        "Configuração inválida! \n" +
                        "O zoom normal deve ter entre 30 e 365 dias.");
            }
        } catch (Exception e) {
            System.out.println("[ERRO] Ajuste de 'zoomNormal' inválido!");
        }
    }//GEN-LAST:event_tfZoomNormalFocusLost

    private void btAplicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAplicarActionPerformed
        janela.getAcao().apagarRegistros();
        janela.atualizarPainel();
    }//GEN-LAST:event_btAplicarActionPerformed

    private void cbExibirEixoXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbExibirEixoXActionPerformed
        Controle.getConfiguracoesUsuario().setExibeEixoX(cbExibirEixoX.isSelected());
    }//GEN-LAST:event_cbExibirEixoXActionPerformed

    private void spVolumeEspacStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spVolumeEspacStateChanged
        if (this.isVisible()) {
            int value = ((Integer) spVolumeEspac.getValue()).intValue();
            if (value < 0) {
                spVolumeEspac.setValue(new Integer(0));
            } else if (value > 8) {
                spVolumeEspac.setValue(new Integer(8));
            }
            Controle.getConfiguracoesUsuario().setEspacColunasVolume(value);
        }
    }//GEN-LAST:event_spVolumeEspacStateChanged

    private void btExcluirEixoExtraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirEixoExtraActionPerformed
        janela.removerEixoExtra((EixoExtra) cbEixosExtras.getSelectedItem());
        popularComboEixosExtras();
    }//GEN-LAST:event_btExcluirEixoExtraActionPerformed

    private void lbCorVolumeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCorVolumeMouseClicked
        Color novaCor = JColorChooser.showDialog(null, "Selecione uma cor", Controle.getConfiguracoesUsuario().getCorVolume());
        if (novaCor != null) {
            setCorVolume(novaCor);
        }
    }//GEN-LAST:event_lbCorVolumeMouseClicked

    private void lbCorCandlesBaixaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCorCandlesBaixaMouseClicked
        Color novaCor = JColorChooser.showDialog(null, "Selecione uma cor", Controle.getConfiguracoesUsuario().getCorCandlesBaixa());
        if (novaCor != null) {
            setCorCandlesBaixa(novaCor);
        }
    }//GEN-LAST:event_lbCorCandlesBaixaMouseClicked

    private void lbCorCandlesAltaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCorCandlesAltaMouseClicked
        Color novaCor = JColorChooser.showDialog(null, "Selecione uma cor", Controle.getConfiguracoesUsuario().getCorCandlesAlta());
        if (novaCor != null) {
            setCorCandlesAlta(novaCor);
        }
    }//GEN-LAST:event_lbCorCandlesAltaMouseClicked

    private void cbExibirSomenteDiasUteisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbExibirSomenteDiasUteisActionPerformed
        Controle.getConfiguracoesUsuario().setExibeSomenteDiasUteis(cbExibirSomenteDiasUteis.isSelected());
    }//GEN-LAST:event_cbExibirSomenteDiasUteisActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        btOKActionPerformed(null);
    }//GEN-LAST:event_formWindowClosed

    private void btOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOKActionPerformed
        this.setVisible(false);
        btAplicarActionPerformed(evt);
    }//GEN-LAST:event_btOKActionPerformed

    private void cbEixosExtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEixosExtrasActionPerformed
        atualizarPanelConfExtras();
    }//GEN-LAST:event_cbEixosExtrasActionPerformed

    private void btNovoEixoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoEixoActionPerformed
        String nomeEixo = (String) JOptionPane.showInputDialog(null, "Qual o nome do eixo?", "Novo eixo",
                JOptionPane.QUESTION_MESSAGE, null, null, "eixoExtra" + (confJanela.getEixosExtras().size() + 1));
        if (nomeEixo != null) {
            EixoExtra novoEixo = new EixoExtra(nomeEixo);
            janela.adicionarEixoExtra(novoEixo);
            popularComboEixosExtras();
            cbEixosExtras.setSelectedItem(novoEixo);
        }
    }//GEN-LAST:event_btNovoEixoActionPerformed

    private void rbStatusSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbStatusSupActionPerformed
        Controle.getConfiguracoesGrafix().setLocalizacaoStatusSuperior(true);
        Controle.getTela().reconstruirTela();
    }//GEN-LAST:event_rbStatusSupActionPerformed

    private void rbStatusInfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbStatusInfActionPerformed
        Controle.getConfiguracoesGrafix().setLocalizacaoStatusSuperior(false);
        Controle.getTela().reconstruirTela();
    }//GEN-LAST:event_rbStatusInfActionPerformed

    private void tfEspacoPrevisaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfEspacoPrevisaoFocusLost
        try {
            int espPrevisao = new Integer(tfEspacoPrevisao.getText()).intValue();
            if (espPrevisao >= 30 && espPrevisao <= 365) {
                Controle.getConfiguracoesGrafix().setEspacoPrevisao(espPrevisao);
            } else {
                Controle.exibirErro(
                        "Configuração inválida! \n" +
                        "O espaço para previsão deve ser entre 30 e 365 dias.");
            }
        } catch (Exception e) {
            System.out.println("[ERRO] Ajuste de 'espacoPrevisao' inválido!");
        }
    }//GEN-LAST:event_tfEspacoPrevisaoFocusLost

    public void setCorCandlesAlta(Color color) {
        Controle.getConfiguracoesUsuario().setCorCandlesAlta(color);
        lbCorCandlesAlta.setBackground(Controle.getConfiguracoesUsuario().getCorCandlesAlta());
    }

    public void setCorCandlesBaixa(Color color) {
        Controle.getConfiguracoesUsuario().setCorCandlesBaixa(color);
        lbCorCandlesBaixa.setBackground(Controle.getConfiguracoesUsuario().getCorCandlesBaixa());
    }

    public void setCorVolume(Color color) {
        Controle.getConfiguracoesUsuario().setCorVolume(color);
        lbCorVolume.setBackground(Controle.getConfiguracoesUsuario().getCorVolume());
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAplicar;
    private javax.swing.JButton btExcluirEixoExtra;
    private javax.swing.JButton btNovoEixo;
    private javax.swing.JButton btOK;
    private javax.swing.JButton btRestaurar;
    private javax.swing.JComboBox cbEixosExtras;
    private javax.swing.JCheckBox cbExibirEixoX;
    private javax.swing.JCheckBox cbExibirSomenteDiasUteis;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbCorCandlesAlta;
    private javax.swing.JLabel lbCorCandlesBaixa;
    private javax.swing.JLabel lbCorVolume;
    private javax.swing.ButtonGroup localizacaoBarraStatus;
    private grafix.telas.secundarias.PanelConfiguracaoEixo panelConfCandles;
    private grafix.telas.secundarias.PanelConfiguracaoEixo panelConfExtras;
    private grafix.telas.secundarias.PanelConfiguracaoEixo panelConfVolumes;
    private javax.swing.JRadioButton rbStatusInf;
    private javax.swing.JRadioButton rbStatusSup;
    private javax.swing.JSpinner spVolumeEspac;
    private javax.swing.JTextField tfDeslocLateral;
    private javax.swing.JTextField tfEspacoPrevisao;
    private javax.swing.JTextField tfIncZoom;
    private javax.swing.JTextField tfZoomNormal;
    // End of variables declaration//GEN-END:variables
}
