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
import grafix.principal.Comandos;
import grafix.principal.Controle;
import java.awt.*;
import java.io.File;
import javax.swing.JFileChooser;

public class FormOpcoes extends javax.swing.JDialog {
    
    public FormOpcoes(Frame frame) {
        super(frame);
        initComponents();
        centralizarFrame();
        iniciarCampos();
    }

    private void centralizarFrame() {
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width -  this.getSize().width) / 2,
             (screenSize.height - this.getSize().height) / 2);
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        btOK = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        cbSalvaConfUsuario = new javax.swing.JCheckBox();
        cbSalvaAnalAcao = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tfDados = new javax.swing.JTextField();
        btDados = new javax.swing.JButton();
        btTestarBase = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Op\u00e7\u00f5es");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        btOK.setText("OK");
        btOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOKActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Op\u00e7\u00f5es"));
        cbSalvaConfUsuario.setSelected(true);
        cbSalvaConfUsuario.setText("Salva configura\u00e7\u00f5es do usu\u00e1rio ao sair");
        cbSalvaConfUsuario.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        cbSalvaConfUsuario.setEnabled(false);
        cbSalvaConfUsuario.setMargin(new java.awt.Insets(0, 0, 0, 0));

        cbSalvaAnalAcao.setSelected(true);
        cbSalvaAnalAcao.setText("Salva an\u00e1lises nas a\u00e7\u00f5es ao sair");
        cbSalvaAnalAcao.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        cbSalvaAnalAcao.setEnabled(false);
        cbSalvaAnalAcao.setMargin(new java.awt.Insets(0, 0, 0, 0));

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(cbSalvaAnalAcao)
                    .add(cbSalvaConfUsuario))
                .addContainerGap(178, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(cbSalvaAnalAcao)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(cbSalvaConfUsuario)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Pastas"));
        jLabel2.setText("Local da base de dados:");

        tfDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfDadosActionPerformed(evt);
            }
        });

        btDados.setText("...");
        btDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDadosActionPerformed(evt);
            }
        });

        btTestarBase.setText("Testar base de dados");
        btTestarBase.setEnabled(false);
        btTestarBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTestarBaseActionPerformed(evt);
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
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(tfDados, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 278, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btDados, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(btTestarBase, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 174, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(122, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(jLabel2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btDados)
                    .add(tfDados, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btTestarBase)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btCancelar.setText("Cancelar");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                            .add(btCancelar)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(btOK, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 76, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 26, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btOK)
                    .add(btCancelar))
                .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btTestarBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTestarBaseActionPerformed
        Comandos.cmdTestarBaseDados();
    }//GEN-LAST:event_btTestarBaseActionPerformed

    private void tfDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfDadosActionPerformed
        this.btOKActionPerformed(evt);
    }//GEN-LAST:event_tfDadosActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        Controle.getTela().acoesIniciais();
    }//GEN-LAST:event_formWindowClosed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btCancelarActionPerformed
    
    private void btOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOKActionPerformed
        String pastaDados = tfDados.getText();
        tfDados.setText(atualizarPastaDados(pastaDados));
        this.dispose();
    }//GEN-LAST:event_btOKActionPerformed

    static public String atualizarPastaDados(String pastaDados) {
        if(!pastaDados.endsWith(File.separator)) {
            pastaDados = pastaDados + File.separator;
        }
        if(ManipuladorArquivos.existeDiretorio(pastaDados)) {
            Controle.getConfiguracoesGrafix().setPathBaseDados(pastaDados);
            Controle.getTela().getComboAcoes().alterarCarteira();
        } else {
            Controle.exibirErro("Pasta de dados inválida. Alteração ignorada!");
        }
        return pastaDados;
    }

    private void btDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDadosActionPerformed
        tfDados.setText(ManipuladorArquivos.procurarPasta(this, Controle.getConfiguracoesGrafix().getPathBaseDados()));
    }//GEN-LAST:event_btDadosActionPerformed
    
//    private String getPastaDados() {
//        JFileChooser chooser = new JFileChooser(new File(Controle.getConfiguracoesGrafix().getPathBaseDados()));
//        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
//        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//        int returnVal = chooser.showOpenDialog(Controle.getTela());
//        if(returnVal == JFileChooser.APPROVE_OPTION) {
//            File file = chooser.getSelectedFile();
//            return file.getAbsolutePath();
//        } else {
//            return Controle.getConfiguracoesGrafix().getPathBaseDados();
//        }
//    }
    
    private void iniciarCampos() {
        tfDados.setText(Controle.getConfiguracoesGrafix().getPathBaseDados());
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btDados;
    private javax.swing.JButton btOK;
    private javax.swing.JButton btTestarBase;
    private javax.swing.JCheckBox cbSalvaAnalAcao;
    private javax.swing.JCheckBox cbSalvaConfUsuario;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField tfDados;
    // End of variables declaration//GEN-END:variables
    
}
