package lambda_streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfaceDemo {
	public static void main(String[] args) {
		
		String doc1 = "<html><body>One of the most common uses of <i>streams</i> is to represent queries over data in collections</body></html>";
		String doc2 = "<html><body>Information integration systems provide valuable services to users by integrating information from a number of autonomous, heterogeneous and distributed Web sources</body></html>";
		String doc3 = "<html><body>Solr is the popular, blazing fast open source enterprise search platform from the Apache Lucene</body></html>";
		String doc4 = "<html><body>Java 8 goes one more step ahead and has developed a streams API which lets us think about parallelism</body></html>";
		
		List<String> documents = new ArrayList<String>(List.of(doc1,doc2,doc3,doc4));
		List<String> targetDocuments = new ArrayList<String>();
		
		for(var doc: documents) {
			//this is one of way of using lambda and functional interface
			boolean isTargetDoc = applyFilter(doc,d->d.contains("streams"));
			if(isTargetDoc) {
				doc = applyTransformation(doc, d -> Indexer.removeStopWords(d));
				targetDocuments.add(doc);
			}
			
			//second way of using lamda and functional interface
			BiFunction<String, String, Boolean> filter = (d,c) -> d.contains(c);
			if(filter.apply(doc, "data")) {
				Function<String, String> stopwordRemover = d -> Indexer.removeStopWords(d);
				doc = stopwordRemover.apply(doc); // or doc = applyTransformation(doc, stopwordRemover);
				targetDocuments.add(doc);
			}
		}
		
		targetDocuments.forEach(d -> System.out.println(d));
		
		for(String doc : targetDocuments) {
			try {
				if(doc.length() > 120) {
					throw new Exception("oversized document");
				}
			}catch(Exception e) {
				print(() -> e.getMessage() + "~"+doc);
			}
		}
	}
	
	private static void print(Supplier<String> message) {
		System.out.println(message.get());
	}
	
	private static boolean applyFilter(String doc,Predicate<String> filter) {
		return filter.test(doc);
	}
	
	private static String applyTransformation(String doc,Function<String,String> transformer) {
		return transformer.apply(doc);
	}
	
	private class Indexer{
		private static List<String> stopWords = Arrays.asList("of", "the", "a", "is", "to", "in", "and");
		
		public static String removeStopWords(String doc) {
				StringBuilder sb = new StringBuilder();
				for(var word : doc.split(" ")) {
					if(!stopWords.contains(word)) {
						sb.append(word).append(" ");
					}
				}
				return sb.toString();
		}

	}

}



