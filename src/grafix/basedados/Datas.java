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
 * Datas.java
 *
 * Created on 22 de Julho de 2007, 22:46
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
public class Datas extends Day {
    
    /** Creates a new instance of Datas */
    public Datas() {
    }
    
    public static Day hoje() {
        Calendar calendar = Calendar.getInstance();
        Day dataHoje = new Day(calendar.get(Calendar.DAY_OF_MONTH),
                1+calendar.get(Calendar.MONTH),
                calendar.get(Calendar.YEAR));
        return dataHoje;
    }
    

    
}
