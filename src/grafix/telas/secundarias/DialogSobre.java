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
import grafix.seguranca.ControleRegistro;
import grafix.seguranca.RegistroLicenca;
import java.awt.Color;

public class DialogSobre extends javax.swing.JDialog {

    public DialogSobre(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jlVersao.setText(Controle.getConfiguracoesGrafix().VERSAO);
        try {
            RegistroLicenca reg = ControleRegistro.getRegistroLicenca();
            lbEmailLicenca.setText(reg.getEmail());
        /*    if(ControleRegistro.isRegistrado()) {
                lbDataLicenca.setText("Expira em: " + Controle.getFormatador().getDataFormatter().format(reg.getDataExpiracao()));
            }
            else {
                lbDataLicenca.setText("Licença expirou em: " + Controle.getFormatador().getDataFormatter().format(reg.getDataExpiracao()));
                lbEmailLicenca.setForeground(Color.RED);
                lbDataLicenca.setForeground(Color.RED);
            }*/
        } catch (Exception exception) {
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jlVersao = new javax.swing.JLabel();
        jlData = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbEmailLicenca = new javax.swing.JLabel();
        lbDataLicenca = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel2.setText("Grafix");

        jLabel3.setText("Versão: ");

        jLabel4.setText("Data: ");

        jlVersao.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlVersao.setText("2.3 beta");

        jlData.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jlData.setText("10/04/2009");

        jLabel7.setText("Desenvolvimento:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel8.setText("João Medeiros");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel9.setText("Paulo Sérgio C Vilela");

        lbEmailLicenca.setText("...");

        lbDataLicenca.setText("...");

        jLabel11.setText("Contatos:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel12.setText("joaomedeiros@iname.com");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel13.setText("paulo@grafix2.com");

        jLabel5.setText("Distribuído sob a licença GPL");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 128, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel10)
                            .add(jLabel2)
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel3)
                                    .add(jLabel4))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jlData)
                                    .add(jlVersao)))
                            .add(layout.createSequentialGroup()
                                .add(10, 10, 10)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(lbDataLicenca, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                                    .add(lbEmailLicenca, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)))))
                    .add(jLabel7)
                    .add(layout.createSequentialGroup()
                        .add(10, 10, 10)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 220, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 220, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jLabel11)
                    .add(layout.createSequentialGroup()
                        .add(10, 10, 10)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jLabel13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 220, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 220, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel5))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 124, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(40, 40, 40)
                        .add(jLabel7))
                    .add(layout.createSequentialGroup()
                        .add(jLabel2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel3)
                            .add(jlVersao))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel4)
                            .add(jlData))
                        .add(29, 29, 29)
                        .add(jLabel10)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(lbEmailLicenca)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(lbDataLicenca)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel8)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel9)
                .add(27, 27, 27)
                .add(jLabel11)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel12)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel13)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel5)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jlData;
    private javax.swing.JLabel jlVersao;
    private javax.swing.JLabel lbDataLicenca;
    private javax.swing.JLabel lbEmailLicenca;
    // End of variables declaration//GEN-END:variables
}
