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

import grafix.telas.secundarias.FormAtualizacao;
import java.io.*;
import java.util.*;
import java.util.zip.*;

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
    
     /***
      * Função criada para descompactar qualquer arquivo ZIP.
      * Na classe Boletim há um método chamado descompactaArquivo()
      * que faz a mesma coisa.
      * 
      * Parâmetros:
      * @param zipname - Nome do arquivo ZIP
      * @param destino - Nome do arquivo de destino
      * @param mostraProgresso - true/false para indicar o proresso
      * @param formAtualizacao - Form que exibirá.
      * @return 
      * @throws java.lang.Exception 
     */
    public static String unZip(String zipname, String destino, boolean mostraProgresso, FormAtualizacao formAtualizacao) throws Exception {        
        ZipFile zip = new ZipFile(zipname);
        Enumeration conteudo = zip.entries();
       
        try {
            while(conteudo.hasMoreElements()) {
                ZipEntry elemento = (ZipEntry)conteudo.nextElement();
                System.out.println(elemento.getName());
                if(elemento.isDirectory()) {
                    (new File(destino + File.separator + elemento.getName())).mkdir();
                    continue;
                
                }              
                gravaInputStream(zip.getInputStream(elemento), new BufferedOutputStream(new FileOutputStream(destino + File.separator + elemento.getName())));
                
            }
            
        } catch(Exception e) {
            
        }
        
        return zip.toString();
    }
    
    public static final void gravaInputStream(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        while((len = in.read(buffer)) >= 0)
          out.write(buffer, 0, len);
        in.close();
        out.close();
    }
    
}