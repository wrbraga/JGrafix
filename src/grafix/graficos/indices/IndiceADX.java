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



package grafix.graficos.indices;

import grafix.graficos.IndiceToolTipGenerator;
import grafix.principal.*;
import grafix.telas.*;
import java.awt.BasicStroke;
import java.awt.Color;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.*;

public class IndiceADX extends Indice {
    
    public IndiceADX() {
        this(null, 2, true, 14, 0, 0);
    }
    
    public IndiceADX(Color cor, int espessura, boolean f, int p1, int p2, int p3) {
        super(ConstrutorDeIndices.INDICE_ADX, "ADX", cor, espessura, f,p1,p2,p3);
        criarParam1("Período", p1);
    }
    
    protected void configurarIndice() {
        this.setNomeIndice("Indicador Direcional Médio");
    }
    
    @Override
    public void plotar(final XYPlot plot, final JanelaGraficos janela, final int contador) {
        XYLineAndShapeRenderer r = new XYLineAndShapeRenderer();
        r.setSeriesLinesVisible(0, true);
        r.setSeriesShapesVisible(0, false);
        r.setSeriesPaint(0, Color.BLUE);
        r.setSeriesStroke(0, new BasicStroke(.75f));
        
        r.setSeriesPaint(1, Color.RED);
        r.setSeriesStroke(1,
                new BasicStroke(
                .6f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                1.0f, new float[] {3.0f, 4.0f}, 0.0f
                )
                );
        r.setSeriesLinesVisible(1, true);
        r.setSeriesShapesVisible(1, false);
        
        r.setSeriesPaint(2, new Color(0f, .6f, 0f));
        r.setSeriesStroke(2,
                new BasicStroke(
                .6f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                2.0f, new float[] {3.0f, 4.0f}, 0.0f
                )
                );
        r.setSeriesLinesVisible(2, true);
        r.setSeriesShapesVisible(2, false);
        
        r.setToolTipGenerator(new IndiceToolTipGenerator(this));
        plot.setRenderer(contador, r);
        plot.setDataset(contador, getDataSet(janela));
    }
    
    protected void calcularValoresIndice(Acao acao) {
        int N = acao.getNumeroRegistros();
        double[] adx = new double[N];
        double[] tr = new double[N];    // tr
        double[] Pdm = new double[N];   // +dm
        double[] Ndm = new double[N];   // -dm
        double[] trPe = new double[N];  // trPeriodo
        double[] PdmPe = new double[N]; // +dmPeriodo
        double[] NdmPe = new double[N]; // -dmPeriodo
        double[] PdiPe = new double[N]; // +diPeriodo
        double[] NdiPe = new double[N]; // -diPeriodo
        double[] diN = new double[N];   // di diff
        double[] diP = new double[N];   // di soma
        double[] dx = new double[N];    // dx
        
        int periodo = getParam1();
        
        if(N <= periodo) {
            setValores(new ValoresIndice(this, acao, dadosZerados(N), dadosZerados(N), dadosZerados(N)));
            return;
        }
        // tr, Pdm, Ndm ------------------------------------------------------------------
        for (int i = 1; i < N; i++) {
            RegistroDiario reg = acao.getRegistro(i);
            RegistroDiario regAnt = acao.getRegistro(i-1);
            tr[i] = Math.max(reg.getHigh() - reg.getLow(),
                    Math.max(Math.abs(reg.getHigh() - regAnt.getClose()),
                    Math.abs(reg.getLow() - regAnt.getClose())));
            Pdm[i] = (reg.getHigh() - regAnt.getHigh() > regAnt.getLow() - reg.getLow()) ?
                Math.max(reg.getHigh() - regAnt.getHigh(), 0) : 0;
            Ndm[i] = (regAnt.getLow() - reg.getLow() > reg.getHigh() - regAnt.getHigh()) ?
                Math.max(regAnt.getLow() - reg.getLow(), 0) : 0;
        }
        
        // trPe, PdmPe, NdmPe, PdiPe, NdiPe, diN, diP, dx --------------------------------
        double somaTR = 0;
        double somaPDM = 0;
        double somaNDM = 0;
        for (int j = 1; j <= periodo; j++) {
            somaTR = somaTR + tr[j];
            somaPDM = somaPDM + Pdm[j];
            somaNDM = somaNDM + Ndm[j];
        }
        trPe[periodo] = somaTR;
        PdmPe[periodo] = somaPDM;
        NdmPe[periodo] = somaNDM;
        PdiPe[periodo] = arred(100*(PdmPe[periodo]/trPe[periodo]), 0);
        NdiPe[periodo] = arred(100*(NdmPe[periodo]/trPe[periodo]), 0);
        diN[periodo] = Math.abs(PdiPe[periodo] - NdiPe[periodo]);
        diP[periodo] = PdiPe[periodo] + NdiPe[periodo];
        dx[periodo] = arred(100*(diN[periodo]/diP[periodo]), 0);
        for (int i = periodo + 1; i < N; i++) {
            trPe[i] = arred(trunc((trPe[i-1] - (trPe[i-1]/periodo) + tr[i]), 3), 2);
            PdmPe[i] = arred(trunc((PdmPe[i-1] - (PdmPe[i-1]/periodo) + Pdm[i]), 3), 2);
            NdmPe[i] = arred(trunc((NdmPe[i-1] - (NdmPe[i-1]/periodo) + Ndm[i]), 3), 2);
            PdiPe[i] = arred(100*(PdmPe[i]/trPe[i]), 0);
            NdiPe[i] = arred(100*(NdmPe[i]/trPe[i]), 0);
            diN[i] = Math.abs(PdiPe[i] - NdiPe[i]);
            diP[i] = PdiPe[i] + NdiPe[i];
            dx[i] = arred(100*(diN[i]/diP[i]), 0);
        }
        
        // ADX ---------------------------------------------------------------------------
        for (int i = 0; i <= (periodo*2) - 2; i++) {
            adx[i] = ValoresIndice.SEM_VALOR;
        }
        double somaDX = 0;
        for (int i = periodo; i < (periodo*2); i++) {
            somaDX += dx[i];
        }
        adx[(periodo*2) - 1] = arred((somaDX / (double)periodo), 0);
        for (int i = periodo*2; i < N; i++) {
            if(acao.getRegistro(i).getClose() == 0) {
                adx[i] = ValoresIndice.SEM_VALOR;
                diN[i] = ValoresIndice.SEM_VALOR;
                diP[i] = ValoresIndice.SEM_VALOR;
            } else {
                adx[i] = arred(((adx[i-1]*(periodo-1)+dx[i])/(double)periodo), 0);
            }
        }
        
//        System.out.println("------------------------------------------------------------");
//        for (int i = 0; i < N; i++) {
//            System.out.println(acao.getHigh(i) + " " + acao.getLow(i) + " " + acao.getClose(i) + " " +
//                    tr[i] + " " + Pdm[i] + " " + Ndm[i] + " " + trPe[i] + " " + PdmPe[i] + " " + NdmPe[i] + " "
//                    + PdiPe[i] + " " + NdiPe[i] + " " + diN[i] + " " + diP[i] + " " + dx[i] + " " + adx[i]);
//        }
        setValores(new ValoresIndice(this, acao, adx, diN, diP));
    }
    
    public static double arred(double d, int c) {
        int aux = (int)Math.pow(10, c);
        return Math.round(d*aux) / (double)aux;
    }
    
    public static double trunc(double d, int c) {
        int aux = (int)Math.pow(10, c);
        return Math.floor(d*aux) / (double)aux;
    }
    
}
