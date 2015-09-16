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

import grafix.graficos.CandlesToolTipGenerator;
import grafix.graficos.IntervaloExibicao;
import grafix.principal.Controle;
import grafix.principal.RegistroDiario;
import grafix.telas.JanelaGraficos;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.IntervalXYDataset;

public class EixoVolume extends Eixo{
    
    public EixoVolume() {
        this.setTamanho(1);
        this.setNomeEixo("volume");
    }
    
    protected void completarPlot(final XYPlot plot, final JanelaGraficos janela) {
            IntervalXYDataset dataVolume = criarVolumeDataset(janela);
            XYBarRenderer volumeRenderer = new XYBarRenderer();
            volumeRenderer.setSeriesPaint(0,Controle.getConfiguracoesUsuario().getCorVolume());
            volumeRenderer.setDrawBarOutline(false);
            volumeRenderer.setMargin((double)Controle.getConfiguracoesUsuario().getEspacColunasVolume() / 10);
            volumeRenderer.setToolTipGenerator(new CandlesToolTipGenerator(janela));
            plot.setDataset(indices.size(), dataVolume);
            plot.setRenderer(indices.size(), volumeRenderer);
    }
    
    private IntervalXYDataset criarVolumeDataset(JanelaGraficos janela) {
        TimeSeries serie = new TimeSeries(janela.getAcao().getCodAcao() + " (volume)",
                Day.class);
        IntervaloExibicao intervalo = janela.getIntervaloExibicao();
        for (int i = intervalo.getInicio(); i < intervalo.getFim(); i++) {
            RegistroDiario reg = janela.getAcao().getRegistro(i);
            serie.add(reg.getData(Controle.getConfiguracoesUsuario().isExibeSomenteDiasUteis()),
                    reg.getVolumeDinheiro()/1000);
        }
        return new TimeSeriesCollection(serie);
    }
}
