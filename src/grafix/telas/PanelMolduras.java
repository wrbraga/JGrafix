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



package grafix.telas;

import grafix.graficos.eixos.Eixo;
import grafix.graficos.elementos.ElementoGrafico;
import java.awt.*;
import java.util.*;
import javax.swing.JPanel;
import org.jfree.chart.*;

public class PanelMolduras extends JPanel {
    
    private Vector<MolduraAreaDados> molduras;
    private JanelaGraficos janela;
    
    public PanelMolduras(JanelaGraficos janela)  {
        this.janela = janela;
        this.setOpaque(false);
        this.setBackground(Color.green);
        this.criarMolduras();
    }

    private void criarMolduras() {
        molduras = new Vector<MolduraAreaDados>();
        Vector<Eixo> eixosVisiveis = getJanela().getConfiguracoesJanela().getEixosVisiveis(); 
        int contador = 0;
        this.setIgnoreRepaint(true);
        for (Eixo eixo : eixosVisiveis) {
            MolduraAreaDados novaMoldura = new MolduraAreaDados(this, eixo, contador++);
            getMolduras().add(novaMoldura);
            add(novaMoldura);
        }
        this.setIgnoreRepaint(false);
    }

    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        try{
            posicionarMolduras();
        } catch (Exception e) {}
    }
    
    private void posicionarMolduras() {
            for (int i = 0; i < getMolduras().size(); i++) {
                getMolduras().get(i).setPosicao(getJanela().getPanelGraficos().getAreaData(i));
            }
    }
    
    public JanelaGraficos getJanela() {
        return janela;
    }
    
    public void habilitarEventos() {
        for (MolduraAreaDados m : molduras) {
            m.adicionarEventos();
        }
    }
    
    public void desabilitarEventos() {
        reiniciarMolduras();
    }
    
    public void reiniciarMolduras() {
        this.removeAll();
        criarMolduras();
    }
    
    public Vector<MolduraAreaDados> getMolduras() {
        return molduras;
    }
    
    public void setMolduras(Vector<MolduraAreaDados> molduras) {
        this.molduras = molduras;
    }
    
    public void excluirElementoGrafico(ElementoGrafico elementoExcluido) {
        for (MolduraAreaDados m : molduras) {
            Vector<ElementoGrafico> elems = m.getAnaliseAcao().getElementos();
            elems.remove(elementoExcluido);
        }
        janela.getTela().atualizarJanelasGraficos();
    }
    
}
