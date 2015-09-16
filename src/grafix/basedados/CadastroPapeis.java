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



/*
 * CadastroPapeis.java
 *
 * Created on 17 de Julho de 2007, 14:18
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package grafix.basedados;

import grafix.auxiliar.*;

/**
 *
 * @author joao
 */
public class CadastroPapeis {
    private String codigo;
    private String nome;
    private double liquidez;
    
    /** Creates a new instance of CadastroPapeis */
    public CadastroPapeis(String codigo, String nome, double liquidez) {
        this.codigo = codigo;
        this.nome = nome;
        this.liquidez = liquidez;
    }
    

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getLiquidez() {
        return liquidez;
    }

    public void setLiquidez(double liquidez) {
        this.liquidez = liquidez;
    }
    
}
