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
public class ProductSearchResponse implements Serializable{
    
    private List<Item> items;
    
    private int allItemCount;

    public ProductSearchResponse() {
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getAllItemCount() {
        return allItemCount;
    }

    public void setAllItemCount(int allItemCount) {
        this.allItemCount = allItemCount;
    }
    
    
    
}
