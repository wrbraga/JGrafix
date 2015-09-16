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



package grafix.basedados;

public class ConfiguracaoBaseDados {
    
    private TiposPapeis tiposPapeis = new TiposPapeis();
    private int pregoesMostrados=100;
    private boolean atualizaViaInternet=true;
    private String formatoBaseDados= "";
    private boolean usaProxy = false;
    private String proxy="";
    private int porta=8080;
    private String usuarioProxy="";
    private String senhaProxy="";
    private String pastaBDIS = "";
    private String urlDatas="http://www.grafix2.com/datas.dat";
    private int periodicidade=1; //1 - diaria, 2 - semanal, 3 - mensal
    private String vendor="";
    private String usuarioVendor="";
    private String senhaVendor = "";
    
    /** Creates a new instance of Configuracao */
    public ConfiguracaoBaseDados() {
    }
    
    public int getPregoesMostrados() {
        return pregoesMostrados;
    }
    
    public void setPregoesMostrados(int pregoesMostrados) {
        this.pregoesMostrados = pregoesMostrados;
    }
    
    public boolean isAtualizaViaInternet() {
        return atualizaViaInternet;
    }
    
    public void setAtualizaViaInternet(boolean atualizaViaInternet) {
        this.atualizaViaInternet = atualizaViaInternet;
    }
    
    public String getUrlDatas() {
        return urlDatas;
    }
    
    public void setUrlDatas(String urlDatas) {
        this.urlDatas = urlDatas;
    }
    
    public String getProxy() {
        return proxy;
    }
    
    public void setProxy(String proxy) {
        this.proxy = proxy;
    }
    
    public int getPorta() {
        return porta;
    }
    
    public void setPorta(int porta) {
        this.porta = porta;
    }
    
    public String getUsuarioProxy() {
        return usuarioProxy;
    }
    
    public void setUsuarioProxy(String usuarioProxy) {
        this.usuarioProxy = usuarioProxy;
    }
    
    public String getSenhaProxy() {
        return senhaProxy;
    }
    
    public void setSenhaProxy(String senhaProxy) {
        this.senhaProxy = senhaProxy;
    }
    
    public String getFormatoBaseDados() {
        return formatoBaseDados;
    }
    
    public void setFormatoBaseDados(String formatoBaseDados) {
        this.formatoBaseDados = formatoBaseDados;
    }
    
    public String getPastaBDIS() {
        return pastaBDIS;
    }
    
    public void setPastaBDIS(String pastaBDIS) {
        this.pastaBDIS = pastaBDIS;
    }
    
    public boolean isUsaProxy() {
        return usaProxy;
    }
    
    public void setUsaProxy(boolean usaProxy) {
        this.usaProxy = usaProxy;
    }
    
    public TiposPapeis getTiposPapeis() {
        return tiposPapeis;
    }
    
    public void setTiposPapeis(TiposPapeis tiposPapeis) {
        this.tiposPapeis = tiposPapeis;
    }

    public int getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(int periodicidade) {
        this.periodicidade = periodicidade;
    }

    public String getUsuarioVendor() {
        return usuarioVendor;
    }

    public void setUsuarioVendor(String usuarioVendor) {
        this.usuarioVendor = usuarioVendor;
    }

    public String getSenhaVendor() {
        return senhaVendor;
    }

    public void setSenhaVendor(String senhaVendor) {
        this.senhaVendor = senhaVendor;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
    
}
