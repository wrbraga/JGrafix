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



package grafix.graficos.eixos;

import grafix.graficos.elementos.MarcaGrafica;
import grafix.graficos.indices.*;
import grafix.principal.*;
import grafix.telas.JanelaGraficos;
import java.awt.*;
import java.util.Vector;
import org.jfree.chart.axis.*;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.*;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.annotations.XYTitleAnnotation;
import org.jfree.ui.*;

public abstract class Eixo {

    protected int tamanho = 1;
    protected Vector<Indice> indices = new Vector<Indice>();
    protected Vector<ResumoIndice> resumosIndices = new Vector<ResumoIndice>();
    private boolean visible = true;
    private String nomeEixo = "Eixo Anônimo";
    private boolean incluiZero = false;
    private boolean autoEscala = true;
    private double escalaMax = 100;
    private double escalaMin = 0;
    private boolean legenda = true;
    private boolean gradeHorizontal = true;
    private Color corGradeHorizontal = Color.lightGray;

    public XYPlot getPlot(JanelaGraficos janela) {
        if (isVisible()) {
            XYPlot plot = new XYPlot();
            configurarPlot(plot);
            incluirIndices(plot, janela);
            incluirMarcas(plot, janela);
            completarPlot(plot, janela);
            return plot;
        }
        return null;
    }

    abstract protected void completarPlot(final XYPlot plot, final JanelaGraficos janela);

    protected void incluirIndices(final XYPlot plot, final JanelaGraficos janela) {
        for (int i = 0; i < indices.size(); i++) {
            indices.get(i).plotar(plot, janela, i);
        }
    }

    protected void incluirMarcas(final XYPlot plot, final JanelaGraficos janela) {
        try {
            Vector<MarcaGrafica> marcas = janela.getAcao().getAnalise().getMarcas();
            for (MarcaGrafica m : marcas) {
                if (m.isRangeMarker()) {
                    if (getNomeEixo().equals(m.getNomeEixo())) {
                        plot.addRangeMarker(gerarRangeMarker(m));
                    }
                } else {
                    plot.addDomainMarker(gerarIntervalMarker(m, janela));
                }
            }
            incluirMarcaIntraday(plot, janela);
        } catch (Exception e) {
            AjudaGrafix.exibirMensagem(AjudaGrafix.ERRO_MARCAS);
            janela.getAcao().getAnalise().apagarMarcasAnalise();
        }
    }

    protected void incluirMarcaIntraday(final XYPlot plot, final JanelaGraficos janela) {
        if (Controle.getConfiguracoesVolateis().isIntraday()) {
            MarcaGrafica m = new MarcaGrafica("", "INTRADAY",
                    janela.getAcao().getRegistro(janela.getAcao().getNumeroRegistros() - 1).getData(), Color.BLUE);
            if (m.isRangeMarker()) {
                if (getNomeEixo().equals(m.getNomeEixo())) {
                    plot.addRangeMarker(gerarRangeMarker(m));
                }
            } else {
                plot.addDomainMarker(gerarIntervalMarker(m, janela));
            }
        }
    }

    protected void configurarPlot(final XYPlot plot) {
        NumberAxis nAxis = definirEixoVertical();
        plot.setRangeAxis(nAxis);
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinesVisible(this.isGradeHorizontal());
        plot.setRangeGridlinePaint(this.getCorGradeHorizontal());
        plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
        incluirLegenda(plot);
        incluirEixoX(plot);
        configurarEscalaVertical(plot);
    }

    // Sobrescrito pelos filhos //
    protected NumberAxis definirEixoVertical() {
        return new NumberAxis();
    }

    private void incluirLegenda(final XYPlot plot) {
        if (isLegenda()) {
            LegendTitle lt = new LegendTitle(plot);
            lt.setItemFont(new Font("Dialog", Font.PLAIN, 11));
            lt.setBackgroundPaint(new Color(255, 255, 255, 100));
            lt.setBorder(new BlockBorder(new Color(180, 180, 180)));
            lt.setPosition(RectangleEdge.TOP);
            XYTitleAnnotation ta = new XYTitleAnnotation(0.01, 0.98, lt,
                    RectangleAnchor.TOP_LEFT);
            ta.setMaxWidth(0.48);
            plot.addAnnotation(ta);
        }
    }

    private void incluirEixoX(XYPlot plot) {
        if (Controle.getConfiguracoesUsuario().isExibeEixoX()) {
            ValueMarker mx = new ValueMarker(0);
            mx.setPaint(Color.BLACK);
            mx.setStroke(new BasicStroke(.9f));
            mx.setAlpha(1.0f);
            plot.addRangeMarker(mx);
        }
    }

