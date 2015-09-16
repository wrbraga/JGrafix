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

import grafix.graficos.elementos.*;
import java.util.Vector;

public class LeitorArquivoAnalise {
    
    static private LeitorArquivoAnalise leitor = null;
    
    private LeitorArquivoAnalise() {
    }
    
    static public LeitorArquivoAnalise getInstance() {
        if(leitor == null) {
            leitor = new LeitorArquivoAnalise();
        }
        return leitor;
    }
    
    public AnaliseAcao getAnaliseAcao(String codAcao) {
        AnaliseAcao resultado = new AnaliseAcao(codAcao);
        Vector<ElementoGrafico> elementos = new Vector<ElementoGrafico>();
        Vector<MarcaGrafica> marcas = new Vector<MarcaGrafica>();
        grafix.auxiliar.ConfiguracaoXML xml = new grafix.auxiliar.ConfiguracaoXML("conf/analise_" + codAcao + ".xml");
        try {
            ResumoAnaliseAcao lista = (ResumoAnaliseAcao) xml.recuperaDoXML();
            if(lista.getResumosElementos()!=null) {
                for (ResumoElemento resumo : lista.getResumosElementos()) {
                    elementos.add(resumo.getElementoGrafico());
                }
            }
            marcas = lista.getMarcas();
        } catch (Exception ex) {}
        resultado.setElementos(elementos);
        resultado.setMarcas(marcas);
        return resultado;
    }
    
    public void salvar(AnaliseAcao analiseAcao) {
        grafix.auxiliar.ConfiguracaoXML xml = new grafix.auxiliar.ConfiguracaoXML("conf/analise_" + analiseAcao.getCodAcao() + ".xml");
        xml.guardaNoXML(analiseAcao.getResumoAnaliseAcao());
    }
}
