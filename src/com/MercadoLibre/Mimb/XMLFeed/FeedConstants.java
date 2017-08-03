/*
 * Copyright (C) 2017 Mayra Beristain for Mercado Libre
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

/**
 * Constants for the Feed Files processing
 * @author Mayra Beristain
 */
public interface FeedConstants {
 
    /**
    * Default file size it's over 500 MB (501)
    */
   public static final int DEFAULT_FILE_SIZE = 501 * 1024 * 1024;
   
   /***
    * Default file name
    */
   public static final String DEFAULT_FILE_NAME = "feed.xml";
   
   /**
    * Maximum file size. Probably the size could be overpase this, but
    * this constant is used to control, if the maximum file size is more than this
    * is convenient to to use long int type
    */
   public static final int MAXIMUM_FILE_SIZE = 2000 * 1024 * 1024;
   
   /**
    * Start file tag
    */
   public static final String TAG_FEED = "feed";
   
   
   /**
    * Start file row
    */
   public static final String TAG_ROW = "row";
   /**
    * Tag label title
    */
   public static final String TAG_TITLE = "title";
   
   /**
    * Tag label description
    */
   public static final String TAG_DESCRIPTION = "description";
  
   
  
}
