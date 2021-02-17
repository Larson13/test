package com.test;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.Document;


public class mongoTest {
    public static void main(String[] args) {


        String mongoUri = "mongodb://root:123456@10.1.12.148:27017/admin";
        MongoClientURI connStr = new MongoClientURI(mongoUri);
        MongoClient mongoClient = new MongoClient(connStr);
        try {
            // 使用名为 someonedb 的数据库
            MongoDatabase database = mongoClient.getDatabase("test");
            // 取得集合/表 someonetable 句柄
            MongoCollection<Document> collection = database.getCollection("someonetable");

            // 准备写入数据
            Document doc = new Document();
            doc.append("key", "value");
            doc.append("username", "jack");
            doc.append("age", 31);

            // 写入数据
           collection.insertOne(doc);
            System.out.println("insert document: " + doc);

            // 读取数据
            BsonDocument filter = new BsonDocument();
            filter.append("username", new BsonString("jack"));
            MongoCursor<Document> cursor = collection.find(filter).iterator();
            while (cursor.hasNext()) {
                System.out.println("find document: " + cursor.next());
                System.out.println("age: " + cursor.next().get("age"));
            }

            //删除数据
            collection.deleteOne(Filters.eq("username","jack"));
            System.out.println("删除成功");
        } finally {
            //关闭连接
            mongoClient.close();
        }
    }

}
