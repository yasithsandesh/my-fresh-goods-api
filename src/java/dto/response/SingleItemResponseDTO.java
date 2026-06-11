/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.response;

import entity.Item;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author yasithsandesh
 */
public class SingleItemResponseDTO implements Serializable{
    
    private Item Item;
    
    private List<Item> similarItemsList;

    public SingleItemResponseDTO() {
    }

    public Item getItem() {
        return Item;
    }

    public void setItem(Item Item) {
        this.Item = Item;
    }

    public List<Item> getSimilarItemsList() {
        return similarItemsList;
    }

    public void setSimilarItemsList(List<Item> similarItemsList) {
        this.similarItemsList = similarItemsList;
    }


    
    
    
}
