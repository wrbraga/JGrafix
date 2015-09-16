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



package grafix.principal;

import grafix.auxiliar.FormatadorPadrao;
import org.jfree.data.time.Day;

public class RegistroDiario {

    private Day data;
    private Day dataCorrida;
    private String hora;
    private double open;
    private double low;
    private double high;
    private double close;
    private double numNegocios;
    private double volumeQuant;
    private double volumeDinheiro;
    private String tooltip = null;
//    public String dataString;
    private String especi;
    private char indcar;
    private double oscila;
    private char sinosc;

    public RegistroDiario() {
        this.especi = new String();
    }

    public RegistroDiario(Day dia) {
        this.especi = new String();
        this.data = dia;
    }

    @Override
    public String toString() {
        return "Data: " + data + "  Abert: " + open + "  Fech: " + close + "  Mín: " + low + "  Máx: " + high;
    }

    public RegistroDiario(Day data, Day dataCorrida, double o, double l, double h, double c, double a, double b, double volume) {
        this.setData(data);
        this.dataCorrida = dataCorrida;
        this.open = o;
        this.low = l;
        this.high = h;
        this.close = c;
        this.numNegocios = a;
        this.volumeQuant = b;
        this.volumeDinheiro = volume;
    //gerarTooltip();
    }

    public Day getData() {
        return data;
    }

    public Day getData(boolean exibeSomenteDiasUteis) {
        return exibeSomenteDiasUteis ? dataCorrida : data;
    }

    public void setData(Day data) {
        this.data = data;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double valorO) {
        this.open = valorO;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double valorL) {
        this.low = valorL;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double valorH) {
        this.high = valorH;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double valorC) {
        this.close = valorC;
    }

    public double getNumNegocios() {
        return numNegocios;
    }

    public void setNumNegocios(double negoc) {
        this.numNegocios = negoc;
    }

    public double getVolumeQuant() {
        return volumeQuant;
    }

    public void setVolumeQuant(double b) {
        this.volumeQuant = b;
    }

    public double getVolumeDinheiro() {
        return volumeDinheiro;
    }

    public void setVolumeDinheiro(double volume) {
        this.volumeDinheiro = volume;
    }

    public Day getDataCorrida() {
        return dataCorrida;
    }

    public void setDataCorrida(Day dataCorrida) {
        this.dataCorrida = dataCorrida;
    }

    public String getTooltip() {
//        if (tooltip == null) {
//            gerarTooltip();
//        }
        return tooltip;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    public void gerarTooltip() {
        try {
            FormatadorPadrao formatador = Controle.getFormatador();

            StringBuffer res = new StringBuffer("<html><body bgcolor='#ffffe1'><table align=center><tr><td><b><u>");
            res.append(formatador.getDataFormatter().format(getData().getStart()));
            res.append("</u></b></td></tr></table> <table><tr><td><b>  Aber </b></td><td>");
            res.append(formatador.getDecimalFormatter().valueToString(getOpen()));
            res.append("</td></tr> <tr><td><b>  Min </b></td><td>");
            res.append(formatador.getDecimalFormatter().valueToString(getLow()));
            res.append("</td></tr> <tr><td><b>  Max </b></td><td>");
            res.append(formatador.getDecimalFormatter().valueToString(getHigh()));
            res.append("</td></tr> <tr><td><b>  Fech </b></td><td>");
            res.append(formatador.getDecimalFormatter().valueToString(getClose()));
            res.append("</td></tr> <tr><td><b>  Vol </b></td><td>");
            res.append(formatador.getVolumeFormatter().valueToString(getVolumeDinheiro()));
            res.append("</td></tr> <tr><td><b>  Neg </b></td><td>");
            res.append(getNumNegocios());
            res.append("</td></tr></table></body></html>");
            setTooltip(res.toString());
        } catch (Exception e) {
            setTooltip("*");
        }
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEspeci() {
        return especi;
    }

    public void setEspeci(String especi) {
        this.especi = especi;
    }

    public char getIndcar() {
        return indcar;
    }

    public void setIndcar(char indcar) {
        this.indcar = indcar;
    }

    public double getOscila() {
        return oscila;
    }

    public void setOscila(double oscila) {
        this.oscila = oscila;
    }

    public char getSinosc() {
        return sinosc;
    }

    public void setSinosc(char sinosc) {
        this.sinosc = sinosc;
    }
    
    public static void copiaDados(RegistroDiario fonte, RegistroDiario destino) {
         destino.data = fonte.data;
         destino.close = fonte.close;
         destino.dataCorrida = fonte.dataCorrida;
         destino.especi = fonte.especi;
         destino.high = fonte.high;
         destino.hora = fonte.hora;
         destino.indcar = fonte.indcar;
         destino.low = fonte.low;
         destino.numNegocios = fonte.numNegocios;
         destino.open = fonte.open;
         destino.oscila = fonte.oscila;
         destino.sinosc = fonte.sinosc;
         destino.tooltip = fonte.tooltip;
         destino.volumeDinheiro = fonte.volumeDinheiro;
         destino.volumeQuant = fonte.volumeQuant;
    
    }
    
}
