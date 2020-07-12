package com.example.lollia.data;

public class show_data_items {
    private String product_image_url,product_image_title;

    public show_data_items(String product_image_url, String product_image_title) {
        this.product_image_url = product_image_url;
        this.product_image_title = product_image_title;
    }
    public show_data_items(){

    }

    public String getProduct_image_url() {
        return product_image_url;
    }

    public void setProduct_image_url(String product_image_url) {
        this.product_image_url = product_image_url;
    }

    public String getProduct_image_title() {
        return product_image_title;
    }

    public void setProduct_image_title(String product_image_title) {
        this.product_image_title = product_image_title;
    }
}
