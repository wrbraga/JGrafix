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
import javax.swing.JButton;
import javax.swing.JPanel;

public class NavegadorAcoes extends JPanel {
    
    private JButton botaoAcima = new JButton();
    private JButton botaoAbaixo = new JButton();
    
    /** Creates a new instance of NavegadorAcoes */
    public NavegadorAcoes() {
        this.setLayout(new java.awt.GridLayout(0, 1, 0, 1));
        this.setPreferredSize(new java.awt.Dimension(25, 25));
        botaoAcima.setIcon(new javax.swing.ImageIcon("resource/icones/acaoAcima.gif"));
        botaoAcima.setToolTipText("(Page Up)");
        botaoAcima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAcimaActionPerformed();
            }
        });
        this.add(botaoAcima);
        
        botaoAbaixo.setIcon(new javax.swing.ImageIcon("resource/icones/acaoAbaixo.gif"));
        botaoAbaixo.setToolTipText("(Page Down)");
        botaoAbaixo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAbaixoActionPerformed();
            }
        });
        this.add(botaoAbaixo);
    }
    
    private void botaoAcimaActionPerformed() {
        Comandos.cmdPassarAcao(true);
    }

    private void botaoAbaixoActionPerformed() {
        Comandos.cmdPassarAcao(false);
    }
    
}
