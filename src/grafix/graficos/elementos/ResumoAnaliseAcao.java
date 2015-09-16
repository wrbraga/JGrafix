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

public class ResumoAnaliseAcao {
    
    private Vector<ResumoElemento> resumosElementos = new Vector<ResumoElemento>();
    private Vector<MarcaGrafica> marcas = new Vector<MarcaGrafica>();
    
    public ResumoAnaliseAcao() {
    }

    void add(ResumoElemento resumoElemento) {
        resumosElementos.add(resumoElemento);
    }
    
    void add(MarcaGrafica marca) {
        marcas.add(marca);
    }

    public Vector<ResumoElemento> getResumosElementos() {
        return resumosElementos;
    }

    public Vector<MarcaGrafica> getMarcas() {
        return marcas;
    }

    public void setMarcas(Vector<MarcaGrafica> marcas) {
        this.marcas = marcas;
    }
}
