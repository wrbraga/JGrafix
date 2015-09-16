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
 * Boletim.java
 *
 * Created on 24 de Julho de 2007, 14:27
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package grafix.basedados;

import grafix.principal.RegistroDiario;
import grafix.telas.secundarias.FormAtualizacao;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 *
 * @author joao
 */
abstract class Boletim {
    private String dataPregao;
    private String nomeCompactado;
    private String nomeDescompactado;
    private String url;
    
    protected ConfiguracaoBaseDados configuracao;
    
    public Boletim(ConfiguracaoBaseDados configuracaoBaseDados, FormAtualizacao formAtualizacao){
        this("01/09/1998",configuracaoBaseDados, formAtualizacao );
        
    }
    
    /** Creates a new instance of Boletim */
    public Boletim(String data, ConfiguracaoBaseDados configuracaoBaseDados, FormAtualizacao formAtualizacao) {
        this.setDataPregao(data);
        this.configuracao = configuracaoBaseDados;
        this.formAtualizacao = formAtualizacao;
    }
    
    protected abstract String montaURL();
    protected abstract String montaNome(String dataPregao);
    protected abstract int extraiDados();
    protected FormAtualizacao formAtualizacao;
    
    protected String montaNome() {
        return this.montaNome(this.getDataPregao());
    }
    
    public boolean existeBoletim(String dataPregao) {
        String nome;
        int retorno=0;
        nome = this.montaNome(dataPregao);
        File file = new File(nome);
        return file.exists();
    }
    
    public boolean existeBoletim() {
        File file = new File(this.getNomeCompactado());
        return file.exists();
    }
    
    public int download() {
        Download download = null;
        download = instaciaDownload();
        return download.baixaArquivo();
    }

    protected Download instaciaDownload() {
        Download download;
        if(this.configuracao.isUsaProxy()) {
            download = new Download(this.getUrl(), this.getNomeCompactado(), this.configuracao.getProxy(),
                    this.configuracao.getPorta(), this.configuracao.getUsuarioProxy(), 
                    this.configuracao.getSenhaProxy(), true, formAtualizacao);
        } else {
            download = new Download(this.getUrl(), this.getNomeCompactado(), true, formAtualizacao);
        }
        return download;
    }
    
    protected String proximaLinhaBDI(BufferedReader b) {
        String linha = null;
        try {
            linha=b.readLine();
        } catch (IOException ex) {
            System.err.println("Erro ao extrair dados do bdi: " + this.getNomeCompactado());
            ex.printStackTrace();
        }
        return linha;
        
    }
    
    protected String descompactaArquivo() throws IOException {
        String saida=null;
        Enumeration entries;
        ZipFile zipFile = new ZipFile(this.nomeCompactado);
        
        
        entries = zipFile.entries();
        while(entries.hasMoreElements()) {
            ZipEntry e = (ZipEntry)entries.nextElement();
            InputStream is = zipFile.getInputStream(e);
            saida = e.getName();
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saida));
            
            byte [] b = new byte[512];
            int len = 0;
            while ( (len=  is.read(b))!= -1 ) {
                out.write(b,0,len);
            }
            out.close();
            is.close();
        }
        
        this.setNomeDescompactado(saida);
        return saida;
    }
    
    protected void copiaRegistro(RegistroDiario fonte, RegistroDiario destino) {
        destino.setOpen(fonte.getOpen());
        destino.setLow(fonte.getLow());
        destino.setHigh(fonte.getHigh());
        destino.setClose(fonte.getClose());
        destino.setNumNegocios(fonte.getNumNegocios());
        destino.setVolumeQuant(fonte.getVolumeQuant());
        destino.setVolumeDinheiro(fonte.getVolumeDinheiro());
    }
    
    public String getDataPregao() {
        return dataPregao;
    }
    
    public void setDataPregao(String dataPregao) {
        this.dataPregao = dataPregao;
        this.nomeCompactado = this.montaNome();
        this.url = this.montaURL();
        
    }
    
    public String getNomeCompactado() {
        return nomeCompactado;
    }
    
    public String getUrl() {
        return url;
    }
    
    public String getNomeDescompactado() {
        return nomeDescompactado;
    }
    
    public void setNomeDescompactado(String nomeDescompactado) {
        this.nomeDescompactado = nomeDescompactado;
    }
    
    
}
