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

import grafix.principal.Comandos;
import grafix.principal.ConfiguracoesGrafix;
import java.awt.Frame;

public class FormMovimento extends javax.swing.JDialog {
    
    final int MOVIMENTO_LATERAL = ConfiguracoesGrafix.DEFAULT_MOVIMENTO_INCREMENTO;
    
    public FormMovimento(Frame frame) {
        super(frame, true);
        initComponents();
        posicionarFrame();
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        btSupAcima = new javax.swing.JButton();
        btSupAbaixo = new javax.swing.JButton();
        btEsqEsq = new javax.swing.JButton();
        btEsqDir = new javax.swing.JButton();
        btDirEsq = new javax.swing.JButton();
        btDirDir = new javax.swing.JButton();
        btInfAcima = new javax.swing.JButton();
        btInfAbaixo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btOK = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ajuste do Quadro");
        setResizable(false);
        jPanel1.setLayout(null);

        btSupAcima.setIcon(new javax.swing.ImageIcon("C:\\Desenvolvimento\\Grafix2\\resource\\icones\\moveAcima.gif"));
        btSupAcima.setFocusPainted(false);
        btSupAcima.setMaximumSize(new java.awt.Dimension(20, 20));
        btSupAcima.setMinimumSize(new java.awt.Dimension(20, 20));
        btSupAcima.setPreferredSize(new java.awt.Dimension(20, 20));
        btSupAcima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSupAcimaActionPerformed(evt);
            }
        });

        jPanel1.add(btSupAcima);
        btSupAcima.setBounds(90, 23, 25, 13);

        btSupAbaixo.setIcon(new javax.swing.ImageIcon("C:\\Desenvolvimento\\Grafix2\\resource\\icones\\moveAbaixo.gif"));
        btSupAbaixo.setFocusPainted(false);
        btSupAbaixo.setMaximumSize(new java.awt.Dimension(20, 20));
        btSupAbaixo.setMinimumSize(new java.awt.Dimension(20, 20));
        btSupAbaixo.setPreferredSize(new java.awt.Dimension(20, 20));
        btSupAbaixo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSupAbaixoActionPerformed(evt);
            }
        });

        jPanel1.add(btSupAbaixo);
        btSupAbaixo.setBounds(90, 46, 25, 13);

        btEsqEsq.setIcon(new javax.swing.ImageIcon("C:\\Desenvolvimento\\Grafix2\\resource\\icones\\moveEsq.gif"));
        btEsqEsq.setFocusPainted(false);
        btEsqEsq.setMaximumSize(new java.awt.Dimension(20, 20));
        btEsqEsq.setMinimumSize(new java.awt.Dimension(20, 20));
        btEsqEsq.setPreferredSize(new java.awt.Dimension(20, 20));
        btEsqEsq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEsqEsqActionPerformed(evt);
            }
        });

        jPanel1.add(btEsqEsq);
        btEsqEsq.setBounds(22, 72, 13, 25);

        btEsqDir.setIcon(new javax.swing.ImageIcon("C:\\Desenvolvimento\\Grafix2\\resource\\icones\\moveDir.gif"));
        btEsqDir.setFocusPainted(false);
        btEsqDir.setMaximumSize(new java.awt.Dimension(20, 20));
        btEsqDir.setMinimumSize(new java.awt.Dimension(20, 20));
        btEsqDir.setPreferredSize(new java.awt.Dimension(20, 20));
        btEsqDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEsqDirActionPerformed(evt);
            }
        });

        jPanel1.add(btEsqDir);
        btEsqDir.setBounds(46, 72, 13, 25);

        btDirEsq.setIcon(new javax.swing.ImageIcon("C:\\Desenvolvimento\\Grafix2\\resource\\icones\\moveEsq.gif"));
        btDirEsq.setFocusPainted(false);
        btDirEsq.setMaximumSize(new java.awt.Dimension(20, 20));
        btDirEsq.setMinimumSize(new java.awt.Dimension(20, 20));
        btDirEsq.setPreferredSize(new java.awt.Dimension(20, 20));
        btDirEsq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDirEsqActionPerformed(evt);
            }
        });

        jPanel1.add(btDirEsq);
        btDirEsq.setBounds(150, 72, 13, 25);

        btDirDir.setIcon(new javax.swing.ImageIcon("C:\\Desenvolvimento\\Grafix2\\resource\\icones\\moveDir.gif"));
        btDirDir.setFocusPainted(false);
        btDirDir.setMaximumSize(new java.awt.Dimension(20, 20));
        btDirDir.setMinimumSize(new java.awt.Dimension(20, 20));
        btDirDir.setPreferredSize(new java.awt.Dimension(20, 20));
        btDirDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDirDirActionPerformed(evt);
            }
        });

        jPanel1.add(btDirDir);
        btDirDir.setBounds(175, 72, 13, 25);

        btInfAcima.setIcon(new javax.swing.ImageIcon("C:\\Desenvolvimento\\Grafix2\\resource\\icones\\moveAcima.gif"));
        btInfAcima.setFocusPainted(false);
        btInfAcima.setMaximumSize(new java.awt.Dimension(20, 20));
        btInfAcima.setMinimumSize(new java.awt.Dimension(20, 20));
        btInfAcima.setPreferredSize(new java.awt.Dimension(20, 20));
        btInfAcima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btInfAcimaActionPerformed(evt);
            }
        });

        jPanel1.add(btInfAcima);
        btInfAcima.setBounds(90, 110, 25, 13);

        btInfAbaixo.setIcon(new javax.swing.ImageIcon("C:\\Desenvolvimento\\Grafix2\\resource\\icones\\moveAbaixo.gif"));
        btInfAbaixo.setFocusPainted(false);
        btInfAbaixo.setMaximumSize(new java.awt.Dimension(20, 20));
        btInfAbaixo.setMinimumSize(new java.awt.Dimension(20, 20));
        btInfAbaixo.setPreferredSize(new java.awt.Dimension(20, 20));
        btInfAbaixo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btInfAbaixoActionPerformed(evt);
            }
        });

        jPanel1.add(btInfAbaixo);
        btInfAbaixo.setBounds(90, 135, 25, 13);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel1.setOpaque(true);
        jPanel1.add(jLabel1);
        jLabel1.setBounds(40, 40, 130, 90);

        btOK.setText("OK");
        btOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOKActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon("C:\\Desenvolvimento\\Grafix2\\resource\\icones\\zoomNormal.gif"));
        jButton1.setIconTextGap(0);
        jButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton1.setPreferredSize(new java.awt.Dimension(25, 25));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel2.add(jButton1);

        jButton2.setIcon(new javax.swing.ImageIcon("C:\\Desenvolvimento\\Grafix2\\resource\\icones\\zoomPrevisao.gif"));
        jButton2.setIconTextGap(0);
        jButton2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton2.setPreferredSize(new java.awt.Dimension(25, 25));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel2.add(jButton2);

        jButton3.setIcon(new javax.swing.ImageIcon("C:\\Desenvolvimento\\Grafix2\\resource\\icones\\zoomHistorico.gif"));
        jButton3.setIconTextGap(0);
        jButton3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton3.setPreferredSize(new java.awt.Dimension(25, 25));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel2.add(jButton3);

        jButton4.setIcon(new javax.swing.ImageIcon("C:\\Desenvolvimento\\Grafix2\\resource\\icones\\zoomMais.gif"));
        jButton4.setIconTextGap(0);
        jButton4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton4.setPreferredSize(new java.awt.Dimension(25, 25));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel2.add(jButton4);

        jButton5.setIcon(new javax.swing.ImageIcon("C:\\Desenvolvimento\\Grafix2\\resource\\icones\\zoomMenos.gif"));
        jButton5.setIconTextGap(0);
        jButton5.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton5.setPreferredSize(new java.awt.Dimension(25, 25));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jPanel2.add(jButton5);

        jButton6.setIcon(new javax.swing.ImageIcon("C:\\Desenvolvimento\\Grafix2\\resource\\icones\\moveEsquerda3.gif"));
        jButton6.setIconTextGap(0);
        jButton6.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton6.setPreferredSize(new java.awt.Dimension(25, 25));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jPanel2.add(jButton6);

        jButton7.setIcon(new javax.swing.ImageIcon("C:\\Desenvolvimento\\Grafix2\\resource\\icones\\moveDireita3.gif"));
        jButton7.setIconTextGap(0);
        jButton7.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton7.setPreferredSize(new java.awt.Dimension(25, 25));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jPanel2.add(jButton7);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(128, Short.MAX_VALUE)
                .add(btOK, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 86, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 156, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btOK)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        Comandos.cmdMoveDireita();
    }//GEN-LAST:event_jButton7ActionPerformed
    
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Comandos.cmdMoveEsquerda();
    }//GEN-LAST:event_jButton6ActionPerformed
    
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Comandos.cmdZoomMenos();
    }//GEN-LAST:event_jButton5ActionPerformed
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Comandos.cmdZoomMais();
    }//GEN-LAST:event_jButton4ActionPerformed
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Comandos.cmdZoomHistorico();
    }//GEN-LAST:event_jButton3ActionPerformed
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
//        Comandos.cmdZoomPrevisao();
    }//GEN-LAST:event_jButton2ActionPerformed
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Comandos.cmdZoomNormal();
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private void btInfAbaixoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btInfAbaixoActionPerformed
//        Comandos.cmdMoverQuadroInfAbaixo();
    }//GEN-LAST:event_btInfAbaixoActionPerformed

    private void btInfAcimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btInfAcimaActionPerformed
