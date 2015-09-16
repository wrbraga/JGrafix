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

import grafix.auxiliar.ManipuladorArquivos;
import grafix.principal.ConfiguracoesGrafix;
import grafix.principal.Controle;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;

public class ControleRegistro {

    private static double PERCENTUAL_EXIBICAO_SOLICITACAO_REGISTRO = 0.1;
    private static String MENSAGEM_SEM_REGISTRO =
            "Registre o seu Grafix.\n" +
            "Habilite todas as funcionalidades e conte com nosso suporte.";
    private static boolean registrado = false;
    private static RegistroLicenca registroLicenca;
    
    public static void avaliarRegistro() {
        try {
            if(!ManipuladorArquivos.existeArquivo(ConfiguracoesGrafix.ARQUIVO_TOKEN_LICENCA)) {
                System.out.println("Arquivo de licença não encontrado!");
                Controle.exibirInfo(MENSAGEM_SEM_REGISTRO);
                return;
            }
            BufferedReader ind = null;
            ind = new BufferedReader(new FileReader(ConfiguracoesGrafix.ARQUIVO_TOKEN_LICENCA));
            String tk1, tk2;
            tk1 = ind.readLine();
            tk2 = ind.readLine();
            ind.close();
            TokenLicenca tk = new TokenLicenca(tk1, tk2);
            setRegistroLicenca(tk.getRegistro());
            Date dataExpiracao = getRegistroLicenca().getDataExpiracao();
            Date hoje = new Date();
            if(hoje.compareTo(dataExpiracao) < 0 ) {
                setRegistrado(true);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setRegistrado(false);
    }
    
    public static void alertaRegistro() {
        if(!ControleRegistro.isRegistrado()) {
            if(amostragem()) {
                Controle.exibirInfo(MENSAGEM_SEM_REGISTRO);
            }
        }
    }
    
    private static boolean amostragem() {
        return (Math.random() < PERCENTUAL_EXIBICAO_SOLICITACAO_REGISTRO) ? true : false;
    }
    
    public static boolean isRegistrado() {
        return registrado;
    }
    
    public static void setRegistrado(boolean aRegistrado) {
        registrado = aRegistrado;
    }
    
    public static RegistroLicenca getRegistroLicenca() {
        return registroLicenca;
    }

    public static void setRegistroLicenca(RegistroLicenca aRegistroLicenca) {
        registroLicenca = aRegistroLicenca;
    }

}
