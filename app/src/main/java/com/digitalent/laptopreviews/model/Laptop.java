package com.digitalent.laptopreviews.model;

import java.io.Serializable;

public class Laptop implements Serializable {
    public static final String KEY_ID = "Id";
    public static final String KEY_NAME = "Name";
    public static final String KEY_DESCRIPTION = "Description";
    public static final String KEY_IMAGE = "Image";
    public static final String KEY_PRICE = "Price";
    public static final String KEY_CPU = "CPU";
    public static final String KEY_GPU = "GPU";
    public static final String KEY_DISPLAY = "Display";
    public static final String KEY_STORAGE = "Storage";
    public static final String KEY_RAM = "RAM";
    public static final String KEY_BATTERY = "Battery";
    public static final String KEY_DIMENSIONS = "Dimensions";
    public static final String KEY_WEIGHT = "Weight";
    public static final String KEY_SOURCE = "Source";

    private int id;
    private String name;
    private String description;
    private String image;
    private String price;
    private String cpu;
    private String gpu;
    private String display;
    private String storage;
    private String ram;
    private String battery;
    private String dimensions;
    private String weight;
    private String source;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
