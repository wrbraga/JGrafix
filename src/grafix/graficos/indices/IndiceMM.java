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

public class IndiceMM extends Indice{
    
    public IndiceMM() {
        this(null, 2, false, 20, 0, 0);
    }
    
    public IndiceMM(Color cor, int espessura, boolean f, int p1, int p2, int p3) {
        super(ConstrutorDeIndices.INDICE_MM, "MM", cor, espessura, f,p1,p2,p3);
        criarParam1("Período", p1);
    }
    
    protected void configurarIndice() {
        this.setNomeIndice("Média Móvel");
    }
    
    protected void calcularValoresIndice(Acao acao) {
        double[] dados = calcularMM(acao, getParam1());
        setValores(new ValoresIndice(this, acao, dados));
    }
    
    static public double[] calcularMM(final Acao acao, final int periodo) {
        int N = acao.getNumeroRegistros();
        if(N <= periodo) {
            return dadosZerados(N);
        }
        double[] mm = new double[N];
        for (int i = 0; i < periodo; i++) {
            mm[i] = ValoresIndice.SEM_VALOR;
        }
        double soma = 0;
        for (int i = 0; i < periodo; i++) {
            soma += acao.getRegistro(i).getClose();
        }
        mm[periodo-1] = soma / periodo;
        
        for (int i = periodo; i < N; i++) {
            if(acao.getRegistro(i).getClose() == 0) {
                mm[i] = ValoresIndice.SEM_VALOR;
            } else {
                soma = soma + acao.getRegistro(i).getClose() - acao.getRegistro(i-periodo).getClose();
                mm[i] = soma / periodo;
            }
        }
        return mm;
    }
}
