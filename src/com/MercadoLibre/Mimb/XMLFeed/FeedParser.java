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


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 * Reads the file
 * @author Mayra Beristain
 */
public class FeedParser {
    
    /**
     * Event reader for read the rows in the document
     */
    private XMLStreamReader xmlStreamReader;
    /***
     * this flag indicates if a document is currently open
     */
    private boolean opened = false;
    
    public boolean isOpen ()
    {
        return opened;
    }
    
    public boolean hasNext() throws XMLStreamException
    {
        return xmlStreamReader.hasNext();
    }
    
    /**
     * Open the file to work with it
     * @param fileName 
     * @throws java.lang.Exception if name is not valid
     */
    public void open( String fileName ) throws java.lang.Exception
    {
       opened = false;
       
       if ( fileName.equals("") )
           throw new Exception("Not a valid file name");
     
      try {
         XMLInputFactory factory = XMLInputFactory.newInstance();
        
         xmlStreamReader = factory.createXMLStreamReader(fileName,
                                   new FileInputStream(fileName));
         opened = true;
         
            
      } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace(System.out);
      }
    }
    
    /**
     * Open the file the default name
     * @throws java.lang.Exception
     */
    public void open( ) throws java.lang.Exception
    {
        open(FeedConstants.DEFAULT_FILE_NAME);
    }
    
    /**
     * Gets a row from the file and transform it to a FeedElement
     * @return next row in the feed, if exist, else null
     */
    public FeedElement getNextElement() throws XMLStreamException
    {
        FeedElement element = null;
        
        if (!opened )
            return element;
        
        boolean hasTitle = false, hasDescription = false, endRow = false;
        int eventType;
        
        if (!xmlStreamReader.hasNext())
            return element;
        
        do
        {
            try{        
                eventType = xmlStreamReader.next();
                // event = eventReader.nextEvent();
                //eventType = event.getEventType();

                switch( eventType )  
                {
                    case XMLStreamConstants.START_ELEMENT:
                       
                        String tag = xmlStreamReader.getLocalName();

                        if (tag.equalsIgnoreCase(FeedConstants.TAG_ROW)) {
                            element = new FeedElement();
                            endRow = false;
                        }
                        else if (tag.equalsIgnoreCase(FeedConstants.TAG_TITLE) && element != null) {
                            hasTitle = true;
                        } else if (tag.equalsIgnoreCase(FeedConstants.TAG_DESCRIPTION) && element != null ) {
                            hasDescription = true;
                        } 
                        
                    break;

                    case XMLStreamConstants.CHARACTERS:
                        int start = xmlStreamReader.getTextStart();
                        int length = xmlStreamReader.getTextLength();
                        
                        if (element != null)
                        { 
                            if ( hasTitle )
                            {
                                element.setTitle(new String(xmlStreamReader.getTextCharacters(), start,length));
                              
                                hasTitle = false;
                            }
                            else if ( hasDescription )
                            {
                                element.setDescription(new String(xmlStreamReader.getTextCharacters(), start,length));
                                hasDescription = false;
                            }
                        }
                        
                    break;
                    
                    case XMLStreamConstants.END_ELEMENT:
                         endRow = xmlStreamReader.getLocalName().equalsIgnoreCase(FeedConstants.TAG_ROW);
                      
                    break;
                }//end switch
                    
            
            } catch(Exception e){
                e.printStackTrace(System.out);
            }
                
        } while ( !endRow && xmlStreamReader.hasNext());
        
        return element;
    }//end method get Next Element

    private void Exception(String not_a_valid_file_name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
