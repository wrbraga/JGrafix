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

import grafix.graficos.eixos.Eixo;
import grafix.graficos.elementos.MarcaGrafica;
import grafix.principal.*;
import grafix.telas.JanelaGraficos;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import org.jfree.data.time.Day;

public class FormMarcas extends javax.swing.JDialog {

    public final static int VALOR = 0;
    public final static int DATA = 1;
    public final static int PERIODO = 2;

    public FormMarcas(Frame frame, int opcaoInicial) {
        super(frame);
        initComponents();
        centralizarFrame();
        iniciarCampos(opcaoInicial);
    }

    private void centralizarFrame() {
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width - this.getSize().width) / 2,
                (screenSize.height - this.getSize().height) / 2);
    }

    private int corrigirAno(int ano) {
        if (ano > 1900) {
            return ano;
        } else if (ano > 50) {
            return 1900 + ano;
        } else {
            return 2000 + ano;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        rbValor = new javax.swing.JRadioButton();
        rbData = new javax.swing.JRadioButton();
        rbPeriodo = new javax.swing.JRadioButton();
        lbDemo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbEixos = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        tfTitulo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfValor = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfDiaI = new javax.swing.JTextField();
        tfDiaF = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tfAnoI = new javax.swing.JTextField();
        tfMesF = new javax.swing.JTextField();
        tfMesI = new javax.swing.JTextField();
        tfAnoF = new javax.swing.JTextField();
        lbCor = new javax.swing.JLabel();
        lbCorMarca = new javax.swing.JLabel();
        btOK = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Adicionar Marca");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo de Marca"));

        rbValor.setText("Valor");
        rbValor.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rbValor.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rbValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbValorActionPerformed(evt);
            }
        });

        rbData.setText("Data");
        rbData.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rbData.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rbData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDataActionPerformed(evt);
            }
        });

        rbPeriodo.setText("Período");
        rbPeriodo.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rbPeriodo.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rbPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPeriodoActionPerformed(evt);
            }
        });

        lbDemo.setOpaque(true);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(rbData)
                    .add(rbPeriodo)
                    .add(rbValor))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 82, Short.MAX_VALUE)
                .add(lbDemo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 141, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(lbDemo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1Layout.createSequentialGroup()
                        .add(11, 11, 11)
                        .add(rbValor)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(rbData)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(rbPeriodo)))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Propriedades"));

        jLabel1.setText("Eixo:");

        jLabel3.setText("Título:");

        jLabel4.setText("Valor:");

        tfValor.setEnabled(false);

        jLabel5.setText("Data inicial:");

        jLabel6.setText("Data final:");

        tfDiaI.setEnabled(false);

        tfDiaF.setEnabled(false);

        jLabel7.setText("/");

        jLabel8.setText("/");

        jLabel9.setText("/");
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel10.setText("/");

        tfAnoI.setEnabled(false);

        tfMesF.setEnabled(false);

        tfMesI.setEnabled(false);

        tfAnoF.setEnabled(false);

        lbCor.setText("Cor:");
        lbCor.setOpaque(true);

        lbCorMarca.setBackground(new java.awt.Color(0, 102, 204));
        lbCorMarca.setForeground(new java.awt.Color(255, 255, 255));
        lbCorMarca.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lbCorMarca.setOpaque(true);
        lbCorMarca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbCorMarcaMouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel3)
                            .add(jLabel1)
                            .add(jLabel4))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(cbEixos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 145, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(tfTitulo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
                                .add(24, 24, 24)
                                .add(lbCor)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(lbCorMarca, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 32, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(61, 61, 61))
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(tfValor, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 67, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel5)
                            .add(jLabel6))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(tfDiaF, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel10)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(tfMesF, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(tfDiaI, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel7)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(tfMesI, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(jLabel9)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(tfAnoI, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(jLabel8)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(tfAnoF, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 46, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(109, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel1)
                            .add(cbEixos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(tfTitulo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel3)
                            .add(lbCor))
                        .add(22, 22, 22)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel4)
                            .add(tfValor, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(12, 12, 12)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel5)
                            .add(tfDiaI, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel7)
                            .add(jLabel9)
                            .add(tfAnoI, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(tfMesI, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel6)
                            .add(tfDiaF, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel10)
                            .add(jLabel8)
                            .add(tfAnoF, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(tfMesF, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(31, 31, 31)
                        .add(lbCorMarca, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btOK.setText("OK");
        btOK.setMaximumSize(new java.awt.Dimension(73, 23));
        btOK.setMinimumSize(new java.awt.Dimension(73, 23));
        btOK.setPreferredSize(new java.awt.Dimension(73, 23));
        btOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOKActionPerformed(evt);
            }
        });

        btCancelar.setText("Cancelar");
        btCancelar.setMaximumSize(new java.awt.Dimension(73, 23));
        btCancelar.setMinimumSize(new java.awt.Dimension(73, 23));
        btCancelar.setPreferredSize(new java.awt.Dimension(73, 23));
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
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(btCancelar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 85, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btOK, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel2, 0, 308, Short.MAX_VALUE)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btOK, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(btCancelar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void btOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOKActionPerformed
        try {
            MarcaGrafica marca;
            String nomeEixo = ((Eixo) cbEixos.getSelectedItem()).getNomeEixo();
            String tag = tfTitulo.getText();
            Color cor = lbCorMarca.getBackground();
            if (rbValor.isSelected()) {
                double valor = (new Double(tfValor.getText())).doubleValue();
                marca = new MarcaGrafica(nomeEixo, tag, valor, cor);
            } else if (rbData.isSelected()) {
                int dia = new Integer(tfDiaI.getText()).intValue();
                int mes = new Integer(tfMesI.getText()).intValue();
                int ano = corrigirAno(new Integer(tfAnoI.getText()).intValue());
                Day data = new Day(dia, mes, ano);
                marca = new MarcaGrafica(nomeEixo, tag, data, cor);
            } else {
                int diaI = new Integer(tfDiaI.getText()).intValue();
                int mesI = new Integer(tfMesI.getText()).intValue();
                int anoI = corrigirAno(new Integer(tfAnoI.getText()).intValue());
                Day dataI = new Day(diaI, mesI, anoI);
                int diaF = new Integer(tfDiaF.getText()).intValue();
                int mesF = new Integer(tfMesF.getText()).intValue();
                int anoF = corrigirAno(new Integer(tfAnoF.getText()).intValue());
                Day dataF = new Day(diaF, mesF, anoF);
                marca = new MarcaGrafica(nomeEixo, tag, dataI, dataF, cor);
            }
            JanelaGraficos janela = Controle.getJanelaAtiva();
            boolean dataInicialValida = marca.getInicioNoGrafico(janela) != null;
            boolean dataFinalValida = marca.getFimNoGrafico(janela) != null;
            if (dataInicialValida && dataFinalValida) {
                Controle.getTela().adicionarMarcaGrafica(marca);
                this.setVisible(false);
            } else {
                if (!dataInicialValida) {
                    JOptionPane.showMessageDialog(this, "Data inicial está fora do intervalo da ação!", "Data inválida", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Data final está fora do intervalo da ação!", "Data inválida", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Preencha os campos corretamente!\n" +
                    "O título é obrigatório, e a data deve estar no formato:  DD/MM/AAAA", "Campos inválidos", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btOKActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btCancelarActionPerformed

    private void rbPeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPeriodoActionPerformed
        configurarMarcaPeriodo();
    }//GEN-LAST:event_rbPeriodoActionPerformed

    private void rbDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDataActionPerformed
        configurarMarcaData();
    }//GEN-LAST:event_rbDataActionPerformed

    private void rbValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbValorActionPerformed
        configurarMarcaValor();
    }//GEN-LAST:event_rbValorActionPerformed

    private void lbCorMarcaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCorMarcaMouseClicked
        Color novaCor = JColorChooser.showDialog(null, "Selecione uma cor", lbCorMarca.getBackground());
        if (novaCor != null) {
            lbCorMarca.setBackground(novaCor);
        }
    }//GEN-LAST:event_lbCorMarcaMouseClicked

    private void configurarMarcaValor() {
        lbDemo.setIcon(new ImageIcon(ConfiguracoesGrafix.PASTA_ICONES + "exempMarcaValor.gif"));
        desabilitarCampos();
        tfValor.setEnabled(true);
        tfValor.setEditable(true);
    }

    private void configurarMarcaData() {
        lbDemo.setIcon(new ImageIcon(ConfiguracoesGrafix.PASTA_ICONES + "exempMarcaData.gif"));
        desabilitarCampos();
        tfDiaI.setEnabled(true);
        tfMesI.setEnabled(true);
        tfAnoI.setEnabled(true);
        tfDiaI.setEditable(true);
        tfMesI.setEditable(true);
        tfAnoI.setEditable(true);
    }

    private void configurarMarcaPeriodo() {
        lbDemo.setIcon(new ImageIcon(ConfiguracoesGrafix.PASTA_ICONES + "exempMarcaPeriodo.gif"));
        desabilitarCampos();
        tfDiaI.setEnabled(true);
        tfMesI.setEnabled(true);
        tfAnoI.setEnabled(true);
        tfDiaI.setEditable(true);
        tfMesI.setEditable(true);
        tfAnoI.setEditable(true);
        tfDiaF.setEnabled(true);
        tfMesF.setEnabled(true);
        tfAnoF.setEnabled(true);
        tfDiaF.setEditable(true);
        tfMesF.setEditable(true);
        tfAnoF.setEditable(true);
    }

    private void desabilitarCampos() {
        tfValor.setEnabled(false);
        tfDiaI.setEnabled(false);
        tfDiaF.setEnabled(false);
        tfMesI.setEnabled(false);
        tfMesF.setEnabled(false);
        tfAnoI.setEnabled(false);
        tfAnoF.setEnabled(false);
        tfValor.setEditable(false);
        tfDiaI.setEditable(false);
        tfDiaF.setEditable(false);
        tfMesI.setEditable(false);
        tfMesF.setEditable(false);
        tfAnoI.setEditable(false);
        tfAnoF.setEditable(false);
    }

    private void iniciarCampos(int opcaoInicial) {
        for (Eixo eixo : Controle.getJanelaAtiva().getConfiguracoesJanela().getEixos()) {
            cbEixos.addItem(eixo);
        }
        lbCorMarca.setBackground(Color.BLUE);
        if (opcaoInicial == DATA) {
            rbData.setSelected(true);
            configurarMarcaData();
        } else if (opcaoInicial == PERIODO) {
            rbPeriodo.setSelected(true);
            configurarMarcaPeriodo();
        } else {
            rbValor.setSelected(true);
            configurarMarcaValor();

        }
        buttonGroup1.add(rbValor);
        buttonGroup1.add(rbData);
        buttonGroup1.add(rbPeriodo);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btOK;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cbEixos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbCor;
    private javax.swing.JLabel lbCorMarca;
    private javax.swing.JLabel lbDemo;
    private javax.swing.JRadioButton rbData;
    private javax.swing.JRadioButton rbPeriodo;
    private javax.swing.JRadioButton rbValor;
    private javax.swing.JTextField tfAnoF;
    private javax.swing.JTextField tfAnoI;
    private javax.swing.JTextField tfDiaF;
    private javax.swing.JTextField tfDiaI;
    private javax.swing.JTextField tfMesF;
    private javax.swing.JTextField tfMesI;
    private javax.swing.JTextField tfTitulo;
    private javax.swing.JTextField tfValor;
    // End of variables declaration//GEN-END:variables
}
