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



package grafix.seguranca;

import java.text.*;
import java.util.*;

public class RegistroLicenca {
    
    private String email;
    private Date dataExpiracao; 
    
    public RegistroLicenca(String email, Date dataExpiracao) {
        setEmail(email);
        setDataExpiracao(dataExpiracao);
    }

    public RegistroLicenca(String email, String data) throws ParseException {
        setEmail(email);
        setDataExpiracao(toData(data).getTime());
    }

    static public GregorianCalendar toData(String data) {
        try {
            String data2 = data.replaceAll("/", " ");
            StringTokenizer st = new StringTokenizer(data2);
            int dia = new Integer(st.nextToken()).intValue();
            int mes = new Integer(st.nextToken()).intValue()-1;
            int ano = new Integer(st.nextToken()).intValue();
            if(dia>=1 && dia<=31) {
                if(mes>=1 && mes<=12) {
                    if(ano>=1900) {
                        return new GregorianCalendar(ano, mes, dia);
                    } else if(ano>=0 && ano<=50) {
                        return new GregorianCalendar(2000 + ano, mes, dia);
                    } else if(ano>50 && ano<=99) {
                        return new GregorianCalendar(1900 + ano, mes, dia);
                    }
                }
            }
        } catch(Exception e) {}
        return null;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(Date dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }
    
}