//        Comandos.cmdMoverQuadroInfAcima();
    }//GEN-LAST:event_btInfAcimaActionPerformed
    
    private void btDirDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDirDirActionPerformed
//        Comandos.cmdMoverQuadroDirDireita();
    }//GEN-LAST:event_btDirDirActionPerformed
    
    private void btDirEsqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDirEsqActionPerformed
//        Comandos.cmdMoverQuadroDirEsquerda();
    }//GEN-LAST:event_btDirEsqActionPerformed
    
    private void btEsqDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEsqDirActionPerformed
//        Comandos.cmdMoverQuadroEsqDireita();
    }//GEN-LAST:event_btEsqDirActionPerformed
    
    private void btEsqEsqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEsqEsqActionPerformed
//        Comandos.cmdMoverQuadroEsqEsquerda();
    }//GEN-LAST:event_btEsqEsqActionPerformed
    
    private void btSupAbaixoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSupAbaixoActionPerformed
//        Comandos.cmdMoverQuadroSupAbaixo();
    }//GEN-LAST:event_btSupAbaixoActionPerformed
    
    private void btSupAcimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSupAcimaActionPerformed
//        Comandos.cmdMoverQuadroSupAcima();
    }//GEN-LAST:event_btSupAcimaActionPerformed
    
    private void btOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOKActionPerformed
        this.dispose();
    }//GEN-LAST:event_btOKActionPerformed
    
    private void posicionarFrame() {
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width -  this.getSize().width) / 2,
                (int)((screenSize.height - this.getSize().height) / 1.3));
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btDirDir;
    private javax.swing.JButton btDirEsq;
    private javax.swing.JButton btEsqDir;
    private javax.swing.JButton btEsqEsq;
    private javax.swing.JButton btInfAbaixo;
    private javax.swing.JButton btInfAcima;
    private javax.swing.JButton btOK;
    private javax.swing.JButton btSupAbaixo;
    private javax.swing.JButton btSupAcima;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
    
}
