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



package grafix.principal;

import grafix.auxiliar.LeitorArquivoAnalise;
import grafix.basedados.Cotacoes;
import grafix.graficos.elementos.AnaliseAcao;
import grafix.telas.JanelaGraficos;
import java.util.Vector;
import org.jfree.data.time.Day;
import org.jfree.date.DateUtilities;

public class Acao {

    private String codAcao;
    private String nomeAcao;
    private Vector<RegistroDiario> registros = null;
    private AnaliseAcao analise;
    private boolean acaoValida;
    public int codbdi;
    public RegistroDiario registro;

    public Acao(String codAcao) {
        setCodAcao(codAcao);
        String nomeAcao_ = Controle.getHistoricoPapeis().getNomeAcao(codAcao);
        if (nomeAcao_ == null) {
            setNomeAcao("");
        } else {
            setNomeAcao(nomeAcao_);
        }
        setAcaoValida(new Cotacoes(Controle.getConfiguracoesGrafix().getPathBaseDados(), 
                 Controle.getConfiguracoesGrafix().getPeriodicidade()).existePapel(codAcao));
        //*debug*/ System.out.println("criar acao " + getCodAcao());
    }

    public void apagarRegistros() {
        this.setRegistros(null);
    }

    private Vector<RegistroDiario> getRegistros() {
        if (registros == null) {
            setAnalise(LeitorArquivoAnalise.getInstance().getAnaliseAcao(getCodAcao()));
            registros = new Cotacoes(Controle.getConfiguracoesGrafix().getPathBaseDados(), Controle.getConfiguracoesGrafix().getPeriodicidade()).retornaRegistros(codAcao);
        }
        return registros;
    }

    private void setRegistros(Vector<RegistroDiario> r) {
        registros = r;
    }

    public Acao(String codAcao, Day dia) {
        this(dia);
        setCodAcao(codAcao);
    }

    public Acao(Day dia) {
        this.registro = new RegistroDiario(dia);
    }

    public int localizarRegistro(JanelaGraficos janela, Day data) {
        final int NOT_FOUND = -1;
        int low = janela.getIntervaloExibicao().getInicio();
        int high = janela.getIntervaloExibicao().getFim() - 1;
        int mid;
        if (Controle.getConfiguracoesUsuario().isExibeSomenteDiasUteis()) {
            while (low <= high) {
                mid = (low + high) / 2;
                if (data.compareTo(this.getRegistro(mid).getDataCorrida()) > 0) {
                    low = mid + 1;
                } else if (data.compareTo(this.getRegistro(mid).getDataCorrida()) < 0) {
                    high = mid - 1;
                } else {
                    return mid;
                }
            }
            return NOT_FOUND;
        } else {
            while (low <= high) {
                mid = (low + high) / 2;
                if (data.compareTo(this.getRegistro(mid).getData()) > 0) {
                    low = mid + 1;
                } else if (data.compareTo(this.getRegistro(mid).getData()) < 0) {
                    high = mid - 1;
                } else {
                    return mid;
                }
            }
            return NOT_FOUND;
        }
    }

    public void addRegistro(RegistroDiario reg) {
        getRegistros().add(reg);
    }

    public RegistroDiario getRegistro(int i) {
        return getRegistros().get(i);
    }

    public String getCodAcao() {
        return codAcao;
    }

    public void setCodAcao(String codAcao) {
        this.codAcao = codAcao;
    }

    public int getNumeroRegistros() {
        return getRegistros().size();
    }

    @Override
    public String toString() {
        return codAcao;
    }

    public String getNomeAcao() {
        return nomeAcao;
    }

    public void setNomeAcao(String nomeAcao) {
        this.nomeAcao = nomeAcao;
    }

    public AnaliseAcao getAnalise() {
        return analise;
    }

    public void setAnalise(AnaliseAcao analise) {
        this.analise = analise;
    }

    public boolean isAcaoValida() {
        return acaoValida;
    }

    public void setAcaoValida(boolean acaoValida) {
        this.acaoValida = acaoValida;
    }

    // METODOS SIMPLIFICADOS PARA A LINGUAGEM LUA -----------------------------------------
    public Day getData(int numRegistro) {
        return getRegistro(numRegistro).getData();
    }

    public Day getData(int numRegistro, boolean exibeSomenteDiasUteis) {
        return getRegistro(numRegistro).getData(exibeSomenteDiasUteis);
    }

    public double getOpen(int numRegistro) {
        return getRegistro(numRegistro).getOpen();
    }

    public double getLow(int numRegistro) {
        return getRegistro(numRegistro).getLow();
    }

    public double getHigh(int numRegistro) {
        return getRegistro(numRegistro).getHigh();
    }

    public double getClose(int numRegistro) {
        return getRegistro(numRegistro).getClose();
    }

    public double getNumNegocios(int numRegistro) {
        return getRegistro(numRegistro).getNumNegocios();
    }

    public double getVolumeQuant(int numRegistro) {
        return getRegistro(numRegistro).getVolumeQuant();
    }

    public double getVolumeDinheiro(int numRegistro) {
        return getRegistro(numRegistro).getVolumeDinheiro();
    }

    public Day getDataCorrida(int numRegistro) {
        return getRegistro(numRegistro).getDataCorrida();
    }

    static public Day criarDataCorrida(int contador) {
        return new Day(DateUtilities.createDate(1900, 1, contador));
    }
}
