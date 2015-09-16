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

import grafix.principal.Controle;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class FormLayout extends javax.swing.JDialog {
    
    public FormLayout(Frame frame) {
        super(frame);
        initComponents();
        centralizarFrame();
        GridLayout layout = Controle.getTela().getGridLayoutJanelas();
        spLin.setValue(layout.getRows());
        spCol.setValue(layout.getColumns());
        atualizarPainelExemplo(layout.getRows(), layout.getColumns());
    }

    private void centralizarFrame() {
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width -  this.getSize().width) / 2,
             (screenSize.height - this.getSize().height) / 2);
    }
    
    private void atualizarPainelExemplo(int lin, int col) {
        panelExemplo.setLayout(new GridLayout(lin, col, 2, 2));
        panelExemplo.removeAll();
        for (int i = 0; i < (lin * col); i++) {
            JPanel p = new JPanel();
            p.setBackground(Color.WHITE);
            p.setBorder(javax.swing.BorderFactory.createLineBorder(Color.black));
            panelExemplo.add(p);
        }
        panelExemplo.repaint();
        this.validate();
        this.repaint();
    }
    
    private void atualizarPainelExemplo() {
        atualizarPainelExemplo(new Integer(spLin.getValue().toString()).intValue(),
                new Integer(spCol.getValue().toString()).intValue());
    }

    private void configurarLayout() {
        Controle.getTela().organizarLayoutJanela(
                new Integer(spLin.getValue().toString()).intValue(), 
                new Integer(spCol.getValue().toString()).intValue());
    }
    
    
    // <editor-fold defaultstate="collapsed" desc=" Código Gerado ">//GEN-BEGIN:initComponents
    private void initComponents() {
        panelExemplo = new javax.swing.JPanel();
        btOK = new javax.swing.JButton();
        btCancel = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        spLin = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        spCol = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Distribui\u00e7\u00e3o das janelas");
        panelExemplo.setLayout(new java.awt.GridLayout(1, 1, 2, 2));

        panelExemplo.setBackground(new java.awt.Color(57, 110, 165));
        panelExemplo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btOK.setText("OK");
        btOK.setMaximumSize(new java.awt.Dimension(67, 23));
        btOK.setMinimumSize(new java.awt.Dimension(67, 23));
        btOK.setPreferredSize(new java.awt.Dimension(67, 23));
        btOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOKActionPerformed(evt);
            }
        });

        btCancel.setText("Cancel");
        btCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Layout da \u00e1rea de gr\u00e1ficos"));
        jLabel1.setText("Linhas:");

        spLin.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spLinStateChanged(evt);
            }
        });

        jLabel2.setText("Colunas:");

        spCol.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spColStateChanged(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1)
                    .add(jLabel2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(spLin)
                    .add(spCol, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 49, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(spLin, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(14, 14, 14)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(spCol, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("Ajuste \u00f3timo");
        jButton1.setEnabled(false);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 15, Short.MAX_VALUE)
                        .add(panelExemplo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 149, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(48, 48, 48)
                        .add(jButton1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 65, Short.MAX_VALUE)
                        .add(btOK, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btCancel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButton1))
                    .add(layout.createSequentialGroup()
                        .add(panelExemplo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 102, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 22, Short.MAX_VALUE)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(btCancel)
                            .add(btOK, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void spColStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spColStateChanged
        if(new Integer(spCol.getValue().toString()).intValue()==0) {
            spCol.setValue(1);
        }
        else {
            atualizarPainelExemplo();
        }
    }//GEN-LAST:event_spColStateChanged

    private void spLinStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spLinStateChanged
        if(new Integer(spLin.getValue().toString()).intValue()==0) {
            spLin.setValue(1);
        }
        else {
            atualizarPainelExemplo();
        }
    }//GEN-LAST:event_spLinStateChanged

    private void btCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btCancelActionPerformed

    private void btOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOKActionPerformed
        configurarLayout();
        this.setVisible(false);
    }//GEN-LAST:event_btOKActionPerformed
    
    
    // Declaração de variáveis - não modifique//GEN-BEGIN:variables
    private javax.swing.JButton btCancel;
    private javax.swing.JButton btOK;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelExemplo;
    private javax.swing.JSpinner spCol;
    private javax.swing.JSpinner spLin;
    // Fim da declaração de variáveis//GEN-END:variables
    
}
