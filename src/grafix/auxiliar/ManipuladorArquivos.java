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



package grafix.auxiliar;

import java.awt.Component;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ManipuladorArquivos {
    
    public static boolean copiarArquivo(String origem, String destino) {
        InputStream is = null;
        OutputStream os = null;
        byte[] buffer;
        boolean success = true;
        try {
            is = new FileInputStream(origem);
            os = new FileOutputStream(destino);
            buffer = new byte[is.available()];
            is.read(buffer);
            os.write(buffer);
        } catch (Exception e) {
            success = false;
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {}
        }
        return success;
    }
    
    public static boolean apagarArquivo(String nome) {
        try {
            File f = new File(nome);
            f.delete();
            return true;
        } catch(Exception e) {
            return false;
        }
    }
    
    public static void apagarDiretorio(String nome) {
        File[] conteudo = listaConteudoDiretorio(nome);
        for(int i=0; i<conteudo.length; i++) {
            if(conteudo[i].isDirectory()) {
                apagarDiretorio(nome + "/" + conteudo[i].getName());
            } else {
                conteudo[i].delete();
            }
        }
        apagarArquivo(nome);
    }
    
    public static boolean existeArquivo(String nome) {
        File f = new File(nome);
        boolean resultado = false;
        try {
            resultado = f.exists();
        } catch(Exception e) {
            return false;
        }
        return resultado;
    }
    
    public static boolean existeDiretorio(String nome) {
        File f = new File(nome);
        boolean resultado = false;
        try {
            resultado = f.isDirectory();
        } catch(Exception e) {
            return false;
        }
        return resultado;
    }
    
    
    public static boolean criarArquivo(String nome) {
        File f = new File(nome);
        try {
            f.createNewFile();
        } catch(Exception e) {
            return false;
        }
        return true;
    }
    
    public static boolean criarPasta(String nome) {
        File f = new File(nome);
        try {
            f.mkdir();
        } catch(Exception e) {
            return false;
        }
        return true;
    }
    
    public static boolean escreverEmArquivo(String nome, String conteudo) {
        FileOutputStream f;
        try {
            f = new FileOutputStream(nome);
            f.write(conteudo.getBytes());
            f.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    
    public static String lerConteudoDeArquivo(String nome) {
        BufferedReader b = null;
        try {
            b = new BufferedReader(new FileReader(nome));
        } catch (Exception e) {
            System.err.println("Nao foi possivel abrir arquivo: " + nome);
        }
        StringBuffer resultado = new StringBuffer();
        String linha;
        try {
            while( (linha=b.readLine()) != null) {
                resultado.append(linha + "\n");
            }
        } catch (Exception ex) {
        }
        return resultado.toString();
    }
    
    public static ArrayList<String> lerListaDeArquivo(String nomeArquivo) {
        BufferedReader b = null;
        try {
            b = new BufferedReader(new FileReader(nomeArquivo));
        } catch (Exception e) {
            System.err.println("Nao foi possivel abrir arquivo: " + nomeArquivo);
        }
        ArrayList<String> resultado = new ArrayList<String>();
        String linha;
        try {
            while( (linha=b.readLine()) != null) {
                linha = linha.trim();
                if(!linha.equals("")) {
                    resultado.add(linha);
                }
            }
        } catch (Exception ex) {
        }
        return resultado;
    }
    
    public static String [] listaDir(String path, String filtro) {
        FilenameFilter filter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.matches(".*xml.*");
            }
        };
        
        File dire = new File(path);
        String lis[] = null;
        lis = dire.list(filter);
        return lis;
        
    }
    public static File[] listaDiretorios(String path) {
        File lista[] = new File(path).listFiles();
        File resultado[];
        java.util.Vector temp = new java.util.Vector();
        for (int i=0; i<lista.length; i++) {
            if (lista[i].isDirectory()) {
                temp.add(lista[i]);
            }
        }
        resultado = new File[temp.size()];
        for (int i=0; i<temp.size(); i++) {
            resultado[i] = (File)temp.get(i);
        }
        return resultado;
    }
    
    public static String procurarPasta(Component parent, String pathDefault) {
        try {
            JFileChooser chooser = new JFileChooser(new File(pathDefault));
            chooser.setDialogType(JFileChooser.OPEN_DIALOG);
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnVal = chooser.showOpenDialog(parent);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                return file.getAbsolutePath() + File.separator;
            } else {
                return pathDefault;
            }
        } catch (Exception e) {
            exibirErro("Ocorreu um erro!");
            return pathDefault;
        }
    }
    
    public static void escreverEmArquivoSobrescrevendo(String arquivo, String conteudo) {
        ManipuladorArquivos.apagarArquivo(arquivo);
        PrintWriter pwOut;
        try {
            pwOut = new PrintWriter(new BufferedWriter(new FileWriter(arquivo, true)));
            pwOut.println(conteudo);
            pwOut.close();
        } catch (Exception e) {
            System.err.println("Não foi possivel abrir arquivo para escrita: " + arquivo);
            
        }
    }
    
    public static void exibirErro(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "ERRO", JOptionPane.ERROR_MESSAGE);
    }
    
    public static File[] listaConteudoDiretorio(String path) {
        return new File(path).listFiles();
    }
    
//    public static void main(String [] args) {
//        String lista[] = ManipuladorArquivos.listaDir(".", "sada");
//        for (int i = 0; i < lista.length; i++) {
//            System.out.println(lista[i]);
//            
//        }
//        
//    }
}
