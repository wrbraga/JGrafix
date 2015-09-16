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

import grafix.auxiliar.*;
import grafix.principal.*;
import java.io.*;
import java.util.*;
import org.jfree.data.time.Day;

public class Cotacoes {

    private String codAcao;
    private String nomeAcao;
    private RegistroDiario registro;
    private RegistroDiario ultimoRegistro;
    private int codbdi;
    private String diretorioCotacoes;
    private String nomeArquivo;
    private int periodicidade;
    private boolean primeiraVez = true;
    private boolean novaSemana = true;
    private BufferedReader brIn = null;
    private PrintWriter pwOut = null;

    public final static int PERIODICIDADE_DIARIA = 1;
    public final static int PERIODICIDADE_SEMANAL = 2;
    public final static int PERIODICIDADE_MENSAL = 3;

    public Cotacoes(String diretorioCotacoes, int periodicidade) {
        this.registro = new RegistroDiario();
        this.diretorioCotacoes = diretorioCotacoes;
        this.periodicidade = periodicidade;
       
    }

    public Cotacoes(String papel, String diretorioCotacoes, int periodicidade) {
        this(diretorioCotacoes, periodicidade);
        this.nomeArquivo = this.verificaCaminhoCompleto(papel);
        this.codAcao = papel;
    }

    public String ultimaLinha(String arquivo, int bytes2) {
        // Considera que a ultima linha nao tem mais que bytes
        RandomAccessFile raf = null;
        int bytes;
        byte[] buffer = new byte[bytes2];
        String str = "";

        try {
            raf = new RandomAccessFile(verificaCaminhoCompleto(arquivo), "r");
            //  System.out.println("lendo ultima linha " + arquivo);
            long tamanho = raf.length();
            if (tamanho > 0) {
                if (((int) tamanho) > bytes2) {
                    bytes = bytes2;
                    raf.seek(tamanho - bytes);
                } else {
                    bytes = (int) tamanho;
                }
                int bytes_read = raf.read(buffer, 0, bytes);

                //percorre o buffer atï¿½ achar um indicador de nova linha
                int tatr = bytes - 2;
                while (tatr > 0) {
                    tatr--;
                    if (buffer[tatr] == '\n') {
                        break;
                    }
                }

                // Se encontrou algum indicador de final de linha, tatr deve ser maior que zero
                if (tatr > 0) {
                    raf.seek(tamanho - (bytes - tatr - 1));
                    str = raf.readLine();
                }
            }
            raf.close();
            raf = null;
            buffer = null;
        } catch (IOException e) {
            System.err.println(e);
        }
        return str;
    }

    public RegistroDiario decodificaLinhaYahoo(String linha) {
        RegistroDiario reg = null;
        if ((linha != null) && linha.length() > 0) {
            String[] campos = linha.split(",");
            int dia = new Integer(campos[0].substring(8, 10)).intValue();
            int mes = new Integer(campos[0].substring(5, 7)).intValue();
            int ano = new Integer(campos[0].substring(0, 4)).intValue();
            reg = new RegistroDiario(new Day(dia, mes, ano));
            reg.setOpen(new Double(campos[1]).doubleValue());
            reg.setHigh(new Double(campos[2]).doubleValue());
            reg.setLow(new Double(campos[3]).doubleValue());
            reg.setClose(new Double(campos[4]).doubleValue());
            reg.setVolumeDinheiro(new Double(campos[5]).doubleValue());
            reg.setVolumeQuant(reg.getVolumeDinheiro());
            reg.setNumNegocios(0.0);
        }
        return reg;
    }

 
    public RegistroDiario decodificaLinha(String linha) {
        RegistroDiario reg = null;
        if ((linha != null) && linha.length() > 0) {
            StringTokenizer st = new StringTokenizer(linha);
            reg = new RegistroDiario();
            reg.setData(converterEmData(st.nextToken()));
            reg.setOpen(new Double(st.nextToken()).doubleValue());
            reg.setLow(new Double(st.nextToken()).doubleValue());
            reg.setHigh(new Double(st.nextToken()).doubleValue());
            reg.setClose(new Double(st.nextToken()).doubleValue());
            reg.setNumNegocios(new Double(st.nextToken()).doubleValue());
            reg.setVolumeQuant(new Double(st.nextToken()).doubleValue());
            reg.setVolumeDinheiro(new Double(st.nextToken()).doubleValue());
        }
        return reg;
    }

    public static Day converterEmData(String data) throws NumberFormatException {
        return new Day(
                new Integer(data.substring(0, 2)).intValue(),
                new Integer(data.substring(3, 5)).intValue(),
                new Integer(data.substring(6, data.length())).intValue());
    }

