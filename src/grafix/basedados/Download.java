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
 * TrazArquivo.java
 *
 * Created on 18 de Julho de 2007, 22:39
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package grafix.basedados;
import grafix.auxiliar.Base64;
import grafix.principal.ConfiguracoesGrafix;
import grafix.telas.secundarias.FormAtualizacao;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

public class Download {
    
    private String url;
    private String arquivo;
    private boolean usaProxy;
    private String servidorProxy;
    private int portaProxy;
    private String usuarioProxy;
    private String senhaProxy;
    private boolean mostraProgresso;
    private FormAtualizacao formAtualizacao;
    
    public Download(String url, boolean mostraProgresso){
        this.mostraProgresso=mostraProgresso;
        this.setUrl(url);
        this.usaProxy=false;
    }
    
    public Download(String url, String arquivo, boolean mostraProgresso){
        this(url, mostraProgresso);
        this.setArquivo(arquivo);
    }
    
    public Download(String url, String arquivo, boolean mostraProgresso, FormAtualizacao formAtualizacao){
        this(url, arquivo, mostraProgresso);
        this.formAtualizacao = formAtualizacao;
    }
    
    public Download(String url, String servidorProxy,
            int portaProxy, String usuarioProxy, String senhaProxy, boolean mostraProgresso){
        this(url, mostraProgresso);
        this.usaProxy=true;
        this.servidorProxy=servidorProxy;
        this.portaProxy=portaProxy;
        this.usuarioProxy=usuarioProxy;
        this.senhaProxy=senhaProxy;
    }
    
    public Download(String url, String arquivo, String servidorProxy,
            int portaProxy, String usuarioProxy, String senhaProxy, boolean mostraProgresso){
        this(url, arquivo, mostraProgresso);
        this.usaProxy=true;
        this.servidorProxy=servidorProxy;
        this.portaProxy=portaProxy;
        this.usuarioProxy=usuarioProxy;
        this.senhaProxy=senhaProxy;
    }
    
