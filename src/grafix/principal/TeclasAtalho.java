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

import java.awt.AWTEvent;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;

public class TeclasAtalho
        implements AWTEventListener {

    public void eventDispatched(AWTEvent event) {
        try {
            if (event instanceof KeyEvent) {
                KeyEvent keyEvent = (KeyEvent) event;
                if (keyEvent.getID() == KeyEvent.KEY_PRESSED) {
                    int keyCode = keyEvent.getKeyCode();
                    if (keyCode == KeyEvent.VK_PAGE_DOWN) {
                        Comandos.cmdPassarAcao(false);
                    }
                    if (keyCode == KeyEvent.VK_PAGE_UP) {
                        Comandos.cmdPassarAcao(true);
                    }
                    if (keyEvent.isControlDown() && keyCode == KeyEvent.VK_H) {
                        Comandos.cmdZoomHistorico();
                    }
                    if (keyEvent.isControlDown() && keyCode == KeyEvent.VK_N) {
                        Comandos.cmdZoomNormal();
                    }
                    if (keyEvent.isControlDown() && keyCode == KeyEvent.VK_RIGHT) {
                        Comandos.cmdMoveDireita();
                    }
                    if (keyEvent.isControlDown() && keyCode == KeyEvent.VK_LEFT) {
                        Comandos.cmdMoveEsquerda();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
