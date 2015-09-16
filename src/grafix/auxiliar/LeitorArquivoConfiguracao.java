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



package grafix.auxiliar;

import grafix.graficos.*;
import grafix.graficos.elementos.*;
import grafix.graficos.indices.Indice;
import grafix.principal.ConfiguracoesGrafix;
import grafix.principal.ConfiguracoesUsuario;
import java.io.File;

public class LeitorArquivoConfiguracao {
    
    static private LeitorArquivoConfiguracao leitor = null;
    
    private LeitorArquivoConfiguracao() {
    }
    
    static public LeitorArquivoConfiguracao getInstance() {
        if(leitor == null) {
            leitor = new LeitorArquivoConfiguracao();
        }
        return leitor;
    }
    
    public ConfiguracoesUsuario lerConfiguracoesUsuario() {
        try {
            ConfiguracaoXML xml = new ConfiguracaoXML(ConfiguracoesUsuario.ARQUIVO_CONFIGURACOES_USUARIO);
            return (ConfiguracoesUsuario) xml.recuperaDoXML();
        } catch (Exception e) {
            System.out.println("[AVISO] Arquivos de configuracoes do usuario nao encontrado. Usar configuracoes default.");
            return null;
        }
    }
    
    public ConfiguracoesGrafix lerConfiguracoesGrafix() {
        try {
            ConfiguracaoXML xml = new ConfiguracaoXML(ConfiguracoesGrafix.ARQUIVO_CONFIGURACOES_GRAFIX);
            return (ConfiguracoesGrafix) xml.recuperaDoXML();
        } catch (Exception e) {
            System.out.println("[AVISO] Arquivos de configuracoes do GRAFIX nao encontrado. Usar configuracoes default.");
            return null;
        }
    }
    
    public void criarCopia(File file) {
        ManipuladorArquivos.copiarArquivo(ConfiguracoesUsuario.ARQUIVO_CONFIGURACOES_USUARIO, file.getAbsolutePath());
    }
    
}
