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

public class AjudaGrafix {

    public static String CARTEIRA_VAZIA = "\n" +
                    "A carteira de ações está vazia. Pode estar acontecendo uma das seguintes situações:        \n\n" +
                    "- A pasta da dados não está corretamente apontada.\n" +
                    "    . Clique em 'Ferramentas > Opções', e configure a pasta correta.\n\n" +
                    "- A lista de ações está vazia, não inclui nenhuma das ações da pasta de dados.\n" +
                    "    . Clique em Ferramentas > Lista de Papéis e escolha uma lista apropriada.\n\n" +
                    "- A pasta de dados está vazia.\n" +
                    "    . Atualizar a base de dados clicando em Arquivo > Atualizar.\n\n" +
                    "\n";

    public static String LISTA_INEXISTENTE = "" +
                    "Nenhuma lista de ações personalizada foi definida!           \n" +
                    "Todas as ações estão sendo mostradas para seleção. \n\n" +
                    "    . Clique em  'Ferramentas > Lista de Papéis'  \n" +
                    "      e escolha uma lista com os papéis do seu interesse. \n" +
                    "      Você pode criar novas listas!\n" +
                    "\n";
    
    public static String ERRO_MARCAS = "" +
                    "Ocorreu um erro ao adicionar as marcas ao gráfico.           \n" +
                    "Todas as marcas foram excluídas! \n\n";
    
    public AjudaGrafix() {
    }

    public static void exibirMensagem(String mensagem) {
        Controle.exibirAjuda(mensagem);
    }
    
}
