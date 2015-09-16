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



package grafix.telas;

import grafix.graficos.eixos.Eixo;
import grafix.graficos.elementos.*;
import grafix.principal.Controle;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.Vector;
import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.*;

public class MolduraAreaDados extends JPanel {

    private PanelMolduras panelMolduras;
    private Eixo eixo;
    private AnaliseAcao analiseAcao;
    private int nPlot;
    private Point origemDoNovoElemento = null;
    private Point finalDoNovoElemento = null;
    private MouseListener mListener = new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent evt) {
            descartarEvento(evt);
        }

        @Override
        public void mouseExited(MouseEvent evt) {
            descartarEvento(evt);
        }

        @Override
        public void mousePressed(MouseEvent evt) {
            molduraMousePressed(evt);
        }

        @Override
        public void mouseReleased(MouseEvent evt) {
            molduraMouseReleased(evt);
        }
    };
    private MouseMotionListener mmListener = new MouseMotionAdapter() {

        @Override
        public void mouseDragged(MouseEvent evt) {
            molduraMouseDragged(evt);
        }

        @Override
        public void mouseMoved(MouseEvent evt) {
            descartarEvento(evt);
        }
    };

    public MolduraAreaDados(PanelMolduras painel, Eixo eixo, int nPlot) {
        this.panelMolduras = painel;
        this.setEixo(eixo);
        this.setNPlot(nPlot);
        this.setAnaliseAcao(painel.getJanela().getAcao().getAnalise());
        configuraMoldura();
    }

    private void configuraMoldura() {
        this.setLayout(null);
        this.setOpaque(false);
    }

    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        adicionarElementosAnaliseAcao();
        desenharSelecaoNovoElemento(g);
    }

    private void desenharSelecaoNovoElemento(final java.awt.Graphics g) {
        ElementoGrafico novoElemento = panelMolduras.getJanela().getTela().getNovoElemento();
        if (novoElemento != null) {
            novoElemento.desenharSelecao(this, g, origemDoNovoElemento, finalDoNovoElemento);
        }
    }

    private void adicionarElementosAnaliseAcao() {
        this.removeAll();
        Vector<ElementoGrafico> elementos = getAnaliseAcao().getElementos();
        if (elementos != null) {
            for (ElementoGrafico elem : elementos) {
                if (elem.getNomeEixo().equals(eixo.getNomeEixo())) {
                    elem.prepararElemento(this);
                    this.add(elem);
                }
            }
        }
    }

    public void setPosicao(Rectangle2D rect) {
        this.setLocation((int) rect.getX(), (int) rect.getY());
        this.setSize((int) rect.getWidth(), (int) rect.getHeight());
    }

