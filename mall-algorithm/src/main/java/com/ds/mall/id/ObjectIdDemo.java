package com.ds.mall.id;

import org.bson.types.ObjectId;

public class ObjectIdDemo {

    public static void main(String[] args) {
        ObjectId id = new ObjectId();
        System.out.println(id.toHexString());
        System.out.println(id.toHexString());
    }
}
