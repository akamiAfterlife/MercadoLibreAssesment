/*
 * Copyright (C) 2017 Mayra Beristain for Mercado Libre;
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.MercadoLibre.Mimb.XMLFeed;


import com.sun.xml.internal.txw2.output.IndentingXMLStreamWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/***
 * Writes an XML feed file
 * @author Mayra Beristain
 */
public class FeedWriter {
    
   //Singleton Section ---------------------------
   private static FeedWriter instance = null;
   
   protected FeedWriter() {
      // Exists only to defeat instantiation.
   }
   
   public static FeedWriter getInstance() {
      if(instance == null) {
         instance = new FeedWriter();
      }
      
      return instance;
   }
   
   //End singleton section -------------------------------
   
   /**
    * Stream writer 
    */
   private IndentingXMLStreamWriter writer;
   /**
    * True if want to print messages about memory usage and
    * file size as you create the file
    * false by default
    */
   private boolean echo = false;
   
   /**
    * Sets the echo 
     * @param echo true if you desire write info as you create the file
    */
   public void setEcho( boolean echo)
   {
       this.echo = echo;
   }
   
   
   public boolean isEco()
   {
       return echo;
   }
           
           
   
   /**
    * Write the feed
    * @param fileName the name of the file
    * @param fileSize the size of the file in bytes
    * @throws java.lang.Exception if file has no name 
    * or fileSize is less than 0 or fileSize exceeds maximum 
    */
   public void write(String fileName, int fileSize) throws Exception {
      
      if ( fileName.equals(""))
          throw new Exception("File without name");
      
      //TODO: Valid if the name is legal
      
      if ( fileSize <= 0)
          throw new Exception("File size must be positive");
      
      if ( fileSize > FeedConstants.MAXIMUM_FILE_SIZE)
          throw new Exception("File size must not exceed " + FeedConstants.MAXIMUM_FILE_SIZE / 1024 /1024 + " MB");
      
      try {
        
         File file = new File(fileName);
         FileOutputStream fileOutputStream = new FileOutputStream(file);
         XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();	
         XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(fileOutputStream, "utf-8");
         writer = new IndentingXMLStreamWriter(xmlStreamWriter);
         writer.setIndentStep("\t");
         
         writer.writeStartDocument();
         writer.writeStartElement(FeedConstants.TAG_FEED);
         
         int rowNumber = 1;
         
         do
         {
            writeRow(rowNumber);
            rowNumber++;
            
            if ( echo && rowNumber % 10 == 0 )
            {
                System.out.format("MEMORY USED %.4f MB: \n", (double) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024));
                System.out.format("FILE SIZE  %.4f MB \n", (double)file.length()/(1024*1024));
            }
            
         }while(file.length() < fileSize);
   
         
         writer.writeCharacters("\n");
         writer.writeEndDocument();

         writer.flush();
         writer.close();


      } catch (XMLStreamException | IOException e) {
          System.out.println(e);
      }
       
       
   }
   
   /***
    * Write a file with de default file name and file size
     * @throws java.lang.Exception
    */
   public void write( ) throws Exception
   {
       write(FeedConstants.DEFAULT_FILE_NAME, FeedConstants.DEFAULT_FILE_SIZE);
   }
   
   /**
    * Write a file with the default size
    * @param fileName name of the file
    * @throws java.lang.Exception 
    */
   public void write( String fileName ) throws Exception
   {
       write(fileName, FeedConstants.DEFAULT_FILE_SIZE);
   }
   
   /**
    * Write a file with the default name
    * @param fileSize file size in bytes
     * @throws java.lang.Exception
    */
   public void write ( int fileSize ) throws Exception
   {
       write(FeedConstants.DEFAULT_FILE_NAME, fileSize);
   }
   /**
    * Write a file row
    * @param number of row
    * @throws XMLStreamException 
    */
   private void writeRow( int number ) throws XMLStreamException
   {
       writer.writeStartElement(FeedConstants.TAG_ROW);			
    
       writer.writeStartElement(FeedConstants.TAG_TITLE);			
       writer.writeCharacters("Title " + number);
       writer.writeEndElement();

       writer.writeStartElement(FeedConstants.TAG_DESCRIPTION);			
       writer.writeCharacters("Description " + number);
       writer.writeEndElement();

       writer.writeEndElement();
       
   }
}