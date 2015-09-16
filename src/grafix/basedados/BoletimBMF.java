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
 * BoletimBMF.java SDD
 *
 * Created on 24 de Julho de 2007, 15:24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package grafix.basedados;

import grafix.principal.Acao;
import grafix.principal.Controle;
import grafix.telas.secundarias.FormAtualizacao;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import org.jfree.data.time.Day;

/**
 *
 * @author joao
 */
public class BoletimBMF extends Boletim{
    private static String urlBMF = "http://www.bmf.com.br/ftp/ContratosPregaoFinal/";
    private BufferedReader b = null;
    private int dia, mes, ano;
    /** Creates a new instance of BoletimBovespa */
 

    
    public BoletimBMF(ConfiguracaoBaseDados configuracaoBaseDados, FormAtualizacao formAtualizacao) {
        super(configuracaoBaseDados, formAtualizacao);
    }
    
    
    public BoletimBMF(String data, ConfiguracaoBaseDados configuracaoBaseDados, FormAtualizacao formAtualizacao) {
        super(data, configuracaoBaseDados, formAtualizacao);
    }
    protected String montaURL() {
        return this.urlBMF + this.getNomeCompactado();
    }

    protected String montaNome(String dataPregao) {
        return "BF" +dataPregao.substring(8,10) + dataPregao.substring(3,5) +
                    dataPregao.substring(0,2) +".ex_";
    }
        
