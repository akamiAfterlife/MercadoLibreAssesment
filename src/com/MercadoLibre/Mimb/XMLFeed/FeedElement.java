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
 * A row or object in the feed
 * @author Mayra Beristain
 */
public class FeedElement {
    private String title;
    
    private String description;
    
    /**
     * set the object title
     * @param title 
     */
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    /**
     * get the object title
     * @return 
     */
    public String getTitle()
    {
        return title;
    }
    
    /**
     * set the object description
     * @param description 
     */
    public void setDescription(String description )
    {
        this.description = description;
    }
    
    /**
     * Get the object description
     * @return 
     */
    public String getDescription()
    {
        return description;
    }
}