    public void configurarEscalaVertical(XYPlot plot) {
        NumberAxis nAxis = (NumberAxis) plot.getRangeAxis();
        if (this.isAutoEscala()) {
            if (this.isIncluiZero()) {
                nAxis.setAutoRange(true);
                nAxis.setAutoRangeIncludesZero(true);
            } else {
                nAxis.setAutoRange(true);
                nAxis.setAutoRangeIncludesZero(false);
            }
        } else {
            if (this.isIncluiZero()) {
                nAxis.setRange(Math.min(getEscalaMin(), 0), Math.max(0, getEscalaMax()));
            } else {
                nAxis.setRange(getEscalaMin(), getEscalaMax());
            }
        }
    }

    protected IntervalMarker gerarIntervalMarker(final MarcaGrafica m, JanelaGraficos janela) {
        try {
            IntervalMarker im = new IntervalMarker(m.getInicioNoGrafico(janela).getFirstMillisecond(), m.getFimNoGrafico(janela).getFirstMillisecond());
            im.setLabelOffsetType(LengthAdjustmentType.EXPAND);
            im.setPaint(m.getCor());
            im.setStroke(new BasicStroke(1.4f));
            if (!m.getFim().equals(m.getInicio())) {
                im.setAlpha(.2f);
                im.setLabelFont(new Font("SansSerif", Font.BOLD, 11));
            } else {
                im.setAlpha(.9f);
                im.setLabelFont(new Font("SansSerif", Font.PLAIN, 11));
            }
            im.setLabel(m.getTag());
            im.setLabelPaint(m.getCor());
            im.setLabelAnchor(RectangleAnchor.TOP_LEFT);
            im.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
            return im;
        } catch (Exception e) {
            return null;
        }
    }

    protected ValueMarker gerarRangeMarker(final MarcaGrafica m) {
        ValueMarker vm = new ValueMarker(m.getValor());
        vm.setLabelOffsetType(LengthAdjustmentType.EXPAND);
        vm.setPaint(m.getCor());
        vm.setStroke(new BasicStroke(.5f));
        vm.setAlpha(0.9f);
        vm.setLabel(m.getTag());
        vm.setLabelFont(new Font("SansSerif", Font.PLAIN, 11));
        vm.setLabelPaint(m.getCor());
        vm.setLabelAnchor(RectangleAnchor.TOP_LEFT);
        vm.setLabelTextAnchor(TextAnchor.BOTTOM_LEFT);
        return vm;
    }

    public void prepararPersistenciaEmXML() {
        resumosIndices = new Vector<ResumoIndice>();
        for (Indice indice : indices) {
            resumosIndices.add(new ResumoIndice(indice));
        }
        indices = null;
    }

    public void restaurarAposPersistenciaEmXML() {
        indices = new Vector<Indice>();
        for (ResumoIndice resumo : resumosIndices) {
            indices.add(ConstrutorDeIndices.novoIndice(resumo));
        }
        resumosIndices = null;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public void addIndice(Indice ind) {
        indices.add(ind);
    }

    public void excluirIndice(Indice ind) {
        indices.remove(ind);
    }

    public Vector<Indice> getIndices() {
        return indices;
    }

    public void setIndices(Vector indices) {
        this.indices = indices;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getNomeEixo() {
        return nomeEixo;
    }

    public void setNomeEixo(String nomeEixo) {
        this.nomeEixo = nomeEixo;
    }

    @Override
    public String toString() {
        return nomeEixo;
    }

    public boolean isIncluiZero() {
        return incluiZero;
    }

    public void setIncluiZero(boolean incluiZero) {
        this.incluiZero = incluiZero;
    }

    public boolean isAutoEscala() {
        return autoEscala;
    }

    public void setAutoEscala(boolean autoEscala) {
        this.autoEscala = autoEscala;
    }

    public double getEscalaMax() {
        return escalaMax;
    }

    public void setEscalaMax(double escalaMax) {
        this.escalaMax = escalaMax;
    }

    public double getEscalaMin() {
        return escalaMin;
    }

    public void setEscalaMin(double escalaMin) {
        this.escalaMin = escalaMin;
    }

    public boolean isLegenda() {
        return legenda;
    }

    public void setLegenda(boolean legenda) {
        this.legenda = legenda;
    }

    public boolean isGradeHorizontal() {
        return gradeHorizontal;
    }

    public void setGradeHorizontal(boolean gradeHorizontal) {
        this.gradeHorizontal = gradeHorizontal;
    }

    public Color getCorGradeHorizontal() {
        return corGradeHorizontal;
    }

    public void setCorGradeHorizontal(Color corGradeHorizontal) {
        this.corGradeHorizontal = corGradeHorizontal;
    }
}
