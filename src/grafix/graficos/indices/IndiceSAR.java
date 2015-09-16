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

public class IndiceSAR extends Indice{
    
    public IndiceSAR() {
        this(null, 2, false, 0, 0, 0);
    }
    
    public IndiceSAR(Color cor, int espessura, boolean f, int p1, int p2, int p3) {
        super(ConstrutorDeIndices.INDICE_SAR, "SAR", cor, espessura, f,p1,p2,p3);
    }
    
    protected void configurarIndice() {
        this.setNomeIndice("Parabolic SAR (Stop and Reversal)");
        this.setTracoContinuo(false);
    }
    
    @SuppressWarnings("empty-statement")
    protected void calcularValoresIndice(Acao acao) {
        int N = acao.getNumeroRegistros();
        double[] b = new double[N];
        double[] c = new double[N];
        float[] d = new float[N];
        double[] e = new double[N];
        double[] f = new double[N];
        boolean[] g = new boolean[N];
        
        float accMax = 0.20f;
        float accInc = 0.02f;
        
        // Iniciar valores
        for (int i = 0; i < N; i++) {
            b[i] = acao.getRegistro(i).getHigh();
            c[i] = acao.getRegistro(i).getLow();
        }
        d[0] = accMax;
        e[0] = b[0];
        f[0] = c[0];
        g[0] = true;
        
        // Calcular
        for (int i = 1; i < N; i++) {
            if(acao.getRegistro(i).getClose() == 0) {
                f[i] = ValoresIndice.SEM_VALOR;
            } else {
                // =SE(G4=1;   SE(C4<F4;0;1);    SE(B4>F4;1;0))
                if(g[i-1]) {
                    g[i] = (c[i-1] < f[i-1])? false : true;
                } else {
                    g[i] = (b[i-1] > f[i-1])? true : false;
                }
                
                //=SE(G4<>G5; 0,02;     SE(D4=0,2; 0,2;      SE(OU(   E(G5=1;B5>B4); E(G5=0;C5<C4)   ); D4+ 0,02; D4)))
                if(g[i-1] != g[i]) {
                    d[i] = accInc;
                } else if(d[i-1]==accMax) {
                    d[i] = accMax;
                } else if( (g[i] && (b[i]>b[i-1])) || (!g[i] && (c[i]<c[i-1])) ) {
                    d[i] = d[i-1] + accInc;
                } else {
                    d[i] = d[i-1];
                }
                
                //=SE(G5=1;  MÁXIMO(E4;B5);   MÍNIMO(E4;C5))
                if(g[i]) {
                    e[i] = Math.max(e[i-1], b[i]);
                } else {
                    e[i] = Math.min(e[i-1], c[i]);
                }
                
                //=F4+(E4-F4)*D4
                if (g[i] == g[i-1]) {
                    f[i] = f[i-1] + (e[i-1] - f[i-1])*d[i-1];
                } else {
                    if(g[i]){
                        int j = i-1;
                        double minimo = c[j];
                        while (j>=0 && !g[j]) {
                            minimo = Math.min(minimo, c[j]);
                            j--;
                        }
                        f[i] = minimo;
                    } else {
                        int j = i-1;
                        double maximo = b[j];
                        while (j>=0 && g[j]) {
                            maximo = Math.max(maximo, b[j]);
                            j--;
                        }
                        f[i] = maximo;
                    }
                }
            }
        }
        setValores(new ValoresIndice(this, acao, f));
    }
}
