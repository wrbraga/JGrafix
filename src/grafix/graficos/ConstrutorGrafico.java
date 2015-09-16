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



package grafix.graficos;

import grafix.graficos.eixos.*;
import grafix.graficos.elementos.*;
import grafix.principal.*;
import grafix.telas.*;
import java.awt.*;
import java.util.Date;
import java.util.Vector;
import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.*;
import org.jfree.data.time.Day;

public class ConstrutorGrafico {
    
    private JanelaGraficos janela;
    
    public ConstrutorGrafico(JanelaGraficos janela) {
        this.janela = janela;
    }
    
    public JFreeChart criarJFreeChart() {
        //*debug*/ System.out.println("[PROFILE] criarJFreeChart() - " + janela.getAcao().getCodAcao());
        DateAxis domainAxis = getEixoHorizontal();
        CombinedDomainXYPlot cplot = new CombinedDomainXYPlot(domainAxis);
        adicionarPlots(cplot);
        configurarEixoHorizontal(domainAxis);
        JFreeChart chart = new JFreeChart(cplot);
        configurarGrafico(chart);
        return chart;
    }

    private void configurarEixoHorizontal(DateAxis domainAxis) {
        if(janela.getIntervaloExibicao().isEspacoPrevisao()) {
            Day ultimoDia = janela.getAcao().getData(janela.getAcao().getNumeroRegistros()-1, Controle.getConfiguracoesUsuario().isExibeSomenteDiasUteis());
            Day ultimoDiaPrevisao = (Day) ultimoDia.next();
            for (int i = 0; i < Controle.getConfiguracoesGrafix().getEspacoPrevisao() - 1; i++) {
                ultimoDiaPrevisao = (Day) ultimoDiaPrevisao.next();
            }
            domainAxis.setMaximumDate(ultimoDiaPrevisao.getSerialDate().toDate());
        }
    }
    
    private void configurarGrafico(JFreeChart chart) {
        chart.removeLegend();
        chart.setBackgroundPaint(Color.white);
    }
    
    private void adicionarPlots(final CombinedDomainXYPlot cplot) {
        cplot.setGap(8.0);
        XYPlot plotCandles = janela.getConfiguracoesJanela().getCandles().getPlot(janela);
        XYPlot plotVolumes = janela.getConfiguracoesJanela().getVolume().getPlot(janela);
        XYPlot[] plotsExtras = gerarTodosOsPlotsExtras();
        if(plotCandles!=null) {
            cplot.add(plotCandles, janela.getConfiguracoesJanela().getCandles().getTamanho());
        }
        if(plotVolumes!=null) {
            cplot.add(plotVolumes, janela.getConfiguracoesJanela().getVolume().getTamanho());
        }
        for (int i = 0; i < plotsExtras.length; i++) {
            if(plotsExtras[i]!=null) {
                cplot.add(plotsExtras[i], 1);  /////////////// Tamanho fixo em 1 !!!!!!!!!!!!!!!!!!!!!!
            }
        }
    }
    
    private XYPlot[] gerarTodosOsPlotsExtras() {
        Vector<EixoExtra> eixosExtras = janela.getConfiguracoesJanela().getEixosExtras();
        XYPlot[] plots = new XYPlot[eixosExtras.size()];
        for (int i = 0; i < plots.length; i++) {
            plots[i] = eixosExtras.get(i).getPlot(janela);
        }
        return plots;
    }
    
    private DateAxis getEixoHorizontal() {
        if(Controle.getConfiguracoesUsuario().isExibeSomenteDiasUteis()) {
            return new EixoHorizontalComLabels(janela);
        } else {
            return new EixoHorizontal(janela);
        }
    }
    
//    static public void aplicarZoomNormal(JanelaGraficos umaJanela, DateAxis domainAxis) {  // Podia ser o comando ????
//        boolean isExibeSomenteDiasUteis = Controle.getConfiguracoesUsuario().isExibeSomenteDiasUteis();
//        Acao acao = umaJanela.getAcao();
//        RegistroDiario regIni;
//        if(Controle.getConfiguracoesGrafix().getZoomNormal() > acao.getNumeroRegistros()) {
//            regIni = acao.getRegistro(0);
//        } else {
//            regIni = acao.getRegistro(acao.getNumeroRegistros() - Controle.getConfiguracoesGrafix().getZoomNormal());
//        }
//        RegistroDiario regFim = acao.getRegistro(acao.getNumeroRegistros() - 1);
//        Day inicio = regIni.getData(isExibeSomenteDiasUteis);
//        Day fim = (Day)regFim.getData(isExibeSomenteDiasUteis).next();
//        domainAxis.setRange(inicio.getSerialDate().toDate(), fim.getSerialDate().toDate());
//   }
    
}