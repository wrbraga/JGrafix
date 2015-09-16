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



package grafix.graficos.indices;

import grafix.graficos.IndiceToolTipGenerator;
import grafix.principal.Acao;
import grafix.telas.JanelaGraficos;
import java.awt.*;
import javax.swing.JOptionPane;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.*;
import org.jfree.data.xy.XYDataset;
import org.jfree.util.ShapeUtilities;

public abstract class Indice {
    
    private int id;
    
    protected String abrevIndice;
    private String nomeIndice;
    private Color cor;
    private int espessura;
    private boolean tracoContinuo = true;
    private ValoresIndice valores;
    private String fileLua;
    
    private String nomeFlag = null;
    private boolean flag;
    private String nomeParam1 = null;
    private int param1;
    private String nomeParam2 = null;
    private int param2;
    private String nomeParam3 = null;
    private int param3;
    
    public Indice(int id, String abrev, Color cor, int espessura, boolean f, int p1, int p2, int p3) {
        this.setId(id);
        if(cor!=null) {
            this.cor = cor;
        } else {
            this.cor = getCorInicial();
        }
        this.setEspessura(espessura);
        this.setAbrevIndice(abrev);
        this.configurarIndice();
    }
    
    abstract protected void calcularValoresIndice(Acao acao);
    
    abstract protected void configurarIndice();
    
    @Override
    public String toString() {
        return getAbrevIndice() + "  -  " + this.getNomeIndice();
    }
    
    public ResumoIndice gerarResumo() {
        ResumoIndice resultado = new ResumoIndice(this);
        return resultado;
    }
    
    protected XYDataset getDataSet(JanelaGraficos janela) {
        if(valoresEstaoDesatualizados(janela)) {
            calcularValoresIndice(janela.getAcao());
        }
        if(getValores() == null) {
            setValores(new ValoresIndice(this, janela.getAcao(), new double[janela.getAcao().getNumeroRegistros()]));
            JOptionPane.showMessageDialog(null, "Índice " + this.getNomeIndice() + " não retorna dados!");
        }
        return getValores().getDataSet(janela);
    }
    
    public void plotar(final XYPlot plot, final JanelaGraficos janela, final int contador) {
        XYLineAndShapeRenderer indicesRenderer = new XYLineAndShapeRenderer();
        indicesRenderer.setSeriesLinesVisible(0, isTracoContinuo());
        indicesRenderer.setSeriesShapesVisible(0, !isTracoContinuo());
        indicesRenderer.setSeriesShape(0, ShapeUtilities.createDiamond(1.5f));
        indicesRenderer.setStroke(new BasicStroke(getEspessura() * 0.5f));
        indicesRenderer.setToolTipGenerator(new IndiceToolTipGenerator(this));
        indicesRenderer.setPaint(getCor());
        plot.setRenderer(contador, indicesRenderer);
        plot.setDataset(contador, getDataSet(janela));
    }
    
    protected Color getCorInicial() {
        return new Color((float)Math.pow(Math.random(),2), (float)Math.pow(Math.random(),2), (float)Math.pow(Math.random(),2));
        //return Color.BLACK;
    }
    
    public ValoresIndice getValores() {
        return valores;
    }
    
    public void setValores(ValoresIndice valores) {
        this.valores = valores;
    }
    
    private boolean valoresEstaoDesatualizados(JanelaGraficos janela) {
        if(getValores() == null) {
            return true;
        }
        return !getValores().isAtualizado(janela);
    }
    
    public String getTagIndice() {
        if(param2!=0) {
            return getAbrevIndice() + "-" + param1 + "/" + param2;
        } else if(param1!=0) {
            return getAbrevIndice() + "-" + param1;
        } else {
            return getAbrevIndice();
        }
    }
    
    protected static double[] dadosZerados(int tam) {
        double[] resultado = new double[tam];
        for (int i = 0; i < resultado.length; i++) {
            resultado[i] = ValoresIndice.SEM_VALOR;
        }
        return resultado;
    }
    
    public String getNomeIndice() {
        return nomeIndice;
    }
    
    public void setNomeIndice(String nomeIndice) {
        this.nomeIndice = nomeIndice;
    }
    
    public Color getCor() {
        return cor;
    }
    
    public void setCor(Color cor) {
        this.cor = cor;
    }
    
    public boolean isTracoContinuo() {
        return tracoContinuo;
    }
    
    public void setTracoContinuo(boolean tracoContinuo) {
        this.tracoContinuo = tracoContinuo;
    }
    
    public String getFileLua() {
        return fileLua;
    }
    
    public void setFileLua(String fileLua) {
        this.fileLua = fileLua;
    }
    
//    abstract public String definirAbrevIndice();
    
    public String getAbrevIndice() {
//        if(abrevIndice == null) {
//            abrevIndice = definirAbrevIndice();
//        }
        return abrevIndice;
    }
    
    public void setAbrevIndice(String abrevIndice) {
        this.abrevIndice = abrevIndice;
    }
    
    // PARAMETROS --------------------------------------------------------------------------
    
    protected void criarFlag(String nome, boolean f) {
        setNomeFlag(nome);
        setFlag(f);
    }
    
    protected void criarParam1(String nome, int p) {
        setNomeParam1(nome);
        setParam1(p);
    }
    
    protected void criarParam2(String nome, int p) {
        setNomeParam2(nome);
        setParam2(p);
    }
    
    protected void criarParam3(String nome, int p) {
        setNomeParam3(nome);
        setParam3(p);
    }
    
    public String getNomeFlag() {
        return nomeFlag;
    }
    
    public void setNomeFlag(String nomeFlag) {
        this.nomeFlag = nomeFlag;
    }
    
    public boolean isFlag() {
        return flag;
    }
    
    public boolean getFlag() {
        return flag;
    }
    
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    
    public String getNomeParam1() {
        return nomeParam1;
    }
    
    public void setNomeParam1(String nomeParam1) {
        this.nomeParam1 = nomeParam1;
    }
    
    public int getParam1() {
        return param1;
    }
    
    public void setParam1(int param1) {
        this.param1 = param1;
    }
    
    public String getNomeParam2() {
        return nomeParam2;
    }
    
    public void setNomeParam2(String nomeParam2) {
        this.nomeParam2 = nomeParam2;
    }
    
    public int getParam2() {
        return param2;
    }
    
    public void setParam2(int param2) {
        this.param2 = param2;
    }
    
    public String getNomeParam3() {
        return nomeParam3;
    }
    
    public void setNomeParam3(String nomeParam3) {
        this.nomeParam3 = nomeParam3;
    }
    
    public int getParam3() {
        return param3;
    }
    
    public void setParam3(int param3) {
        this.param3 = param3;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEspessura() {
        return espessura;
    }

    public void setEspessura(int espessura) {
        this.espessura = espessura;
    }
    
}
