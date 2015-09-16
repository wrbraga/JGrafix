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
import grafix.graficos.comparativo.ConstrutorGraficoComparativos;
import grafix.graficos.comparativo.IndiceComparativo;
import grafix.principal.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.data.time.Day;

public class TelaComparativos extends javax.swing.JFrame {
    
    private ConstrutorGraficoComparativos construtor;
    private ChartPanel chartPanel;
    
    public TelaComparativos() {
        initComponents();
        popularComboDatas();
        popularCombos();
        centralizarFrame();
        panelGrafico.setLayout(new BorderLayout());
        atualizarGrafico();
    }
    
    private void atualizarGrafico() {
        Vector<IndiceComparativo> indices = gerarIndices();
        construtor = new ConstrutorGraficoComparativos(indices);
        panelGrafico.removeAll();
        chartPanel = new ChartPanel(construtor.criarJFreeChart());
        panelGrafico.add(chartPanel);
        panelGrafico.revalidate();
        panelGrafico.repaint();
        this.repaint();
    }
    
    private void centralizarFrame() {
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width -  this.getSize().width) / 2,
                (screenSize.height - this.getSize().height) / 2);
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jToolBar1 = new javax.swing.JToolBar();
        btSair = new javax.swing.JButton();
        btCapturarJPEG = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        combo1 = new javax.swing.JComboBox();
        combo2 = new javax.swing.JComboBox();
        cb2 = new javax.swing.JCheckBox();
        cb3 = new javax.swing.JCheckBox();
        cb4 = new javax.swing.JCheckBox();
        cb5 = new javax.swing.JCheckBox();
        combo3 = new javax.swing.JComboBox();
        combo4 = new javax.swing.JComboBox();
        combo5 = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        comboData = new javax.swing.JComboBox();
        panelGrafico = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Grafix - Comparativo de Pap\u00e9is");
        jToolBar1.setFloatable(false);
        jToolBar1.setMargin(new java.awt.Insets(0, 5, 0, 0));
        jToolBar1.setPreferredSize(new java.awt.Dimension(100, 35));
        btSair.setIcon(new javax.swing.ImageIcon("C:\\Desenvolvimento\\Grafix2\\resource\\icones\\sair.gif"));
        btSair.setFocusPainted(false);
        btSair.setMaximumSize(new java.awt.Dimension(25, 25));
        btSair.setMinimumSize(new java.awt.Dimension(25, 25));
        btSair.setPreferredSize(new java.awt.Dimension(25, 25));
        btSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSairActionPerformed(evt);
            }
        });

        jToolBar1.add(btSair);

        btCapturarJPEG.setIcon(new javax.swing.ImageIcon("C:\\Desenvolvimento\\Grafix2\\resource\\icones\\fotografia2.gif"));
        btCapturarJPEG.setFocusPainted(false);
        btCapturarJPEG.setMaximumSize(new java.awt.Dimension(25, 25));
        btCapturarJPEG.setMinimumSize(new java.awt.Dimension(25, 25));
        btCapturarJPEG.setPreferredSize(new java.awt.Dimension(25, 25));
        btCapturarJPEG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCapturarJPEGActionPerformed(evt);
            }
        });

        jToolBar1.add(btCapturarJPEG);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("A\u00e7\u00f5es"));

        combo2.setEnabled(false);

        cb2.setText(" ");
        cb2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        cb2.setFocusPainted(false);
        cb2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        cb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb2ActionPerformed(evt);
            }
        });

        cb3.setText(" ");
        cb3.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        cb3.setFocusPainted(false);
        cb3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        cb3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb3ActionPerformed(evt);
            }
        });

        cb4.setText(" ");
        cb4.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        cb4.setFocusPainted(false);
        cb4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        cb4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb4ActionPerformed(evt);
            }
        });

        cb5.setText(" ");
        cb5.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        cb5.setFocusPainted(false);
        cb5.setMargin(new java.awt.Insets(0, 0, 0, 0));
        cb5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb5ActionPerformed(evt);
            }
        });

        combo3.setEnabled(false);

        combo4.setEnabled(false);

        combo5.setEnabled(false);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(cb2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(combo2, 0, 107, Short.MAX_VALUE)
                            .add(combo1, 0, 107, Short.MAX_VALUE)))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(cb3)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(combo3, 0, 107, Short.MAX_VALUE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(cb4)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(combo4, 0, 107, Short.MAX_VALUE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(cb5)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(combo5, 0, 107, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(combo1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(cb2)
                    .add(combo2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(cb3)
                    .add(combo3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(cb4)
                    .add(combo4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(cb5)
                    .add(combo5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Configura\u00e7\u00e3o"));
        jLabel1.setText("In\u00edcio da Compara\u00e7\u00e3o");

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(comboData, 0, 134, Short.MAX_VALUE)
                    .add(jLabel1))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(comboData, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(231, Short.MAX_VALUE))
        );

        panelGrafico.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.layout.GroupLayout panelGraficoLayout = new org.jdesktop.layout.GroupLayout(panelGrafico);
        panelGrafico.setLayout(panelGraficoLayout);
        panelGraficoLayout.setHorizontalGroup(
            panelGraficoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 608, Short.MAX_VALUE)
        );
        panelGraficoLayout.setVerticalGroup(
            panelGraficoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 566, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(panelGrafico, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .add(jToolBar1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 806, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(jToolBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(panelGrafico, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCapturarJPEGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCapturarJPEGActionPerformed
        salvarJPEG();
    }//GEN-LAST:event_btCapturarJPEGActionPerformed
    
    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btSairActionPerformed
    
    private void cb5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb5ActionPerformed
        combo5.setEnabled(cb5.isSelected());
        atualizarGrafico();
    }//GEN-LAST:event_cb5ActionPerformed
    
    private void cb4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb4ActionPerformed
        combo4.setEnabled(cb4.isSelected());
        atualizarGrafico();
    }//GEN-LAST:event_cb4ActionPerformed
    
    private void cb3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb3ActionPerformed
        combo3.setEnabled(cb3.isSelected());
        atualizarGrafico();
    }//GEN-LAST:event_cb3ActionPerformed
    
    private void cb2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb2ActionPerformed
        combo2.setEnabled(cb2.isSelected());
        atualizarGrafico();
    }//GEN-LAST:event_cb2ActionPerformed
    
    private void popularCombos() {
        popularCombo(combo1);
        popularCombo(combo2);
        popularCombo(combo3);
        popularCombo(combo4);
        popularCombo(combo5);
    }
    
    private void popularCombo(JComboBox combo) {
        removerListener(combo);
        combo.removeAllItems();
        Vector<Acao> acoes = Controle.getCarteira().getAcoes();
        for (Acao a: acoes) {
            combo.addItem(a);
        }
        adicionarListener(combo);
    }
    
    private void removerListener(JComboBox combo) {
        try {
            combo.removeActionListener(combo.getActionListeners()[0]);
        } catch (Exception e) {}
    }
    
    private void adicionarListener(JComboBox combo) {
        combo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atualizarGrafico();
            }
        });
    }
    
    private Vector<IndiceComparativo> gerarIndices() {
        Vector<IndiceComparativo> resultado = new Vector<IndiceComparativo>();
        Day dataComparacao = Day.parseDay(comboData.getSelectedItem().toString());
        resultado.add(new IndiceComparativo(Controle.getCarteira().getAcao(combo1.getSelectedItem().toString()), dataComparacao));
        if(cb2.isSelected()) {
            resultado.add(new IndiceComparativo(Controle.getCarteira().getAcao(combo2.getSelectedItem().toString()), dataComparacao));
        }
        if(cb3.isSelected()) {
            resultado.add(new IndiceComparativo(Controle.getCarteira().getAcao(combo3.getSelectedItem().toString()), dataComparacao));
        }
        if(cb4.isSelected()) {
            resultado.add(new IndiceComparativo(Controle.getCarteira().getAcao(combo4.getSelectedItem().toString()), dataComparacao));
        }
        if(cb5.isSelected()) {
            resultado.add(new IndiceComparativo(Controle.getCarteira().getAcao(combo5.getSelectedItem().toString()), dataComparacao));
        }
        return resultado;
    }
    
    private void popularComboDatas() {
        ArrayList<String> datas = ManipuladorArquivos.lerListaDeArquivo("datas.dat");
        for (String d : datas) {
            comboData.addItem(d);
        }
        comboData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizarGrafico();
            }
        });
    }
    
    private void salvarJPEG() {
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File(".jpg"));
        int returnVal = chooser.showSaveDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                ChartUtilities.saveChartAsJPEG(file,
                        chartPanel.getChart(),
                        chartPanel.getWidth(),
                        chartPanel.getHeight());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCapturarJPEG;
    private javax.swing.JButton btSair;
    private javax.swing.JCheckBox cb2;
    private javax.swing.JCheckBox cb3;
    private javax.swing.JCheckBox cb4;
    private javax.swing.JCheckBox cb5;
    private javax.swing.JComboBox combo1;
    private javax.swing.JComboBox combo2;
    private javax.swing.JComboBox combo3;
    private javax.swing.JComboBox combo4;
    private javax.swing.JComboBox combo5;
    private javax.swing.JComboBox comboData;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel panelGrafico;
    // End of variables declaration//GEN-END:variables
    
}


