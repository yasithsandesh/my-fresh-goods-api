/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.response;

import entity.Category;
import java.util.LinkedHashSet;
import java.util.List;

/**
 *
 * @author yasithsandesh
 */
public class LoadHomeDTO {

    private List<Category> categorys;
    private List<TopCategory> topCategorys;

    public LoadHomeDTO() {
    }

    public List<Category> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<Category> categorys) {
        this.categorys = categorys;
    }

    public List<TopCategory> getTopCategorys() {
        return topCategorys;
    }

    public void setTopCategorys(List<TopCategory> topCategorys) {
        this.topCategorys = topCategorys;
    }

    
    

}
