/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author yasithsandesh
 */
@Entity()
@Table(name = "garden")
public class Garden implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "garden_name", nullable = false)
    private String gradenName;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "garden_address_id")
    private GardenAddress gradenAddress;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    public Garden() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGradenName() {
        return gradenName;
    }

    public void setGradenName(String gradenName) {
        this.gradenName = gradenName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GardenAddress getGradenAddress() {
        return gradenAddress;
    }

    public void setGradenAddress(GardenAddress gradenAddress) {
        this.gradenAddress = gradenAddress;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }



}
