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

import grafix.graficos.IntervaloExibicao;
import grafix.principal.*;
import grafix.telas.JanelaGraficos;
import org.jfree.data.time.*;
import org.jfree.data.xy.XYDataset;

public class ValoresIndice {

    private String tagIndice;
    private Day diaInicio;
    private Day diaFim;
    private String codAcao;
    private boolean exibeSomenteDiasUteis;
    private double[] dados1;
    private double[] dados2;  // Alguns índices tem outras linhas
    private double[] dados3;  // Alguns índices tem outras linhas
//    private int zoomAnterior;
//    private int ultimoRegistroAnterior;
    public static final double SEM_VALOR = -9999;

    public ValoresIndice(final Indice indice, final Acao acao, final double[] dados) {
        tagIndice = indice.getTagIndice();
        diaInicio = acao.getRegistro(0).getData();
        diaFim = acao.getRegistro(acao.getNumeroRegistros() - 1).getData();
        codAcao = acao.getCodAcao();
        exibeSomenteDiasUteis = Controle.getConfiguracoesUsuario().isExibeSomenteDiasUteis();
        this.dados1 = dados;
    }

    public ValoresIndice(final Indice indice, final Acao acao, final double[] dados1, final double[] dados2) {
        this(indice, acao, dados1);
        this.dados2 = dados2;
    }

    public ValoresIndice(final Indice indice, final Acao acao, final double[] dados1, final double[] dados2, final double[] dados3) {
        this(indice, acao, dados1);
        this.dados2 = dados2;
        this.dados3 = dados3;
    }

    private TimeSeriesCollection construirDataset(JanelaGraficos janela) {
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        TimeSeries series = new TimeSeries(tagIndice, Day.class);
        IntervaloExibicao intervalo = janela.getIntervaloExibicao();
        for (int i = intervalo.getInicio(); i < intervalo.getFim(); i++) {
            if (this.dados1[i] != SEM_VALOR) {
                series.add(janela.getAcao().getRegistro(i).getData(exibeSomenteDiasUteis), this.dados1[i]);
            }
        }
        dataset.addSeries(series);
        if (dados2 != null) {
            TimeSeries series2 = new TimeSeries(tagIndice, Day.class);
            for (int i = intervalo.getInicio(); i < intervalo.getFim(); i++) {
                if (this.dados2[i] != SEM_VALOR) {
                    series2.add(janela.getAcao().getRegistro(i).getData(exibeSomenteDiasUteis), this.dados2[i]);
                }
            }
            dataset.addSeries(series2);
        }
        if (dados3 != null) {
            TimeSeries series3 = new TimeSeries(tagIndice, Day.class);
            for (int i = intervalo.getInicio(); i < intervalo.getFim(); i++) {
                if (this.dados3[i] != SEM_VALOR) {
                    series3.add(janela.getAcao().getRegistro(i).getData(exibeSomenteDiasUteis), this.dados3[i]);
                }
            }
            dataset.addSeries(series3);
        }
        return dataset;
    }

    protected XYDataset getDataSet(JanelaGraficos janela) {
        return construirDataset(janela);
    }

    protected boolean isAtualizado(JanelaGraficos janela) {
        Acao acaoAtual = janela.getAcao();
        return exibeSomenteDiasUteis == Controle.getConfiguracoesUsuario().isExibeSomenteDiasUteis() &&
                codAcao.equals(acaoAtual.getCodAcao()) &&
                diaInicio.equals(acaoAtual.getRegistro(0).getData()) &&
                diaFim.equals(acaoAtual.getRegistro(acaoAtual.getNumeroRegistros() - 1).getData());
    }

    public String getTagIndice() {
        return tagIndice;
    }

    public Object getDadoParaExibicao(int numSerie, int i) {
        if (numSerie == 0) {
            if (dados1[i] == ValoresIndice.SEM_VALOR) {
                return null;
            } else {
                return new Double(dados1[i]);
            }
        }
        if (numSerie == 1) {
            if (dados2[i] == ValoresIndice.SEM_VALOR) {
                return null;
            } else {
                return new Double(dados2[i]);
            }
        }
        if (numSerie == 2) {
            if (dados3[i] == ValoresIndice.SEM_VALOR) {
                return null;
            } else {
                return new Double(dados3[i]);
            }
        }
        return null;
    }

    public int getNumSeries() {
        if (dados3 != null) {
            return 3;
        } else if (dados2 != null) {
            return 2;
        } else if (dados1 != null) {
            return 1;
        } else {
            return 0;
        }
    }
}
