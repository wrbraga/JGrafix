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

public class IndicePO extends Indice{
    
    public IndicePO() {
        this(null, 2, false, 10, 30, 0);
    }
    
    public IndicePO(Color cor, int espessura, boolean f, int p1, int p2, int p3) {
        super(ConstrutorDeIndices.INDICE_PO, "PO", cor, espessura, f,p1,p2,p3);
        criarParam1("Menor MM", p1);
        criarParam2("Maior MM", p2);
    }
    
    protected void configurarIndice() {
        this.setNomeIndice("Price Oscillator");
    }
    
    protected void calcularValoresIndice(Acao acao) {
        double percentMenor = 2 / ((double)getParam2() + 1);
        double percentMaior = 2 / ((double)getParam1() + 1);
        double[] dados = calcularPO(acao, percentMenor, percentMaior);
        setValores(new ValoresIndice(this, acao, dados));
    }
    
    static public double[] calcularPO(final Acao acao, final double percentMenor, final double percentMaior) {
        int N = acao.getNumeroRegistros();
        double[] menorMM = new double[N];
        double[] maiorMM = new double[N];
        double[] po = new double[N];

        if(N <= 25) {
            return dadosZerados(N);
        }
        // Inicio
        po[0] = ValoresIndice.SEM_VALOR;
        menorMM[0] = acao.getRegistro(0).getClose();
        maiorMM[0] = acao.getRegistro(0).getClose();
        // Próximos 25
        for (int i = 1; i < 25; i++) {
            double close = acao.getRegistro(i).getClose();
            menorMM[i] = (close * percentMenor) + (menorMM[i-1] * (1-percentMenor));
            maiorMM[i] = (close * percentMaior) + (maiorMM[i-1] * (1-percentMaior));
            po[i] = 0;
        }
        // Demais
        for (int i = 25; i < N; i++) {
            if(acao.getRegistro(i).getClose() == 0) {
                po[i] = ValoresIndice.SEM_VALOR;
            } else {
                double close = acao.getRegistro(i).getClose();
                menorMM[i] = (close * percentMenor) + (menorMM[i-1] * (1-percentMenor));
                maiorMM[i] = (close * percentMaior) + (maiorMM[i-1] * (1-percentMaior));
                po[i] = maiorMM[i] - menorMM[i];
            }
        }
        return po;
    }
    
}
