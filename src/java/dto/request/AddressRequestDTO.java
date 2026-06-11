/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.request;

import java.io.Serializable;

/**
 *
 * @author yasithsandesh
 */
public class AddressRequestDTO implements Serializable {

    private boolean isCurrentAddress;
    
    private String firstName;

    private String lastName;

    private int cityId;

    private String line1;

    private String line2;

    private String postalCode;

    private String mobile;

    public AddressRequestDTO() {
    }

    public boolean isIsCurrentAddress() {
        return isCurrentAddress;
    }

    public void setIsCurrentAddress(boolean isCurrentAddress) {
        this.isCurrentAddress = isCurrentAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    
}
