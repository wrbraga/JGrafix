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


package grafix.telas.componentes;

import grafix.auxiliar.FormatadorPadrao;
import grafix.principal.*;
import grafix.telas.*;
import java.text.ParseException;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;

public class BarraStatus2 extends javax.swing.JPanel {

    protected TelaGrafix tela;

    public BarraStatus2(TelaGrafix tela) {
        this.tela = tela;
        initComponents();
    }

    public void atualizar(RegistroDiario reg, double atual) {
        if (reg != null) {
            try {
                FormatadorPadrao formatador = Controle.getFormatador();
                if (reg.getOpen() < reg.getClose()) {
                    lbLed.setBackground(Controle.getConfiguracoesUsuario().getCorCandlesAlta()); // Alta
                } else {
                    lbLed.setBackground(Controle.getConfiguracoesUsuario().getCorCandlesBaixa()); // Baixa
                }
                tfData.setText(formatador.getDataFormatter().format(reg.getData().getStart()));
                tfAber.setText(formatador.getDecimalFormatter().valueToString(reg.getOpen()));
                tfMin.setText(formatador.getDecimalFormatter().valueToString(reg.getLow()));
                tfMax.setText(formatador.getDecimalFormatter().valueToString(reg.getHigh()));
                tfFech.setText(formatador.getDecimalFormatter().valueToString(reg.getClose()));
                tfVol.setText(formatador.getVolumeFormatter().valueToString(reg.getVolumeDinheiro()));
                tfNeg.setText(reg.getNumNegocios()+"");
                tfAtual.setText(formatador.getDecimalFormatter().valueToString(atual));
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jToolBar1 = new javax.swing.JToolBar();
        jPanel1 = new javax.swing.JPanel();
        lbLed = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tfData = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfAber = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfMin = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfMax = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tfFech = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tfVol = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tfNeg = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jLabel2 = new javax.swing.JLabel();
        tfAtual = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        jToolBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar1.setFloatable(false);
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 3));

        jPanel1.setMaximumSize(new java.awt.Dimension(35, 22));
        jPanel1.setMinimumSize(new java.awt.Dimension(35, 22));
        jPanel1.setPreferredSize(new java.awt.Dimension(35, 22));
        lbLed.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        lbLed.setMaximumSize(new java.awt.Dimension(15, 15));
        lbLed.setMinimumSize(new java.awt.Dimension(15, 6));
        lbLed.setOpaque(true);
        lbLed.setPreferredSize(new java.awt.Dimension(15, 15));
        jPanel1.add(lbLed);

        jToolBar1.add(jPanel1);

        jLabel1.setText("   Data   ");
        jToolBar1.add(jLabel1);

        tfData.setEditable(false);
        tfData.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfData.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tfData.setMaximumSize(new java.awt.Dimension(65, 25));
        tfData.setMinimumSize(new java.awt.Dimension(65, 25));
        tfData.setPreferredSize(new java.awt.Dimension(65, 25));
        jToolBar1.add(tfData);

        jLabel3.setText("     Aber   ");
        jToolBar1.add(jLabel3);

        tfAber.setEditable(false);
        tfAber.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfAber.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tfAber.setMaximumSize(new java.awt.Dimension(47, 25));
        tfAber.setMinimumSize(new java.awt.Dimension(47, 25));
        tfAber.setPreferredSize(new java.awt.Dimension(47, 25));
        jToolBar1.add(tfAber);

        jLabel4.setText("     M\u00edn   ");
        jToolBar1.add(jLabel4);

        tfMin.setEditable(false);
        tfMin.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfMin.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tfMin.setMaximumSize(new java.awt.Dimension(47, 25));
        tfMin.setMinimumSize(new java.awt.Dimension(47, 25));
        tfMin.setPreferredSize(new java.awt.Dimension(47, 25));
        jToolBar1.add(tfMin);

        jLabel5.setText("     M\u00e1x   ");
        jToolBar1.add(jLabel5);

        tfMax.setEditable(false);
        tfMax.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfMax.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tfMax.setMaximumSize(new java.awt.Dimension(47, 25));
        tfMax.setMinimumSize(new java.awt.Dimension(47, 25));
        tfMax.setPreferredSize(new java.awt.Dimension(47, 25));
        jToolBar1.add(tfMax);

        jLabel6.setText("     Fech   ");
        jToolBar1.add(jLabel6);

        tfFech.setEditable(false);
        tfFech.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfFech.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tfFech.setMaximumSize(new java.awt.Dimension(47, 25));
        tfFech.setMinimumSize(new java.awt.Dimension(47, 25));
        tfFech.setPreferredSize(new java.awt.Dimension(47, 25));
        jToolBar1.add(tfFech);

        jLabel7.setText("     Vol   ");
        jToolBar1.add(jLabel7);

        tfVol.setEditable(false);
        tfVol.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfVol.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tfVol.setMaximumSize(new java.awt.Dimension(50, 25));
        tfVol.setMinimumSize(new java.awt.Dimension(50, 25));
        tfVol.setPreferredSize(new java.awt.Dimension(50, 25));
        jToolBar1.add(tfVol);

        jLabel8.setText("     Neg   ");
        jToolBar1.add(jLabel8);

        tfNeg.setEditable(false);
        tfNeg.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfNeg.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tfNeg.setMaximumSize(new java.awt.Dimension(50, 25));
        tfNeg.setMinimumSize(new java.awt.Dimension(50, 25));
        tfNeg.setPreferredSize(new java.awt.Dimension(50, 25));
        jToolBar1.add(tfNeg);

        jLabel9.setText("   ");
        jToolBar1.add(jLabel9);

        jToolBar1.add(jSeparator1);

        jLabel2.setText("   Valor Y   ");
        jToolBar1.add(jLabel2);

        tfAtual.setEditable(false);
        tfAtual.setFont(new java.awt.Font("Tahoma", 1, 11));
        tfAtual.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tfAtual.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tfAtual.setMaximumSize(new java.awt.Dimension(70, 25));
        tfAtual.setMinimumSize(new java.awt.Dimension(70, 25));
        tfAtual.setPreferredSize(new java.awt.Dimension(70, 25));
        jToolBar1.add(tfAtual);

        jLabel10.setText("   ");
        jToolBar1.add(jLabel10);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jToolBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jToolBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lbLed;
    private javax.swing.JTextField tfAber;
    private javax.swing.JTextField tfAtual;
    private javax.swing.JTextField tfData;
    private javax.swing.JTextField tfFech;
    private javax.swing.JTextField tfMax;
    private javax.swing.JTextField tfMin;
    private javax.swing.JTextField tfNeg;
    private javax.swing.JTextField tfVol;
    // End of variables declaration//GEN-END:variables
}
