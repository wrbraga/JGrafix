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

public class IndiceROC extends Indice{
    
    public IndiceROC() {
        this(null, 2, false, 20, 0, 0);
    }
    
    public IndiceROC(Color cor, int espessura, boolean f, int p1, int p2, int p3) {
        super(ConstrutorDeIndices.INDICE_ROC, "ROC", cor, espessura, f,p1,p2,p3);
        criarFlag("Resultado percentual", f);
        criarParam1("Período", p1);
    }
    
    protected void configurarIndice() {
        this.setNomeIndice("Price Rate-of-Change");
    }
    
    protected void calcularValoresIndice(Acao acao) {
        int N = acao.getNumeroRegistros();
        int periodo = getParam1();
        if(N <= periodo) {
            setValores(new ValoresIndice(this, acao, dadosZerados(N)));
            return;
        }
        double[] dados = new double[N];
        for (int i = 0; i < periodo; i++) {
            dados[i] = ValoresIndice.SEM_VALOR;
        }
        for (int i = periodo; i < N; i++) {
            if(acao.getRegistro(i).getClose() == 0) {
                dados[i] = ValoresIndice.SEM_VALOR;
            } else {
                if(isFlag()) {
                    dados[i] = 100 * ((acao.getRegistro(i).getClose() -
                            acao.getRegistro(i-periodo).getClose()) /
                            acao.getRegistro(i-periodo).getClose());
                } else {
                    dados[i] = acao.getRegistro(i).getClose() - acao.getRegistro(i-periodo).getClose();
                }
            }
        }
        setValores(new ValoresIndice(this, acao, dados));
    }
}
