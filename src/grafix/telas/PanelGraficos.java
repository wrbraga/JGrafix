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


package grafix.telas;

import grafix.graficos.MonitorAtualizacaoStatus;
import grafix.principal.Controle;
import grafix.telas.componentes.GrafixChartMouseListener;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.*;
import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.*;

public class PanelGraficos extends ChartPanel {

    final public int MINIMA_LARGURA = 10;
    final public int MINIMA_ALTURA = 10;
    final public int MAXIMA_LARGURA = 2000;
    final public int MAXIMA_ALTURA = 2000;
//    final private Color COR_CURSOR_EXTENDIDO = Color.GRAY;
    private JanelaGraficos janela;
    
    public PanelGraficos(JanelaGraficos janela, JFreeChart chart) {
        super(chart, true);
        this.janela = janela;
        this.setMinimumDrawWidth(MINIMA_LARGURA);
        this.setMinimumDrawHeight(MINIMA_ALTURA);
        this.setMaximumDrawWidth(MAXIMA_LARGURA);
        this.setMaximumDrawHeight(MAXIMA_ALTURA);
        this.addChartMouseListener(new GrafixChartMouseListener(this));
        setInitialDelay(50);
        setDismissDelay(10000);
        this.setPopupMenu(null);
        this.setCursorExtendido(Controle.getConfiguracoesUsuario().isCursorExtendido());
    }

    // Construtores obrigatorios ---------------------------------------------------------
    public PanelGraficos(JFreeChart chart, boolean useBuffer) {
        super(chart, useBuffer);
    }

    public PanelGraficos(JFreeChart chart, boolean properties, boolean save, boolean print, boolean zoom, boolean tooltips) {
        super(chart, properties, save, print, zoom, tooltips);
    }

    public PanelGraficos(JFreeChart chart, int width, int height, int minimumDrawWidth, int minimumDrawHeight, int maximumDrawWidth, int maximumDrawHeight, boolean useBuffer, boolean properties, boolean save, boolean print, boolean zoom, boolean tooltips) {
        super(chart, width, height, minimumDrawWidth, minimumDrawHeight, maximumDrawWidth, maximumDrawHeight, useBuffer, properties, save, print, zoom, tooltips);
    }

    public void setCursorExtendido(boolean flag) {
        this.setHorizontalAxisTrace(flag);
        this.setVerticalAxisTrace(flag);
    }

    public Rectangle2D getAreaData(int i) {
        return this.getChartRenderingInfo().getPlotInfo().getSubplotInfo(i).getDataArea();
    }

    public int getNumPlots() {
        return ((CombinedDomainXYPlot) Controle.getJanelaAtiva().getPanelGraficos().getChart().getPlot()).getSubplots().size();
    }

    public DateAxis getDomainAxis() {
        return (DateAxis) this.getChart().getXYPlot().getDomainAxis();
    }

    public ValueAxis getValueAxisCandles() {
        CombinedDomainXYPlot cplot = (CombinedDomainXYPlot) this.getChart().getPlot();
        XYPlot plot = (XYPlot) cplot.getSubplots().get(0);
        ValueAxis va = plot.getRangeAxis();
        return va;
    }

    @Override
    public void mouseMoved(MouseEvent evt) {
       super.mouseMoved(evt);
       java.awt.EventQueue.invokeLater(new MonitorAtualizacaoStatus(evt));
    }

    public JanelaGraficos getJanela() {
        return janela;
    }

    public void setJanela(JanelaGraficos janela) {
        this.janela = janela;
    }    
}
