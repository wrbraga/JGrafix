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

import grafix.principal.*;
import java.awt.Color;

public class IndiceTRIX extends Indice{
    
    public IndiceTRIX() {
        this(null, 2, false, 9, 0, 0);
    }

    public IndiceTRIX(Color cor, int espessura, boolean f, int p1, int p2, int p3) {
        super(ConstrutorDeIndices.INDICE_TRIX, "TRIX", cor, espessura, f,p1,p2,p3);
        criarParam1("Período", p1);
    }
    
    protected void configurarIndice() {
        this.setNomeIndice("TRIX");
    }
    
    protected void calcularValoresIndice(Acao acao) {
        double percent = 2 / ((double)getParam1() + 1);
        double[] dados = new double[acao.getNumeroRegistros()];
        double[] mme1 = IndiceMME.calcularMME(acao, percent);
        double[] mme2 = calcularMMEdeArray(mme1, percent);
        double[] mme3 = calcularMMEdeArray(mme2, percent);
        // Calcular ROC 1-day percent
        dados[0] = ValoresIndice.SEM_VALOR;
        for (int i = 1; i < acao.getNumeroRegistros(); i++) { 
            if(acao.getRegistro(i).getClose() == 0) {
                dados[i] = ValoresIndice.SEM_VALOR;
            } else {
                dados[i] = 100 * ((mme3[i] - mme3[i-1]) / mme3[i-1]);
            }
        }
        setValores(new ValoresIndice(this, acao, dados));
    }

    static public double[] calcularMMEdeArray(final double[] origem, final double percent) {
        double[] mme = new double[origem.length];
        // Primeiro MME
        mme[0] = origem[0];
        // Próximos
        for (int i = 1; i < origem.length; i++) {
            mme[i] = ( (origem[i] * percent) + 
                    (mme[i-1]*(1-percent)) );
        }
        return mme;
    }
    
}
