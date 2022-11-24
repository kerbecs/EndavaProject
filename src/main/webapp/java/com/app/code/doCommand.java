package com.app.code;

import java.util.LinkedList;
import java.util.List;

public class doCommand {
    private List<String> productList=new LinkedList<String>();

    public doCommand() {
    }

    public List<String> getProductList() {
        return productList;
    }

    public void setProductList(List<String> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "doCommand{" +
                "productList=" + productList +
                '}';
    }
}
