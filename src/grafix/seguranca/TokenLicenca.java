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

import grafix.principal.Controle;
import java.text.ParseException;

public class TokenLicenca {
    
    private String token1;
    private String token2;
    
    public TokenLicenca(String tk1, String tk2) {
        token1 = tk1;
        token2 = tk2;
    }

    public TokenLicenca(RegistroLicenca reg) {
        token1 = Controle.getCriptografia().cifrarSenha(reg.getEmail());
        token2 = Controle.getCriptografia().cifrarSenha(Controle.getFormatador().getDataFormatter().format(reg.getDataExpiracao()));
    }

    public RegistroLicenca getRegistro() throws ParseException {
        String s1 = Controle.getCriptografia().decifrarSenha(token1);
        String s2 = Controle.getCriptografia().decifrarSenha(token2);
        return new RegistroLicenca(s1, s2);
    }
    


}
