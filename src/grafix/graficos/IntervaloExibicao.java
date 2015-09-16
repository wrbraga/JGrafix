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



package grafix.graficos;

import grafix.telas.*;
import grafix.principal.Controle;

public class IntervaloExibicao {

    private JanelaGraficos janela;
    private int inicio;
    private int fim;
    private boolean espacoPrevisao;

    public IntervaloExibicao(JanelaGraficos janela) {
        this.janela = janela;
        this.aplicarZoomNormal();
    }

    public void aplicarZoomNormal() {
        setEspacoPrevisao(false);
        fim = janela.getAcao().getNumeroRegistros();
        int zNormal = Controle.getConfiguracoesGrafix().getZoomNormal();
        if (zNormal >= fim) {
            inicio = 0;
        } else {
            inicio = fim - zNormal;
        }
    }
    
    public void atualizarIntervaloExibicaoAposTrocaDeAcao() {
        // Estudar nova estratégia  ------
            //if(isZoomHistorico()) {
            //    aplicarZoomHistorico();
            //} else {
            //    aplicarZoomNormal();
            //}
        // -------------------------------
        aplicarZoomNormal();
    }

    public void aplicarZoomHistorico() {
        setEspacoPrevisao(false);
        inicio = 0;
        fim = janela.getAcao().getNumeroRegistros();
    }

    public void aplicarZoomPrevisao() {
        aplicarZoomNormal();
        setEspacoPrevisao(true);
    }

    public int getInicio() {
        return inicio;
    }

    public boolean isZoomHistorico() {
        return inicio == 0;
    }

    public void moverADireita() {
        int m = Controle.getConfiguracoesGrafix().getMovimentoIncremento();
        if(fim == janela.getAcao().getNumeroRegistros()) {
            // Topado à direita
            espacoPrevisao = true;
            return;
        }
        if(fim + m > janela.getAcao().getNumeroRegistros()) {
            int aux = fim - inicio;
            fim = janela.getAcao().getNumeroRegistros();
            inicio = fim - aux;
        } else {
            fim += m;
            inicio += m;
        }
    }

    public void moverAEsquerda() {
        int m = Controle.getConfiguracoesGrafix().getMovimentoIncremento();
        if(espacoPrevisao == true) {
            espacoPrevisao = false;
            return;
        }
        if(inicio > m) {
            inicio -= m;
            fim -= m;
        } else {
            int aux = fim - inicio;
            inicio = 0;
            fim = aux;
        }
    }

    public void moverMargemEsqADireita() {
        int m = Controle.getConfiguracoesGrafix().getMovimentoIncremento();
        if(inicio + m*2 < fim) {
            inicio += m;
        } 
    }

    public void moverMargemEsqAEsquerda() {
        int m = Controle.getConfiguracoesGrafix().getMovimentoIncremento();
        if(inicio > m) {
            inicio -= m;
        } else {
            inicio = 0;
        }
    }

    public void moverMargemDirADireita() {
        int m = Controle.getConfiguracoesGrafix().getMovimentoIncremento();
        if(fim + m > janela.getAcao().getNumeroRegistros()) {
            fim = janela.getAcao().getNumeroRegistros();
        } else {
            fim += m;
        }
    }

    public void moverMargemDirAEsquerda() {
        int m = Controle.getConfiguracoesGrafix().getMovimentoIncremento();
        if(fim -2*m > inicio) {
            fim -= m;
        } 
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    public int getFim() {
        return fim;
    }

    public void setFim(int fim) {
        this.fim = fim;
    }

    public boolean isEspacoPrevisao() {
        return espacoPrevisao;
    }

    public void setEspacoPrevisao(boolean espacoPrevisao) {
        this.espacoPrevisao = espacoPrevisao;
    }
    
}
