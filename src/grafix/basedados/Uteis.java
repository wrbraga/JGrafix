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
 * Uteis.java
 *
 * Created on 24 de Julho de 2007, 16:25
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package grafix.basedados;

import java.util.Calendar;
import org.jfree.data.time.Day;

/**
 *
 * @author joao
 */
public class Uteis {
    
    /** Creates a new instance of Uteis */
    public Uteis() {
    }
    
    public static String removeBrancos(String nome) {
        int i, j,tamanho;
        char frase[] = new char[100];
        char c;
        tamanho=nome.length();
        for(i=0,j=0;i<tamanho;++i) {
            if(nome.charAt(i)!=' '){
                frase[j]=nome.charAt(i);
                ++j;
            }
        }
        return new String(frase).trim();
    }
    
     public static String converteCString2Java(byte []b, int ini, int comp) {
        char palavra[] = new char[comp];
        int j=0;
        for(int i=ini;i<ini+comp;++i,++j) {
            palavra[j] = (char)b[i];
            if(palavra[j]=='\0') break;
            
        }
        return new String(palavra,0,j);
    
    }
    
     public static Day hoje() {
        Calendar calendar = Calendar.getInstance();
        Day dataHoje = new Day(calendar.get(Calendar.DAY_OF_MONTH),
                1+calendar.get(Calendar.MONTH),
                calendar.get(Calendar.YEAR));
        return dataHoje;
    }
    
}
