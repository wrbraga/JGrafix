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

import java.awt.Color;


public class ResumoIndice {
    
    private int id;
    
    private String abrevIndice;
    private String nomeIndice;
    private Color cor;
    private int espessura;
    private boolean tracoContinuo = true;
    private String fileLua;
    
    private String nomeFlag = null;
    private boolean flag;
    private String nomeParam1 = null;
    private int param1;
    private String nomeParam2 = null;
    private int param2;
    private String nomeParam3 = null;
    private int param3;

    public ResumoIndice(Indice indice) {
        this.setId(indice.getId());
        this.setAbrevIndice(indice.getAbrevIndice());
        this.setCor(indice.getCor());
        this.setEspessura(indice.getEspessura());
        this.setFileLua(indice.getFileLua());
        this.setFlag(indice.getFlag());
        this.setNomeFlag(indice.getNomeFlag());
        this.setNomeIndice(indice.getNomeIndice());
        this.setNomeParam1(indice.getNomeParam1());
        this.setNomeParam2(indice.getNomeParam2());
        this.setNomeParam3(indice.getNomeParam3());
        this.setParam1(indice.getParam1());
        this.setParam2(indice.getParam2());
        this.setParam3(indice.getParam3());
        this.setTracoContinuo(indice.isTracoContinuo());
    }

    @Override
    public String toString() {
        return "ResumoIndice - " + this.getAbrevIndice();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAbrevIndice() {
        return abrevIndice;
    }

    public void setAbrevIndice(String abrevIndice) {
        this.abrevIndice = abrevIndice;
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

    public String getNomeFlag() {
        return nomeFlag;
    }

    public void setNomeFlag(String nomeFlag) {
        this.nomeFlag = nomeFlag;
    }

    public boolean isFlag() {
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

    public int getEspessura() {
        return espessura;
    }

    public void setEspessura(int espessura) {
        this.espessura = espessura;
    }
    
}
