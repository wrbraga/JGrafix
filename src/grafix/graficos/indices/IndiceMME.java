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

public class IndiceMME extends Indice{
    
    public IndiceMME() {
        this(null, 2, false, 20, 0, 0);
    }
    
    public IndiceMME(Color cor, int espessura, boolean f, int p1, int p2, int p3) {
        super(ConstrutorDeIndices.INDICE_MME, "MME", cor, espessura, f,p1,p2,p3);
        criarParam1("Período", p1);
    }
    
    protected void configurarIndice() {
        this.setNomeIndice("Média Móvel Exponencial");
    }
    
    protected void calcularValoresIndice(Acao acao) {
        double percent = 2 / ((double)getParam1() + 1);
        double[] dados = calcularMME(acao, percent);
        setValores(new ValoresIndice(this, acao, dados));
    }
    
    static public double[] calcularMME(final Acao acao, final double percent) {
        int N = acao.getNumeroRegistros();
        double[] mme = new double[N];
        // Primeiro MME
        mme[0] = acao.getRegistro(0).getClose();
        // Próximos
        for (int i = 1; i < N; i++) {
            if(acao.getRegistro(i).getClose() == 0) {
                mme[i] = ValoresIndice.SEM_VALOR;
            } else {
                mme[i] = ( (acao.getRegistro(i).getClose() * percent) +
                        (mme[i-1]*(1-percent)) );
            }
        }
        return mme;
    }
}
