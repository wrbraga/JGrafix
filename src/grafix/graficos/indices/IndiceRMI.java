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

public class IndiceRMI extends Indice{
    
    public IndiceRMI() {
        this(null, 2, false, 8, 4, 0);
    }
    
    public IndiceRMI(Color cor, int espessura, boolean f, int p1, int p2, int p3) {
        super(ConstrutorDeIndices.INDICE_RMI, "RMI", cor, espessura, f,p1,p2,p3);
        criarParam1("Periodo RMI", p1);
        criarParam2("Momentos (***)", p2);
    }
    
    protected void configurarIndice() {
        this.setNomeIndice("Momento Relativo");
    }
    
    protected void calcularValoresIndice(Acao acao) {
        int periodo1 = getParam1();
        int periodo2 = getParam2();
        double[] dados = calcularRMI(acao, periodo1, periodo2);
        setValores(new ValoresIndice(this, acao, dados));
    }
    
    public static double[] calcularRMI(final Acao acao, final int periodo1, final int periodo2) {
        int N = acao.getNumeroRegistros();
        double[] rmi = new double[N];
        double[] incrFechamento = new double[N];
        double[] decrFechamento = new double[N];
        double[] smoothIncr = new double[N];
        double[] smoothDecr = new double[N];
        if(periodo1 >= N || periodo1 >= N) {
            return dadosZerados(N);
        }
        for (int i = periodo2; i < N; i++) {
            double dif = acao.getRegistro(i).getClose() - acao.getRegistro(i-periodo2).getClose();
            incrFechamento[i] = (dif > 0) ? dif : 0;
            decrFechamento[i] = (dif < 0) ? -dif : 0;
        }
        smoothIncr = calcularSmoothing(incrFechamento, periodo1);
        smoothDecr = calcularSmoothing(decrFechamento, periodo1);
        
        for (int i = 0; i < periodo1; i++) {
            rmi[i] = ValoresIndice.SEM_VALOR;
        }
        rmi[periodo1] = 100 - (100/ (1 + (smoothIncr[periodo1]/smoothDecr[periodo1])));
        for (int i = periodo1+1; i < N; i++) {
            if(acao.getRegistro(i).getClose() == 0) {
                rmi[i] = ValoresIndice.SEM_VALOR;
            } else {
                rmi[i] = 100 - (100/ (1 + (smoothIncr[i]/smoothDecr[i]) ));
            }
        }
        return rmi;
    }
    
    public static double[] calcularSmoothing(double[] dados, int periodo) {
        double[] smooth = new double[dados.length];
        double soma = 0;
        for (int i = 1; i <= periodo; i++) {
            soma += dados[i];
        }
        smooth[periodo] = soma/periodo;
        for (int i = periodo + 1; i < dados.length; i++) {
            smooth[i] = smooth[i-1] + (1/(double)periodo)*(dados[i] - smooth[i-1]);
        }
        return smooth;
    }
}
