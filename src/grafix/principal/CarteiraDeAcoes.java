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

import grafix.auxiliar.ManipuladorArquivos;
import java.io.File;
import java.util.*;

public class CarteiraDeAcoes {
    
    Vector<Acao> acoes = new Vector<Acao>();
    
    public CarteiraDeAcoes() {
        ArrayList<String> papeis = getListaDePapeis();
        for (String papel : papeis) {
            Acao acao = new Acao(papel);
            if(acao.isAcaoValida()) {
                acoes.add(acao);
            }
        }
    }
    
    public Vector getAcoes() {
        return acoes;
    }
    
    public Acao getAcao(String codAcao) {
        for (Acao elem : acoes) {
            if(elem.getCodAcao().equals(codAcao)) {
                return elem;
            }
        }
        // Nao achou - retorna qualquer uma!
        return acoes.get(0);
    }
    
    private ArrayList<String> getListaDePapeis() {
        try {
            String arqListaDePapeis = Controle.getConfiguracoesGrafix().getListaAcoes();
            if(arqListaDePapeis == null) {
                //return todosOsPapeis();
                return algunsPapeis();
            } else {
                return ManipuladorArquivos.lerListaDeArquivo(ConfiguracoesGrafix.PASTA_LISTAS + arqListaDePapeis);
            }
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<String> algunsPapeis() {
        ArrayList<String> resultado = new ArrayList<String>();
        resultado.add("petr4");
        resultado.add("vale5");
        resultado.add("ibvsp");
        return resultado;
        
    }
    public ArrayList<String> todosOsPapeis() {
        ArrayList<String> resultado = new ArrayList<String>();
        File[] arquivos = ManipuladorArquivos.listaConteudoDiretorio(Controle.getConfiguracoesGrafix().getPathBaseDados());
        for (int i = 0; i < arquivos.length; i++) {
            if(arquivos[i].getName().indexOf(".")<0) {
                resultado.add(arquivos[i].getName());
            }
        }
        return resultado;
    }
    
}
