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

import grafix.telas.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.Map;
import java.util.Vector;
import javax.swing.*;

public abstract class ElementoGrafico extends JPanel implements Cloneable {
    private Color cor;
    private Rectangle2D posicaoReal;
    private boolean movendo;
    private Point pontoArrasto;
    private String nomeEixo;
    private MolduraAreaDados moldura = null;
    private int direcaoReta;  // deve virar parametro !!
    protected JPopupMenu popup;
    private Map parametros;
    private boolean persistente = true; 
    private boolean desenhaArrastando = true; 
    
    protected final int MARGEM_CLIQUE = 5;
    
    public ElementoGrafico() {
        configurarElemento();
        criarPopupMenu();
        adicionarEventos();
    }
    
    public ElementoGrafico(Color color) {
        this();
        setCor(color);
    }
    
    abstract protected void configurarElemento();
    abstract protected boolean eSobreALinha(Point2D p);
    abstract public void desenharSelecao(MolduraAreaDados moldura, Graphics g, Point origemDoNovoElemento, Point finalDoNovoElemento);
    abstract public void duplicarElemento();
    abstract public boolean finalizarElemento();
    
    protected void adicionarEventos() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) { descartarEvento(evt); }
            //public void mouseEntered(MouseEvent evt) { descartarEvento(evt); }
            //public void mouseExited(MouseEvent evt) { descartarEvento(evt); }
            @Override
            public void mousePressed(MouseEvent evt) { elemMousePressed(evt); }
            @Override
            public void mouseReleased(MouseEvent evt) { elemMouseReleased(evt); }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent evt) { elemMouseDragged(evt); }
            @Override
            public void mouseMoved(MouseEvent evt) { elemMouseMoved(evt); }
        });
    }
    
    protected void elemMousePressed(java.awt.event.MouseEvent evt) {
        if(eSobreALinha(evt.getPoint())) {
            if (evt.getButton()==MouseEvent.BUTTON3) {
                popup.show(evt.getComponent(),
                        evt.getX(), evt.getY());
            } else {
                this.setMovendo(true);
                this.setPontoArrasto(evt.getPoint());
            }
        } else {
            descartarEvento(evt);
        }
    }
    
    protected void elemMouseReleased(java.awt.event.MouseEvent evt) {
        if(this.isMovendo()) {
            this.setMovendo(false);
            this.atualizarPosicaoReal();
        } else {
            descartarEvento(evt);
        }
    }
    
    protected void elemMouseDragged(java.awt.event.MouseEvent evt) {
        if(this.isMovendo()) {
            this.setLocation(new Point(evt.getX() + this.getX() - (int)this.getPontoArrasto().getX(), evt.getY() + this.getY() - (int)this.getPontoArrasto().getY()));
        } else {
            descartarEvento(evt);
        }
    }
    
    protected void elemMouseMoved(java.awt.event.MouseEvent evt) {
        if(eSobreALinha(evt.getPoint())) {
            if(!this.getCursor().equals(new Cursor(Cursor.HAND_CURSOR))) {
                for (ElementoGrafico elem : moldura.getAnaliseAcao().getElementos()) {
                    elem.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }
        } else {
            if(!this.getCursor().equals(new Cursor(Cursor.DEFAULT_CURSOR))) {
                for (ElementoGrafico elem : moldura.getAnaliseAcao().getElementos()) {
                    elem.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            }
            descartarEvento(evt);
        }
    }
    
    public boolean isMovendo() {
        return movendo;
    }
    
    public void setMovendo(boolean movendo) {
        this.movendo = movendo;
    }
    
    public Point getPontoArrasto() {
        return pontoArrasto;
    }
    
    public void setPontoArrasto(Point pontoArrasto) {
        this.pontoArrasto = pontoArrasto;
    }
    
//    protected void descartarEvento_semRepasseAoElementoAbaixo(final java.awt.event.MouseEvent evt) {
//        MolduraAreaDados moldura = (MolduraAreaDados) this.getParent();
//        if(moldura.getMouseListeners().length==0) {
//            Rectangle2D r = moldura.getAreaData();
//            evt.translatePoint( this.getX() + arred(r.getX()),  this.getY() + arred(r.getY()));
//            moldura.getPanelMolduras().getJanela().getPanelGraficos().dispatchEvent(evt);
//        } else {
//            evt.translatePoint( this.getX(),  this.getY() );
//            moldura.dispatchEvent(evt);
//        }
//    }
    
    protected void descartarEvento(final java.awt.event.MouseEvent evt) {
        Point pontoNaMoldura = new Point(evt.getX() + this.getX(), evt.getY() + this.getY());
        
        Vector<ElementoGrafico> elementos = moldura.getAnaliseAcao().getElementos();
        if(elementos!=null) {
            boolean chegouNoNivelDoElemento = false;
            for (ElementoGrafico elem : elementos) {
                if(elem.getNomeEixo().equals(moldura.getEixo().getNomeEixo())) {
                    if(elem.equals(this)) {
                        chegouNoNivelDoElemento = true;
                    } else {
                        if(chegouNoNivelDoElemento) {
                            if(elem.estaEm(pontoNaMoldura)) {
                                dispacharEventoParaElementoAbaixo(evt, elem);
                                return;
                            }
                        }
                    }
                }
            }
        }
        if(moldura.getMouseListeners().length==0) {
            dispacharEventoParaPanelGraficos(evt, moldura);
        } else {
            dispacharEventoParaMoldura(evt, moldura);
        }
    }
    
    private boolean estaEm(Point pontoNaMoldura) {
        int sobraX = (int)pontoNaMoldura.getX() - this.getX();
        int sobraY = (int)pontoNaMoldura.getY() - this.getY();
        if(sobraX > 0 && sobraX <= this.getWidth() && sobraY > 0 && sobraY <= this.getHeight()) {
            return true;
        }
        return false;
    }
    
    private void dispacharEventoParaElementoAbaixo(final java.awt.event.MouseEvent evt, final ElementoGrafico elemento) {
        evt.translatePoint( this.getX() - elemento.getX() , this.getY() - elemento.getY());
        evt.setSource(elemento);
        elemento.dispatchEvent(evt);
    }
    
    private void dispacharEventoParaMoldura(final java.awt.event.MouseEvent evt, final MolduraAreaDados moldura) {
        evt.translatePoint( this.getX(),  this.getY() );
        moldura.dispatchEvent(evt);
    }
    
    private void dispacharEventoParaPanelGraficos(final java.awt.event.MouseEvent evt, final MolduraAreaDados moldura) {
        Rectangle2D r = moldura.getAreaData();
        evt.translatePoint( this.getX() + arred(r.getX()),  this.getY() + arred(r.getY()));
        PanelGraficos pg = moldura.getPanelMolduras().getJanela().getPanelGraficos();
        evt.setSource(pg);
        pg.dispatchEvent(evt);
    }
    
    public void atualizarPosicaoReal() {
        Point inicioNaMoldura = getLocation();
        Point2D.Double inicioPosicaoReal = (Point2D.Double) getMoldura().converterPontoNaMolduraParaPlot(inicioNaMoldura);
        getPosicaoReal().setRect(inicioPosicaoReal.getX(), inicioPosicaoReal.getY(), getPosicaoReal().getWidth(), getPosicaoReal().getHeight());
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
    
    public void prepararElemento(MolduraAreaDados novaMoldura) {
        if(!isMovendo()) {
            this.setMoldura(novaMoldura);
            posicionarElementoPelaPosicaoReal();
        }
    }
    
    // Método sobrescrito pela CaixaTexto (devido a não ter dimensões absolutas)
    protected void posicionarElementoPelaPosicaoReal() {
        Point2D.Double inicioPosicaoReal = new Point2D.Double(getPosicaoReal().getX(), getPosicaoReal().getY());
        Point2D inicioNaMoldura = getMoldura().converterPontoNoPlotParaMoldura(inicioPosicaoReal);
        int x = arred(inicioNaMoldura.getX());
        int y = arred(inicioNaMoldura.getY());
        
        Point2D.Double fimPosicaoReal = new Point2D.Double(getPosicaoReal().getX() + getPosicaoReal().getWidth(), getPosicaoReal().getY() - getPosicaoReal().getHeight());
        Point2D fimNaMoldura = getMoldura().converterPontoNoPlotParaMoldura(fimPosicaoReal);
        int width = arred(fimNaMoldura.getX() - inicioNaMoldura.getX());
        int height = arred(fimNaMoldura.getY() - inicioNaMoldura.getY());
        
        //Livrar bordas
        x--; y--; height+=2; width+=2;
        
        this.setBounds(x, y, width, height);
    }
    
    public MolduraAreaDados getMoldura() {
        return moldura;
    }
    
    public void setMoldura(MolduraAreaDados moldura) {
        this.moldura = moldura;
    }
    
    protected int arred(double d) {
        return (int)Math.round(d);
    }
    
    public void setPosicaoReal(MolduraAreaDados moldura, Point origemDoNovoElemento, Point finalDoNovoElemento) {
        Rectangle2D posicaoNaMoldura = definirRetanguloAPartirDeDoisVerticesQuaisquer(origemDoNovoElemento, finalDoNovoElemento);
        Point2D pontoInicialReal = moldura.converterPontoNaMolduraParaPlot(new Point((int)posicaoNaMoldura.getX(), (int)posicaoNaMoldura.getY()));
        Point2D pontoFinalReal = moldura.converterPontoNaMolduraParaPlot(getPontoFinal(posicaoNaMoldura));
        this.setPosicaoReal(definirRetanguloPosicaoReal(pontoInicialReal, pontoFinalReal));
    }
    
    static public Rectangle2D definirRetanguloAPartirDeDoisVerticesQuaisquer(Point2D verticeI, Point2D verticeF) {
        double w = verticeF.getX() - verticeI.getX();
        double h = verticeF.getY() - verticeI.getY();
        if(h<=0 && w>=0) { //  Quadr 1
            return new Rectangle2D.Double(verticeI.getX(), verticeI.getY() + h, w, -h);
        } else if(h>=0 && w<=0) { //  Quadr 3
            return new Rectangle2D.Double(verticeI.getX() + w, verticeI.getY(), -w, h);
        } else if(h<=0 && w<=0) { //  Quadr 4
            return new Rectangle2D.Double(verticeI.getX() + w, verticeI.getY() + h, -w, -h);
        } else {
            return new Rectangle2D.Double(verticeI.getX(), verticeI.getY(), w, h);
        }
    }
    
    static public Rectangle2D definirRetanguloPosicaoReal(Point2D verticeI, Point2D verticeF) {
        double w = verticeF.getX() - verticeI.getX();
        double h = - verticeF.getY() + verticeI.getY();
        
        return new Rectangle2D.Double(verticeI.getX(), verticeI.getY(), w, h);
    }
    
    static public Point getPontoFinal(Rectangle2D rect) {
        return new Point((int)(rect.getX() + rect.getWidth()), (int)(rect.getY() + rect.getHeight()));
    }
    
    public int getDirecaoReta() {
        return direcaoReta;
    }
    
    public void setDirecaoReta(int direcaoReta) {
        this.direcaoReta = direcaoReta;
    }
    
    private void criarPopupMenu() {
        popup = new JPopupMenu();
        JMenuItem menuItem;
        menuItem = new JMenuItem("Selecionar Cor");
        menuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selecionarNovaCor();
            }
        });
        popup.add(menuItem);
        
        menuItem = new JMenuItem("Redimensionar");
        menuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redimensionarElemento();
            }
        });
        menuItem.setEnabled(false);
        popup.add(menuItem);
        
        menuItem = new JMenuItem("Duplicar");
        menuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                duplicarElemento();
            }
        });
        popup.add(menuItem);
        
        menuItem = new JMenuItem("Excluir");
        menuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirElemento();
            }
        });
        popup.add(menuItem);
    }
    
    public void excluirElemento() {
        moldura.getPanelMolduras().excluirElementoGrafico(this);
    }
    
    public void redimensionarElemento() {
        //moldura.getPanelMolduras().excluirElementoGrafico(this);
    }
    
    
    public void selecionarNovaCor() {
        Color novaCor = JColorChooser.showDialog(null, "Selecione uma cor", getCor());
        if(novaCor != null) {
            setCor(novaCor);
            repaint();
        }
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

    public boolean isPersistente() {
        return persistente;
    }

    public void setPersistente(boolean persistente) {
        this.persistente = persistente;
    }

    public boolean isDesenhaArrastando() {
        return desenhaArrastando;
    }

    public void setDesenhaArrastando(boolean desenhaArrastando) {
        this.desenhaArrastando = desenhaArrastando;
    }
    
}
