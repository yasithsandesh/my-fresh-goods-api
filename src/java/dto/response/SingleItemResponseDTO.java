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
    
    private Item singleItem;
    
    private List<Item> similarItems;

    public SingleItemResponseDTO() {
    }

    public Item getSingleItem() {
        return singleItem;
    }

    public void setSingleItem(Item singleItem) {
        this.singleItem = singleItem;
    }

    public List<Item> getSimilarItems() {
        return similarItems;
    }

    public void setSimilarItems(List<Item> similarItems) {
        this.similarItems = similarItems;
    }
    
    
    
}
