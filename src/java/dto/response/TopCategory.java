/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.response;

import entity.Category;
import entity.Item;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author yasithsandesh
 */
public class TopCategory implements  Serializable{

    private Category category;
    private List<Item> items;
    
    public TopCategory(){
    
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}
