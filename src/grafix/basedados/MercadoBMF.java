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
 * MercadoBMF.java
 *
 * Created on 24 de Julho de 2007, 19:32
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package grafix.basedados;

import grafix.principal.Controle;
import grafix.telas.secundarias.FormAtualizacao;
import java.io.File;

/**
 *
 * @author joao
 */
public class MercadoBMF extends MercadoNacional {

    /** Creates a new instance of BMF */
    public MercadoBMF(ConfiguracaoBaseDados configuracao, FormAtualizacao formAtualizacao) {
        super(formAtualizacao);
        this.configuracao = configuracao;
        this.arquivoDatasJaRealizadas = Controle.getConfiguracoesGrafix().getPathBaseDados() + File.separator + "cfg" +
                File.separator + "datasbmf";
        this.boletim = new BoletimBMF(configuracao, formAtualizacao);
    }
}
