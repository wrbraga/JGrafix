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
 * MercadoBovespa.java
 *
 * Created on 24 de Julho de 2007, 16:23
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package grafix.basedados;

import grafix.telas.secundarias.DialogLogAtualizacao;
import grafix.telas.secundarias.FormAtualizacao;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import org.jfree.data.time.Day;

/**
 *
 * @author joao
 */
abstract public class MercadoNacional implements Mercados {
    
    protected String arquivoDatasJaRealizadas;
    protected ConfiguracaoBaseDados configuracao;
    protected Boletim boletim;
    protected DialogLogAtualizacao telaLog;
    protected FormAtualizacao formAtualizacao;
    
    public MercadoNacional(FormAtualizacao formAtualizacao) {
        this.formAtualizacao = formAtualizacao;
    }

    public int atualiza(){
        int retorno = 0;
        String data;
        Day datad=null;
        
        //   telaLog = new DialogAtualizar();
        Day dataHoje = Uteis.hoje();
        BufferedReader brDatas=null;
        Day ultimaAtualizacao= this.ultimaAtualizacao();
        
        if(ultimaAtualizacao.compareTo(dataHoje)==0) {
            formAtualizacao.informarLog("já está atualizado");
            return 0;
        }
        
        try {
            brDatas =new BufferedReader(new FileReader("datas.dat"));
        } catch (FileNotFoundException ex) {
            formAtualizacao.informarLog("Erro ao abrir datas.dat");
            ex.printStackTrace();
            return 1;
        }
        
        try {
            do {
                data = brDatas.readLine();
                if(data==null) {
                    break;
                }
                datad = Cotacoes.converterEmData(data);
            } while  (datad.compareTo( ultimaAtualizacao ) <= 0);
            
            while(datad.compareTo( dataHoje ) <= 0) {
                
                boolean pegou = this.pegouData(data,this.arquivoDatasJaRealizadas);
                String nomeBDI=null;
                if(!pegou) {
                    boletim.setDataPregao(data);
                    if(this.configuracao.isAtualizaViaInternet()) {
                        formAtualizacao.informarLog("Efetuando download do arquivo " + boletim.getNomeCompactado());                        
                        retorno= boletim.download();
                    } else {
                        if(boletim.existeBoletim()) retorno=0;
                        else retorno=1;
                    }

                    if(operacaoCancelada()) {
                        retorno=5;
                        break;
                    }
                    
                    if(retorno==0) {
                        //conseguiu baixar ou o bdi existe, agora faz
                        // a descompactacao
                        //  nomeBDI="BF070305.zip";
                        String nomeDescompactado = boletim.descompactaArquivo();
                        if(nomeDescompactado!=null) {
                            if( boletim.extraiDados()==0) {
                                this.inclueData(arquivoDatasJaRealizadas, data);
                            }
                        }
                    } else {
                        formAtualizacao.informarLog("Não conseguiu baixar ou arquivo " +
                                boletim.getNomeCompactado() + " não existe.");
                        
                        brDatas.close();
                        formAtualizacao.setTextoBotao("Fechar");
                        formAtualizacao.habilitaBotao(true);
                        retorno=6;
                        break;
                    }
                    
                }
                if(operacaoCancelada()) {
                    retorno=5;
                    break;
                }
                
                data = brDatas.readLine();
                if(data==null) {
                    brDatas.close();
                    retorno=3;
                    break;
                }
                datad = Cotacoes.converterEmData(data);
            }
            
            if(retorno==0) formAtualizacao.informarLog("Atualização concluída");
            
            
        } catch (IOException ex) {
            formAtualizacao.informarLog("Erro ao ler arquivo datas.dat");
            ex.printStackTrace();
            retorno=4;
        }
        
        return retorno;
    }
    
    private boolean operacaoCancelada() {
        boolean retorno = false;
        if(formAtualizacao.isPararAtualizacao()) {
            formAtualizacao.informarLog("******* Atualização cancelada pelo usuário *******");
            formAtualizacao.habilitaBotao(true);
            retorno=true;
            //break;
        }
        return retorno;
    }
    
    private void inclueData(String arquivo, String data) {
        PrintWriter pwOut;
        try {
            pwOut = new PrintWriter(new BufferedWriter(new FileWriter(arquivo, true)));
            pwOut.println(data);
            pwOut.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
    }
    
    
    public boolean pegouData(String dataPregao, String arquivo) {
        boolean retorno = false;
        BufferedReader brDatas=null;
        try {
            brDatas =new BufferedReader(new FileReader(arquivo));
            String data="sfs";
            while(data!=null) {
                data = brDatas.readLine();
                if(dataPregao.equals(data)) {
                    retorno=true;
                    break;
                }
            }
            brDatas.close();
        } catch (FileNotFoundException ex) {
            formAtualizacao.informarLog("Erro ao abrir " + arquivo);
            ex.printStackTrace();
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        return retorno;
    }
    
    public String ultimaAtualizacaoString() {
        return Cotacoes.tail(this.arquivoDatasJaRealizadas,1);
    }
    
    public Day ultimaAtualizacao() {
        String temp = this.ultimaAtualizacaoString();
        Day ultima = null;
        if(temp!=null) {
            ultima = Cotacoes.converterEmData(temp.substring(0,10));
        }
        return ultima;
    }
    
    
    public static String codNegociacaoIndice(String indice) {
        BufferedReader ind = null;
        // TODO : Acrescentar o caminho completo do arquivo de indices
        try {
            ind = new BufferedReader(new FileReader("indicesbovespa"));
        } catch (Exception e) {
            System.err.println("Não foi possivel abrir arquivo: indicesbovespa ");
            return "";
        }
        boolean  temIndice=false;
        String linha;
        String indice2;
        String codigoNegociacao = "";
        try {
            while( (linha=ind.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(linha);
                codigoNegociacao = st.nextToken();
                indice2 = st.nextToken();
                if(indice.equals(indice2)) {
                    temIndice=true;
                    break;
                }
            }
            ind.close();
            
        } catch(IOException ex) {
            System.err.println("Erro ao ler arquivo de indices");
            ex.printStackTrace();
        }
        if(!temIndice) {
            codigoNegociacao="";
        }
        return codigoNegociacao;
    }
    
    
    
}
