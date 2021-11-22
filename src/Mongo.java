import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Mongo {

	public MongoClient openConnection(String IP, Integer PORT) {

		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));

		return mongoClient;
	}

	public Document saveDocument(MongoClient mongoClient, String DB, String COLLECTION, Integer idDocument,
			String text) {

		MongoDatabase db = mongoClient.getDatabase(DB);
		MongoCollection collection = db.getCollection(COLLECTION);

		Document document = new Document();
		document.append("id", idDocument);
		document.append("text", text);
		collection.insertOne(document);

		return document;

	}

	public void getDocuments(MongoClient mongoClient, String DB, String COLLECTION, String textoBuscar) {

		MongoDatabase db = mongoClient.getDatabase(DB);
		MongoCollection collection = db.getCollection(COLLECTION);

		BasicDBObject regexQuery = new BasicDBObject();
		regexQuery.put("ocr", new BasicDBObject("$regex", "" + textoBuscar + ".*").append("$options", "i"));

		FindIterable<Document> cursor = collection.find(regexQuery);
		ArrayList<Integer> idDocuments = new ArrayList<Integer>();

		for (Document doc : cursor) {
			System.out.println(doc.get("id"));
		}

	}

}
