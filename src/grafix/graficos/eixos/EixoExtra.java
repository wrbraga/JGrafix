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



package grafix.graficos.eixos;

import grafix.graficos.indices.*;
import grafix.telas.JanelaGraficos;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;

public class EixoExtra extends Eixo{
    
    public EixoExtra(String nomeEixo) {
        this.setNomeEixo(nomeEixo);
        this.setTamanho(1);
        Indice i = new IndiceIFR();
        i.setCor(java.awt.Color.BLUE);
        this.addIndice(i);
    }
    
    protected void completarPlot(final XYPlot plot, final JanelaGraficos janela) {
        if(indices.size()==0){  
            prepararPlotVazio(plot);
        }
    }
    
    private void prepararPlotVazio(final XYPlot plot) {
        StandardXYItemRenderer indicesRenderer = new StandardXYItemRenderer();
        plot.setRenderer(0, indicesRenderer);
    }
    
}
