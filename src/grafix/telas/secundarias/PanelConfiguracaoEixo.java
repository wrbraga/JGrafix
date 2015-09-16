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

import grafix.graficos.eixos.Eixo;
import grafix.principal.Controle;
import grafix.telas.JanelaGraficos;
import java.awt.Color;
import javax.swing.*;

public class PanelConfiguracaoEixo extends javax.swing.JPanel {
    
    private Eixo eixo;
    
    public PanelConfiguracaoEixo() {
        initComponents();
        centralizarFrame();
    }

    private void centralizarFrame() {
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width -  this.getSize().width) / 2,
             (screenSize.height - this.getSize().height) / 2);
    }
    
    public void iniciarPanel(Eixo eixo) {
        try {
            this.eixo = eixo;
            cbExibirEixo.setSelected(eixo.isVisible());
            cbIncluiZero.setSelected(eixo.isIncluiZero());
            cbEscalaAutomatica.setSelected(eixo.isAutoEscala());
            tfEscalaMax.setText(eixo.getEscalaMax() + "");
            tfEscalaMin.setText(eixo.getEscalaMin() + "");
            cbLegenda.setSelected(eixo.isLegenda());
            if(eixo.isAutoEscala()){
                tfEscalaMax.setEnabled(false);
                tfEscalaMin.setEnabled(false);
            } else {
                tfEscalaMax.setEnabled(true);
                tfEscalaMin.setEnabled(true);
            }
            lbCorGradesHorizontais.setBackground(eixo.getCorGradeHorizontal());
            cbGradesHorizontais.setSelected(eixo.isGradeHorizontal());
        } catch (Exception e) {
            //desabilitarPainel();
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        cbExibirEixo = new javax.swing.JCheckBox();
        jPanel14 = new javax.swing.JPanel();
        btIndices = new javax.swing.JButton();
        btMarcas = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        spUnidades = new javax.swing.JSpinner();
        jLabel15 = new javax.swing.JLabel();
        tfPercentual = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        cbEscalaAutomatica = new javax.swing.JCheckBox();
        cbIncluiZero = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        tfEscalaMax = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tfEscalaMin = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        cbGradesVerticais = new javax.swing.JCheckBox();
        cbGradesHorizontais = new javax.swing.JCheckBox();
        lbCorGradesVerticais = new javax.swing.JLabel();
        lbCorGradesHorizontais = new javax.swing.JLabel();
        cbLegenda = new javax.swing.JCheckBox();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Configurar Eixo"));
        cbExibirEixo.setText("EXIBIR EIXO");
        cbExibirEixo.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        cbExibirEixo.setMargin(new java.awt.Insets(0, 0, 0, 0));
        cbExibirEixo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbExibirEixoActionPerformed(evt);
            }
        });

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("Adicionar"));
        btIndices.setText("\u00cdndices...");
        btIndices.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIndicesActionPerformed(evt);
            }
        });

        btMarcas.setText("Marcas...");
        btMarcas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMarcasActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel14Layout = new org.jdesktop.layout.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(btIndices)
                    .add(btMarcas))
                .addContainerGap(101, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel14Layout.createSequentialGroup()
                .add(btIndices)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btMarcas)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Ocupa\u00e7\u00e3o da Janela"));
        jLabel14.setText("Unidades:");
        jLabel14.setEnabled(false);

        spUnidades.setEnabled(false);

        jLabel15.setText("Representa (%):");
        jLabel15.setEnabled(false);

        tfPercentual.setEditable(false);
        tfPercentual.setText("100");
        tfPercentual.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        tfPercentual.setEnabled(false);

        org.jdesktop.layout.GroupLayout jPanel13Layout = new org.jdesktop.layout.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel13Layout.createSequentialGroup()
                        .add(jLabel14)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(spUnidades, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel13Layout.createSequentialGroup()
                        .add(jLabel15)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(tfPercentual, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 39, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel14)
                    .add(spUnidades, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(14, 14, 14)
                .add(jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel15)
                    .add(tfPercentual, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Escala"));
        cbEscalaAutomatica.setText("Escala autom\u00e1tica");
        cbEscalaAutomatica.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        cbEscalaAutomatica.setMargin(new java.awt.Insets(0, 0, 0, 0));
        cbEscalaAutomatica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEscalaAutomaticaActionPerformed(evt);
            }
        });

        cbIncluiZero.setText("Incluir o zero");
        cbIncluiZero.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        cbIncluiZero.setMargin(new java.awt.Insets(0, 0, 0, 0));
        cbIncluiZero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbIncluiZeroActionPerformed(evt);
            }
        });

        jLabel8.setText("M\u00e1ximo:");

        tfEscalaMax.setText("0");
        tfEscalaMax.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfEscalaMaxFocusLost(evt);
            }
        });
        tfEscalaMax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfEscalaMaxKeyReleased(evt);
            }
        });

        jLabel9.setText("M\u00ednimo:");

        tfEscalaMin.setText("0");
        tfEscalaMin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfEscalaMinFocusLost(evt);
            }
        });
        tfEscalaMin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfEscalaMinKeyReleased(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(18, 18, 18)
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel8)
                            .add(jLabel9))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(tfEscalaMin)
                            .add(tfEscalaMax, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 41, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(cbIncluiZero)
                    .add(cbEscalaAutomatica))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel4Layout.createSequentialGroup()
                .add(cbEscalaAutomatica)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel8)
                    .add(tfEscalaMax, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel9)
                    .add(tfEscalaMin, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(19, 19, 19)
                .add(cbIncluiZero)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Linhas de Grade"));
        cbGradesVerticais.setText("Linhas verticais");
        cbGradesVerticais.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        cbGradesVerticais.setEnabled(false);
        cbGradesVerticais.setMargin(new java.awt.Insets(0, 0, 0, 0));

        cbGradesHorizontais.setText("Linhas Horizontais");
        cbGradesHorizontais.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        cbGradesHorizontais.setMargin(new java.awt.Insets(0, 0, 0, 0));
        cbGradesHorizontais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbGradesHorizontaisActionPerformed(evt);
            }
        });

        lbCorGradesVerticais.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lbCorGradesVerticais.setEnabled(false);
        lbCorGradesVerticais.setOpaque(true);
        lbCorGradesVerticais.setPreferredSize(new java.awt.Dimension(30, 15));

        lbCorGradesHorizontais.setBackground(new java.awt.Color(153, 153, 153));
        lbCorGradesHorizontais.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lbCorGradesHorizontais.setOpaque(true);
        lbCorGradesHorizontais.setPreferredSize(new java.awt.Dimension(30, 15));
        lbCorGradesHorizontais.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbCorGradesHorizontaisMouseClicked(evt);
            }
        });

        cbLegenda.setText("Legenda");
        cbLegenda.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        cbLegenda.setMargin(new java.awt.Insets(0, 0, 0, 0));
        cbLegenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLegendaActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                        .add(jPanel1Layout.createSequentialGroup()
                            .add(cbGradesVerticais)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(lbCorGradesVerticais, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(jPanel1Layout.createSequentialGroup()
                            .add(cbGradesHorizontais)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(lbCorGradesHorizontais, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(cbLegenda))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(cbGradesVerticais)
                    .add(lbCorGradesVerticais, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(cbGradesHorizontais)
                    .add(lbCorGradesHorizontais, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(cbLegenda)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(cbExibirEixo)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel14, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(cbExibirEixo)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .add(layout.createSequentialGroup()
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(11, 11, 11))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lbCorGradesHorizontaisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCorGradesHorizontaisMouseClicked
        Color novaCor = JColorChooser.showDialog(null, "Selecione uma cor", eixo.getCorGradeHorizontal());
        if(novaCor != null) {
            lbCorGradesHorizontais.setBackground(novaCor);
            eixo.setCorGradeHorizontal(novaCor);
        }
    }//GEN-LAST:event_lbCorGradesHorizontaisMouseClicked

    private void cbGradesHorizontaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbGradesHorizontaisActionPerformed
        eixo.setGradeHorizontal(((JCheckBox)evt.getSource()).isSelected());
    }//GEN-LAST:event_cbGradesHorizontaisActionPerformed

    private void btMarcasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMarcasActionPerformed
        new FormMarcas(Controle.getTela(), FormMarcas.VALOR).setVisible(true);
    }//GEN-LAST:event_btMarcasActionPerformed

    private void cbLegendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLegendaActionPerformed
        eixo.setLegenda(((JCheckBox)evt.getSource()).isSelected());
    }//GEN-LAST:event_cbLegendaActionPerformed

    private void tfEscalaMaxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfEscalaMaxKeyReleased
        try {
            eixo.setEscalaMax( new Double(((JTextField)evt.getSource()).getText()).doubleValue());
        } catch (Exception e) {}
    }//GEN-LAST:event_tfEscalaMaxKeyReleased

    private void tfEscalaMaxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfEscalaMaxFocusLost
        tfEscalaMax.setText(eixo.getEscalaMax() + "");
    }//GEN-LAST:event_tfEscalaMaxFocusLost

    private void tfEscalaMinFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfEscalaMinFocusLost
        tfEscalaMin.setText(eixo.getEscalaMin() + "");
    }//GEN-LAST:event_tfEscalaMinFocusLost

    private void tfEscalaMinKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfEscalaMinKeyReleased
        try {
            eixo.setEscalaMin( new Double(((JTextField)evt.getSource()).getText()).doubleValue());
        } catch (Exception e) {}
    }//GEN-LAST:event_tfEscalaMinKeyReleased
            
    private void cbEscalaAutomaticaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEscalaAutomaticaActionPerformed
        eixo.setAutoEscala(((JCheckBox)evt.getSource()).isSelected());
        if(eixo.isAutoEscala()){
            tfEscalaMax.setEnabled(false);
            tfEscalaMin.setEnabled(false);
        } else {
            tfEscalaMax.setEnabled(true);
            tfEscalaMin.setEnabled(true);
        }
    }//GEN-LAST:event_cbEscalaAutomaticaActionPerformed
    
    private void cbIncluiZeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbIncluiZeroActionPerformed
        eixo.setIncluiZero(((JCheckBox)evt.getSource()).isSelected());
    }//GEN-LAST:event_cbIncluiZeroActionPerformed
    
    private void cbExibirEixoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbExibirEixoActionPerformed
        eixo.setVisible(((JCheckBox)evt.getSource()).isSelected());
    }//GEN-LAST:event_cbExibirEixoActionPerformed
    
    private void btIndicesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIndicesActionPerformed
        new FormIndices(Controle.getTela(), eixo).setVisible(true);
    }//GEN-LAST:event_btIndicesActionPerformed
    
//    private void desabilitarPainel() {
//        System.out.println("desabiliar painelconfffffffffffffffffffffffffffffffffffff");
//    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btIndices;
    private javax.swing.JButton btMarcas;
    private javax.swing.JCheckBox cbEscalaAutomatica;
    private javax.swing.JCheckBox cbExibirEixo;
    private javax.swing.JCheckBox cbGradesHorizontais;
    private javax.swing.JCheckBox cbGradesVerticais;
    private javax.swing.JCheckBox cbIncluiZero;
    private javax.swing.JCheckBox cbLegenda;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lbCorGradesHorizontais;
    private javax.swing.JLabel lbCorGradesVerticais;
    private javax.swing.JSpinner spUnidades;
    private javax.swing.JTextField tfEscalaMax;
    private javax.swing.JTextField tfEscalaMin;
    private javax.swing.JTextField tfPercentual;
    // End of variables declaration//GEN-END:variables
    
}
