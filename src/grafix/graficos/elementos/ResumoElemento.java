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

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.Map;

public class ResumoElemento {
    
    private String classeName;
    private Color cor;
    private Rectangle2D posicaoReal;
    private int direcaoReta;
    private String nomeEixo;
    private Map parametros;

    public ResumoElemento(ElementoGrafico elem) {
        setClasseName(elem.getClass().getSimpleName());
        setCor(elem.getCor());
        setPosicaoReal(elem.getPosicaoReal());
        setDirecaoReta(elem.getDirecaoReta());
        setNomeEixo(elem.getNomeEixo());
        setParametros(elem.getParametros());
    }
    
    public ElementoGrafico getElementoGrafico() {
        ElementoGrafico elemento = null;
        try {
            elemento = (ElementoGrafico) (Class.forName("grafix.graficos.elementos." + getClasseName()).newInstance());
            elemento.setCor(cor);
            elemento.setPosicaoReal(posicaoReal);
            elemento.setDirecaoReta(direcaoReta);
            elemento.setNomeEixo(nomeEixo);
            elemento.setParametros(parametros);
            elemento.configurarElemento();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return elemento;
    }

    public Color getCor() {
        return cor;
    }
    
    public void setCor(Color cor) {
        this.cor = cor;
    }
    
    public Rectangle2D getPosicaoReal() {
        return posicaoReal;
    }
    
    public void setPosicaoReal(Rectangle2D posicaoReal) {
        this.posicaoReal = posicaoReal;
    }
    
    public String getClasseName() {
        return classeName;
    }
    
    public void setClasseName(String classeName) {
        this.classeName = classeName;
    }
    
    public int getDirecaoReta() {
        return direcaoReta;
    }

    public void setDirecaoReta(int direcaoReta) {
        this.direcaoReta = direcaoReta;
    }

    public String getNomeEixo() {
        return nomeEixo;
    }

    public void setNomeEixo(String nomeEixo) {
        this.nomeEixo = nomeEixo;
    }

    public Map getParametros() {
        return parametros;
    }

    public void setParametros(Map parametros) {
        this.parametros = parametros;
    }
}
