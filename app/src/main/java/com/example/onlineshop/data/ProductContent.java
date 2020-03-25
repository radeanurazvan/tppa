package com.example.onlineshop.data;

import android.net.Uri;
import android.os.Environment;
import android.widget.ImageView;

import com.example.onlineshop.R;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductContent {

    private static final String DEFAULT_DESCRIPTION = "My awesome product";
    public static final List<Product> ITEMS = new ArrayList<Product>();
    public static final Map<String, Product> ITEM_MAP = new HashMap<String, Product>();

    static {
        addItem(new Product("Gizmos", DEFAULT_DESCRIPTION, R.drawable.gizmos));
        addItem(new Product("Code Names", DEFAULT_DESCRIPTION, R.drawable.codenames));
        addItem(new Product("Too many Poops", DEFAULT_DESCRIPTION, R.drawable.toomanypoops));
    }

    private static void addItem(Product item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.name, item);
    }

    public static class Product {
        public final String name;
        public final String description;
        public final int image;

        public Product(String name, String description, int image) {
            this.name = name;
            this.description = description;
            this.image = image;
        }
    }
}
