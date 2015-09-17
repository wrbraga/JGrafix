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
 * BoletimBovespa.java
 *
 * Created on 24 de Julho de 2007, 15:07
 *
 */
package grafix.basedados;

import grafix.principal.Acao;
import grafix.principal.Controle;
import grafix.principal.RegistroDiario;
import grafix.telas.secundarias.FormAtualizacao;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.jfree.data.time.Day;

/**
 *
 * @author joao
 */
class BoletimBovespa extends Boletim {
    // http://www.bmfbovespa.com.br/fechamento-pregao/bdi/bdi0915.zip 
    // http://www.bmfbovespa.com.br/fechamento-pregao/bdi/ajus0915.zip
    // http://www.negrijp.blog.br/2011/02/download-dos-arquivos-de-mercado-da-bm.html
    
    /* Link para cotações hitóricas anuais, no exemplo abaixo é o ano de 2010
     http://www.bmfbovespa.com.br/InstDados/SerHist/COTAHIST_A2010.ZIP
    */
    
    private static String urlBovespa = "http://www.bmfbovespa.com.br/fechamento-pregao/bdi/";
   
    private BufferedReader b = null;
    private int codigoRegistro;
    private int dia,  mes,  ano;
    private Acao ibvsp;

    /** Creates a new instance of BoletimBovespa */
    public BoletimBovespa(ConfiguracaoBaseDados configuracaoBaseDados, FormAtualizacao formAtualizacao) {
        super(configuracaoBaseDados, formAtualizacao);
    }

    public BoletimBovespa(String data, ConfiguracaoBaseDados configuracaoBaseDados, FormAtualizacao formAtualizacao) {
        super(data, configuracaoBaseDados, formAtualizacao);
    }

    protected String montaNome(String dataPregao) {
        return "bdi" + dataPregao.substring(3, 5) + dataPregao.substring(0, 2) + ".zip";
    }

    protected String montaURL() {
        return BoletimBovespa.urlBovespa + this.getNomeCompactado();
    }
    //abstract String montaNomeBDI(String dataPregao);
    @Override
    public int download() {
        Download download = this.instaciaDownload();
        int retorno = 0;

        retorno = download.baixaArquivo();
       

        return retorno;
    }

