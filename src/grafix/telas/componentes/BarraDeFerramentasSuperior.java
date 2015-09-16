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

import grafix.principal.Comandos;
import grafix.telas.TelaGrafix;
import javax.swing.AbstractButton;

public class BarraDeFerramentasSuperior extends BarraDeFerramentas{
    
    public BarraDeFerramentasSuperior(TelaGrafix tela) {
        super(tela);
        this.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
    }
    
    public void criarBotoesBarra() {
        add(tela.getComboAcoes());
        add(new NavegadorAcoes());
        adicionarBotao("", "Listas de papéis", "lista2.gif", false, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comandos.cmdListas();
            }
        });
        adicionarBotao("", "Atualizar Cotações", "atualizar.gif", false, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comandos.cmdAtualizar();
            }
        }, true);
        adicionarBotao("", "Intraday", "intraday.gif", true, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comandos.cmdIntraday(((AbstractButton)evt.getSource()).isSelected());
            }
        }, true);
        adicionarSeparador(true);
        add(tela.getComboConfiguracoes());
        adicionarBotao("", "Salvar Configuração", "salvar.gif", false, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comandos.cmdSalvarConfiguracao();
            }
        });
        adicionarBotao("", "Aplicar a configuração default à janela", "default2.gif", false, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comandos.cmdAplicarDefault();
            }
        });
        adicionarSeparador(true);
        adicionarBotao("", "Zoom Normal  (Ctrl + N)", "zoomNormal.gif", false, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comandos.cmdZoomNormal();
            }
        });
        adicionarBotao("", "Zoom Previsao", "zoomPrevisao.gif", false, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comandos.cmdZoomPrevisao();
            }
        }, true);
        adicionarBotao("", "Zoom Historico  (Ctrl + H)", "zoomHistorico.gif", false, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comandos.cmdZoomHistorico();
            }
        });
        adicionarSeparador(true);
        adicionarBotao("", "Zoom Mais", "zoomMais.gif", false, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comandos.cmdZoomMais();
            }
        });
        adicionarBotao("", "Zoom Menos", "zoomMenos.gif", false, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comandos.cmdZoomMenos();
            }
        });
        adicionarBotao("", "Move à Esquerda  (Ctrl + Seta à Esquerda)", "moveEsquerda3.gif", false, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comandos.cmdMoveEsquerda();
            }
        });
        adicionarBotao("", "Move à Direita  (Ctrl + Seta à Direita)", "moveDireita3.gif", false, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comandos.cmdMoveDireita();
            }
        });
        adicionarSeparador(true);
        adicionarBotao("", "Tabela do Gráfico", "historico.gif", false, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comandos.cmdHistorico();
            }
        });
        adicionarSeparador(true);
        adicionarBotao("", "Opções", "opcoes.gif", false, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comandos.cmdOpcoes();
            }
        });
        adicionarSeparador(true);
        adicionarBotao("", "Adicionar Janela", "novaJanela.gif", false, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comandos.cmdAddJanela();
            }
        });
        adicionarBotao("", "Configurar Janela", "config.gif", false, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comandos.cmdConfigurarJanela();
            }
        });
        adicionarBotao("", "Índices", "indices.gif", false, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comandos.cmdIndices();
            }
        });
        adicionarBotao("", "Organizar Janelas de Gráfico", "distribuir.gif", false, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            Comandos.cmdOrganizarJanelas();
            }
        });
        adicionarSeparador(true);
        adicionarBotao("", "Salvar gráfico em JPEG", "fotografia2.gif", false, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comandos.cmdSalvarJPEG();
            }
        });
        adicionarSeparador(true);
        adicionarBotao("", "Comparativo de papéis", "comparativo.png", false, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comandos.cmdComparativo();
            }
        });
 
    }    
}
