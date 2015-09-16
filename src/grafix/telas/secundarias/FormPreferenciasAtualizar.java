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

import grafix.auxiliar.ConfiguracaoXML;
import grafix.auxiliar.ManipuladorArquivos;
import grafix.basedados.ConfiguracaoBaseDados;
import grafix.principal.ConfiguracoesGrafix;
import grafix.principal.Controle;
import java.awt.Frame;
import java.io.File;
import javax.swing.JFileChooser;

public class FormPreferenciasAtualizar extends javax.swing.JDialog {
    private ConfiguracaoBaseDados configuracao;
    private boolean clicouOK = false;
    
    public FormPreferenciasAtualizar(Frame parent, String title, boolean modal) {
//        super(Controle.getTela());
        super(parent, title, modal);
        initComponents();
        centralizarFrame();
        iniciarCampos();
    }

    private void centralizarFrame() {
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width -  this.getSize().width) / 2,
             (screenSize.height - this.getSize().height) / 2);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfBDI = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        tfDados = new javax.swing.JTextField();
        btProcurarPastaDados = new javax.swing.JButton();
        btProcurarPastaBDI = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jCheckBox13 = new javax.swing.JCheckBox();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jCheckBox10 = new javax.swing.JCheckBox();
        jCheckBox11 = new javax.swing.JCheckBox();
        jCheckBox12 = new javax.swing.JCheckBox();
        btOK = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Preferências de Atualização de dados");
        setLocationByPlatform(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Origem"));

        jLabel1.setText("Localização do arquivo 'datas.dat' na internet:");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jTextField1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Destino"));

        jLabel2.setText("Pasta da base de dados:");

        jLabel3.setText("Pasta dos arquivos 'bdi.zip':");

        jLabel4.setText("Formato da base de dados:");

        jTextField3.setEditable(false);

        btProcurarPastaDados.setText("Procurar");
        btProcurarPastaDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProcurarPastaDadosActionPerformed(evt);
            }
        });

        btProcurarPastaBDI.setText("Procurar");
        btProcurarPastaBDI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProcurarPastaBDIActionPerformed(evt);
            }
        });

        jButton3.setText("Alterar");
        jButton3.setEnabled(false);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel3)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel4)
                            .add(jLabel2))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(jTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 76, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jButton3))
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, tfBDI)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, tfDados, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(btProcurarPastaDados)
                                    .add(btProcurarPastaBDI))))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(tfDados, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(btProcurarPastaDados))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(jTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton3))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(tfBDI, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(btProcurarPastaBDI)))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Configurações de proxy"));

        jLabel5.setText("Servidor proxy:");

        jCheckBox13.setText("Usa servidor proxy");
        jCheckBox13.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        jLabel6.setText("Porta:");

        jLabel7.setText("Usuário:");

        jLabel9.setText("Senha:");

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jTextField5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel7)
                            .add(jLabel9)
                            .add(jLabel6))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jTextField7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jPasswordField1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                            .add(jTextField6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)))
                    .add(jCheckBox13)
                    .add(jLabel5))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jCheckBox13)
                .add(14, 14, 14)
                .add(jLabel5)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jTextField5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel6)
                    .add(jTextField6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel7)
                    .add(jTextField7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel9)
                    .add(jPasswordField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Extrair dados dos seguintes tipos de papéis"));

        jPanel3.setLayout(new java.awt.GridLayout(6, 2, 3, 0));

        jCheckBox1.setText("Lote padrão");
        jCheckBox1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jPanel3.add(jCheckBox1);

        jCheckBox7.setText("Opções de compra do índice");
        jCheckBox7.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jPanel3.add(jCheckBox7);

        jCheckBox2.setText("Concordatárias");
        jCheckBox2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jPanel3.add(jCheckBox2);

        jCheckBox3.setText("Opções de venda do índice");
        jCheckBox3.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jPanel3.add(jCheckBox3);

        jCheckBox4.setText("Direito / Recibo");
        jCheckBox4.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jPanel3.add(jCheckBox4);

        jCheckBox5.setText("Opções de compra");
        jCheckBox5.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jPanel3.add(jCheckBox5);

        jCheckBox6.setText("Bônus");
        jCheckBox6.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jPanel3.add(jCheckBox6);

        jCheckBox8.setText("Opções de venda");
        jCheckBox8.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jPanel3.add(jCheckBox8);

        jCheckBox9.setText("Termo");
        jCheckBox9.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jPanel3.add(jCheckBox9);

        jCheckBox10.setText("Fracionário");
        jCheckBox10.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jPanel3.add(jCheckBox10);

        jCheckBox11.setText("Recuperção judicial");
        jCheckBox11.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jPanel3.add(jCheckBox11);

        jCheckBox12.setText("Fundo imobiliário");
        jCheckBox12.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jPanel3.add(jCheckBox12);

        org.jdesktop.layout.GroupLayout jPanel6Layout = new org.jdesktop.layout.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 212, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btOK.setText("OK");
        btOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOKActionPerformed(evt);
            }
        });

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
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(btOK, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 74, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btCancelar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btCancelar)
                    .add(btOK))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
      clicouOK = false;
    }//GEN-LAST:event_formWindowActivated
    
    private void btOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOKActionPerformed
        this.salvarCampos();
        this.dispose();
       clicouOK = true;
    }//GEN-LAST:event_btOKActionPerformed
    
    private void btProcurarPastaDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProcurarPastaDadosActionPerformed
        tfDados.setText(ManipuladorArquivos.procurarPasta(this, Controle.getConfiguracoesGrafix().getPathBaseDados()));
    }//GEN-LAST:event_btProcurarPastaDadosActionPerformed

    private void btProcurarPastaBDIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProcurarPastaBDIActionPerformed
        tfBDI.setText(ManipuladorArquivos.procurarPasta(this, Controle.getConfiguracoesGrafix().getPathBaseDados()));
    }//GEN-LAST:event_btProcurarPastaBDIActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btOK;
    private javax.swing.JButton btProcurarPastaBDI;
    private javax.swing.JButton btProcurarPastaDados;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBox12;
    private javax.swing.JCheckBox jCheckBox13;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField tfBDI;
    private javax.swing.JTextField tfDados;
    // End of variables declaration//GEN-END:variables
    
    private void salvarCampos() {
        FormOpcoes.atualizarPastaDados(tfDados.getText());
        //String senhaCifrada = Controle.getCriptografia().cifrarSenha(new String (jPasswordField1.getPassword()));
        String senhaCifrada = jPasswordField1.getPassword().toString();

        ConfiguracaoXML configuracaoXML = new ConfiguracaoXML(ConfiguracoesGrafix.ARQUIVO_CONFIGURACOES_BASEDADOS);
        
        configuracao.setUrlDatas(jTextField1.getText());
        configuracao.setPastaBDIS(tfBDI.getText());
        configuracao.setFormatoBaseDados(jTextField3.getText());
        configuracao.setUsaProxy(jCheckBox13.isSelected());
        configuracao.setProxy(jTextField5.getText());
        configuracao.setAtualizaViaInternet(jCheckBox13.isSelected());
        if(jTextField6.getText().length()>0) {
          configuracao.setPorta(new Integer(jTextField6.getText()).intValue());
        }
        else {
            configuracao.setPorta(8080);
        }
        configuracao.setUsuarioProxy(jTextField7.getText());
        configuracao.setSenhaProxy(senhaCifrada);
        configuracao.getTiposPapeis().setLotePadrao(jCheckBox1.isSelected());
        configuracao.getTiposPapeis().setConcordatarias(jCheckBox2.isSelected());
        configuracao.getTiposPapeis().setDireitoRecibo(jCheckBox3.isSelected());
        configuracao.getTiposPapeis().setBonus(jCheckBox4.isSelected());
        configuracao.getTiposPapeis().setTermo(jCheckBox5.isSelected());
        configuracao.getTiposPapeis().setRecJudic(jCheckBox6.isSelected());
        configuracao.getTiposPapeis().setOpcCompIndice(jCheckBox7.isSelected());
        configuracao.getTiposPapeis().setOpcVendaIndice(jCheckBox8.isSelected());
        configuracao.getTiposPapeis().setOpcoesCompra(jCheckBox9.isSelected());
        configuracao.getTiposPapeis().setOpcoesVenda(jCheckBox10.isSelected());
        configuracao.getTiposPapeis().setFracionario(jCheckBox11.isSelected());
        configuracao.getTiposPapeis().setFundoImob(jCheckBox12.isSelected());
        
        
        configuracaoXML.guardaNoXML(configuracao);
    }
    
    private String getPastaDados() {
        JFileChooser chooser = new JFileChooser(new File(Controle.getConfiguracoesGrafix().getPathBaseDados()));
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = chooser.showOpenDialog(Controle.getTela());
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            return file.getAbsolutePath();
        } else {
            return null;
        }
    }
    
    private void iniciarCampos()  {
        tfDados.setText(Controle.getConfiguracoesGrafix().getPathBaseDados());
        File arquivo = new File(ConfiguracoesGrafix.ARQUIVO_CONFIGURACOES_BASEDADOS);
        
        if(arquivo.exists()) {
            ConfiguracaoXML configuracaoXML = new ConfiguracaoXML(ConfiguracoesGrafix.ARQUIVO_CONFIGURACOES_BASEDADOS);
            try {
                configuracao = (ConfiguracaoBaseDados) configuracaoXML.recuperaDoXML();
                jTextField1.setText(configuracao.getUrlDatas());
                tfBDI.setText(configuracao.getPastaBDIS());
                jTextField3.setText(configuracao.getFormatoBaseDados());
                
                jTextField5.setText(configuracao.getProxy());
                jCheckBox13.setSelected(configuracao.isAtualizaViaInternet());
                jTextField6.setText("" + configuracao.getPorta());
                jTextField7.setText(configuracao.getUsuarioProxy());
                //String senhaDecifrada = Controle.getCriptografia().decifrarSenha(configuracao.getSenhaProxy());
                String senhaDecifrada = "123456";
                jPasswordField1.setText(senhaDecifrada);
                jCheckBox1.setSelected(configuracao.getTiposPapeis().isLotePadrao());
                jCheckBox2.setSelected(configuracao.getTiposPapeis().isConcordatarias());
                jCheckBox3.setSelected(configuracao.getTiposPapeis().isDireitoRecibo());
                jCheckBox4.setSelected(configuracao.getTiposPapeis().isBonus());
                jCheckBox5.setSelected(configuracao.getTiposPapeis().isTermo());
                jCheckBox6.setSelected(configuracao.getTiposPapeis().isRecJudic());
                jCheckBox7.setSelected(configuracao.getTiposPapeis().isOpcCompIndice());
                jCheckBox8.setSelected(configuracao.getTiposPapeis().isOpcVendaIndice());
                jCheckBox9.setSelected(configuracao.getTiposPapeis().isOpcoesCompra());
                jCheckBox10.setSelected(configuracao.getTiposPapeis().isOpcoesVenda());
                jCheckBox11.setSelected(configuracao.getTiposPapeis().isFracionario());
                jCheckBox12.setSelected(configuracao.getTiposPapeis().isFundoImob());
                
                jCheckBox13.setSelected(configuracao.isUsaProxy());
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
        }
        
    }

  

    public boolean getClicouOK() {
        return clicouOK;
    }
}
