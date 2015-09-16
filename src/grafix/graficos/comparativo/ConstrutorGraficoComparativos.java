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



package grafix.graficos.comparativo;

import java.awt.geom.GeneralPath;
import java.util.Vector;
import org.jfree.chart.*;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.*;
import org.jfree.data.xy.XYDataset;


public class ConstrutorGraficoComparativos {
    
    private Vector<IndiceComparativo> indices;
    
    public ConstrutorGraficoComparativos(Vector<IndiceComparativo> indices) {
        this.indices = indices;
    }
    
    public JFreeChart criarJFreeChart() {
        XYDataset dataset = criarDataset();
        JFreeChart chart = criarChart(dataset);
        return chart;
    }

    private static JFreeChart criarChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Comparativo da Evolução de Papéis", "Período", "Evolução (%)", dataset, 
                true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer 
            = (XYLineAndShapeRenderer) plot.getRenderer();
        GeneralPath zigzag = new GeneralPath();
        zigzag.moveTo(-6.0f, 0.0f);
        zigzag.lineTo(-3.0f, 6.0f);
        zigzag.lineTo(3.0f, -6.0f);
        zigzag.lineTo(6.0f, 0.0f);
        renderer.setLegendLine(zigzag);
        return chart;
       
    }

    private XYDataset criarDataset() {
        TimeSeriesCollection tc = new TimeSeriesCollection();
        for (IndiceComparativo elem : indices) {
            TimeSeries tSerie = elem.getTimeSeries();
            tc.addSeries(tSerie);
        }
        return tc;
    }
}