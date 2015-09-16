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
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Quadro extends ElementoRetangular {
    
    public Quadro() {
        super();
    }
    
    public Quadro(Color cor) {
        super(cor);
    }
    
    protected void configurarElemento() {
        setOpaque(false);
//        setBorder(BorderFactory.createLineBorder(this.getCor()));
    }
    
    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        g.setColor(this.getCor());
        g.drawRect(1,1,getWidth()-2, getHeight()-2);
    }
    
    protected boolean eSobreALinha(Point2D p) {
        if(     p.getX()<=MARGEM_CLIQUE ||
                p.getX()>=(getWidth() - MARGEM_CLIQUE) ||
                p.getY()<=MARGEM_CLIQUE ||
                p.getY()>=(getHeight() - MARGEM_CLIQUE)) {
            return true;
        }
        return false;
    }

    public void duplicarElemento() {
        Quadro novoElemento = new Quadro(this.getCor());
        novoElemento.setNomeEixo(this.getNomeEixo());
        Rectangle2D rect = (Rectangle2D) this.getPosicaoReal().clone();
        rect.setRect(rect.getX() + rect.getWidth()*.5,
                rect.getY() - rect.getHeight()*.5,
                rect.getWidth(), 
                rect.getHeight());

        novoElemento.setPosicaoReal(rect);
        novoElemento.setMoldura(this.getMoldura());
        novoElemento.setDirecaoReta(this.getDirecaoReta());
        this.getMoldura().getAnaliseAcao().addElemento(novoElemento);
        this.getMoldura().getPanelMolduras().repaint();
    }

    public boolean finalizarElemento() {
        return true;
    }
    
}
