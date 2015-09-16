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
 * Bovespa.java
 *
 * Created on 24 de Julho de 2007, 19:04
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package grafix.basedados;

import grafix.principal.Controle;
import grafix.telas.secundarias.FormAtualizacao;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author joao
 */
public class MercadoBovespa extends MercadoNacional{
    
    /** Creates a new instance of Bovespa */
    public MercadoBovespa(ConfiguracaoBaseDados configuracao, FormAtualizacao formAtualizacao) {
        super(formAtualizacao);
        this.configuracao = configuracao;
        this.arquivoDatasJaRealizadas = Controle.getConfiguracoesGrafix().getPathBaseDados()+File.separator+"cfg"+
                File.separator+"datasjarealizadas";
        this.boletim  = new BoletimBovespa(configuracao, formAtualizacao);
        
    }
    
    public static void adicionaNovoIndice(String indice){
        FileWriter writer = null;
        try {
            writer = new FileWriter(new File("indicesbovespa"),true);
        } catch (IOException ex) {
            System.err.println("Não foi possivel escrever no arquivo: indicesbovespa ");
        }
        String linha = Uteis.removeBrancos(indice);
        PrintWriter saida = new PrintWriter(writer,true);
        saida.println(linha + " " + linha);
        
        saida.close();
        try {
            writer.close();
        } catch (IOException ex) {
            System.err.println("Erro ao fechar arquivo: indicesbovespa ");
        }
        
    }
    
}
