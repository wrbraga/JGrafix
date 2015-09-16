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

public class DialogLogAtualizacao extends javax.swing.JDialog {

    private FormAtualizacao formAtualizacao;
    
    public DialogLogAtualizacao(FormAtualizacao formAtualizacao) {
        this(Controle.getTela(), false, formAtualizacao);
    }
    
    public DialogLogAtualizacao(java.awt.Frame parent, boolean modal, FormAtualizacao formAtualizacao) {
        super(parent, modal);
        this.formAtualizacao = formAtualizacao;
        initComponents();
        centralizarFrame();
        taMensagens.setText("\n");
        this.setTitle("Executando Atualização....");
        this.setVisible(true);
    }
    
    private void centralizarFrame() {
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width -  this.getSize().width) / 2,
                (screenSize.height - this.getSize().height) / 2);
    }
    
    public void append(String texto) {
        taMensagens.append(texto + "\n");
        taMensagens.setCaretPosition(taMensagens.getDocument().getLength());
    }
    
    public void finalizar() {
        jButton1.setEnabled(true);
        this.setTitle("Atualização concluída");
    }
    
    public void setTextojButton1(String texto) {
        jButton1.setText(texto);
    }
    
    public void habilitajButton1(boolean habilita) {
        jButton1.setEnabled(habilita);
    }
    
    public void informaBytesLidos(int bytes) {
        jLabel1.setText(String.format("%d", bytes) + " bytes lidos");
    }
    /**
     * Valores de 0 a 100
     **/
    public void definirPercentualProgresso(int progresso_0a100) {
        jProgressBar1.setValue(progresso_0a100);
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Código Gerado ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        taMensagens = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        taMensagens.setColumns(20);
        taMensagens.setEditable(false);
        taMensagens.setRows(5);
        jScrollPane1.setViewportView(taMensagens);

        jButton1.setText("Fechar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(jLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 383, Short.MAX_VALUE)
                        .add(jButton1))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jProgressBar1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jProgressBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jButton1)
                    .add(jLabel1))
                .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.cancelarAtualizacao();
    }//GEN-LAST:event_formWindowClosing
    
    private void cancelarAtualizacao() {
        if(jButton1.getText().equals("Cancelar")) {
            formAtualizacao.cancelarAtualizacao();
            this.jButton1.setEnabled(false);
            this.setTextojButton1("Fechar");
        } else {
            this.setVisible(false);
        }
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.cancelarAtualizacao();
    }//GEN-LAST:event_jButton1ActionPerformed
    
    // Declaração de variáveis - não modifique//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea taMensagens;
    // Fim da declaração de variáveis//GEN-END:variables
    
}
