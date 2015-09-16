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

import grafix.telas.MolduraAreaDados;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class ElementoRetangular extends ElementoGrafico {
    
    public ElementoRetangular() {
        super();
    }
    
    public ElementoRetangular(Color cor) {
        super(cor);
    }
    
    public void desenharSelecao(MolduraAreaDados moldura, Graphics g, Point origemDoNovoElemento, Point finalDoNovoElemento) {
        if(finalDoNovoElemento!=null) {
            g.setColor(getCor());
            Rectangle2D rect = ElementoGrafico.definirRetanguloAPartirDeDoisVerticesQuaisquer(origemDoNovoElemento, finalDoNovoElemento);
            g.drawRect(arred(rect.getX()), arred(rect.getY()), arred(rect.getWidth()), arred(rect.getHeight()));
        }
    }

}
