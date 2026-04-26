package lambda_streams.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class FirstStreamDemo {

	static String doc1 = "<html><body>One of the most common uses of <i>streams</i> is to represent queries over data in collections</body></html>";
	static String doc2 = "<html><body>Information integration systems provide valuable services to users by integrating information from a number of autonomous, heterogeneous and distributed Web sources</body></html>";
	static String doc3 = "<html><body>Solr is the popular, blazing fast open source enterprise search platform from the Apache Lucene</body></html>";
	static String doc4 = "<html><body>Java 8 goes one more step ahead and has developed a streams API which lets us think about parallelism</body></html>";

	static List<String> documents = new ArrayList<String>(List.of(doc1, doc2, doc3, doc4));


	public static void main(String[] args) {
		imperative();
		declarative();
	}

	private static void declarative() {
		
		System.out.println("\nPrinting using streams");
		
		documents.stream()
			.filter(d -> d.contains("streams"))
			.map(Indexer::removeStopWords)
			.forEach(System.out::println);
		
	}

	private static void imperative() {
		
		System.out.println("\nPrinting normally");
		
		for (var doc : documents) {

			Predicate<String> filter = d -> d.contains("stream");
			if (filter.test(doc)) {

				Function<String, String> stopwordRemover = Indexer::removeStopWords;

				doc = stopwordRemover.apply(doc);
				System.out.println(doc);

			}
		}
	}
	
	private class Indexer {
		private static List<String> stopWords = Arrays.asList("of", "the", "a", "is", "to", "in", "and");

		public static String removeStopWords(String doc) {
			StringBuilder sb = new StringBuilder();
			for (var word : doc.split(" ")) {
				if (!stopWords.contains(word)) {
					sb.append(word).append(" ");
				}
			}
			return sb.toString();
		}

	}

}
