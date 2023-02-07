package com.example.orderhibfx.dao.jaspersoft;

import com.example.orderhibfx.models.Product;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import java.util.ArrayList;
import java.util.List;

public class ProductDataSource implements JRDataSource {

    private int index = -1;
    private List<Product> products;

    public ProductDataSource(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean next() throws JRException {
        index++;
        return index < products.size();
    }

    @Override
    public Object getFieldValue(JRField jrField) throws JRException {
        Object value = null;
        String fieldName = jrField.getName();
        if ("Nombre".equals(fieldName)) {
            value = products.get(index).getName();
        } else if ("Tipo".equals(fieldName)) {
            value = products.get(index).getType();
        } else if ("Precio".equals(fieldName)) {
            value = products.get(index).getPrice();
        } else if ("Disponibilidad".equals(fieldName)) {
            value = products.get(index).getAvailibity();
        }
        return value;
    }
}
