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



package grafix.graficos.indices;

import grafix.auxiliar.ManipuladorArquivos;
import grafix.principal.*;
import java.awt.Color;
import java.util.Vector;
import javax.swing.JOptionPane;
import org.keplerproject.luajava.*;

public class USERIndice extends Indice{
    
    public USERIndice() {
        this(null, 2, false, 1, 1, 0);
    }
    
    public USERIndice(Color cor, int espessura, boolean f, int p1, int p2, int p3) {
        super(ConstrutorDeIndices.INDICE_USER, null, cor, espessura, f,p1,p2,p3);
        criarFlag("Flag", f);
        criarParam1("Parâmetro 1", p1);
        criarParam2("Parâmetro 2", p2);
    }

    @Override
    public String getAbrevIndice() {
        if(abrevIndice == null) {
            setAbrevIndice(definirAbrevIndice());
        }
        return abrevIndice;
    }
    
    protected void configurarIndice() {
        this.setNomeIndice(null);
    }
    
    @Override
    public String getNomeIndice() {
        if(super.getNomeIndice() == null && getFileLua() != null) {
            this.setNomeIndice(getFileLua().substring(0, getFileLua().length() - 4) + " (Script LUA)");
        } 
        return super.getNomeIndice();
    }
    
    public String definirAbrevIndice() {
        int posHifen = getFileLua().indexOf("-");
        if (posHifen == -1) {
            return "USER";
        } else {
            return getFileLua().substring(0, posHifen);
        }
    }
    
    @Override
    public String getTagIndice() {
        if(getParam2() > 1) {
            return getAbrevIndice() + "-" + getParam1() + "/" + getParam2();
        } else if(getParam1() > 1) {
            return getAbrevIndice() + "-" + getParam1();
        } else {
            return getAbrevIndice();
        }
    }
    
    protected void calcularValoresIndice(Acao acao) {
        if(getFileLua()!=null) {
            LuaState L = LuaStateFactory.newLuaState();
            L.openLibs();
            try {
                L.pushObjectValue(acao);
                L.setGlobal("acao");
                L.pushObjectValue(this);
                L.setGlobal("indice");
            } catch (LuaException ex) {
                ex.printStackTrace();
            }
            
//        checarLUA(L.LdoFile("lua/" + getFileLua()));
            checarLUA(L.LdoString(getCodigoLUA()));
            L.close();
        }
    }
    
    public void setValoresLUA(Acao acao, Vector v){
        double[] dados = new double[v.size()];
        for (int i = 0; i < dados.length; i++) {
            dados[i] = ((Double)v.get(i)).doubleValue();
        }
        setValores(new ValoresIndice(this, acao, dados));
    }
    
    private void checarLUA(int retorno) {
        if(retorno == 0){
          //  System.out.println("OK - Lua");
        } else {
           // System.out.println("Erro - Lua");
            JOptionPane.showMessageDialog(null, "Erro de sintaxe no arquivo LUA : " + this.getFileLua());
        }
    }
    
    private String getCodigoLUA() {
        try {
            StringBuffer s = new StringBuffer();
            s.append("nxx = acao:getNumeroRegistros()\n");
            s.append("dados = { }\n");
            s.append("ixx = 0\n");
            s.append("while ixx < nxx do\n");
            s.append("  dados[ixx] = -9999\n");
            s.append("  ixx = ixx + 1\n");
            s.append("end\n");
            s.append(ManipuladorArquivos.lerConteudoDeArquivo("lua/" + getFileLua()));
            s.append("vet = luajava.newInstance(\"java.util.Vector\")\n");
            s.append("j = 0\n");
            s.append("while j < numReg do\n");
            s.append("  vet:add(luajava.newInstance(\"java.lang.Double\" , tonumber(dados[j])))\n");
            s.append("  j = j + 1\n");
            s.append("end\n");
            s.append("indice:setValoresLUA(acao, vet)\n");
           // System.out.println(s);
            return s.toString();
        } catch (Exception e) {
            //e.printStackTrace();
            Controle.exibirErro("Erro ao ler arquivo " + getFileLua());
            return "";
        }
    }
}
