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



package grafix.graficos.comparativo;

import grafix.principal.*;
import org.jfree.data.time.*;

public class IndiceComparativo {
    
    private Day dataInicio = null;
    private Acao acao = null;
    
    public IndiceComparativo(Acao acao, Day dataInicio) {
        setAcao(acao);
        setDataInicio(dataInicio);
    }
    
    public void setDataInicio(Day dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Day getDataInicio() {
        return dataInicio;
    }

    public Acao getAcao() {
        return acao;
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }

    public TimeSeries getTimeSeries() {
        TimeSeries ts = new TimeSeries(acao.getCodAcao());
        double referencia = 0;
        boolean definiuReferencia = false;
        for (int i = 0; i < acao.getNumeroRegistros(); i++) {
            RegistroDiario reg = acao.getRegistro(i);
            if(reg.getData().compareTo(dataInicio) > 0) {
                if(!definiuReferencia) {
                    referencia = reg.getClose();
                    definiuReferencia = true;
                }
                ts.add(reg.getData(), new Double((reg.getClose()/referencia - 1)*100));
            }
        }
        return ts;
    }
}