    private RegistroDiario leCotacao() {
        RegistroDiario reg = null;
        String linha;
        try {
            linha = this.brIn.readLine();
            if (linha != null) {
                reg = this.decodificaLinha(linha);
            }
        } catch (IOException ex) {
            System.err.println("Erro ao ler cotaoes de " + this.getCodAcao());
            ex.printStackTrace();
        }
        return reg;
    }

    public RegistroDiario proximaCotacao() {
        RegistroDiario reg = new RegistroDiario();
        RegistroDiario registro2 = null;
        novaSemana = false;
        switch (periodicidade) {
            case PERIODICIDADE_DIARIA:
                reg = leCotacao();
                break;
            case PERIODICIDADE_SEMANAL:
                Day dataInicio;
                if (primeiraVez) {
                    reg = leCotacao();
                    ultimoRegistro = reg;
                    novaSemana = false;
                }else{
                    if(ultimoRegistro == null)
                        return null;
                    RegistroDiario.copiaDados(ultimoRegistro, reg);
                }
                dataInicio = reg.getData();
                while (!novaSemana) {
                    primeiraVez = false;
                    registro2 = leCotacao();
                    if (registro2 != null) {
                        Day dataFim = registro2.getData();

                        if (Utils.mesmaSemana(dataInicio, dataFim)) {
                           
                            reg.setClose(registro2.getClose());
                            reg.setVolumeQuant(reg.getVolumeQuant() + registro2.getVolumeQuant());
                            reg.setHigh(Math.max(reg.getHigh(), registro2.getHigh()));
                            reg.setLow(Math.min(reg.getLow(), registro2.getLow()));
                            reg.setNumNegocios(reg.getNumNegocios() + registro2.getNumNegocios());
                            reg.setVolumeDinheiro(reg.getVolumeDinheiro()+registro2.getVolumeDinheiro());


                        } else {
                            novaSemana = true;
                            primeiraVez = false;
                            ultimoRegistro = registro2;

                        }
                    }else{
                        novaSemana = true;
                        ultimoRegistro = registro2;
                    }
                }
                break;
            case PERIODICIDADE_MENSAL:
                if (primeiraVez) {
                    reg = leCotacao();
                    ultimoRegistro = reg;
                    novaSemana = false;
                }else{
                    if(ultimoRegistro == null)
                        return null;
                    RegistroDiario.copiaDados(ultimoRegistro, reg);
                }
                dataInicio = reg.getData();
                while (!novaSemana) {
                    primeiraVez = false;
                    registro2 = leCotacao();
                    if (registro2 != null) {
                        Day dataFim = registro2.getData();

                        if (Utils.mesmoMes(dataInicio, dataFim)) {

                            reg.setClose(registro2.getClose());
                            reg.setVolumeQuant(reg.getVolumeQuant() + registro2.getVolumeQuant());
                            reg.setHigh(Math.max(reg.getHigh(), registro2.getHigh()));
                            reg.setLow(Math.min(reg.getLow(), registro2.getLow()));
                            reg.setNumNegocios(reg.getNumNegocios() + registro2.getNumNegocios());
                             reg.setVolumeDinheiro(reg.getVolumeDinheiro()+registro2.getVolumeDinheiro());
                        } else {
                            novaSemana = true;
                            primeiraVez = false;
                            ultimoRegistro = registro2;

                        }
                    }else{
                        novaSemana = true;
                        ultimoRegistro = registro2;
                    }
                }

            break;

        }
        return reg;
    }

    public Vector<RegistroDiario> retornaRegistros(String codAcao) {
        Vector<RegistroDiario> resultado = new Vector<RegistroDiario>();
        int contador = 0;

        if (this.abreArquivoParaLeitura(codAcao)) {
            RegistroDiario reg = proximaCotacao();
            if (reg != null) {
                do {
                    reg.setDataCorrida(Acao.criarDataCorrida(++contador));
                    reg.gerarTooltip();
                    resultado.add(reg);
                    reg = proximaCotacao();
                } while (reg != null);
            }
            this.fechaArquivoIn();
        }
        if (Controle.getConfiguracoesVolateis().isIntraday()) {
            RegistroDiario intraday = IntradayYahoo.lerIntraday(codAcao);
            if (intraday != null) {
                intraday.setDataCorrida(Acao.criarDataCorrida(++contador));
                intraday.gerarTooltip();
                resultado.add(intraday);
            } else {
                System.out.println("INTRADAY NULL");
            }
        }
        return resultado;
    }

