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

import grafix.auxiliar.ConfiguracaoXML;
import grafix.auxiliar.LeitorArquivoConfiguracao;

public class ConfiguracoesGrafix {
    
    private String pathBaseDados;
    private String pathBDI;
    private String listaAcoes;
    private String lookAndFeel;
    private int espacoPrevisao;
    private int zoomNormal;
    private int zoomIncremento;
    private int movimentoIncremento;
    private boolean localizacaoStatusSuperior;
    private int periodicidade;
    private boolean primeiroAcesso;
    
    static final public String ARQUIVO_CONFIGURACOES_GRAFIX = "conf/configuracoesGrafix.xml";
    static final public String ARQUIVO_CONFIGURACOES_BASEDADOS = "conf/configuracaoBaseDados.xml";
    static final public String ARQUIVO_HISTORICO_PAPEIS = "conf/historicoPapeis.xml";
    static final public String ARQUIVO_CADASTRO_PAPEIS = "conf/cadastroPapeis.xml";
    static final public String ARQUIVO_TOKEN_LICENCA = "conf/grafix.lic";
    
    static final public String PASTA_LISTAS = "resource/listas/";
    static final public String PASTA_ICONES = "resource/icones/";
    static final public String PASTA_TEMPLATES = "resource/templates/";
    static final public String PASTA_LUA = "lua/";
    static final public String EXTENSAO_LISTAS = ".lst";
    static final public String EXTENSAO_TEMPLATES = ".tpt";
    
    static final public String DEFAULT_PATH_DADOS = "dados/";
    static final public String DEFAULT_PATH_BDI = "bdi/";
    static final public String DEFAULT_LISTA_ACOES = null;
    static final public String DEFAULT_LOOK_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
    static final public int DEFAULT_ESPACO_PREVISAO = 100;
    static final public int DEFAULT_ZOOM_NORMAL = 150;
    static final public int DEFAULT_ZOOM_INCREMENTO = 15;
    static final public int DEFAULT_MOVIMENTO_INCREMENTO = 30;
    static final public boolean DEFAULT_LOCALIZACAO_STATUS = true;
    static final public int DEFAULT_PERIODICIDADE = 1;
    static final public String VERSAO = "2.3.4.1"; 
           
            
            
            
    
 /*
  *     Estas sï¿½o as configuraï¿½ï¿½es que nï¿½o sï¿½o salvas como 'templates' ou perfis de usuï¿½rio.
  *     Sï¿½o configuraï¿½ï¿½es mais ligadas ao funcionamento do software.
  */
    
    private ConfiguracoesGrafix() {
        pathBaseDados = DEFAULT_PATH_DADOS;
        pathBDI = DEFAULT_PATH_BDI;
        listaAcoes = DEFAULT_LISTA_ACOES;
        lookAndFeel = DEFAULT_LOOK_FEEL;
        espacoPrevisao = DEFAULT_ESPACO_PREVISAO;
        zoomNormal = DEFAULT_ZOOM_NORMAL;
        zoomIncremento = DEFAULT_ZOOM_INCREMENTO;
        movimentoIncremento = DEFAULT_MOVIMENTO_INCREMENTO;
        localizacaoStatusSuperior = DEFAULT_LOCALIZACAO_STATUS;
        periodicidade = DEFAULT_PERIODICIDADE;
        primeiroAcesso = false;
    }
    
    public static ConfiguracoesGrafix carregar() {
        ConfiguracoesGrafix conf = LeitorArquivoConfiguracao.getInstance().lerConfiguracoesGrafix();
        if(conf==null) {
            return new ConfiguracoesGrafix();
        } else {
            conf.setPeriodicidade(1);
            return conf;
        }
    }
    
    public static ConfiguracoesGrafix reiniciar() {
        return new ConfiguracoesGrafix();
    }
    
    public void salvar() {
        new ConfiguracaoXML(ARQUIVO_CONFIGURACOES_GRAFIX).guardaNoXML(this);
    }
    
    public String getPathBaseDados() {
        return pathBaseDados;
    }
    
    public void setPathBaseDados(String pathBaseDados) {
        this.pathBaseDados = pathBaseDados;
    }
    
    public String getPathBDI() {
        return pathBDI;
    }
    
    public void setPathBDI(String pathBDI) {
        this.pathBDI = pathBDI;
    }
    
    public String getListaAcoes() {
        return listaAcoes;
    }
    
    public void setListaAcoes(String listaAcoes) {
        this.listaAcoes= listaAcoes;
    }
    
    public String getLookAndFeel() {
        return lookAndFeel;
    }
    
    public void setLookAndFeel(String lookAndFeel) {
        this.lookAndFeel = lookAndFeel;
    }
    
    public int getEspacoPrevisao() {
        return espacoPrevisao;
    }
    
    public void setEspacoPrevisao(int espacoPrevisao) {
        this.espacoPrevisao = espacoPrevisao;
    }
    
    public int getZoomNormal() {
        return zoomNormal;
    }
    
    public void setZoomNormal(int zoomNormal) {
        this.zoomNormal = zoomNormal;
    }
    
    public int getZoomIncremento() {
        return zoomIncremento;
    }
    
    public void setZoomIncremento(int zoomIncremento) {
        this.zoomIncremento = zoomIncremento;
    }
    
    public int getMovimentoIncremento() {
        return movimentoIncremento;
    }
    
    public void setMovimentoIncremento(int movimentoIncremento) {
        this.movimentoIncremento = movimentoIncremento;
    }
    
    public boolean isLocalizacaoStatusSuperior() {
        return localizacaoStatusSuperior;
    }

    public void setLocalizacaoStatusSuperior(boolean localizacaoStatusSuperior) {
        this.localizacaoStatusSuperior = localizacaoStatusSuperior;
    }

    public int getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(int periodicidade) {
        this.periodicidade = periodicidade;
    }

    public boolean isPrimeiroAcesso() {
        return primeiroAcesso;
    }

    public void setPrimeiroAcesso(boolean primeiroAcesso) {
        this.primeiroAcesso = primeiroAcesso;
    }

}