/////  Talvez dê pra otimizar esta busca ao plot criando uma variável local
    private XYPlot getPlot() {
        JFreeChart jfchart = getPanelMolduras().getJanela().getPanelGraficos().getChart();
        CombinedDomainXYPlot cplot = (CombinedDomainXYPlot) jfchart.getXYPlot();
        XYPlot plot = (XYPlot) cplot.getSubplots().get(getNPlot());
        return plot;
    }

    public Point2D converterPontoNaMolduraParaPlot(Point2D pontoMoldura) {
        if (Controle.getConfiguracoesVolateis().isLog()) {
            return converterPontoNaMolduraParaPlot_EixoLog(pontoMoldura);
        } else {
            return converterPontoNaMolduraParaPlot_EixoLinear(pontoMoldura);
        }
    }

    private Point2D converterPontoNaMolduraParaPlot_EixoLinear(final Point2D pontoMoldura) {
        XYPlot plot = getPlot();
        ValueAxis vAxis = plot.getRangeAxis();
        ValueAxis dAxis = plot.getDomainAxis();
        double fracaoX = pontoMoldura.getX() / this.getWidth();
        double fracaoY = 1 - (pontoMoldura.getY() / this.getHeight());
        double dX = dAxis.getUpperBound() - dAxis.getLowerBound();
        double dY = vAxis.getUpperBound() - vAxis.getLowerBound();
        return new Point2D.Double(dAxis.getLowerBound() + dX * fracaoX, vAxis.getLowerBound() + dY * fracaoY);
    }

    private Point2D converterPontoNaMolduraParaPlot_EixoLog(final Point2D pontoMoldura) {
        XYPlot plot = getPlot();
        ValueAxis vAxis = plot.getRangeAxis();
        ValueAxis dAxis = plot.getDomainAxis();
        double fracaoX = pontoMoldura.getX() / this.getWidth();
        double fracaoYlog = 1 - (pontoMoldura.getY() / this.getHeight());
        double dX = dAxis.getUpperBound() - dAxis.getLowerBound();
        double dYlog = Math.log10(vAxis.getUpperBound()) - Math.log10(vAxis.getLowerBound());
        double ylog = Math.log10(vAxis.getLowerBound()) + fracaoYlog * dYlog;
        return new Point2D.Double(dAxis.getLowerBound() + dX * fracaoX, Math.pow(10, ylog));
    }

    public Point2D converterPontoNoPlotParaMoldura(Point2D pontoPlot) {
        if (Controle.getConfiguracoesVolateis().isLog()) {
            return converterPontoNoPlotParaMoldura_EixoLog(pontoPlot);
        } else {
            return converterPontoNoPlotParaMoldura_EixoLinear(pontoPlot);
        }
    }

    private Point2D converterPontoNoPlotParaMoldura_EixoLinear(final Point2D pontoPlot) {
        XYPlot plot = getPlot();
        ValueAxis vAxis = plot.getRangeAxis();
        ValueAxis dAxis = plot.getDomainAxis();
        double dX = dAxis.getUpperBound() - dAxis.getLowerBound();
        double dY = vAxis.getUpperBound() - vAxis.getLowerBound();
        double proporcaoX = this.getWidth() / dX;
        double proporcaoY = this.getHeight() / dY;
        double fracaoX = pontoPlot.getX() - dAxis.getLowerBound();
        double fracaoY = pontoPlot.getY() - vAxis.getLowerBound();
        return new Point2D.Double(fracaoX * proporcaoX, this.getHeight() - (fracaoY * proporcaoY));
    }

    private Point2D converterPontoNoPlotParaMoldura_EixoLog(final Point2D pontoPlot) {
        XYPlot plot = getPlot();
        ValueAxis vAxis = plot.getRangeAxis();
        ValueAxis dAxis = plot.getDomainAxis();
        double dX = dAxis.getUpperBound() - dAxis.getLowerBound();
        double dYlog = Math.log10(vAxis.getUpperBound()) - Math.log10(vAxis.getLowerBound());
        double proporcaoX = this.getWidth() / dX;
        double fracaoX = pontoPlot.getX() - dAxis.getLowerBound();
        double fracaoYlog = Math.log10(pontoPlot.getY()) - Math.log10(vAxis.getLowerBound());
        double coefYlog = fracaoYlog / dYlog;
        return new Point2D.Double(fracaoX * proporcaoX, this.getHeight() - (coefYlog * this.getHeight()));
    }

    public PanelMolduras getPanelMolduras() {
        return panelMolduras;
    }

    public AnaliseAcao getAnaliseAcao() {
        return analiseAcao;
    }

    public void setAnaliseAcao(AnaliseAcao analiseAcao) {
        this.analiseAcao = analiseAcao;
    }

    public void adicionarEventos() {
        this.addMouseListener(mListener);
        this.addMouseMotionListener(mmListener);
    }

    public void removerEventos() {
        this.removeMouseListener(mListener);
        this.removeMouseMotionListener(mmListener);
    }

    private void molduraMousePressed(java.awt.event.MouseEvent evt) {
        ElementoGrafico novoElemento = panelMolduras.getJanela().getTela().getNovoElemento();
        if (novoElemento != null) {
            origemDoNovoElemento = evt.getPoint();
            if(!novoElemento.isDesenhaArrastando()) {
                finalDoNovoElemento = evt.getPoint();
                molduraMouseReleased(evt);
            }
        } else {
            descartarEvento(evt);
        }
    }

    private void molduraMouseDragged(java.awt.event.MouseEvent evt) {
        //Controle.getTela().getJanelaAtiva().getPanelGraficos().setIgnoreRepaint(true);
        if (origemDoNovoElemento != null) {
            finalDoNovoElemento = evt.getPoint();
            repaint();
        } else {
            descartarEvento(evt);
        }
    }

    private void molduraMouseReleased(java.awt.event.MouseEvent evt) {
        if (origemDoNovoElemento != null) {
            ElementoGrafico novoElemento = panelMolduras.getJanela().getTela().getNovoElemento();
            // Inicia o novo elemento
            if (novoElemento.isPersistente()) {
                if (origemDoNovoElemento != null && finalDoNovoElemento != null) {
                    novoElemento.setNomeEixo(eixo.getNomeEixo());
                    novoElemento.setPosicaoReal(this, origemDoNovoElemento, finalDoNovoElemento);
                    if(novoElemento.finalizarElemento()) {
                        panelMolduras.getJanela().getAcao().getAnalise().addElemento(novoElemento);
                    } 
                }
            }
            // Encerra inserção do elemento
            origemDoNovoElemento = null;
            finalDoNovoElemento = null;
            Controle.getTela().finalizarSelecaoNovoElemento(novoElemento.getClass().getSimpleName());
            getPanelMolduras().repaint();
        } else {
            descartarEvento(evt);
        }
    }

    protected void descartarEvento(final java.awt.event.MouseEvent evt) {
        Rectangle2D areaDados = getAreaData();
        evt.translatePoint(arred(areaDados.getX()), arred(areaDados.getY()));
//        this.getPanelMolduras().dispatchEvent(evt);
        Controle.getJanelaAtiva().getPanelGraficos().dispatchEvent(evt); // Em teste
    }

    protected void gerarStatus(final java.awt.event.MouseEvent evt) {
        System.out.println(evt.getX());
    //descartarEvento(evt);
    }

    private int arred(double d) {
        return (int) Math.round(d);
    }

    public Rectangle2D getAreaData() {
        return getPanelMolduras().getJanela().getPanelGraficos().getAreaData(this.getNPlot());
    }

    public Eixo getEixo() {
        return eixo;
    }

    public void setEixo(Eixo eixo) {
        this.eixo = eixo;
    }

    public int getNPlot() {
        return nPlot;
    }

    public void setNPlot(int nPlot) {
        this.nPlot = nPlot;
    }
}


