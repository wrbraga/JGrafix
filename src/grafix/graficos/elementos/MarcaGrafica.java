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



package grafix.graficos.elementos;

import grafix.principal.*;
import grafix.telas.JanelaGraficos;
import java.awt.Color;
import org.jfree.data.time.Day;


public class MarcaGrafica {
    
    private String nomeEixo;
    private String tag;
    private double valor;
    private Day inicio;
    private Day fim;
    private Color cor;
    private boolean rangeMarker;
    
    // Periodo
    public MarcaGrafica(String nomeEixo, String tag, Day inicio, Day fim, Color cor) {
        this.setNomeEixo(nomeEixo);
        this.setTag(tag);
        this.setInicio(inicio);
        this.setFim(fim);
        this.setCor(cor);
        this.setRangeMarker(false);
    }
    
    // Data
    public MarcaGrafica(String nomeEixo, String tag, Day inicio, Color cor) {
        this(nomeEixo, tag, inicio, inicio, cor);
    }
    
    // Valor
    public MarcaGrafica(String nomeEixo, String tag, double valor, Color cor) {
        this.setNomeEixo(nomeEixo);
        this.setTag(tag);
        this.setValor(valor);
        this.setCor(cor);
        this.setRangeMarker(true);
    }
    
    public Day getInicioNoGrafico(JanelaGraficos janela) {
        return getDataNoGrafico(inicio, janela, true);
    }
    
    public Day getFimNoGrafico(JanelaGraficos janela) {
        if(inicio.equals(fim)) {
            return getDataNoGrafico(fim, janela, true);
        }
        return getDataNoGrafico(fim, janela, false);
    }
    
    private Day getDataNoGrafico(Day data, JanelaGraficos janela, boolean antes) {
        Acao acao = janela.getAcao();
        for (int i = 0; i < acao.getNumeroRegistros(); i++) {
            RegistroDiario r = acao.getRegistro(i);
            if(antes) {
                if(r.getData().compareTo(data) >=0) {
                    return r.getData(Controle.getConfiguracoesUsuario().isExibeSomenteDiasUteis());
                }
            } else {
                if(r.getData().compareTo(data) > 0) {
                    return r.getData(Controle.getConfiguracoesUsuario().isExibeSomenteDiasUteis());
                }
            }
        }
        System.err.println("[ERRO] Erro na conversao de data da marca!");
        return null;
    }
    
    public String getTag() {
        return tag;
    }
    
    public void setTag(String tag) {
        this.tag = tag;
    }
    
    public Color getCor() {
        return cor;
    }
    
    public void setCor(Color cor) {
        this.cor = cor;
    }
    
    public boolean isRangeMarker() {
        return rangeMarker;
    }
    
    public void setRangeMarker(boolean rangeMarker) {
        this.rangeMarker = rangeMarker;
    }
    
    public double getValor() {
        return valor;
    }
    
    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public Day getInicio() {
        return inicio;
    }
    
    public void setInicio(Day inicio) {
        this.inicio = inicio;
    }
    
    public Day getFim() {
        return fim;
    }
    
    public void setFim(Day fim) {
        this.fim = fim;
    }

    public String getNomeEixo() {
        return nomeEixo;
    }

    public void setNomeEixo(String nomeEixo) {
        this.nomeEixo = nomeEixo;
    }
    
}
