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

import grafix.graficos.*;
import grafix.graficos.indices.*;
import grafix.principal.*;
import grafix.telas.*;
import java.awt.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.xy.*;
import org.jfree.data.time.ohlc.*;
import org.jfree.data.xy.*;

public class EixoCandles extends Eixo {

    public EixoCandles() {
        this.setTamanho(3);
        this.setNomeEixo("candles");
        this.setGradeHorizontal(false);
    }

    protected void completarPlot(final XYPlot plot, final JanelaGraficos janela) {
        OHLCDataset dataCandles = criarOHLCDataset(janela);
        CandlestickRenderer candRenderer = new CandlestickRenderer();
        candRenderer.setUpPaint(Controle.getConfiguracoesUsuario().getCorCandlesAlta());
        candRenderer.setDownPaint(Controle.getConfiguracoesUsuario().getCorCandlesBaixa());
        candRenderer.setSeriesPaint(0, Color.black);
        candRenderer.setStroke(new BasicStroke(.75f));
        candRenderer.setToolTipGenerator(new CandlesToolTipGenerator(janela));
        plot.setDataset(indices.size(), dataCandles);
        plot.setRenderer(indices.size(), candRenderer);
        incluirMarcaIntraday(plot, janela);
    }

    @Override
    protected NumberAxis definirEixoVertical() {
        if (!Controle.getConfiguracoesVolateis().isLog()) {
            return new NumberAxis();
        } else {
            NumberAxis n = new LogarithmicAxis("Eixo logarítmico");
            return n;
        }
    }

    private OHLCDataset criarOHLCDataset(JanelaGraficos janela) {
        //*debug*/ System.out.println("[CHAMADA] criarOHLCDataset()");
        OHLCSeries serie = new OHLCSeries(janela.getAcao().getCodAcao()); 
        IntervaloExibicao intervalo = janela.getIntervaloExibicao();
        for (int i = intervalo.getInicio(); i < intervalo.getFim(); i++) {
            RegistroDiario reg = janela.getAcao().getRegistro(i);
            serie.add(reg.getData(Controle.getConfiguracoesUsuario().isExibeSomenteDiasUteis()),
                    reg.getOpen(), reg.getHigh(), reg.getLow(), reg.getClose());
        }
        OHLCSeriesCollection dataset = new OHLCSeriesCollection();
        dataset.addSeries(serie);
        return dataset;
    }

//    private void incluirMarcaIntraday(final XYPlot plot, final JanelaGraficos janela) {
//        if (Controle.getConfiguracoesVolateis().isIntraday()) {
//            MarcaGrafica m = new MarcaGrafica("", "INTRADAY",
//                    janela.getAcao().getRegistro(janela.getAcao().getNumeroRegistros() - 1).getData(), Color.BLUE);
//            if (m.isRangeMarker()) {
//                if (getNomeEixo().equals(m.getNomeEixo())) {
//                    plot.addRangeMarker(gerarRangeMarker(m));
//                }
//            } else {
//                plot.addDomainMarker(gerarIntervalMarker(m, janela));
//            }
//        }
//    }
}
