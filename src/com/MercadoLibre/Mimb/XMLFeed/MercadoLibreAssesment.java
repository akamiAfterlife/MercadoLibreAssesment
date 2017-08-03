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

import javax.xml.stream.XMLStreamException;



/**
 *
 * @author Mayra Beristain
 */
public class MercadoLibreAssesment {

    /**
     * @param args the command line arguments
     * No arguments default name and size
     * First argument is file name
     * Second argument is size in bytes
     */
    public static void main(String[] args) throws XMLStreamException {
        
       try{
            //shows messages in the console as write the file
            FeedWriter.getInstance().setEcho(true);
            //this call writes a feed file
            //with 0 arguments the file will be named feed.xml and size of
            //501 MB aprox
            //with 1 argument the file will be named as the argument specifies
            //(please specify the extention) and its size will be of 501 MB aprox
            //with 2 arguments the file will be named as the first argument and 
            //with a size specified in bytes by the second argument
            switch (args.length) {
                case 0:
                    FeedWriter.getInstance().write();
                    break;
                case 1:
                    FeedWriter.getInstance().write(args[0]);
                    break;
                case 2:
                    try{
                        int size = Integer.parseInt(args[1]); 
                        FeedWriter.getInstance().write(args[0], size);
                    }catch(NumberFormatException e)
                    {
                        e.printStackTrace(System.out);
                    }   break;
                default:
                    break;
            }
                
            
        }catch(Exception e)
        {
            e.printStackTrace(System.out);
        }
        
        System.out.println("............. FIN DE LA ESCRITURA ..............");
        
        FeedParser feedParser = new FeedParser();
        
        try {
            //this call open the xml feed file for reading it
            //with 0 arguments open the default file
            //with 1 argument open the file with the specified name
            //with 2 arguments we use the first argument as name, and the second is ignored
            switch( args.length )
            {
                case 0:
                    feedParser.open();
                    break;
                case 1: case 2:
                    feedParser.open(args[0]);
                    break;
                
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        
        while ( feedParser.hasNext())
        {
            FeedElement fe = feedParser.getNextElement();
            
            if ( fe != null)
            {
                System.out.println("Siguiente elemento: ");
                System.out.println("\tTitulo:  " + fe.getTitle());
                System.out.println("\tDescripción:  " + fe.getDescription());
            }
        }
        
        
    }
    
}
