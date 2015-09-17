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
import grafix.auxiliar.Utils;
import grafix.basedados.Atualiza;
import grafix.basedados.Uteis;
import grafix.principal.Controle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FormAtualizarPadrao extends javax.swing.JDialog implements FormAtualizacao {

    public Atualiza atualiza;
    private boolean problemaBaseDados = false;
    private DialogLogAtualizacao telaLog = null;
    private boolean pararAtualizacao = false;

    public FormAtualizarPadrao() {
        super(Controle.getTela());
        initComponents();
        centralizarFrame();
        buttonGroup1.add(rbDownload);
        buttonGroup1.add(rbLocal);
        atualiza = new Atualiza(this);
        iniciarCampos();
    }

    private void centralizarFrame() {
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width - this.getSize().width) / 2,
                (screenSize.height - this.getSize().height) / 2);
    }

    private void iniciarCampos() {
        String vendor = Controle.getConfiguracoesVolateis().getVendor();
        jTextField1.setText(Utils.getDataSistema());

        if (vendor.equals("yahoo")) {

            jTextField2.setText("");

            jTextField3.setText("");
            btAtualizaBovespa.setEnabled(false);
            btAtualizaBMF.setEnabled(false);
            btAtualizaTodos.setEnabled(false);



        } else {
            rbDownload.setSelected(true);
            jTextField1.setText(Utils.getDataSistema());
            String ultimaAtualizacao = atualiza.ultimaAtualizacaoBovespa();
            if (ultimaAtualizacao == null) {
                String mensagem = "Erro ao ler data da última atualização da base de dados localizada em " +
                        Controle.getConfiguracoesGrafix().getPathBaseDados() +
                        ".\n Provavelmente a base de dados não está no formato do grafix, não será possével realizar a atualização.";
            //Utils.exibeMsgErro(mensagem);
            //problemaBaseDados = true;
            } else {
                jTextField2.setText(atualiza.ultimaAtualizacaoBovespa());
                jTextField3.setText(atualiza.ultimaAtualizacaoBMF());
            }
        }
    }

    public void iniciarTelaLog() {
        final FormAtualizacao aux = this;
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                telaLog = new DialogLogAtualizacao(aux);
            }
        });

    }

    public void setTextoBotao(final String texto) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                telaLog.setTextojButton1(texto);
            }
        });
    }

    public void habilitaBotao(final boolean habilita) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                telaLog.habilitajButton1(habilita);
            }
        });
    }

    public void informarLog(final String mensagem) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                telaLog.append(mensagem);
            }
        });



    }

    public void definirPercentualProgresso(final int percentual) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                telaLog.definirPercentualProgresso(percentual);
            }
        });
    }

    public void informaBytesLidos(final int bytes) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                telaLog.informaBytesLidos(bytes);
            }
        });
    }

    public void finalizarTelaLog() {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                telaLog.finalizar();
            }
        });

    }

    public boolean isPararAtualizacao() {
        return pararAtualizacao;
    }

    public void setPararAtualizacao(final boolean aPararAtualizacao) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                pararAtualizacao = aPararAtualizacao;
            }
        });


    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        btAtualizaBovespa = new javax.swing.JButton();
        btAtualizaBMF = new javax.swing.JButton();
        btAtualizaInternacional = new javax.swing.JButton();
        btAtualizaTodos = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        rbDownload = new javax.swing.JRadioButton();
        rbLocal = new javax.swing.JRadioButton();
        jButton5 = new javax.swing.JButton();
        btPreferencias = new javax.swing.JButton();
        btOK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Atualizar Base de Dados");
        setLocationByPlatform(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Atualizações"));

        jLabel1.setText("Data do Sistema:");

        jLabel2.setText("Última atualização Bovespa:");

        jLabel3.setText("Última atualização BM&F:");

        jTextField1.setEditable(false);
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("00/00/2000");

        jTextField2.setEditable(false);
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText("00/00/2000");

        jTextField3.setEditable(false);
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setText("00/00/2000");

        btAtualizaBovespa.setText("Atualizar Bovespa");
        btAtualizaBovespa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtualizaBovespaActionPerformed(evt);
            }
        });

        btAtualizaBMF.setText("Atualizar BM&F");
        btAtualizaBMF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtualizaBMFActionPerformed(evt);
            }
        });

        btAtualizaInternacional.setText("Atualizar via Yahoo");
        btAtualizaInternacional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtualizaInternacionalActionPerformed(evt);
            }
        });

        btAtualizaTodos.setText("Atualizar Todos");
        btAtualizaTodos.setEnabled(false);
        btAtualizaTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtualizaTodosActionPerformed(evt);
            }
        });

        jButton1.setText("Seleciona Papéis Yahoo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Atualização manual");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel2)
                                    .add(jLabel3))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(jTextField3)
                                    .add(jTextField2)))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel1)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 80, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, btAtualizaBMF, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, btAtualizaBovespa, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, btAtualizaInternacional, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jButton2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(284, 284, 284)
                        .add(btAtualizaTodos, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(284, 284, 284)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jButton1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                            .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel1)
                            .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(20, 20, 20))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(jButton2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)))
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(btAtualizaBovespa)
                    .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(btAtualizaBMF)
                    .add(jTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btAtualizaInternacional)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btAtualizaTodos))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Configurações"));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo de Atualização"));

        rbDownload.setText("Download (Internet)");
        rbDownload.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        rbLocal.setText("Arquivos locais (no disco rígido)");
        rbLocal.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(rbDownload)
                    .add(rbLocal))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(rbDownload)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(rbLocal)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jButton5.setText("Selecionar Indicadores Internacionais");
        jButton5.setEnabled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        btPreferencias.setText("Preferências");
        btPreferencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPreferenciasActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(20, 20, 20)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jButton5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(btPreferencias, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jButton5)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btPreferencias))
                    .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btOK.setText("OK");
        btOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOKActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, btOK, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 79, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
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
                .add(btOK)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        FormPapeisYahoo fpa =
                new FormPapeisYahoo(Controle.getTela(), "", true);
        fpa.setVisible(true);

    }//GEN-LAST:event_jButton1ActionPerformed
                private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
                    if (problemaBaseDados) {
                        this.dispose();
                    }

    }//GEN-LAST:event_formWindowOpened

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btAtualizaTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtualizaTodosActionPerformed
    }//GEN-LAST:event_btAtualizaTodosActionPerformed

    private void btAtualizaInternacionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtualizaInternacionalActionPerformed
        this.atualiza.configuracao.setAtualizaViaInternet(this.rbDownload.isSelected());
        Thread thr = new Thread(new AtualizaMercado(this, "internacional"));
        thr.start();

    }//GEN-LAST:event_btAtualizaInternacionalActionPerformed

    private void btAtualizaBMFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtualizaBMFActionPerformed
        this.atualiza.configuracao.setAtualizaViaInternet(this.rbDownload.isSelected());
        Thread thr = new Thread(new AtualizaMercado(this, "bmf"));
        thr.start();
    }//GEN-LAST:event_btAtualizaBMFActionPerformed
                        private void btAtualizaBovespaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtualizaBovespaActionPerformed
                            this.atualiza.configuracao.setAtualizaViaInternet(this.rbDownload.isSelected());
                            Thread thr = new Thread(new AtualizaMercado(this, "bovespa"));
                            thr.start();

    }//GEN-LAST:event_btAtualizaBovespaActionPerformed

    private void btOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOKActionPerformed
        this.dispose();
    }//GEN-LAST:event_btOKActionPerformed

    private void btPreferenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPreferenciasActionPerformed
        FormPreferenciasAtualizar fpa =
                new FormPreferenciasAtualizar(Controle.getTela(), "", true);
        fpa.setVisible(true);
        if (fpa.getClicouOK()) {
            atualiza = new Atualiza(this);
        }
    }//GEN-LAST:event_btPreferenciasActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        try {
            String a = ManipuladorArquivos.procurarArquivo(this, Controle.getConfiguracoesGrafix().getPathBaseDados());            
            Uteis.unZip(a, ManipuladorArquivos.getPath(a), true, this);
        } catch (Exception ex) {
            Logger.getLogger(FormAtualizarPadrao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAtualizaBMF;
    private javax.swing.JButton btAtualizaBovespa;
    private javax.swing.JButton btAtualizaInternacional;
    private javax.swing.JButton btAtualizaTodos;
    private javax.swing.JButton btOK;
    private javax.swing.JButton btPreferencias;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JRadioButton rbDownload;
    private javax.swing.JRadioButton rbLocal;
    // End of variables declaration//GEN-END:variables

    public void cancelarAtualizacao() {
        informarLog("******* Aguarde, atualização sendo cancelada ... ");
        setPararAtualizacao(true);
    }
}

class AtualizaMercado implements Runnable {

    private FormAtualizarPadrao form;
    private String nomeMercado;

    public AtualizaMercado(FormAtualizarPadrao form, String nomeMercado) {
        this.form = form;
        this.nomeMercado = nomeMercado;
    }

    public void run() {
        form.iniciarTelaLog();
        /*  for(int i=0;i<1000;i++) {
        FormAtualizar.informarLog("dada");
        try {
        Thread.sleep(50);
        } catch (InterruptedException ex) {
        ex.printStackTrace();
        }
        }*/
        form.setTextoBotao("Cancelar");
        form.habilitaBotao(true);
        form.setPararAtualizacao(false);
        if (nomeMercado.equals("bovespa")) {
            form.atualiza.atualizaBovespa();
        } else if (nomeMercado.equals("bmf")) {
            form.atualiza.atualizaBMF();
        } else if (nomeMercado.equals("internacional")) {
            form.atualiza.atualizaInternacionais();
        }

        Controle.getTela().atualizarJanelasGraficos();
        Controle.getTela().ativarPrimeiraJanela();
        form.setTextoBotao("Fechar");
        form.finalizarTelaLog();

    }
}
