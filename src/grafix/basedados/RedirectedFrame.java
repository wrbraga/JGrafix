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



/*
 * RedirectedFrame.java
 *
 * Created on 25 de Julho de 2007, 10:07
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package grafix.basedados;

/**
 *
 * @author joao
 */
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class RedirectedFrame extends Frame {
    TextArea aTextArea = new TextArea();
    PrintStream aPrintStream  =
            new PrintStream(
            new FilteredStream(
            new ByteArrayOutputStream()));
    
    boolean logFile;
    
    public RedirectedFrame(boolean logFile) {
        this.logFile = logFile;
        System.setOut(aPrintStream);
        System.setErr(aPrintStream);
        setTitle("Error message");
        setSize(500,300);
        setLayout(new BorderLayout());
        add("Center" , aTextArea);
        displayLog();
        addWindowListener
                (new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        }
        );
    }
    
    class FilteredStream extends FilterOutputStream {
        public FilteredStream(OutputStream aStream) {
            super(aStream);
        }
        
        public void write(byte b[]) throws IOException {
            String aString = new String(b);
            aTextArea.append(aString);
        }
        
        public void write(byte b[], int off, int len) throws IOException {
            String aString = new String(b , off , len);
            aTextArea.append(aString);
            if (logFile) {
                FileWriter aWriter = new FileWriter("error.log", true);
                aWriter.write(aString);
                aWriter.close();
            }
        }
    }
    
    public void displayLog() {
        Dimension dim = getToolkit().getScreenSize();
        Rectangle abounds = getBounds();
        Dimension dd = getSize();
        setLocation((dim.width - abounds.width) / 2,
                (dim.height - abounds.height) / 2);
        setVisible(true);
        requestFocus();
    }
    
    public static void main(String s[]){
        try {
            // force an exception for demonstration purpose
            Class.forName("unknown").newInstance();
        } catch (Exception e) {
            // for applet, always RedirectedFrame(false)
            RedirectedFrame r = new RedirectedFrame(true);
            e.printStackTrace();
        }
    }
}


