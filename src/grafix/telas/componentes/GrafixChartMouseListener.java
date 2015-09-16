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

import grafix.principal.*;
import grafix.telas.*;
import grafix.telas.secundarias.FormConfiguracaoJanela;
import grafix.telas.secundarias.FormIndices;
import java.awt.event.MouseEvent;
import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.entity.*;
import org.jfree.chart.plot.*;

public class GrafixChartMouseListener implements ChartMouseListener {

    private PanelGraficos panel;
    private JPopupMenu popup;
    private int plotClicado = -1;

    public GrafixChartMouseListener(PanelGraficos panel) {
        this.panel = panel;
        criarPopupMenu();
    }

    public void chartMouseClicked(ChartMouseEvent evt) {
        if (evt.getTrigger().getButton() == MouseEvent.BUTTON3) {
            int clickY = evt.getTrigger().getY();
            for (int i = 0; i < panel.getNumPlots(); i++) {
                int finalPlot = (int) panel.getAreaData(i).getY() + (int) panel.getAreaData(i).getHeight();
                if (clickY <= finalPlot) {
                    plotClicado = i;
                    popup.show(evt.getTrigger().getComponent(),
                            evt.getTrigger().getX(), evt.getTrigger().getY());
                    System.out.println("plot " + i);
                    return;
                }
            }
        }
    }

    private void abrirFormConfiguracoes(int i) {
        new FormConfiguracaoJanela(Controle.getTela(), i).setVisible(true);
    }

    private void abrirFormIndices(int i) {
        new FormIndices(Controle.getTela(), i).setVisible(true);
    }

    public void chartMouseMoved(ChartMouseEvent arg0) {
    }

    private void criarPopupMenu() {
        popup = new JPopupMenu();
        JMenuItem menuItem;

        menuItem = new JMenuItem("Configurar janela...");
        menuItem.setIcon(new javax.swing.ImageIcon(ConfiguracoesGrafix.PASTA_ICONES + "config.gif"));
        menuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirFormConfiguracoes(-1);
            }
        });
        popup.add(menuItem);

        menuItem = new JMenuItem("Configurar gráfico...");
        menuItem.setIcon(new javax.swing.ImageIcon(ConfiguracoesGrafix.PASTA_ICONES + "configurar3.gif"));
        menuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirFormConfiguracoes(plotClicado);
            }
        });
        popup.add(menuItem);

        menuItem = new JMenuItem("Índices...");
        menuItem.setIcon(new javax.swing.ImageIcon(ConfiguracoesGrafix.PASTA_ICONES + "indices.gif"));
        menuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirFormIndices(plotClicado);
            }
        });
        popup.add(menuItem);
    }
}
