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

import java.text.*;
import javax.swing.text.*;

public class FormatadorPadrao {

    private SimpleDateFormat dataFormatter;
    private NumberFormatter decimalFormatter;
    private NumberFormatter volumeFormatter;

    public FormatadorPadrao() {
        dataFormatter = new SimpleDateFormat("dd/MM/yy");
        decimalFormatter = new NumberFormatter(new DecimalFormat("0.00"));
        //volumeFormatter = new NumberFormatter(new DecimalFormat("0.00E0"));
        // Mostra o volume em R$ - O volume que está sendo mostrado é o de dinheiro
        volumeFormatter = new NumberFormatter(new DecimalFormat("0,000.00"));
    }

    public SimpleDateFormat getDataFormatter() {
        return dataFormatter;
    }

    public NumberFormatter getDecimalFormatter() {
        return decimalFormatter;
    }

    public NumberFormatter getVolumeFormatter() {
        return volumeFormatter;
    }
}
