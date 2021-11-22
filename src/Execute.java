import com.mongodb.MongoClient;

public class Execute {

	public static void main(String[] args) {

		final String IP = "locahost";
		final Integer PORT = 27017;
		final String DB = "admin";
		final String COLLECTION = "Person";

		Mongo mongo = new Mongo();

		MongoClient connection = mongo.openConnection(IP,PORT);
		mongo.saveDocument(connection, DB, COLLECTION, 2, "This is a new document");
		mongo.getDocuments(connection, DB, COLLECTION, "document");
		System.out.println("The connection works");

	}
}