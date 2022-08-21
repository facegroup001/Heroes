package com.facegroup.heroes.Store;

public class WealthModel {

    int image;
    int amountToAdd;
    int amountToSubtract;

    public WealthModel(int image, int amountToAdd, int amountToSubtract) {
        this.image = image;
        this.amountToAdd = amountToAdd;
        this.amountToSubtract = amountToSubtract;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getAmountToAdd() {
        return amountToAdd;
    }

    public void setAmountToAdd(int amountToAdd) {
        this.amountToAdd = amountToAdd;
    }

    public int getAmountToSubtract() {
        return amountToSubtract;
    }

    public void setAmountToSubtract(int amountToSubtract) {
        this.amountToSubtract = amountToSubtract;
    }

}
