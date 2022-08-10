package com.facegroup.heroes.Biography;

public class Person {

    private String englishName, persianName;
    private int price;
    private String priceUnit;
    private String[] englishBiography, persianBiography;
    private String[] englishWords, persianWords;
    private int[] images;
    private int lockedProfileImage, unlockedProfileImage;
    private boolean isUnlock;

    public Person() {
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getPersianName() {
        return persianName;
    }

    public void setPersianName(String persianName) {
        this.persianName = persianName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public String[] getEnglishBiography() {
        return englishBiography;
    }

    public void setEnglishBiography(String[] englishBiography) {
        this.englishBiography = englishBiography;
    }

    public String[] getPersianBiography() {
        return persianBiography;
    }

    public void setPersianBiography(String[] persianBiography) {
        this.persianBiography = persianBiography;
    }

    public String[] getEnglishWords() {
        return englishWords;
    }

    public void setEnglishWords(String[] englishWords) {
        this.englishWords = englishWords;
    }

    public String[] getPersianWords() {
        return persianWords;
    }

    public void setPersianWords(String[] persianWords) {
        this.persianWords = persianWords;
    }

    public int[] getImages() {
        return images;
    }

    public void setImages(int[] images) {
        this.images = images;
    }

    public int getLockedProfileImage() {
        return lockedProfileImage;
    }

    public void setLockedProfileImage(int lockedProfileImage) {
        this.lockedProfileImage = lockedProfileImage;
    }

    public int getUnlockedProfileImage() {
        return unlockedProfileImage;
    }

    public void setUnlockedProfileImage(int unlockedProfileImage) {
        this.unlockedProfileImage = unlockedProfileImage;
    }

    public boolean isUnlock() {
        return isUnlock;
    }

    public void setUnlock(boolean unlock) {
        isUnlock = unlock;
    }

}
