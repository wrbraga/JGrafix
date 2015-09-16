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



package grafix.basedados;

import grafix.principal.RegistroDiario;
import grafix.telas.secundarias.FormAtualizarPadrao;
import java.io.*;
import java.util.StringTokenizer;
import org.jfree.data.time.Day;

public class IntradayYahoo {
    
    public IntradayYahoo() {
    }
    
    static RegistroDiario lerIntraday(String codAcao) {
        RegistroDiario registro = null;
        String codigo = IntradayYahoo.codigoYahoo(codAcao);
        Atualiza atualiza = new Atualiza(null);
        Download download;
        String nomeArquivo = "id/"+codAcao;
        String url = "http://finance.yahoo.com/d/quotes.csv?s="+codigo+"&f=sd1t1ohgl1v&e=.csv";
        if(atualiza.configuracao.isUsaProxy()) {
            download = new Download(url, nomeArquivo, atualiza.configuracao.getProxy(),
                    atualiza.configuracao.getPorta(), atualiza.configuracao.getUsuarioProxy(),
                    atualiza.configuracao.getSenhaProxy(), false, null);
        } else {
            download = new Download(url, nomeArquivo, false, null);
        }
        int ret=download.baixaArquivo();
        if(ret==0) {
            // le o arquivo
            String linha = Cotacoes.tail(nomeArquivo,1);
            registro = decodificaLinha(linha);
            
        }
        
//        System.err.println("INTRADAY NÂO FOI GERADO");
        return registro;
    }
    
    
    static RegistroDiario decodificaLinha(String linha) {
        //"ACES4.SA","9/8/2003","4:07pm",1.44,1.45,1.41,1.41,3116362
        RegistroDiario registro=null;
        try {
            String[] campos = linha.split(",");
            String data = campos[1].toString();
            data = data.replaceAll("\"","");
            String[] sep = data.split("/");
            
            int  dia = new Integer(sep[1].toString()).intValue();
            int  mes = new Integer(sep[0].toString()).intValue();
            int  ano = new Integer(sep[2].toString()).intValue();
            registro = new RegistroDiario(new Day(dia, mes, ano));
            String hora = campos[2].toString().replaceAll("\"","");
            registro.setHora(hora);
            registro.setOpen(new Double(campos[3]).doubleValue());
            registro.setHigh(new Double(campos[4]).doubleValue());
            registro.setLow(new Double(campos[5]).doubleValue());
            registro.setClose(new Double(campos[6]).doubleValue());
            registro.setVolumeDinheiro(new Double(campos[7]).doubleValue());
            registro.setVolumeQuant(registro.getVolumeDinheiro());
            registro.setNumNegocios(0.0);
        } catch (Exception ex) {
            registro=null;
        }
        return registro;
    }
    
    /**
     * Verifica qual é o código do papel no yahoo.
     * Papéis brasileiros têm um .sa acrescentado ao código
     * papéis internacionais não têm
     */
    static String codigoYahoo(String codAcao) {
        String codigo=codAcao+".sa";
        BufferedReader brInternacionais=null;
        
        try {
            brInternacionais =new BufferedReader(new FileReader("internacionais.txt"));
        } catch (FileNotFoundException ex) {
            //FormAtualizarPadrao.informarLog("Erro ao abrir internacionais.txt");
            ex.printStackTrace();
            return codigo;
        }
        
        String linha="asd";
        
        while(linha!=null) {
            try {
                linha = brInternacionais.readLine();
                if(linha!=null) {
                    StringTokenizer st = new StringTokenizer(linha);
                    String codGrafix = st.nextToken().toLowerCase();
                    String codYahoo = st.nextToken().toLowerCase();
                    if(codGrafix.equals(codAcao)) {
                        codigo=codYahoo;
                        break;
                    }
                    
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                try {
                    brInternacionais.close();
                } catch (IOException ex2) {
                    ex2.printStackTrace();
                }
                return codigo;
            }
        }
        try {
            brInternacionais.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return codigo;
    }
}
