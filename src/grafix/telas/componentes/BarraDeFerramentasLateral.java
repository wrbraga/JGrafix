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

import grafix.graficos.elementos.*;
import grafix.principal.Comandos;
import grafix.principal.Controle;
import grafix.telas.TelaGrafix;
import grafix.telas.secundarias.*;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

public class BarraDeFerramentasLateral extends BarraDeFerramentas{
    
    private JLabel amostraCor;
    
    public BarraDeFerramentasLateral(TelaGrafix tela) {
        super(tela);
        setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        setPreferredSize(new java.awt.Dimension(37 , TAMANHO_BOTAO));
    }
    
    public void criarBotoesBarra() {
        adicionarBotao("Cor", "Selecionar Cor", "cores.gif", false, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCoresActionPerformed();
            }
        });
        adicionarAmostraCor();
        adicionarSeparador(false);
        adicionarBotao("Linha", "Adicionar Linha", "linha.gif", true, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLinhaActionPerformed(evt);
            }
        });
        adicionarBotao("Quadro", "Adicionar Quadro", "quadro.gif", true, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btQuadroActionPerformed(evt);
            }
        });
        adicionarBotao("RetasFibonacci", "Adicionar Retas de Fibonacci", "fibonacci.gif", true, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFibonacciActionPerformed(evt);
            }
        }, true);
        adicionarBotao("CaixaTexto", "Adicionar Caixa de Texto", "text.gif", true, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTextoActionPerformed(evt);
            }
        }, true);
        adicionarBotao("MarcaValor", "Adicionar Marca de Valor", "marcaValor.gif", false, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMarcaValorActionPerformed(evt);
            }
        });
        adicionarBotao("MarcaData", "Adicionar Marca de Data", "marcaData.gif", false, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMarcaDataActionPerformed(evt);
            }
        });
        adicionarBotao("MarcaPeriodo", "Adicionar Marca de Período", "marcaPeriodo.gif", false, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMarcaPeriodoActionPerformed(evt);
            }
        });
        adicionarBotao("Apagar", "Apagar Objetos", "apagar.gif", false, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btApagarActionPerformed();
            }
        });
        adicionarSeparador(false);
        adicionarBotao("Cursor", "Cursor Extendido", "cursor.gif", true, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCursorActionPerformed(evt);
            }
        });
        this.alterarBotao("Cursor", Controle.getConfiguracoesUsuario().isCursorExtendido());
        adicionarSeparador(false);
        adicionarBotao("Comparador", "Adicionar Comparador", "comparador.gif", true, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btComparadorActionPerformed(evt);
            }
        });
        adicionarSeparador(false);
        adicionarBotao("Log", "Eixo Logarítmico", "log.gif", true, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comandos.cmdLog(((AbstractButton)evt.getSource()).isSelected());
            }
        });


    }
    
    private void adicionarAmostraCor() {
        amostraCor = new JLabel();
        amostraCor.setText("");
        amostraCor.setPreferredSize(new java.awt.Dimension(25, 5));
        amostraCor.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        amostraCor.setOpaque(true);
        atualizarCor();
        add(amostraCor);
    }
    
    public void atualizarCor() {
        amostraCor.setBackground(tela.getCorNovosElementos());
    }

    public void abortarSelecoesIniciadas(ActionEvent evt) {  
///// Incluir novos botoes ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        String botao = "";
        try {
            botao = ((AbstractButton)evt.getSource()).getName();
        } catch (Exception e) {}
        if(!"Linha".equals(botao))  alterarBotao("Linha", false);
        if(!"Quadro".equals(botao))  alterarBotao("Quadro", false);
        if(!"RetasFibonacci".equals(botao))  alterarBotao("RetasFibonacci", false);
        if(!"CaixaTexto".equals(botao))  alterarBotao("CaixaTexto", false);
    }

    private void btApagarActionPerformed() {
        if(JOptionPane.showConfirmDialog(tela.getJanelaAtiva(), "Deseja apagar todos os objetos da janela?" , "Confirmação", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
            tela.getJanelaAtiva().apagarTodosOsElementosGraficos();
        }
    }
    
    private void btCoresActionPerformed() {
        Color novaCor = JColorChooser.showDialog(null, "Selecione uma cor", tela.getCorNovosElementos());
        if(novaCor != null) {
            tela.setCorNovosElementos(novaCor);
        }
    }
    
    private void btLinhaActionPerformed(java.awt.event.ActionEvent evt) {
        if(((AbstractButton)evt.getSource()).isSelected()) {
            abortarSelecoesIniciadas(evt);
            tela.iniciarSelecaoNovoElemento(new Linha(tela.getCorNovosElementos()));
        } else {
            tela.finalizarSelecaoNovoElemento(null);
        }
    }
    
    private void btComparadorActionPerformed(java.awt.event.ActionEvent evt) {
        if(((AbstractButton)evt.getSource()).isSelected()) {
            abortarSelecoesIniciadas(evt);
            tela.iniciarSelecaoNovoElemento(new Comparador(tela.getCorNovosElementos()));
        } else {
            tela.finalizarSelecaoNovoElemento(null);
        }
    }
    
    private void btQuadroActionPerformed(java.awt.event.ActionEvent evt) {
        if(((AbstractButton)evt.getSource()).isSelected()) {
            abortarSelecoesIniciadas(evt);
            tela.iniciarSelecaoNovoElemento(new Quadro(tela.getCorNovosElementos()));
        } else {
            tela.finalizarSelecaoNovoElemento(null);
        }
    }
    
    private void btFibonacciActionPerformed(java.awt.event.ActionEvent evt) {
        if(((AbstractButton)evt.getSource()).isSelected()) {
            abortarSelecoesIniciadas(evt);
            tela.iniciarSelecaoNovoElemento(new RetasFibonacci(tela.getCorNovosElementos()));
        } else {
            tela.finalizarSelecaoNovoElemento(null);
        }
    }
    
    private void btTextoActionPerformed(java.awt.event.ActionEvent evt) {
        if(((AbstractButton)evt.getSource()).isSelected()) {
            abortarSelecoesIniciadas(evt);
            tela.iniciarSelecaoNovoElemento(new CaixaTexto(tela.getCorNovosElementos()));
        } else {
            tela.finalizarSelecaoNovoElemento(null);
        }
    }

    private void btMarcaValorActionPerformed(java.awt.event.ActionEvent evt) {
        abortarSelecoesIniciadas(evt);
        new FormMarcas(tela, FormMarcas.VALOR).setVisible(true);
    }

    private void btMarcaDataActionPerformed(java.awt.event.ActionEvent evt) {
        abortarSelecoesIniciadas(evt);
        new FormMarcas(tela, FormMarcas.DATA).setVisible(true);
    }
    
    private void btMarcaPeriodoActionPerformed(java.awt.event.ActionEvent evt) {
        abortarSelecoesIniciadas(evt);
        new FormMarcas(tela, FormMarcas.PERIODO).setVisible(true);
    }

    private void btCursorActionPerformed(java.awt.event.ActionEvent evt) {
        Comandos.cmdCursorExtendido(((JToggleButton)evt.getSource()).isSelected());
    }
    
}
