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
 * Ajustes.java
 *
 * Created on 13 de Julho de 2007, 09:07
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package grafix.basedados;

import grafix.auxiliar.*;
import grafix.principal.RegistroDiario;
import java.io.IOException;
import org.jfree.data.time.Day;
/**
 * @author joao
 */
public class Ajustes {
    
    public Day ultimoDia;
    public double fatorCorrecao;
    public String papel;
    /** Creates a new instance of Ajustes */
    public Ajustes() {
    }
    
    public int ajustaPapel(String diretorio) {
        int retorno=0;
        Cotacoes cotas = new Cotacoes(diretorio, Cotacoes.PERIODICIDADE_DIARIA);
        Cotacoes ajustado = new Cotacoes(diretorio, Cotacoes.PERIODICIDADE_DIARIA);
        RegistroDiario registro = new RegistroDiario();
        String arquivoAjustado = System.getProperty("user.dir")+System.getProperty("file.separator")+"ajustado";
        if(ajustado.abreArquivoParaEscritaSobreescrevendo(arquivoAjustado)==1) {
            return 1;
        }
        
        if(!cotas.abreArquivoParaLeitura(this.papel)) {
            return 1;
        }
        
        registro = cotas.proximaCotacao();
    //    int diferenca = registro.getData().compareTo(this.ultimoDia);
        while(registro.getData().compareTo(this.ultimoDia) <0) {
            registro.setVolumeQuant(registro.getVolumeQuant()*this.fatorCorrecao);
            registro.setClose(registro.getClose()/this.fatorCorrecao);
            registro.setHigh(registro.getHigh()/this.fatorCorrecao);
            registro.setLow(registro.getLow()/this.fatorCorrecao);
            registro.setOpen(registro.getOpen()/this.fatorCorrecao);
            try {
                
                ajustado.escreveRegistro(registro);
            } catch (IOException ex) {
                System.err.println("Erro ao escrever registro da ação " + this.papel);
                ex.printStackTrace();
                retorno=1;
            }
            registro = cotas.proximaCotacao();
            if(registro==null) break;
         //   System.out.println("ultimodia "+ this.ultimoDia + " hoje "+registro.dataString);
            
            
        }
        
        if(registro!=null) {
            try {
                ajustado.escreveRegistro(registro);
            } catch (IOException ex) {
                System.err.println("Erro ao escrever registro da ação " + this.papel);
                ex.printStackTrace();
                
            }
            // lê as proximas cotações
            while((registro = cotas.proximaCotacao())!=null){
                try {
                    ajustado.escreveRegistro(registro);
                } catch (IOException ex) {
                    System.err.println("Erro ao escrever registro da ação " + this.papel);
                    ex.printStackTrace();
                }
            }
        }
        cotas.fechaArquivoIn();
        ajustado.fechaArquivoOut();
        
        cotas.abreArquivoParaLeitura(arquivoAjustado);
        ajustado.abreArquivoParaEscritaSobreescrevendo(this.papel);
        
        while((registro=cotas.proximaCotacao())!=null){
            try {
                ajustado.escreveRegistro(registro);
            } catch (IOException ex) {
                System.err.println("Erro ao escrever registro da ação " + this.papel);
                ex.printStackTrace();
            }
            
        }
        cotas.fechaArquivoIn();
        ajustado.fechaArquivoOut();

        
        return retorno;
    }
    
    
}
