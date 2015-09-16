
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



/*******************************************************************
 * Author:		Ryan D. Emerle
 * Date:			10.12.2004
 * Desc:			Reverse file reader.  Reads a file from the end to the
 *						beginning
 *
 * Known Issues:
 *						Does not support unicode!
 *******************************************************************/
 
package grafix.basedados;
import java.io.*;
import java.util.*;
 
public class ReverseFileReader {	
		private String filename;	
		private RandomAccessFile randomfile;	
		private long position;
		
		public ReverseFileReader (String filename) throws Exception {		
			// Open up a random access file
			this.randomfile=new RandomAccessFile(filename,"r");
			// Set our seek position to the end of the file
			this.position=this.randomfile.length();
				
			// Seek to the end of the file
			this.randomfile.seek(this.position);
			//Move our pointer to the first valid position at the end of the file.
			String thisLine=this.randomfile.readLine();
			while(thisLine == null ) {
				this.position--;
				this.randomfile.seek(this.position);
				thisLine=this.randomfile.readLine();
				this.randomfile.seek(this.position);
			}
		}	
		
		// Read one line from the current position towards the beginning
		public String readLine() throws Exception {		
			int thisCode;
			char thisChar;
			String finalLine="";
			
			// If our position is less than zero already, we are at the beginning
			// with nothing to return.
			if ( this.position < 0 ) {
					return null;
			}
			
			for(;;) {
				// we've reached the beginning of the file
				if ( this.position < 0 ) {
					break;
				}
				// Seek to the current position
				this.randomfile.seek(this.position);
				
				// Read the data at this position
				thisCode=this.randomfile.readByte();
				thisChar=(char)thisCode;
				
				// If this is a line break or carrige return, stop looking
				if (thisCode == 13 || thisCode == 10 ) {
					// See if the previous character is also a line break character.
					// this accounts for crlf combinations
					this.randomfile.seek(this.position-1);
					int nextCode=this.randomfile.readByte();
					if ( (thisCode == 10 && nextCode == 13) || (thisCode == 13 && nextCode == 10) ) {
						// If we found another linebreak character, ignore it
						this.position=this.position-1;
					}
					// Move the pointer for the next readline
					this.position--;
					break;
				} else {
					// This is a valid character append to the string
					finalLine=thisChar + finalLine;
				}
				// Move to the next char
				this.position--;
			}
			// return the line
			return finalLine;
		}	
}
 

