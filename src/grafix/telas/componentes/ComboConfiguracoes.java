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

import grafix.auxiliar.*;
import grafix.principal.*;
import grafix.telas.TelaGrafix;
import java.awt.Dimension;
import java.awt.event.*;
import java.io.File;
import java.util.Vector;
import javax.swing.*;

public class ComboConfiguracoes extends JComboBox {

    public ComboConfiguracoes(TelaGrafix tela){
        this.setModel(new DefaultComboBoxModel());
        this.popularCombo();
        this.setPreferredSize(new Dimension(100, 25));
        this.setToolTipText("Escolha um perfil salvo");
    }
    
    private void comboConfiguracoesActionPerformed() {
        try {
            ConfiguracoesUsuario conf = (ConfiguracoesUsuario)this.getSelectedItem();
            Comandos.cmdAplicarConfiguracao(conf);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void popularCombo() {
        removerListener();
        this.removeAllItems();
        File[] files = ManipuladorArquivos.listaConteudoDiretorio(ConfiguracoesGrafix.PASTA_TEMPLATES);
        Vector<File> templates = new Vector<File>();
        for (int i = 0; i < files.length; i++) {
            if(files[i].getName().endsWith(ConfiguracoesGrafix.EXTENSAO_TEMPLATES)) {
                templates.add(files[i]);
            }
        }
        if(templates.size() > 0) {
            this.addItem(" Perfis...");
        }
        else {
            this.addItem(" Sem perfis...");
        }
        for (File file : templates) {
            try {
                ConfiguracoesUsuario conf = (ConfiguracoesUsuario)(new ConfiguracaoXML(file.getPath()).recuperaDoXML());
                this.addItem(conf);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        adicionarListener();
    }
    
    private void removerListener() {
        try {
            this.removeActionListener(getActionListeners()[0]);
        } catch (Exception e) {}
    }
    
    private void adicionarListener() {
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                comboConfiguracoesActionPerformed();
            }
        });
    }
    
}