     public int extraiDados()  {
        int retorno=0;
        try {
            this.b = new BufferedReader(new FileReader(this.getNomeDescompactado()));
        } catch (Exception e) {
            formAtualizacao.informarLog("Não foi possivel abrir arquivo: " + this.getNomeCompactado());
            return 1;
        }
        PrintWriter pwBmf=null;
        try {
            pwBmf = new PrintWriter(new BufferedWriter(new FileWriter("resource/listas/bmf.lst")));
        } catch (IOException ex) {
            pwBmf=null;
            ex.printStackTrace();
        }
        double MaxInd, MaxDol, MaxAlcool, MaxBoigordo, MaxMilho, MaxCafe;
        double MaxCbond, MaxCupomcambial, MaxAcucar, MaxOuro;
        MaxInd=0;
        MaxDol=0;
        MaxAlcool=0;
        MaxBoigordo=0;
        MaxMilho=0;
        MaxCafe=0;
        MaxCbond=0;
        MaxCupomcambial=0;
        MaxAcucar=0;
        MaxOuro=0;
        
        String linha = this.proximaLinhaBDI(this.b);
        this.ano = new Integer(linha.substring(11,15)).intValue();
        this.mes = new Integer(linha.substring(15,17)).intValue();
        this.dia = new Integer(linha.substring(17,19)).intValue();
        //System.out.println("dia " + dia + " mes " + mes + "ano " + ano  );
        Day dataPregao = new Day(dia,mes,ano);
        
        int mercado;
        Acao acao = new Acao(new Day(dia,mes,ano));
        Acao indbmf = new Acao("indfut",new Day(dia,mes,ano));
        Acao dolar = new Acao("dolar",new Day(dia,mes,ano));
        Acao alcool = new Acao("alcool",new Day(dia,mes,ano));
        Acao boiGordo = new Acao("boigordo",new Day(dia,mes,ano));
        Acao milho = new Acao("milho",new Day(dia,mes,ano));
        Acao cafe = new Acao("cafe",new Day(dia,mes,ano));
        Acao cbond = new Acao("cbond",new Day(dia,mes,ano));
        Acao cupomCambial = new Acao("cupomcambial",new Day(dia,mes,ano));
        Acao acucar = new Acao("acucar",new Day(dia,mes,ano));
        Acao ouro = new Acao("ouro",new Day(dia,mes,ano));
        
        
        
        
        Cotacoes cotacao = new Cotacoes(Controle.getConfiguracoesGrafix().getPathBaseDados(),1);
        do {
            String mercadoria = linha.substring(21,24);
            mercado = (new Integer(""+linha.charAt(24))).intValue();
            this.destrinchaBMF(linha, acao);
            
            ano = new Integer(linha.substring(11,15)).intValue();
            mes = new Integer(linha.substring(15,17)).intValue();
            dia = new Integer(linha.substring(17,19)).intValue();
            
            Day diaLinha =new Day(dia,mes,ano);
            
            if(dataPregao.compareTo(diaLinha)!=0) {
                formAtualizacao.informarLog("Encontrado datas diferentes dentro do mesmo BDFinal.txt");
            }
            
            if( (mercado==2 || mercado==1) && (acao.getCodAcao().length()>0) &&
                    (acao.registro.getNumNegocios() >0)) {
                // escreve as cotacoes
                //cotacao.escreveRegistroNoFinal(acao);
                formAtualizacao.informarLog("Extraindo dados de " +acao.getCodAcao() );
                
                if(acao.registro.getNumNegocios()>0){
                    if(mercadoria.equals("DOL")) {
                        acao.registro.setOpen(acao.registro.getOpen()/1000.0);
                        acao.registro.setLow(acao.registro.getLow()/1000.0);
                        acao.registro.setHigh(acao.registro.getHigh()/1000.0);
                        acao.registro.setClose(acao.registro.getClose()/1000.0);
                        
                    }
                    cotacao.escreveRegistroNoFinal(acao);
                    pwBmf.println(acao.getCodAcao());
                }
            }
            
            if(mercadoria.equals("DOL") && (mercado==2)) {
                if(acao.registro.getNumNegocios()>MaxDol) {
                    MaxDol = acao.registro.getNumNegocios();
                    this.copiaRegistro(acao.registro,dolar.registro);
                }
            }
            
            if(mercadoria.equals("IND") && (mercado==2)) {
                if(acao.registro.getNumNegocios()>MaxInd) {
                    MaxInd = acao.registro.getNumNegocios();
                    this.copiaRegistro(acao.registro,indbmf.registro);
                }
            }
            
            if(mercadoria.equals("ALA") && (mercado==2)) {
                if(acao.registro.getNumNegocios()>MaxAlcool) {
                    MaxAlcool = acao.registro.getNumNegocios();
                    this.copiaRegistro(acao.registro,alcool.registro);
                }
            }
            
            if(mercadoria.equals("BCB") && (mercado==2)) {
                if(acao.registro.getNumNegocios()>MaxCbond) {
                    MaxCbond = acao.registro.getNumNegocios();
                    this.copiaRegistro(acao.registro,cbond.registro);
                }
            }
            
            if(mercadoria.equals("BGI") && (mercado==2)) {
                if(acao.registro.getNumNegocios()>MaxBoigordo) {
                    MaxBoigordo = acao.registro.getNumNegocios();
                    this.copiaRegistro(acao.registro,boiGordo.registro);
                }
            }
            
            if(mercadoria.equals("CNI") && (mercado==2)) {
                if(acao.registro.getNumNegocios()>MaxMilho) {
                    MaxMilho = acao.registro.getNumNegocios();
                    this.copiaRegistro(acao.registro,milho.registro);
                }
            }
            
            
            if(mercadoria.equals("DDI") && (mercado==2)) {
                if(acao.registro.getNumNegocios()>MaxCupomcambial) {
                    MaxCupomcambial = acao.registro.getNumNegocios();
                    this.copiaRegistro(acao.registro,cupomCambial.registro);
                }
            }
            
            if(mercadoria.equals("ICF") && (mercado==2)) {
                if(acao.registro.getNumNegocios()>MaxCafe) {
                    MaxCafe = acao.registro.getNumNegocios();
                    this.copiaRegistro(acao.registro,cafe.registro);
                }
            }
            
            if(mercadoria.equals("ISU") && (mercado==2)) {
                if(acao.registro.getNumNegocios()>MaxAcucar) {
                    MaxAcucar = acao.registro.getNumNegocios();
                    this.copiaRegistro(acao.registro,acucar.registro);
                }
            }
            
            if(mercadoria.equals("OZI") && (mercado==2)) {
                if(acao.registro.getNumNegocios()>MaxOuro) {
                    MaxOuro = acao.registro.getNumNegocios();
                    this.copiaRegistro(acao.registro,ouro.registro);
                }
            }
            
            linha = this.proximaLinhaBDI(this.b);
            
        } while (linha!=null);
        try {
            
            this.b.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        if(MaxInd>0) {
            cotacao.escreveRegistroNoFinal(indbmf);
        }
        pwBmf.println("indfut");
        
        if(MaxDol>0) {
            cotacao.escreveRegistroNoFinal(dolar);
        }
        pwBmf.println("dolar");
        
        if(MaxAlcool>0) {
            cotacao.escreveRegistroNoFinal(alcool);
        }
        pwBmf.println("alcool");
        
        if(MaxCbond>0) {
            cotacao.escreveRegistroNoFinal(cbond);
        }
        pwBmf.println("cbond");
        
        if(MaxBoigordo>0) {
            cotacao.escreveRegistroNoFinal(boiGordo);
        }
        pwBmf.println("boigordo");
        
        if(MaxCupomcambial>0) {
            cotacao.escreveRegistroNoFinal(cupomCambial);
        }
        pwBmf.println("cupomcambial");
        
        if(MaxCafe>0) {
            cotacao.escreveRegistroNoFinal(cafe);
        }
        pwBmf.println("cafe");
        
        if(MaxAcucar>0) {
            cotacao.escreveRegistroNoFinal(acucar);
        }
        pwBmf.println("acucar");
        
        if(MaxOuro>0) {
            cotacao.escreveRegistroNoFinal(ouro);
        }
        pwBmf.println("ouro");
        
        pwBmf.close();
        
        return 0;
        
    }
    
     private void destrinchaBMF(String linha, Acao acao) {
        double prec = 10.0;
        int decimais = (new Integer(""+linha.charAt(315))).intValue();
        prec = Math.pow(10.0,decimais);
        //  acao.codbdi = new Integer(linha.substring(2,4)).intValue();
        //RetiraString(3,4,registro,palavra,3); /* codigo dbi */
        
        acao.setNomeAcao(linha.substring(454,474).trim().toLowerCase());
        acao.setCodAcao(acao.getNomeAcao());
        
        acao.registro.setOpen(new Double(linha.substring(149,157)).doubleValue()/prec);
        //RetiraString(150,157,registro,palavra,22); /* preco de abertura  */
        
        acao.registro.setHigh(new Double(linha.substring(167,175)).doubleValue()/prec);
        //RetiraString(168,175,registro,palavra,22); /* preco maximo   */
        
        acao.registro.setLow(new Double(linha.substring(158,166)).doubleValue()/prec);
        //RetiraString(159,166,registro,palavra,22); /* preco minimo   */
        
        acao.registro.setClose(new Double(linha.substring(190,198)).doubleValue()/prec);
        //RetiraString(191,198,registro,palavra,22); /* ultimo preco   */
        
        
        acao.registro.setNumNegocios(new Double(linha.substring(104,112)).doubleValue());
        //RetiraString(105,112,registro,palavra,22); /* numero de negocios efetuados no pregao   */
        
        acao.registro.setVolumeQuant(new Double(linha.substring(112,120)).doubleValue());
        //RetiraString(113,120,registro,palavra,22); /* numero de titulos negociados no pregao   */
        
        acao.registro.setVolumeDinheiro(new Double(linha.substring(70,83)).doubleValue());
        //RetiraString(71,83,registro,palavra,22); /* volume total de titulos negociados no pregao   */
    }
    
    
    
    
} 
