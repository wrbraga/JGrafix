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

import grafix.auxiliar.FormatadorPadrao;
import grafix.graficos.*;
import grafix.graficos.eixos.Eixo;
import grafix.graficos.indices.*;
import grafix.principal.*;
import grafix.telas.JanelaGraficos;
import java.text.*;
import java.util.Vector;
import javax.swing.text.*;

public class FormPlanilha extends javax.swing.JDialog {

    JanelaGraficos janela;

    public FormPlanilha() {
        this.janela = Controle.getJanelaAtiva();
        if (janela == null) {
            throw new RuntimeException("Não há janela ativa! Abertura da tela abortada!");
        }
        initComponents();
        centralizarFrame();
        preencherTabela();
    }

    private void centralizarFrame() {
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width - this.getSize().width) / 2,
                (screenSize.height - this.getSize().height) / 2);
    }

    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDados = new javax.swing.JTable();
        lbAcao = new javax.swing.JLabel();
        btSair = new javax.swing.JButton();
        btSalvar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Hist\u00f3rico da a\u00e7\u00e3o");
        tbDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Título 1", "Título 2", "Título 3", "Título 4"
            }
        ));
        jScrollPane1.setViewportView(tbDados);

        lbAcao.setFont(new java.awt.Font("Tahoma", 1, 12));
        lbAcao.setText("A\u00c7\u00c3O");

        btSair.setText("Sair");
        btSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSairActionPerformed(evt);
            }
        });

        btSalvar.setText("Salvar em arquivo");
        btSalvar.setEnabled(false);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, lbAcao, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(btSalvar)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btSair)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(lbAcao)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btSair)
                    .add(btSalvar))
                .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btSairActionPerformed

    private void preencherTabela() {
        Acao acao = janela.getAcao();
        Vector<ValoresIndice> valoresIndices = new Vector<ValoresIndice>();

        lbAcao.setText(acao.getCodAcao() + " - " + acao.getNomeAcao());

        int numColunasIndices = 0;
        for (Eixo eixo : janela.getConfiguracoesJanela().getEixos()) {
            for (Indice indice : eixo.getIndices()) {
                valoresIndices.add(indice.getValores());
                numColunasIndices += indice.getValores().getNumSeries();
            }
        }

        Object[][] planilha = new Object[acao.getNumeroRegistros()][8 + numColunasIndices];
        Object[] cabecalho = new Object[8 + numColunasIndices];

        cabecalho[0] = "Data";
        cabecalho[1] = "Open";
        cabecalho[2] = "Low";
        cabecalho[3] = "High";
        cabecalho[4] = "Close";
        cabecalho[5] = "NumNegocios";
        cabecalho[6] = "VolumeQuant";
        cabecalho[7] = "VolumeDinheiro";
        int coluna = 7;
        for (int j = 0; j < valoresIndices.size(); j++) {
            ValoresIndice val = valoresIndices.get(j);
            for (int k = 0; k < val.getNumSeries(); k++) {
                cabecalho[++coluna] = valoresIndices.get(j).getTagIndice();
            }
        }
        FormatadorPadrao formatador = Controle.getFormatador();
        for (int i = 0; i < acao.getNumeroRegistros(); i++) {
            try {
                planilha[i][0] = formatador.getDataFormatter().format(acao.getRegistro(i).getData().getStart());
                planilha[i][1] = formatador.getDecimalFormatter().valueToString(acao.getRegistro(i).getOpen());
                planilha[i][2] = formatador.getDecimalFormatter().valueToString(acao.getRegistro(i).getLow());
                planilha[i][3] = formatador.getDecimalFormatter().valueToString(acao.getRegistro(i).getHigh());
                planilha[i][4] = formatador.getDecimalFormatter().valueToString(acao.getRegistro(i).getClose());
                planilha[i][5] = formatador.getDecimalFormatter().valueToString(acao.getRegistro(i).getNumNegocios());
                planilha[i][6] = formatador.getVolumeFormatter().valueToString(acao.getRegistro(i).getVolumeQuant());
                planilha[i][7] = formatador.getVolumeFormatter().valueToString(acao.getRegistro(i).getVolumeDinheiro());
                coluna = 7;
                for (int j = 0; j < valoresIndices.size(); j++) {
                    ValoresIndice val = valoresIndices.get(j);
//System.out.println(val.getNumSeries());
                    for (int k = 0; k < val.getNumSeries(); k++) {
                        planilha[i][++coluna] = formatador.getDecimalFormatter().valueToString(val.getDadoParaExibicao(k, i));
                    }
                }
            } catch (ParseException e) {
                System.out.println(e.getLocalizedMessage());
                e.printStackTrace();
            }
        }
        tbDados.setModel(new javax.swing.table.DefaultTableModel(planilha, cabecalho));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btSair;
    private javax.swing.JButton btSalvar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbAcao;
    private javax.swing.JTable tbDados;
    // End of variables declaration//GEN-END:variables
}