    public void escreveRegistroNoFinal(Acao acao) {
        if (this.abreArquivoParaEscrita(acao.getCodAcao().toLowerCase()) == 0) {
            try {
                this.escreveRegistro(acao.registro);
                this.fechaArquivoOut();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("nï¿½o conseguiu gravar cotacao de " + acao.getCodAcao().toLowerCase());
        }
    }

    public void escreveRegistro() throws IOException {
        this.escreveRegistro(this.getRegistro());
    }

    public void escreveRegistro(RegistroDiario registro) throws IOException {
        String linha = String.format("%02d/%02d/%04d ", registro.getData().getDayOfMonth(),
                registro.getData().getMonth(),
                registro.getData().getYear());
        linha += String.format(Locale.US, "%.2f %.2f %.2f %.2f %.0f %.2f %.2f",
                registro.getOpen(), registro.getLow(),
                registro.getHigh(), registro.getClose(),
                registro.getNumNegocios(), registro.getVolumeQuant(), registro.getVolumeDinheiro());
        this.pwOut.println(linha);

    }

    public RegistroDiario ultimaCotacao(String papel) {
        RegistroDiario reg = null;
        String ultimaLinha = this.ultimaLinha(papel, 1024);
        if (ultimaLinha.length() > 0) {
            reg = this.decodificaLinha(ultimaLinha);
        }
        return reg;
    }

    public boolean abreArquivoParaLeitura(String arquivo) {
        boolean retorno = true;

        String arquivo2 = this.verificaCaminhoCompleto(arquivo);
        try {
            this.brIn = new BufferedReader(new FileReader(arquivo2));
            this.primeiraVez  = true;
        } catch (Exception e) {
            System.err.println("Nï¿½o foi possivel abrir arquivo para leitura: " + arquivo);
            retorno = false;
        }
        return retorno;
    }

    private String verificaCaminhoCompleto(String arquivo) {
        File file = new File(arquivo);
        String path = file.getParent();
        String arquivo2 = null;
        if (path == null) {
            arquivo2 = this.getDiretorioCotacoes() + arquivo;
        } else {
            arquivo2 = arquivo;
        }
        return arquivo2;
    }

    public void fechaArquivoIn() {
        try {
            this.brIn.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void fechaArquivoOut() {
        this.pwOut.close();
    }

    public int abreArquivoParaEscrita(String arquivo) {
        int retorno = 0;
        String arquivo2 = this.verificaCaminhoCompleto(arquivo);
        try {
            this.pwOut = new PrintWriter(new BufferedWriter(new FileWriter(arquivo2, true)));
        } catch (Exception e) {
            System.err.println("Nï¿½o foi possivel abrir arquivo para escrita: " + arquivo2);
            retorno = 1;
        }
        return retorno;
    }

    public int abreArquivoParaEscritaSobreescrevendo(String arquivo) {
        int retorno = 0;
        String arquivo2 = this.verificaCaminhoCompleto(arquivo);
        ManipuladorArquivos.apagarArquivo(arquivo2);

        try {
            this.pwOut = new PrintWriter(new BufferedWriter(new FileWriter(arquivo2, true)));
        } catch (Exception e) {
            System.err.println("Nï¿½o foi possivel abrir arquivo para escrita: " + arquivo2);
            retorno = 1;
        }
        return retorno;
    }

    /**
     * Implementation of a unix-like 'tail' command
     *
     * @param aFileName a file name String
     * @return An array of two strings is returned. At index 0 the String
     *         representation of at most 10 last lines is located.
     *         At index 1 there is an informational string about how large a
     *         segment of the file is being returned.
     *         Null is returned if errors occur (file not found or io exception)
     */
    public static String tail(String aFileName) {
        return tail(aFileName, 10);
    }

    /**
     * Implementation of a unix-like 'tail -n' command
     *
     * @param aFileName a file name String
     * @param n int number of lines to be returned
     * @return An array of two strings is returned. At index 0 the String
     *         representation of at most n last lines is located.
     *         At index 1 there is an informational string about how large a
     *         segment of the file is being returned.
     *         Null is returned if errors occur (file not found or io exception)
     */
    public static String tail(String aFileName, int n) {
        try {
            return tail(new RandomAccessFile(new File(aFileName), "r"), n);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Implementation of a unix-like 'tail -n' command
     *
     * @param raf a RandomAccessFile to tail
     * @param n int number of lines to be returned
     * @return An array of two strings is returned. At index 0 the String
     *         representation of at most n last lines is located.
     *         At index 1 there is an informational string about how large a
     *         segment of the file is being returned.
     *         Null is returned if errors occur (file not found or io exception)
     */
    public static String tail(RandomAccessFile raf, int n) {
        int BUFFERSIZE = 1024;
        long pos;
        long endPos;
        long lastPos;
        int numOfLines = 0;
        String info = null;
        byte[] buffer = new byte[BUFFERSIZE];
        StringBuffer sb = new StringBuffer();
        try {
            endPos = raf.length();
            lastPos = endPos;

            // Check for non-empty file
            // Check for newline at EOF
            if (endPos > 0) {
                byte[] oneByte = new byte[1];
                raf.seek(endPos - 1);
                raf.read(oneByte);
                if ((char) oneByte[0] != '\n') {
                    numOfLines++;
                }
            }

            do {
                // seek back BUFFERSIZE bytes
                // if length of the file if less then BUFFERSIZE start from BOF
                pos = 0;
                if ((lastPos - BUFFERSIZE) > 0) {
                    pos = lastPos - BUFFERSIZE;
                }
                raf.seek(pos);
                // If less then BUFFERSIZE avaliable read the remaining bytes
                if ((lastPos - pos) < BUFFERSIZE) {
                    int remainer = (int) (lastPos - pos);
                    buffer = new byte[remainer];
                }
                raf.readFully(buffer);
                // in the buffer seek back for newlines
                for (int i = buffer.length - 1; i >= 0; i--) {
                    if ((char) buffer[i] == '\n') {
                        numOfLines++;
                        // break if we have last n lines
                        if (numOfLines > n) {
                            pos += (i + 1);
                            break;
                        }
                    }
                }
                // reset last postion
                lastPos = pos;
            } while ((numOfLines <= n) && (pos != 0));

            // print last n line starting from last postion
            for (pos = lastPos; pos < endPos; pos += buffer.length) {
                raf.seek(pos);
                if ((endPos - pos) < BUFFERSIZE) {
                    int remainer = (int) (endPos - pos);
                    buffer = new byte[remainer];
                }
                raf.readFully(buffer);
                sb.append(new String(buffer));
            }

            info = "lista.size()= " + sb.length() + " raf.length= " + raf.length();
        } catch (FileNotFoundException e) {
            sb = null;
        } catch (IOException e) {
            e.printStackTrace();
            sb = null;
        } finally {
            try {
                if (raf != null) {
                    raf.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (sb == null) {
            return null;
        }
        String tmp = sb.toString();

        return tmp;
    }

    public boolean existePapel(String papel) {
        String nomeArq = this.verificaCaminhoCompleto(papel);
        File file = new File(nomeArq);

        return file.exists();
    }

    public boolean removeArquivo(String papel) {
        String nomeArq = this.verificaCaminhoCompleto(papel);
        File file = new File(nomeArq);
        return file.delete();
    }

    public String[] ultimasCotacoes(String papel, int pregoes) {
        String temp = Cotacoes.tail(this.verificaCaminhoCompleto(papel), pregoes);
        temp = temp.replaceAll("\r\n", "\n");
        temp = temp.replaceAll("\r", "\n");
        String[] ultimasCotacoes = temp.split("\n");
        return ultimasCotacoes;
    }

    public Day dataUltimaCotacao() {
        String ultimaLinha = Cotacoes.tail(this.verificaCaminhoCompleto(this.getCodAcao()), 1);
        Day dia = null;
        if (ultimaLinha != null) {

            dia = new Day(new Integer(ultimaLinha.substring(0, 2)).intValue(),
                    new Integer(ultimaLinha.substring(3, 5)).intValue(),
                    new Integer(ultimaLinha.substring(6, 10)).intValue());
        }
        return dia;
    }

    public String getCodAcao() {
        return codAcao;
    }

    public void setCodAcao(String codAcao) {
        this.codAcao = codAcao;
    }

    public String getNomeAcao() {
        return nomeAcao;
    }

    public void setNomeAcao(String nomeAcao) {
        this.nomeAcao = nomeAcao;
    }

    public RegistroDiario getRegistro() {
        return registro;
    }

    public void setRegistro(RegistroDiario registro) {
        this.registro = registro;
    }

    public int getCodbdi() {
        return codbdi;
    }

    public void setCodbdi(int codbdi) {
        this.codbdi = codbdi;
    }

    public String getDiretorioCotacoes() {
        return diretorioCotacoes;
    }

    public void setDiretorioCotacoes(String diretorioCotacoes) {
        this.diretorioCotacoes = diretorioCotacoes;
    }
}
