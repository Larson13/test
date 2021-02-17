package com.test;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//@Slf4j
public class MmongoTest {

    public static void main(String[] args) {
        // 创建 MongoDB 连接
        MongoClient mongo = new MongoClient("10.1.12.148" , 27017 );
        // 连接到 MongoDB
        MongoCredential credential;
        credential = MongoCredential.createCredential("root", "admin", "123456".toCharArray());
        System.out.println("Connected to the database successfully");
        // 访问数据库
        MongoDatabase database = mongo.getDatabase("admin");
        System.out.println("Credentials ::"+ credential);

       // database.createCollection("tutorial");
        // 检索集合
        MongoCollection<Document> collection = database.getCollection("tutorial");
        System.out.println("集合选择成功 tutorial");
        //log.info("集合选择成功1 tutorial");
        Document document1 = new Document("title", "MongoDB")
                .append("description", "database")
                .append("likes", 100)
                .append("url", "http://www.biancheng.net/mongodb/")
                .append("by", "编程帮");
        Document document2 = new Document("title", "MongoDB")
                .append("description", "database")
                .append("likes", 100)
                .append("url", "http://www.biancheng.net/mongodb/")
                .append("by", "编程帮");

        // 将文档插入到集合中
        //collection.insertOne(document);
        System.out.println("文档插入成功");

        List<Document> list = new ArrayList<Document>();
        list.add(document1);
        list.add(document2);
        collection.insertMany(list);
        // 获取 iterable 对象
        FindIterable<Document> iterDoc = collection.find();
        int i = 1;
        // 获取迭代器
        Iterator it = iterDoc.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
            i++;
        }
    }
}
