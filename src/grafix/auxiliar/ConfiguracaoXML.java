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

import java.io.*;
import com.thoughtworks.xstream.XStream;

/**
 * Este e um util componente que intermedia a gravaï¿½ï¿½o e leitura de dados em arquivos XML
 * ï¿½ utilizada a classe com.thoughtworks.xstream.XStream da biblioteca externa xstream-1.0.1.jar.
 * Este componente faz a gravaï¿½ï¿½o e recuperaï¿½ï¿½o de OBJETOS em arquivos XML.
 */
public class ConfiguracaoXML {

    XStream xstream = new XStream();
    String nomeArquivoXML;

    /**
     * No construtor ï¿½ passado o nome do arquivo XML com o qual o componente vai trabalhar.
     */
    public ConfiguracaoXML(String nomeArquivoXML) {
        this.nomeArquivoXML = nomeArquivoXML;
    }

    /**
     * O mï¿½todo guardaNoXML recebe um objeto e o guarda no arquivo XML.
     */
    public void guardaNoXML(Object objeto) {
        String novoXML = xstream.toXML(objeto);
        escreverNoArquivo(novoXML);
    }

    /**
     * O mï¿½todo recuperaDoXML recupera o objeto do arquivo e o retorna como um Object.
     * Cabe a quem estï¿½ usando este componente, ao receber o Object, fazer a conversï¿½o para o tipo original.
     */
    public Object recuperaDoXML() throws Exception {
        Object objeto;
        try {
            String atualXML = leDoArquivo();
            objeto = xstream.fromXML(atualXML);
        } catch (Exception e) {
            throw e;
        }
        return objeto;
    }

    private void escreverNoArquivo(String texto) {
        try {
            FileWriter writer = new FileWriter(nomeArquivoXML);
            PrintWriter saida = new PrintWriter(writer);
            saida.println(texto);
            saida.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private String leDoArquivo() throws Exception {
//        try {
//            return ManipuladorArquivos.lerConteudoDeArquivo(nomeArquivoXML);
//        } catch (Exception e) {
//            throw e;
//        }
//    }
//
    private String leDoArquivo() throws Exception{
        try {
            FileReader reader = new FileReader(nomeArquivoXML);
            BufferedReader leitor = new BufferedReader(reader,1*1024*1024);
            String linha = "";
            StringBuffer resultado = new StringBuffer("");
            linha = leitor.readLine();
            while(linha!=null) {
                resultado.append(linha);
                linha = leitor.readLine();
            }
            leitor.close();
            reader.close();
            return resultado.toString();
        } catch(Exception e) {
            throw e; 
        }
    }
}
