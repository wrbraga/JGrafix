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

public class IndiceIFR extends Indice {
    
    public IndiceIFR() {
        this(null, 2, true, 14, 0, 0);
    }
    
    public IndiceIFR(Color cor, int espessura, boolean f, int p1, int p2, int p3) {
        super(ConstrutorDeIndices.INDICE_IFR, "IFR", cor, espessura, f,p1,p2,p3);
        criarFlag("Suavizar cálculo (****)", f);
        criarParam1("Período", p1);
    }
    
    protected void configurarIndice() {
        this.setNomeIndice("Índice de Força Relativa");
    }
    
    //  Implementar SEM SUAVIZAÇÂO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    
    protected void calcularValoresIndice(Acao acao) {
        double[] dados = IndiceRMI.calcularRMI(acao, getParam1(), 1);
        setValores(new ValoresIndice(this, acao, dados));
    }
    
}



////////////////////////////////////////////////////
//            javax.swing.text.NumberFormatter decimalFormatter = new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("0.0000"));
//            //decimalFormatter.valueToString(getValorO()));
//
//        for (int i = 0; i < N; i++) {
//            try {
//                System.out.println(decimalFormatter.valueToString(valorFechamento[i]) +
//                        " - " + decimalFormatter.valueToString(incrFechamento[i]) +
//                        " - " + decimalFormatter.valueToString(smoothIncr[i]) +
//                        " - " + decimalFormatter.valueToString(decrFechamento[i]) +
//                        " - " + decimalFormatter.valueToString(smoothDecr[i]) +
//                        " - " + decimalFormatter.valueToString(dados[i]));
//            } catch (ParseException ex) {
//                ex.printStackTrace();
//            }
//        }


