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

import grafix.principal.*;
import grafix.telas.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class ComboAcoes extends JComboBox {

    public ComboAcoes() {
        super();
        this.setModel(new DefaultComboBoxModel());
        this.setPreferredSize(new Dimension(80, 25));
        this.setEditable(true);
        this.popularAcoes();
    }

    public ComboAcoes(String[] strings) {
        super(strings);
        this.setModel(new DefaultComboBoxModel());
        this.setPreferredSize(new Dimension(80, 25));
        this.setEditable(true);
    }

    public void alterarSelecaoSemDispararTroca(Acao acao) {
        removerListener();
        this.setSelectedItem(acao);
        adicionarListener();
    }

    private void adicionarListener() {
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                comboAcoesActionPerformed(e);
            }
        });
    }

    private void removerListener() {
        try {
            this.removeActionListener(getActionListeners()[0]);
        } catch (Exception e) {}
    }

    private void comboAcoesActionPerformed(ActionEvent e) {
        if ("comboBoxChanged".equalsIgnoreCase(e.getActionCommand())) {
            Acao novaAcao;
            try {
                novaAcao = (Acao) this.getSelectedItem();
                trocarAcao(novaAcao);
                return;
            } catch (Exception ex) {
            }

            // Acao digitada pelo usuário
            try {
                String codAcao = this.getSelectedItem().toString();
                novaAcao = new Acao(codAcao);
                if (novaAcao.isAcaoValida()) {
                    trocarAcao(novaAcao);
                    return;
                } else {
                    Controle.exibirErro("Código da ação inválido ou ação não existe: " + codAcao);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void trocarAcao(Acao novaAcao) {
        try {
            Controle.getJanelaAtiva().trocarAcao(novaAcao);
            Controle.getTela().atualizarJanelasGraficos();
        } catch (Exception e) {
            Controle.exibirErro("Ocorreu um erro ao carregar a ação: " + novaAcao.getCodAcao());
            e.printStackTrace();
        }
    }

    public void popularAcoes() {
        removerListener();
        this.removeAllItems();
        Vector<Acao> acoes = Controle.getCarteira().getAcoes();
        for (Acao a : acoes) {
            this.addItem(a);
        }
        if (this.getItemCount() == 0) {
            this.setBackground(Color.PINK);
        } else {
            this.setBackground(new Color(238, 238, 238));
        }
        adicionarListener();
    }

    public void alterarCarteira() {
        Controle.setCarteira(new CarteiraDeAcoes());
        popularAcoes();
        trocarAcao((Acao) this.getSelectedItem());
    }
}
