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



package grafix.telas.componentes;

import grafix.principal.ConfiguracoesGrafix;
import grafix.telas.*;
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.*;

public abstract class BarraDeFerramentas extends JPanel{
    
    protected boolean horizontal = true;
    final protected int TAMANHO_BOTAO = 28;
    protected TelaGrafix tela;
    
    public BarraDeFerramentas(TelaGrafix tela) {
        this.tela = tela;
    }
    
    public abstract void criarBotoesBarra();
    
    public void adicionarSeparador(boolean vertical) {
        JSeparator separador = new JSeparator();
        if(vertical) {
            separador.setOrientation(javax.swing.SwingConstants.VERTICAL);
            separador.setPreferredSize(new java.awt.Dimension(2, TAMANHO_BOTAO));
        } else {
            separador.setOrientation(javax.swing.SwingConstants.HORIZONTAL);
            separador.setPreferredSize(new java.awt.Dimension(TAMANHO_BOTAO, 2));
        }
        add(separador);
    }
    
    public void adicionarBotao(String name, String title, String icone, boolean toggle, ActionListener actionListener) {
        adicionarBotao(name, title, icone, toggle, actionListener, true); 
    }
    
    public void adicionarBotao(String name, String title, String icone, boolean toggle, ActionListener actionListener, boolean enable) {
        AbstractButton botao;
        if(toggle) {
            botao = new JToggleButton();
        } else {
            botao = new JButton();
        }
        botao.setIcon(new javax.swing.ImageIcon(ConfiguracoesGrafix.PASTA_ICONES + icone));
        botao.setToolTipText(title);
        botao.setPreferredSize(new java.awt.Dimension(TAMANHO_BOTAO, TAMANHO_BOTAO));
        botao.addActionListener(actionListener);
        botao.setName(name);
        botao.setEnabled(enable);
        botao.setFocusPainted(false);
        add(botao);
    }
    
    public AbstractButton getBotao(String botao) {
        for (Component elem : this.getComponents()) {
            try {
                if(botao.equals(elem.getName())) {
                    return (AbstractButton) elem;
                }
            } catch (Exception e) { break;}
        }
        return null;
    }

    public boolean alterarBotao(String botao, boolean selected) {
        try {
            getBotao(botao).setSelected(selected);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
