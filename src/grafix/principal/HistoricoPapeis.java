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
import grafix.basedados.CadastroPapeis;
import java.util.ArrayList;
import java.util.HashMap;

public class HistoricoPapeis {
    
    private HashMap<String, CadastroPapeis> papeis;
    
    private HistoricoPapeis() {
        setPapeis(new HashMap<String, CadastroPapeis>());
        gravar();
    }
    
    public static HistoricoPapeis carregar() {
        try {
            return (HistoricoPapeis)(new ConfiguracaoXML(ConfiguracoesGrafix.ARQUIVO_HISTORICO_PAPEIS).recuperaDoXML());
        } catch (Exception e) {
            e.printStackTrace();
            return new HistoricoPapeis();
        }
    }
    
    public String getNomeAcao(String cod) {
        try {
            return getPapeis().get(cod).getNome();
        } catch (Exception e) {
            return null;
        }
    }
    
    public double getLiquidezAcao(String cod) {
        try {
            return getPapeis().get(cod).getLiquidez();
        } catch (Exception e) {
            return -1;
        }
    }
    
    public HashMap<String, CadastroPapeis> getPapeis() {
        return papeis;
    }
    
    public void setPapeis(HashMap<String, CadastroPapeis> papeis) {
        this.papeis = papeis;
    }
    
    public void atualizar(ArrayList lista) {  // OTIMIZAR
        try {
         //   List lista = (List)new ConfiguracaoXML(ConfiguracoesGrafix.ARQUIVO_CADASTRO_PAPEIS).recuperaDoXML();
            for (int i = 0; i < lista.size(); i++) {
                CadastroPapeis c = (CadastroPapeis)lista.get(i);
                getPapeis().put(c.getCodigo(), c);
            }
        } catch (Exception ex) {
            System.out.println("Arquivo '" + ConfiguracoesGrafix.ARQUIVO_CADASTRO_PAPEIS + "' não encontrado!"); 
        }
        gravar();
    }
    
    private void gravar() {
        new ConfiguracaoXML(ConfiguracoesGrafix.ARQUIVO_HISTORICO_PAPEIS).guardaNoXML(this);
    }
}