    public Download(String url, String arquivo, String servidorProxy,
            int portaProxy, String usuarioProxy, String senhaProxy, boolean mostraProgresso, FormAtualizacao formAtualizacao){
        this(url, arquivo, mostraProgresso, formAtualizacao);
        this.usaProxy=true;
        this.servidorProxy=servidorProxy;
        this.portaProxy=portaProxy;
        this.usuarioProxy=usuarioProxy;
        this.senhaProxy=senhaProxy;
    }
    
    
    public int baixaArquivo(){
        // Create an instance of HttpClient.
        HttpClient client = new HttpClient();
        int retorno=0;
        // Create a method instance.
        
        if(this.usaProxy) {
            client.getHostConfiguration().setProxy(this.servidorProxy, this.portaProxy);
            client.getState().setProxyCredentials(new AuthScope(this.servidorProxy,this.portaProxy),
                    new UsernamePasswordCredentials(this.usuarioProxy, this.senhaProxy));
            client.getParams().setAuthenticationPreemptive(true);
            
        }
        
        URI url2;
        String ff=null;
        try {
            url2 = new URI(this.getUrl(), false);
            ff = url2.toString();
        } catch (URIException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        
        GetMethod method = new GetMethod(ff);
        byte [] arquivo = null;
        long totalBytesRead = 0;
        long loopBytesRead = 0;
        // Provide custom retry handler is necessary
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(3, false));
        
        byte[] buffer = new byte[4096];
        int progresso=0;
        try {
            int statusCode = client.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + method.getStatusCode() + " " +method.getStatusLine());
                retorno=method.getStatusCode();
            } else {
                long contentLength = method.getResponseContentLength();
                File file = new File(ConfiguracoesGrafix.DEFAULT_PATH_BDI + this.getArquivo());
                FileOutputStream os = new FileOutputStream(file);
                InputStream stream = method.getResponseBodyAsStream();
                while ((loopBytesRead = stream.read(buffer)) != -1) {
                    for(int i = 0; i < loopBytesRead; i++){
                        os.write(buffer[i]);
                    }
                    totalBytesRead += loopBytesRead;
                    progresso = (int) ( (float) totalBytesRead / contentLength *100);
                    if(progresso >=0 || progresso <=100) {
                        //   System.out.println("download " + progresso + " %");
                        if(this.mostraProgresso) formAtualizacao.definirPercentualProgresso(progresso);
                    }
                }
                os.flush();
                os.close();
                stream.close();
            }
        } catch (HttpException e) {
            retorno=2;
            System.err.println("Fatal protocol violation: " + e.getMessage());
            e.printStackTrace();
            
        } catch (IOException e) {
            retorno=3;
            System.err.println("Fatal transport error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Release the connection.
            method.releaseConnection();
        }
        
        if(this.mostraProgresso) formAtualizacao.definirPercentualProgresso(0);
        return retorno;
        
    }
    
    public String baixaString(){
        String retorno = null;
        URL url= null;
        URLConnection con = null;
        try {
            url = new URL(this.url);
            con = url.openConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
            return retorno;
        }
        if(this.usaProxy) {
            System.setProperty("http.proxySet", "true");
            System.setProperty("http.proxyHost", this.servidorProxy);
            System.setProperty("http.proxyPort",  String.format("%d", this.portaProxy));
            System.setProperty("http.proxyType", "4");
            if(this.usuarioProxy!=null) {
                if(this.usuarioProxy.length()>0) {
                    String proxyUser = this.usuarioProxy,
                            proxyPassword = this.senhaProxy;
                    con.setRequestProperty(
                            "Proxy-Authorization",
                            "Basic " + Base64.encodeToString((proxyUser + ":" + proxyPassword).getBytes(), false) 	);
                }
            }
        }
        try {
            int len = con.getContentLength();
            byte[] b = new byte[len];
            InputStream is = con.getInputStream();
            is.read(b, 0, len );
            
            String s = new String(b);
            return s;
            
        } catch(MalformedURLException ex) {
            System.out.println("Erro");
            return "";
        } catch(IOException ex) {
            System.out.println("Erro");
            System.out.println(ex);
            return "";
        }
        
    }
    
    public String baixaArquivoGZIP(){
        String retorno = null;
        URL url= null;
        URLConnection con = null;
        try {
            url = new URL(this.url);
            con = url.openConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
            return retorno;
        }
        if(this.usaProxy) {
            System.setProperty("http.proxySet", "true");
            System.setProperty("http.proxyHost", this.servidorProxy);
            System.setProperty("http.proxyPort",  String.format("%d", this.portaProxy));
            System.setProperty("http.proxyType", "4");
            if(this.usuarioProxy!=null) {
                if(this.usuarioProxy.length()>0) {
                    String proxyUser = this.usuarioProxy,
                            proxyPassword = this.senhaProxy;
                    con.setRequestProperty(
                            "Proxy-Authorization",
                            "Basic " + Base64.encodeToString((proxyUser + ":" + proxyPassword).getBytes(), false) 	);
                }
            }
        }
        GZIPInputStream gzip;
        try {
            gzip = new GZIPInputStream(con.getInputStream());
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            int readLen = 0;
            byte[] buffer = new byte[4096];
            int bytes = 0;
          //  int sfs =   gzip.      //con.getInputStream().available();
            int total = con.getContentLength();
            if(this.mostraProgresso) this.formAtualizacao.definirPercentualProgresso(0);
            while( (readLen=gzip.read(buffer, 0, buffer.length))>0)  {
                  os.write(buffer, 0, readLen);
                //  int sfs =  con.getInputStream().available();
                  bytes+=readLen;
                  if(this.mostraProgresso) this.formAtualizacao.informaBytesLidos(bytes);
                  // float sd = (float)(total-sfs)/(float)total*100.0f;
                 // this.formAtualizacao.informarLog("Baixados" + (total - sfs) + " bytes de " + total + "progresso " + (int) sd);
                 // this.formAtualizacao.definirPercentualProgresso( (int) sd);
            }
            retorno =  os.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return retorno;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getArquivo() {
        return arquivo;
    }
    
    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }
    
}
