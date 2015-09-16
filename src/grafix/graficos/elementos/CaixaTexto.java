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
import java.awt.*;
import java.awt.geom.*;
import java.util.TreeMap;

public class CaixaTexto extends ElementoRetangular {

    final private int TAMANHO_FONTE = 11;
    final private int ALTURA_CAIXA_TEXTO = 16;
    final private int LARGURA_LETRA = 7; // Achado por tentativa
    final private int MARGEM = 3; 

    public CaixaTexto() {
        super();
    }

    public CaixaTexto(Color cor) {
        super(cor);
    }

    protected void configurarElemento() {
        setOpaque(false);
        setDesenhaArrastando(false);
    }

    @Override
    public void paintComponent(java.awt.Graphics g) {
        String texto = getParametros().get("texto") + "";
        definirTamanho(this.getX(), this.getY());
        super.paintComponent(g);
        g.setColor(this.getCor());
        g.drawRect(1,1,getWidth()-2, getHeight()-2);
        g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, TAMANHO_FONTE));
        g.drawString(texto, MARGEM, TAMANHO_FONTE);
    }

    private void definirTamanho(int x, int y) {
        String texto = getParametros().get("texto") + "";
        this.setBounds(x, y, LARGURA_LETRA * texto.length() + MARGEM*2, ALTURA_CAIXA_TEXTO);
    }

    protected boolean eSobreALinha(Point2D p) {
        return true;
    }

    public void duplicarElemento() {
        CaixaTexto novoElemento = new CaixaTexto(this.getCor());
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
        return definirTexto();
    }

    public boolean definirTexto() {
        setParametros(new TreeMap());
        String texto = Controle.exibirInputBox("Qual o conteúdo da Caixa de texto?");
        if ("".equals(texto) || texto == null) {
            return false;
        }
        getParametros().put("texto", texto);
        return true;
    }
    
    // Método sobrescrito pela CaixaTexto (devido a não ter dimensões absolutas)
    @Override
    protected void posicionarElementoPelaPosicaoReal() {
        try {
            Point2D.Double inicioPosicaoReal = new Point2D.Double(getPosicaoReal().getX(), getPosicaoReal().getY());
            Point2D inicioNaMoldura = getMoldura().converterPontoNoPlotParaMoldura(inicioPosicaoReal);
            int x = arred(inicioNaMoldura.getX()) - 1;
            int y = arred(inicioNaMoldura.getY()) - 1;
            definirTamanho(x, y);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
