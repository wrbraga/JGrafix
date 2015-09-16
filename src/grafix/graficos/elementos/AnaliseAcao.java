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



package grafix.graficos.elementos;

import java.util.Vector;

public class AnaliseAcao {
    
    private Vector<ElementoGrafico> elementos;
    private Vector<MarcaGrafica> marcas;
    private String codAcao;
    
    public AnaliseAcao(String codAcao) {
        setCodAcao(codAcao);
    }
    
    public void apagarObjetosAnalise() {
        elementos = new Vector<ElementoGrafico>();
        marcas = new Vector<MarcaGrafica>();
    }

    public void apagarMarcasAnalise() {
        marcas = new Vector<MarcaGrafica>();
    }

    public Vector<ElementoGrafico> getElementos() {
        return elementos;
    }

    public void setElementos(Vector<ElementoGrafico> elementos) {
        this.elementos = elementos;
    }

    public void addElemento(ElementoGrafico novoElemento) {
        elementos.add(novoElemento);
    }
    
    public ResumoAnaliseAcao getResumoAnaliseAcao() {
        ResumoAnaliseAcao resultado = new ResumoAnaliseAcao();
        for (ElementoGrafico elem : elementos) {
            resultado.add(new ResumoElemento(elem));
        }
        resultado.setMarcas(marcas);
        return resultado;
    }

    public Vector<MarcaGrafica> getMarcas() {
        return marcas;
    }

    public void setMarcas(Vector<MarcaGrafica> marcas) {
        this.marcas = marcas;
    }

    public String getCodAcao() {
        return codAcao;
    }

    public void setCodAcao(String codAcao) {
        this.codAcao = codAcao;
    }

}
