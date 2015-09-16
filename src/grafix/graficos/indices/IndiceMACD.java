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

public class IndiceMACD extends Indice{
    
    public IndiceMACD() {
        this(null, 2, false, 0, 0, 0);
    }

    public IndiceMACD(Color cor, int espessura, boolean f, int p1, int p2, int p3) {
        super(ConstrutorDeIndices.INDICE_MACD, "MACD", cor, espessura, f,p1,p2,p3);
    }
    
    protected void configurarIndice() {
        this.setNomeIndice("Moving Average Convergence/Divergence");
    }
    
    protected void calcularValoresIndice(Acao acao) {
        double[] dados = IndicePO.calcularPO(acao, .075, .15);
        setValores(new ValoresIndice(this, acao, dados));
    }
}