    public int extraiDados() {
        int retorno = 0;
        String linha;
        try {
            this.b = new BufferedReader(new FileReader(this.getNomeDescompactado()));
        } catch (Exception e) {
            System.err.println("Não foi possivel abrir arquivo: " + this.getNomeDescompactado());
            return 1;
        }
        linha = this.proximaLinhaBDI(b);
        this.ano = new Integer(linha.substring(30, 34)).intValue();
        this.mes = new Integer(linha.substring(34, 36)).intValue();
        this.dia = new Integer(linha.substring(36, 38)).intValue();
        formAtualizacao.informarLog("dia " + dia + " mes " + mes + "ano " + ano);

        linha = this.retiraInformacoesIndices();
        linha = this.retiraInformacoesPapeis(linha);

        // complenta as informaçoes do ibovespa
        Acao acao = new Acao(new Day(dia, mes, ano));
        while (this.codigoRegistro == 3) {
            this.destrinchaResumoDiario(linha, acao);
            if (acao.codbdi == 99) {
                this.ibvsp.registro.setNumNegocios(acao.registro.getNumNegocios());
                this.ibvsp.registro.setVolumeQuant(acao.registro.getVolumeQuant());
                this.ibvsp.registro.setVolumeDinheiro(acao.registro.getVolumeDinheiro());
                Cotacoes cotacao = new Cotacoes(Controle.getConfiguracoesGrafix().getPathBaseDados(),1);
                cotacao.escreveRegistroNoFinal(this.ibvsp);

            }
            linha = this.proximaLinhaBDI(b);
            this.codigoRegistro = new Integer(linha.substring(0, 2)).intValue();

        }
        try {
            this.b.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return retorno;
    }

    private String retiraInformacoesIndices() {
        Cotacoes cotacao = new Cotacoes(Controle.getConfiguracoesGrafix().getPathBaseDados(),1);
        ibvsp = new Acao(new Day(dia, mes, ano));
        String linha = null;
        // pula as primeiras linhas
        while (codigoRegistro != 1) {
            linha = this.proximaLinhaBDI(b);
            this.codigoRegistro = new Integer(linha.substring(0, 2)).intValue();
            formAtualizacao.informarLog("codigoRegistro " + codigoRegistro);
        }
        Acao acao = new Acao(new Day(dia, mes, ano));
        //acao.registro.setData(new Day(dia,mes,ano));
        String codigoNegociacao;
        while (codigoRegistro == 1) {
            this.destrinchaIndices(linha, acao);
            acao.setNomeAcao(Uteis.removeBrancos(acao.getNomeAcao()).toLowerCase());
            formAtualizacao.informarLog(acao.getNomeAcao());
            codigoNegociacao = MercadoNacional.codNegociacaoIndice(acao.getNomeAcao());
            acao.setCodAcao(codigoNegociacao);
            if (codigoNegociacao.length() == 0) {
                formAtualizacao.informarLog("Encontrado novo indice " + acao.getNomeAcao());
                MercadoBovespa.adicionaNovoIndice(acao.getNomeAcao());
                codigoNegociacao = acao.getNomeAcao();
            }

            if (!codigoNegociacao.equals("ibvsp")) {
                //escreve as cotacoes
                cotacao.escreveRegistroNoFinal(acao);
            } else {
                /* O IBVSP fica separado, não é escrito logo no arquivo de cotaï¿½ï¿½es porque o
                 * volume total em dinheiro ï¿½ retirado do total de todos os negï¿½cios
                 */
                ibvsp.setCodAcao(codigoNegociacao);
                //ibvsp.codneg = codigoNegociacao;
                ibvsp.registro.setOpen(acao.registro.getOpen());
                ibvsp.registro.setLow(acao.registro.getLow());
                ibvsp.registro.setHigh(acao.registro.getHigh());
                ibvsp.registro.setClose(acao.registro.getClose());
            }
            linha = this.proximaLinhaBDI(b);
            this.codigoRegistro = new Integer(linha.substring(0, 2)).intValue();
        }

        return linha;

    }

    private void destrinchaLinha(String linha, Acao acao) {

        acao.codbdi = new Integer(linha.substring(2, 4)).intValue();
        //RetiraString(3,4,registro,palavra,3); /* codigo dbi */

        acao.setNomeAcao(linha.substring(34, 46).trim());
//        RetiraString(35,46,registro,palavra,20); /* nome resumido */
//        RetiraEspacosADireita(acao->nomres);

        acao.registro.setEspeci(linha.substring(46, 56).trim());
//        RetiraString(47,56,registro,palavra,20); /* especificacao do papel: on pn */

        acao.registro.setIndcar(linha.charAt(56));
//        RetiraString(57,57,registro,palavra,20); /* indicador de caracteristica do papel: */
//        strncpy(&acao->indcar,palavra,1);       /* . participa do indice bovespa, etc */

        acao.setCodAcao(linha.substring(57, 69).trim());
//        RetiraString(58,69,registro,palavra,20); /* codigo da acao */

        acao.registro.setOpen(new Double(linha.substring(90, 101)).doubleValue() / 100.0);
//        RetiraString(91,101,registro,palavra,20); /* preco de abertura  */

        acao.registro.setHigh(new Double(linha.substring(101, 112)).doubleValue() / 100.0);
//        RetiraString(102,112,registro,palavra,20); /* preco maximo   */

        acao.registro.setLow(new Double(linha.substring(112, 123)).doubleValue() / 100.0);
//        RetiraString(113,123,registro,palavra,20); /* preco minimo   */

        acao.registro.setClose(new Double(linha.substring(134, 145)).doubleValue() / 100.0);
        //  RetiraString(135,145,registro,palavra,20); /* ultimo preco   */

        acao.registro.setSinosc(linha.charAt(145));
        //RetiraString(146,146,registro,palavra,20); /* sinal da oscilacao em relacao ao pregao anterior */


        if (acao.registro.getSinosc() == '-') {
            acao.registro.setOscila(-new Double(linha.substring(146, 151)).doubleValue() / 100.0);
        } else {
            acao.registro.setOscila(new Double(linha.substring(146, 151)).doubleValue() / 100.0);
        }
//        RetiraString(147,151,registro,palavra,20); /* oscilacao em relacao ao pregao anterior   */

        acao.registro.setNumNegocios(new Double(linha.substring(173, 178)).doubleValue());
//        RetiraString(174,178,registro,palavra,20); /* numero de negocios efetuados no pregao   */

        acao.registro.setVolumeQuant(new Double(linha.substring(178, 193)).doubleValue());
//        RetiraString(179,193,registro,palavra,20); /* numero de titulos negociados no pregao   */

        acao.registro.setVolumeDinheiro(new Double(linha.substring(193, 210)).doubleValue() / 100.0);
    //RetiraString(194,210,registro,palavra,20); /* volume total de titulos negociados no pregao   */

    }

    private String retiraInformacoesPapeis(String linha) {
        Acao acao = new Acao(new Day(dia, mes, ano));
        String papel;
        TiposPapeis global = this.configuracao.getTiposPapeis();
        PrintWriter pwIbovespa = null;
        PrintWriter pwOpcoes = null;
        ArrayList cadastroPapeis = new ArrayList();
        try {
            pwIbovespa = new PrintWriter(new BufferedWriter(new FileWriter("resource/listas/ibovespa.lst")));
            pwOpcoes = new PrintWriter(new BufferedWriter(new FileWriter("resource/listas/opcoes.lst")));
        } catch (IOException ex) {
            pwIbovespa = null;
            pwOpcoes = null;
            ex.printStackTrace();
        }

        while (this.codigoRegistro == 2) {
            this.destrinchaLinha(linha, acao);
            papel = acao.getCodAcao();
            papel = papel.trim();
            papel = papel.toLowerCase();
            boolean escreve = false;
            if (acao.registro.getNumNegocios() > 0) { //Se houve negocios com o papel naquele dia, extrai os dados
                switch (acao.codbdi) {
                    case 2: //lote padrao
                        if (global.isLotePadrao()) {
                            escreve = true;
                        }
                        break;
                    case 6: //concordatarias
                        if (global.isConcordatarias()) {
                            escreve = true;
                        }
                        break;
                    case 8: // Recuperacao Judicial
                        if (global.isRecJudic()) {
                            escreve = true;
                        }
                        break;
                    case 10: //Direito / recibo
                        if (global.isDireitoRecibo()) {
                            escreve = true;
                        }
                        break;
                    case 12: // Fundo Imobiliario
                        if (global.isFundoImob()) {
                            escreve = true;
                        }
                        break;
                    case 14: // Cer / Debentures / dividas / BDR
                        if (global.isCerDebDivBdr()) {
                            escreve = true;
                        }
                        break;
                    case 22: //Bonus
                        if (global.isBonus()) {
                            escreve = true;
                        }
                        break;
                    case 58: //outros
                        escreve = true;
                        break;
                    case 62: //Termo
                        if (global.isTermo()) {
                            escreve = true;
                        }
                        break;
                    case 74: //Opcoes de compra do indices
                        if (global.isOpcCompIndice()) {
                            escreve = true;
                        }
                        break;
                    case 75: //Opcoes de venda do indices
                        if (global.isOpcVendaIndice()) {
                            escreve = true;
                        }
                        break;
                    case 78: //Opcoes de compra
                        if (global.isOpcoesCompra()) {
                            escreve = true;
                        }
                        break;
                    case 82: // opcoes de venda
                        if (global.isOpcoesVenda()) {
                            escreve = true;
                        }
                        break;
                    case 96: //fracionario
                        if (global.isFracionario()) {
                            escreve = true;
                        }
                        break;
                }
                if (escreve) {
                    formAtualizacao.informarLog("extraindo dados de " + acao.getCodAcao().toLowerCase() + "-" +
                            acao.getNomeAcao() + " " + acao.registro.getEspeci());
                    this.escreveRegistroArquivo(acao);

                    // escreve nos arquivos opcoes e ibovespa
                    if (acao.registro.getIndcar() == '.') {
                        if (pwIbovespa != null) {
                            pwIbovespa.println(papel);
                        }
                    }

                    if ((acao.codbdi == 78) || (acao.codbdi == 82)) {
                        if (pwOpcoes != null) {
                            pwOpcoes.println(papel);
                        }
                    }
                    double liquidez = retornaLiquidez(papel, this.configuracao.getPregoesMostrados());
                    CadastroPapeis cadastro = new CadastroPapeis(papel, acao.getNomeAcao() +
                            " " + acao.registro.getEspeci(), liquidez);
                    cadastroPapeis.add(cadastro);
                }
            }

            linha = this.proximaLinhaBDI(b);
            this.codigoRegistro = new Integer(linha.substring(0, 2)).intValue();
        }
        // ConfiguracaoXML configuracaoXML = new ConfiguracaoXML(ConfiguracoesGrafix.ARQUIVO_CADASTRO_PAPEIS);
        // configuracaoXML.guardaNoXML(cadastroPapeis);
        Controle.getHistoricoPapeis().atualizar(cadastroPapeis);
        pwIbovespa.close();
        pwOpcoes.close();
        return linha;
    }

    private double retornaLiquidez(String papel, int npregoes) {
        double liquidez = 0.0;
        Cotacoes cotacao = new Cotacoes(Controle.getConfiguracoesGrafix().getPathBaseDados(), 1);
        // retorna os ultimos pregosMostrados do arquivo Pregoes.dat

        String datas = Cotacoes.tail(cotacao.getDiretorioCotacoes() + "cfg/datasjarealizadas", npregoes);
        datas = datas.replaceAll("\r\n", "\n");
        datas = datas.replaceAll("\r", "\n");

        String[] ultimosPregoes = datas.split("\n");

        // retorna as ultimas cotacoes do papel

        String[] ultimasCotacoes = cotacao.ultimasCotacoes(papel, npregoes);
        Day primeiroDia = Cotacoes.converterEmData(ultimosPregoes[0]);

        for (int i = 0; i < ultimasCotacoes.length; ++i) {
            String data = ultimasCotacoes[i].substring(0, 10);
            if (primeiroDia.compareTo(Cotacoes.converterEmData(data)) <= 0) {
                liquidez += 1;
            }
        }
        return 100.0 * liquidez / (double) npregoes;

    }

    private void escreveRegistroArquivo(Acao acao) {

        Cotacoes cotacao = new Cotacoes(Controle.getConfiguracoesGrafix().getPathBaseDados(), 1);

        //Verifica se existe necessidade de ajuste e realiza o ajuste caso necessario
        if ((acao.registro.getSinosc() == '-') || (acao.registro.getSinosc() == '+') || (acao.registro.getSinosc() == '=')) {
            RegistroDiario ultima = cotacao.ultimaCotacao(acao.getCodAcao().toLowerCase());
            // FormAtualizar.informarLog(acao.getCodAcao().toLowerCase());
            if (ultima != null) {
                double ultcalc = 0.0;
                if (acao.registro.getOscila() != 0) {
                    ultcalc = acao.registro.getClose() / (1.0 + acao.registro.getOscila() / 100.0);
                } else {
                    ultcalc = acao.registro.getClose();
                }

                double oscilacao = Math.abs(1 - (ultima.getClose() / ultcalc));
                if (oscilacao > 0.01) {
                    Ajustes ajuste = new Ajustes();
                    ajuste.fatorCorrecao = ultima.getClose() / ultcalc;
                    ajuste.ultimoDia = acao.registro.getData();
                    ajuste.papel = acao.getCodAcao().toLowerCase();
                    formAtualizacao.informarLog("Ajustando " + ajuste.papel);
                    ajuste.ajustaPapel(Controle.getConfiguracoesGrafix().getPathBaseDados());
                }

            }

        }
        cotacao.escreveRegistroNoFinal(acao);

    }

    private void destrinchaResumoDiario(String registro, Acao acao) {
        acao.codbdi = new Integer(registro.substring(2, 4)).intValue();
        acao.setNomeAcao(registro.substring(4, 34));/* nome resumido */
        acao.getNomeAcao().trim();
        acao.registro.setNumNegocios(new Double(registro.substring(34, 39)).doubleValue());
        acao.registro.setVolumeQuant(new Double(registro.substring(39, 54)).doubleValue());
        acao.registro.setVolumeDinheiro(new Double(registro.substring(54, 71)).doubleValue() / 100.00);

    }

    private void destrinchaIndices(String registro, Acao acao) {
        acao.codbdi = new Integer(registro.substring(2, 4)).intValue();  //codigo bdi

        acao.setNomeAcao(registro.substring(4, 34).trim());/* nome resumido */

        acao.setCodAcao(acao.getNomeAcao());

        acao.registro.setOpen(new Double(registro.substring(34, 40)).doubleValue());

        acao.registro.setLow(new Double(registro.substring(40, 46)).doubleValue());

        acao.registro.setHigh(new Double(registro.substring(46, 52)).doubleValue());

        acao.registro.setClose(new Double(registro.substring(92, 98)).doubleValue());

        acao.registro.setNumNegocios(new Double(registro.substring(158, 164)).doubleValue());

        acao.registro.setVolumeQuant(new Double(registro.substring(164, 179)).doubleValue());

        acao.registro.setVolumeDinheiro(new Double(registro.substring(179, 196)).doubleValue() / 100.00);

    }
}
