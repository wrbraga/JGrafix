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

import grafix.auxiliar.ManipuladorArquivos;
import grafix.graficos.*;
import grafix.graficos.eixos.Eixo;
import grafix.graficos.indices.*;
import grafix.principal.ConfiguracoesGrafix;
import grafix.principal.Controle;
import grafix.telas.JanelaGraficos;
import java.awt.Color;
import java.awt.Frame;
import java.io.File;
import javax.swing.*;

public class FormIndices extends javax.swing.JDialog {

    private JanelaGraficos janela;
    private ConfiguracoesJanela confJanela; //???????????????????????????

    public FormIndices(Frame frame) {
        super(frame);
        this.janela = Controle.getJanelaAtiva();
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

    public FormIndices(Frame frame, Eixo eixoInicial) {
        this(frame);
        selecionarEixoInicial(eixoInicial);
    }

    public FormIndices(Frame frame, int numPlotInicial) {
        this(frame);
        selecionarEixoInicial(numPlotInicial);
    }

    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbEixo = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        ltIndices = new javax.swing.JList();
        btExcluirIndice = new javax.swing.JButton();
        btSair = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btNovoIndice = new javax.swing.JButton();
        cbIndices = new javax.swing.JComboBox();
        btAplicar = new javax.swing.JButton();
        pnPropriedades = new javax.swing.JPanel();
        lbCor = new javax.swing.JLabel();
        lbCorIndice = new javax.swing.JLabel();
        lbNomeIndice = new javax.swing.JLabel();
        lbEspessura = new javax.swing.JLabel();
        spEspessura = new javax.swing.JSpinner();
        chFlag = new javax.swing.JCheckBox();
        spParam1 = new javax.swing.JSpinner();
        lbParam1 = new javax.swing.JLabel();
        spParam2 = new javax.swing.JSpinner();
        lbParam2 = new javax.swing.JLabel();
        spParam3 = new javax.swing.JSpinner();
        lbParam3 = new javax.swing.JLabel();
        lbAuxiliar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configurar \u00cdndices");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("\u00cdndices por eixo"));
        jLabel1.setText("Eixo:");

        cbEixo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEixoActionPerformed(evt);
            }
        });

        ltIndices.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                ltIndicesValueChanged(evt);
            }
        });

        jScrollPane1.setViewportView(ltIndices);

        btExcluirIndice.setText("Excluir \u00cdndice");
        btExcluirIndice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirIndiceActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(jLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(cbEixo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 152, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(btExcluirIndice, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(cbEixo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btExcluirIndice)
                .addContainerGap())
        );

        btSair.setText("OK");
        btSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSairActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Novo \u00cdndice"));
        btNovoIndice.setText("Adicionar \u00edndice no eixo");
        btNovoIndice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovoIndiceActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(cbIndices, 0, 425, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, btNovoIndice, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 216, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(cbIndices, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btNovoIndice)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btAplicar.setText("Aplicar");
        btAplicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAplicarActionPerformed(evt);
            }
        });

        pnPropriedades.setBorder(javax.swing.BorderFactory.createTitledBorder("Propriedades do \u00cdndice"));
        lbCor.setText("Cor:");
        lbCor.setOpaque(true);

        lbCorIndice.setBackground(new java.awt.Color(0, 102, 204));
        lbCorIndice.setForeground(new java.awt.Color(255, 255, 255));
        lbCorIndice.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lbCorIndice.setOpaque(true);
        lbCorIndice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbCorIndiceMouseClicked(evt);
            }
        });

        lbNomeIndice.setText("Nome do \u00edndice");
        lbNomeIndice.setOpaque(true);

        lbEspessura.setText("Espessura: ");

        spEspessura.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spEspessuraStateChanged(evt);
            }
        });

        chFlag.setText("flag");
        chFlag.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        chFlag.setMargin(new java.awt.Insets(0, 0, 0, 0));
        chFlag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chFlagActionPerformed(evt);
            }
        });

        spParam1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spParam1StateChanged(evt);
            }
        });

        lbParam1.setText("p1");
        lbParam1.setOpaque(true);

        spParam2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spParam2StateChanged(evt);
            }
        });

        lbParam2.setText("p2");
        lbParam2.setOpaque(true);

        spParam3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spParam3StateChanged(evt);
            }
        });

        lbParam3.setText("p3");
        lbParam3.setOpaque(true);

        org.jdesktop.layout.GroupLayout pnPropriedadesLayout = new org.jdesktop.layout.GroupLayout(pnPropriedades);
        pnPropriedades.setLayout(pnPropriedadesLayout);
        pnPropriedadesLayout.setHorizontalGroup(
            pnPropriedadesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnPropriedadesLayout.createSequentialGroup()
                .addContainerGap()
                .add(pnPropriedadesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(lbAuxiliar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(lbNomeIndice, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 187, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(pnPropriedadesLayout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(lbCor)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(lbCorIndice, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 32, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(24, 24, 24)
                        .add(lbEspessura)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(spEspessura, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(pnPropriedadesLayout.createSequentialGroup()
                        .add(pnPropriedadesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(spParam3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(spParam2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(pnPropriedadesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(lbParam3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 138, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(lbParam2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 138, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(pnPropriedadesLayout.createSequentialGroup()
                        .add(spParam1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(lbParam1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 138, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(chFlag, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 187, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnPropriedadesLayout.setVerticalGroup(
            pnPropriedadesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnPropriedadesLayout.createSequentialGroup()
                .addContainerGap()
                .add(lbNomeIndice)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(pnPropriedadesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(pnPropriedadesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(lbEspessura)
                        .add(spEspessura, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(pnPropriedadesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                        .add(lbCor, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(lbCorIndice, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(chFlag)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(pnPropriedadesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(spParam1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(lbParam1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(pnPropriedadesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(spParam2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(lbParam2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(pnPropriedadesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(spParam3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(lbParam3))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(lbAuxiliar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(layout.createSequentialGroup()
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(pnPropriedades, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                        .add(layout.createSequentialGroup()
                            .add(btAplicar)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(btSair, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 62, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(pnPropriedades, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(9, 9, 9)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btSair)
                    .add(btAplicar))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void btAplicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAplicarActionPerformed
        janela.atualizarPainel();
    }//GEN-LAST:event_btAplicarActionPerformed

    private void spParam3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spParam3StateChanged
        int valor = ((Integer) ((JSpinner) evt.getSource()).getValue()).intValue();
        if (valor <= 0) {
            valor = 1;
            ((JSpinner) evt.getSource()).setValue(new Integer(valor));
        }
        getIndiceAtual().setParam3(valor);
        getIndiceAtual().setValores(null);
//        parametrosAlterados();
    }//GEN-LAST:event_spParam3StateChanged

    private void spParam2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spParam2StateChanged
        int valor = ((Integer) ((JSpinner) evt.getSource()).getValue()).intValue();
        if (valor <= 0) {
            valor = 1;
            ((JSpinner) evt.getSource()).setValue(new Integer(valor));
        }
        getIndiceAtual().setParam2(valor);
        getIndiceAtual().setValores(null);
//        parametrosAlterados();
    }//GEN-LAST:event_spParam2StateChanged

    private void spParam1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spParam1StateChanged
        int valor = ((Integer) ((JSpinner) evt.getSource()).getValue()).intValue();
        if (valor <= 0) {
            valor = 1;
            ((JSpinner) evt.getSource()).setValue(new Integer(valor));
        }
        getIndiceAtual().setParam1(valor);
        getIndiceAtual().setValores(null);
//        parametrosAlterados();
    }//GEN-LAST:event_spParam1StateChanged

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        encerrarDialog();
    }//GEN-LAST:event_formWindowClosing

    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
        encerrarDialog();
        this.setVisible(false);
    }//GEN-LAST:event_btSairActionPerformed

    private void chFlagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chFlagActionPerformed
        getIndiceAtual().setFlag(((JCheckBox) evt.getSource()).isSelected());
        getIndiceAtual().setValores(null);
//        parametrosAlterados();
    }//GEN-LAST:event_chFlagActionPerformed

    private void lbCorIndiceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCorIndiceMouseClicked
        Color novaCor = JColorChooser.showDialog(null, "Selecione uma cor", getIndiceAtual().getCor());
        if (novaCor != null) {
            getIndiceAtual().setCor(novaCor);
            exibirPropriedades(getIndiceAtual());
//            parametrosAlterados();
        }
    }//GEN-LAST:event_lbCorIndiceMouseClicked

    private void btExcluirIndiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirIndiceActionPerformed
        try {
            Eixo eixoAtual = (Eixo) cbEixo.getSelectedItem();
            eixoAtual.excluirIndice(getIndiceAtual());
            exibirIndices();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btExcluirIndiceActionPerformed

    private void btNovoIndiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoIndiceActionPerformed
        try {
            Indice ind = (Indice) cbIndices.getSelectedItem();
            Indice novoIndice = (Indice) (Class.forName(ind.getClass().getName())).newInstance();
            Eixo eixoAtual = (Eixo) cbEixo.getSelectedItem();
            eixoAtual.addIndice(novoIndice);
            novoIndice.setFileLua(ind.getFileLua());
            exibirIndices();
            ltIndices.setSelectedValue(novoIndice, true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }//GEN-LAST:event_btNovoIndiceActionPerformed

    private void ltIndicesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_ltIndicesValueChanged
        ocultarPropriedades();
        exibirPropriedades(getIndiceAtual()); // (Indice)((JList)evt.getSource()).getSelectedValue()
    }//GEN-LAST:event_ltIndicesValueChanged

    private void cbEixoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEixoActionPerformed
        exibirIndices();
    }//GEN-LAST:event_cbEixoActionPerformed

    private void spEspessuraStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spEspessuraStateChanged
        int valor = ((Integer) ((JSpinner) evt.getSource()).getValue()).intValue();
        if (valor <= 0) {
            valor = 1;
            ((JSpinner) evt.getSource()).setValue(new Integer(valor));
        }
        getIndiceAtual().setEspessura(valor);
}//GEN-LAST:event_spEspessuraStateChanged

    private void iniciarCampos() {
        for (Eixo eixo : confJanela.getEixos()) {
            cbEixo.addItem(eixo);
        }
        popularCbIndices();
        ocultarPropriedades();
        cbEixo.setSelectedIndex(0);
    }

    private void selecionarEixoInicial(Eixo eixo) {
        cbEixo.setSelectedItem(eixo);
    }

    private void selecionarEixoInicial(int numPlot) {
        cbEixo.setSelectedIndex(numPlot);
    }

    private void exibirIndices() {
        Eixo eixoAtual = (Eixo) cbEixo.getSelectedItem();
        ltIndices.setListData(eixoAtual.getIndices());
        try {
            ltIndices.setSelectedIndex(0);
        } catch (Exception e) {
        }
    }

    private void ocultarPropriedades() {
        lbNomeIndice.setVisible(false);
        lbCor.setVisible(false);
        lbCorIndice.setVisible(false);
        lbEspessura.setVisible(false);
        spEspessura.setVisible(false);
        chFlag.setVisible(false);
        lbParam1.setVisible(false);
        spParam1.setVisible(false);
        lbParam2.setVisible(false);
        spParam2.setVisible(false);
        lbParam3.setVisible(false);
        spParam3.setVisible(false);
    }

    private void exibirPropriedades(Indice indice) {
        if (indice != null) {
            lbNomeIndice.setVisible(true);
            lbNomeIndice.setText(indice.getNomeIndice());
            lbCor.setVisible(true);
            lbCorIndice.setVisible(true);
            lbCorIndice.setBackground(indice.getCor());
            lbEspessura.setVisible(true);
            spEspessura.setVisible(true);
            spEspessura.setValue(new Integer(indice.getEspessura()));
            if (indice.getNomeFlag() != null) {
                chFlag.setVisible(true);
                chFlag.setText(indice.getNomeFlag());
                chFlag.setSelected(indice.isFlag());
            }
            if (indice.getNomeParam1() != null) {
                lbParam1.setVisible(true);
                lbParam1.setText(indice.getNomeParam1());
                spParam1.setVisible(true);
                spParam1.setValue(new Integer(indice.getParam1()));
            }
            if (indice.getNomeParam2() != null) {
                lbParam2.setVisible(true);
                lbParam2.setText(indice.getNomeParam2());
                spParam2.setVisible(true);
                spParam2.setValue(new Integer(indice.getParam2()));
            }
            if (indice.getNomeParam3() != null) {
                lbParam3.setVisible(true);
                lbParam3.setText(indice.getNomeParam3());
                spParam3.setVisible(true);
                spParam3.setValue(new Integer(indice.getParam3()));
            }
//            if(indice instanceof IndiceUSER) {
//                popularCbFileLua((IndiceUSER)indice);
//                cbFileLua.setVisible(true);
//                lbFileLua.setVisible(true);
//                btAplicarScript.setVisible(true);
//            }
        }
    }

    private void popularCbIndices() {
        // Adicionar índices do Grafix
        cbIndices.addItem(new IndiceADX());
        cbIndices.addItem(new IndiceBollinger());
        cbIndices.addItem(new IndiceIFR());
        cbIndices.addItem(new IndiceMACD());
        cbIndices.addItem(new IndiceMM());
        cbIndices.addItem(new IndiceMME());
        cbIndices.addItem(new IndiceOBV());
        cbIndices.addItem(new IndicePO());
        cbIndices.addItem(new IndiceRMI());
        cbIndices.addItem(new IndiceROC());
        cbIndices.addItem(new IndiceSAR());
        cbIndices.addItem(new IndiceTRIX());
        // Adicionar índices implementados em LUA
        File[] filesLua = ManipuladorArquivos.listaConteudoDiretorio(ConfiguracoesGrafix.PASTA_LUA);
        for (int i = 0; i < filesLua.length; i++) {
            String nomeFile = filesLua[i].getName();
            if (nomeFile.endsWith(".lua")) {
                try {
                    USERIndice ind = new USERIndice();
                    ind.setFileLua(nomeFile);
                    cbIndices.addItem(ind);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private Indice getIndiceAtual() {
        return (Indice) ltIndices.getSelectedValue();
    }

    private void encerrarDialog() {
        janela.atualizarPainel();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAplicar;
    private javax.swing.JButton btExcluirIndice;
    private javax.swing.JButton btNovoIndice;
    private javax.swing.JButton btSair;
    private javax.swing.JComboBox cbEixo;
    private javax.swing.JComboBox cbIndices;
    private javax.swing.JCheckBox chFlag;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbAuxiliar;
    private javax.swing.JLabel lbCor;
    private javax.swing.JLabel lbCorIndice;
    private javax.swing.JLabel lbEspessura;
    private javax.swing.JLabel lbNomeIndice;
    private javax.swing.JLabel lbParam1;
    private javax.swing.JLabel lbParam2;
    private javax.swing.JLabel lbParam3;
    private javax.swing.JList ltIndices;
    private javax.swing.JPanel pnPropriedades;
    private javax.swing.JSpinner spEspessura;
    private javax.swing.JSpinner spParam1;
    private javax.swing.JSpinner spParam2;
    private javax.swing.JSpinner spParam3;
    // End of variables declaration//GEN-END:variables
}
