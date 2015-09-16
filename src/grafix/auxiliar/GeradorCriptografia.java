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

import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class GeradorCriptografia {
    
    private String digest;
    private String cifra; 
    private String chave; 
    
    public GeradorCriptografia(String digest, String cifra, String chave) {
        setDigest(digest);
        setCifra(cifra);
        setChave(chave);
    }
    
    public String decifrarSenha(String senha) {
        String seqOrig = senha;
        MessageDigest dig;
        byte [] result=null;
        try {
            dig = MessageDigest.getInstance(getDigest());
            byte[] hashDig = dig.digest(getChave().getBytes());
            // Gera uma SecretKey a partir do hash
            SecretKey sk = new SecretKeySpec(hashDig, getCifra());
            Cipher decifracao;
            decifracao = Cipher.getInstance(getCifra());
            decifracao.init(Cipher.DECRYPT_MODE, sk);
            // Decifra a seqüência original
            result = decifracao.doFinal(Utils.strHexaParaBytes(seqOrig));
            
        } catch (Exception ex) {
            ex.printStackTrace();
            Utils.exibeMsgErro("Erro ao decifrar seqüência. Detalhes:" +
                    Utils.SEPLINHA +
                    ex.getClass().getName() + Utils.SEPLINHA +
                    ex.getMessage());
        }
        // retorna seqüência processada
        return new String(result);
    }
    
    public String cifrarSenha(String senha) {
/*        byte [] ch = chave.getBytes();
        for(int i=0;i<ch.length;i++) {
            int x = ch[i];
            x = x+2;
            ch[i] = (byte) x;
        }
        chave = new String(ch);
  */      
        String seqOrig = senha;
        String senhaCifrada=null;
        
        
        try {
            // Gera um hash a partir da chave fornecida pelo usuário
            MessageDigest dig = MessageDigest.getInstance(digest);
            byte[] hashDig = dig.digest(chave.getBytes());
            
            // Gera uma SecretKey a partir do hash
            SecretKey sk = new SecretKeySpec(hashDig, cifra);
            
            // Gera um objeto de cifração
            Cipher cifracao = Cipher.getInstance(cifra);
            cifracao.init(Cipher.ENCRYPT_MODE, sk);
            
            // Cifra a seqüência original
            byte[] result = cifracao.doFinal(senha.getBytes());
            
            // Exibe a seqüência processada
            senhaCifrada = Utils.bytesParaStrHexa(result);
        } catch (Exception ex) {
            ex.printStackTrace();
            Utils.exibeMsgErro("Erro ao cifrar seqüência. Detalhes:" +
                    Utils.SEPLINHA +
                    ex.getClass().getName() + Utils.SEPLINHA +
                    ex.getMessage());
        }
        return senhaCifrada;
        
    }
    
    private String getDigest() {
        return digest;
    }
    
    public void setDigest(String digest) {
        this.digest = digest;
    }
    
    private String getCifra() {
        return cifra;
    }
    
    public void setCifra(String cifra) {
        this.cifra = cifra;
    }
    
    private String getChave() {
        return chave;
    }
    
    public void setChave(String chave) {
        this.chave = chave;
    }
    
}
