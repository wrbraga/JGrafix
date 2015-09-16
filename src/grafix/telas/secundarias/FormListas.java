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
import grafix.principal.*;
import grafix.telas.componentes.ComboAcoes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class FormListas extends javax.swing.JDialog {
    
    public FormListas() {
        super(Controle.getTela());
        initComponents();
        centralizarFrame();
        popularComboListas();
        exibirConteudoLista();
    }
    
    private void centralizarFrame() {
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width -  this.getSize().width) / 2,
                (screenSize.height - this.getSize().height) / 2);
    }
    
    public void popularComboListas() {
        removerListener();
        cbListas.removeAllItems();
        String listaAtual = Controle.getConfiguracoesGrafix().getListaAcoes();
        int itemSelecionado = 0;
        File[] files = ManipuladorArquivos.listaConteudoDiretorio(ConfiguracoesGrafix.PASTA_LISTAS);
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            try {
                if(file.getName().endsWith(ConfiguracoesGrafix.EXTENSAO_LISTAS)) {
                    cbListas.addItem(file.getName());
                    if(file.getName().equals(listaAtual)) {
                        itemSelecionado = cbListas.getItemCount()-1;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if(cbListas.getItemCount()!=0) {
            cbListas.setSelectedIndex(itemSelecionado);
        }
        adicionarListener();
    }
    
    private void removerListener() {
        try {
            cbListas.removeActionListener(cbListas.getActionListeners()[0]);
        } catch (Exception e) {}
    }
    
    private void adicionarListener() {
        cbListas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exibirConteudoLista();
            }
        });
    }
    
    private void exibirConteudoLista() {
        try {
            if(cbListas.getSelectedItem() != null) {
                taConteudo.setText(ManipuladorArquivos.lerConteudoDeArquivo(
                        ConfiguracoesGrafix.PASTA_LISTAS + cbListas.getSelectedItem()));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private void alterarCarteira() {
        try {
            definirListaAtual(cbListas.getSelectedItem().toString());
            Controle.getTela().getComboAcoes().alterarCarteira();
        } catch (Exception e) {}
    }
    
    private boolean validarLista() {
        return true; //////////////////////////////////////////////////////////////////////
    }
    
    private void criarLista(final String lista) {
        ManipuladorArquivos.criarArquivo(ConfiguracoesGrafix.PASTA_LISTAS + lista + ConfiguracoesGrafix.EXTENSAO_LISTAS);
        definirListaAtual(lista + ConfiguracoesGrafix.EXTENSAO_LISTAS);
        popularComboListas();
        exibirConteudoLista();
    }
    
    private void definirListaAtual(String lista) {
        Controle.getConfiguracoesGrafix().setListaAcoes(lista);
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taConteudo = new javax.swing.JTextArea();
        btCarregarTodas = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        cbListas = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        btCriar = new javax.swing.JButton();
        btSair = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btSalvar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Listas de Papeis");
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Editar lista"));
        taConteudo.setColumns(10);
        taConteudo.setRows(5);
        taConteudo.setTabSize(1);
        jScrollPane1.setViewportView(taConteudo);

        btCarregarTodas.setText("Carregar Todas");
        btCarregarTodas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCarregarTodasActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .add(btCarregarTodas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 119, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 315, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btCarregarTodas)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Selecione a lista de papeis"));

        jLabel1.setText("Atual:");

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(cbListas, 0, 210, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(cbListas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btCriar.setText("Criar Nova Lista");
        btCriar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCriarActionPerformed(evt);
            }
        });

        btSair.setText("Sair");
        btSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSairActionPerformed(evt);
            }
        });

        btExcluir.setText("Excluir Lista");
        btExcluir.setEnabled(false);
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });

        jButton1.setText("Testar Lista");
        jButton1.setEnabled(false);

        btSalvar.setText("Salvar e Sair");
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
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
                    .add(layout.createSequentialGroup()
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, btSair, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                .add(jButton1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(btExcluir, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(btCriar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .add(btSalvar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(21, 21, 21)
                        .add(btCriar)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btExcluir)
                        .add(36, 36, 36)
                        .add(jButton1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(btSalvar)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btSair)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void btCarregarTodasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCarregarTodasActionPerformed
        taConteudo.setText("");
        ArrayList<String> papeis = Controle.getCarteira().todosOsPapeis();
        for (String elem : papeis) {
            taConteudo.append(elem + "\n");
        }
    }//GEN-LAST:event_btCarregarTodasActionPerformed
    
    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        if(cbListas.getSelectedItem() == null) {
            this.dispose();
        }
        else if(validarLista()) {
            if(ManipuladorArquivos.escreverEmArquivo(ConfiguracoesGrafix.PASTA_LISTAS + cbListas.getSelectedItem(), taConteudo.getText())) {
                //Controle.exibirInfo("Lista alterada com sucesso.");
                btSairActionPerformed(evt);
            }
        } else {
            Controle.exibirErro("O texto é inválido para compor lista de papeis.");
        }
    }//GEN-LAST:event_btSalvarActionPerformed
    
    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
        if(cbListas.getSelectedItem() != null) {
            alterarCarteira();
        }
        this.dispose();
    }//GEN-LAST:event_btSairActionPerformed
    
    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        // TODO adicione seu código de manipulação aqui:
    }//GEN-LAST:event_btExcluirActionPerformed
    
    private void btCriarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCriarActionPerformed
        String lista = JOptionPane.showInputDialog(Controle.getTela(), "Qual o nome da nova lista?", "", JOptionPane.QUESTION_MESSAGE);
        if(lista == null || lista.equals("")) {
            Controle.exibirErro("Nome inválido!");
        } else {
            criarLista(lista);
        }
    }//GEN-LAST:event_btCriarActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCarregarTodas;
    private javax.swing.JButton btCriar;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btSair;
    private javax.swing.JButton btSalvar;
    private javax.swing.JComboBox cbListas;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea taConteudo;
    // End of variables declaration//GEN-END:variables
    
}
