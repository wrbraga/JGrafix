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

import grafix.principal.Controle;
import grafix.telas.MolduraAreaDados;
import grafix.telas.secundarias.DialogFibonacci;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.TreeMap;

public class RetasFibonacci extends ElementoRetangular {

    final private int TAMANHO_FONTE = 9;
    final public double PERCENTUAL_DEFAULT = .382;

    public RetasFibonacci() {
        super();
    }

    public RetasFibonacci(Color cor) {
        super(cor);
    }

    protected void configurarElemento() {
        setOpaque(false);

    }

    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        double percentual;
        try {
            percentual = new Double(getParametros().get("percentual").toString()).doubleValue();
        } catch (Exception e) {
            percentual = PERCENTUAL_DEFAULT;
        }
        g2.setColor(this.getCor());
        g2.drawLine(1, 1, getWidth() - 2, 1);
        g2.drawLine(1, arred(getHeight() * percentual), getWidth() - 2, arred(getHeight() * percentual));
        g2.drawLine(1, arred(getHeight() * (1 - percentual)), getWidth() - 2, arred(getHeight() * (1 - percentual)));
        g2.drawLine(1, getHeight() - 2, getWidth() - 2, getHeight() - 2);
        float[] tracoPonto = {5f, 3.5f, 1f, 3.5f};
        Stroke tracejada = new BasicStroke(1.0f,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER,
                10.0f, tracoPonto, 0.0f);
        g2.setStroke(tracejada);
        g2.drawLine(1, arred(getHeight() * .5), getWidth() - 2, arred(getHeight() * .5));
        g2.setFont(new Font(Font.DIALOG, Font.PLAIN, TAMANHO_FONTE));
        g2.drawString("100%", 0, 10);
        g2.drawString((percentual * 100) + "%", 0, arred(getHeight() * (1 - percentual)) + 10);
        g2.drawString("50%", 0, arred(getHeight() * .5) + 10);
        g2.drawString(((1 - percentual) * 100) + "%", 0, arred(getHeight() * percentual) + 10);
        g2.drawString("0%", 0, getHeight() - 3);
    }

    @Override
    public void desenharSelecao(MolduraAreaDados moldura, Graphics g, Point origemDoNovoElemento, Point finalDoNovoElemento) {
        if (finalDoNovoElemento != null) {
            g.setColor(getCor());
            Rectangle2D rect = ElementoGrafico.definirRetanguloAPartirDeDoisVerticesQuaisquer(origemDoNovoElemento, finalDoNovoElemento);
            g.drawLine(arred(rect.getX()), arred(rect.getY()), arred(rect.getWidth() + rect.getX()), arred(rect.getY()));
            g.drawLine(arred(rect.getX()), arred(rect.getHeight() + rect.getY()), arred(rect.getWidth() + rect.getX()), arred(rect.getHeight() + rect.getY()));
            g.drawLine(arred(rect.getX()), arred(rect.getHeight() * PERCENTUAL_DEFAULT + rect.getY()), arred(rect.getWidth() + rect.getX()), arred(rect.getHeight() * PERCENTUAL_DEFAULT + rect.getY()));
            g.drawLine(arred(rect.getX()), arred(rect.getHeight() * (1 - PERCENTUAL_DEFAULT) + rect.getY()), arred(rect.getWidth() + rect.getX()), arred(rect.getHeight() * (1 - PERCENTUAL_DEFAULT) + rect.getY()));
            g.drawLine(arred(rect.getX()), arred(rect.getHeight() * .5 + rect.getY()), arred(rect.getWidth() + rect.getX()), arred(rect.getHeight() * .5 + rect.getY()));
        }
    }

    protected boolean eSobreALinha(Point2D p) {
        if (p.getX() <= MARGEM_CLIQUE ||
                p.getX() >= (getWidth() - MARGEM_CLIQUE) ||
                p.getY() <= MARGEM_CLIQUE ||
                p.getY() >= (getHeight() - MARGEM_CLIQUE)) {
            return true;
        }
        return false;
    }

    public void duplicarElemento() {
        RetasFibonacci novoElemento = new RetasFibonacci(this.getCor());
        novoElemento.setNomeEixo(this.getNomeEixo());
        Rectangle2D rect = (Rectangle2D) this.getPosicaoReal().clone();
        rect.setRect(rect.getX() + rect.getWidth() * .5,
                rect.getY() - rect.getHeight() * .5,
                rect.getWidth(),
                rect.getHeight());

        novoElemento.setPosicaoReal(rect);
        novoElemento.setMoldura(this.getMoldura());
        novoElemento.setParametros(this.getParametros());
        this.getMoldura().getAnaliseAcao().addElemento(novoElemento);
        this.getMoldura().getPanelMolduras().repaint();
    }

    public boolean finalizarElemento() {
        setParametros(new TreeMap());
        new DialogFibonacci(Controle.getTela(), true, this).setVisible(true);
        // **  validar resultado do dialog e retornar false para cancelar elemento **
        return true;
    }

    public void setPercentual(Double percent) {
        getParametros().put("percentual", percent);
    }

    public Double getPercentual() {
        try {
            Double d = (Double) getParametros().get("percentual");
            if (d != null) {
                return d;
            }
        } catch (Exception e) {
        }
        return new Double(PERCENTUAL_DEFAULT * 100);
    }
}
