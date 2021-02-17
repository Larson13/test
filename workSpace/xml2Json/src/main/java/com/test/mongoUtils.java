package com.test;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;

import java.util.Arrays;

public class mongoUtils {



    public static  String selectOne(String host,int port,String password) {
return null;

    }
    static Block<Document> printBlock = new Block<Document>() {
        public void apply(final Document document) {
            System.out.println(document.toJson());
        }
    };



    public static void main(String[] args) {


    }


}
