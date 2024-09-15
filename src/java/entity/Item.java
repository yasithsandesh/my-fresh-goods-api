/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import model.MeasuringType;

/**
 *
 * @author yasithsandesh
 */
@Entity()
@Table(name = "item")
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "measuring_type", nullable = false)
    private MeasuringType measuringType;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "fresh", nullable = false)
    private long freshness;

    @ManyToOne
    @JoinColumn(name = "graden_id")
    private Garden garden;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "qty", nullable = false)
    private int qty;

    @ManyToOne
    @JoinColumn(name = "item_status_id")
    private ItemStatus itemStatus;

    public Item() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MeasuringType getMeasuringType() {
        return measuringType;
    }

    public void setMeasuringType(MeasuringType measuringType) {
        this.measuringType = measuringType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

  
    public Garden getGarden() {
        return garden;
    }

    public void setGarden(Garden garden) {
        this.garden = garden;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public ItemStatus getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(ItemStatus itemStatus) {
        this.itemStatus = itemStatus;
    }

    public long getFreshness() {
        return freshness;
    }

    public void setFreshness(long freshness) {
        this.freshness = freshness;
    }

    
}
