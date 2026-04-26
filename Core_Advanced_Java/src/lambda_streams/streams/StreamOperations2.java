package lambda_streams.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import lambda_streams.streams.StreamOperations.Book;

public class StreamOperations2 {

	static List<Book> booklist;
	public static void main(String[] args) {
		
		booklist = StreamOperations.getBooks();
		
		//Terminal Operation : reduction
				reduction(booklist);
				overloadedReduction();

		//Terminal Operation : collect
				collect();
				collectToCollection(booklist);
				collectToMap(booklist);
				collectToGrouping(booklist);
	}
	
	private static void collectToGrouping(List<Book> booklist) {
	
		System.out.println("\n\nIn grouping method :");
		
		System.out.println("\nGrouping => Rating : Book as List");
		Map<Double,List<Book>> mapList = booklist.stream()
								.distinct()
								.collect(Collectors.groupingBy(Book::getRating));
		for(Entry<Double, List<Book>> e : mapList.entrySet()) {
			System.out.println("Key : "+e.getKey());
			for(Book val : e.getValue()) {
				System.out.println(val);
			}
		}
		
		System.out.println("\nGrouping => Rating : BookTitle as List");
		Map<Double,List<String>> mapList2 = booklist.stream()
								.distinct()
								.collect(Collectors.groupingBy(Book::getRating,Collectors.mapping(Book::getTitle, Collectors.toList())));
		for(Entry<Double, List<String>> e : mapList2.entrySet()) {
			System.out.println("Key : "+e.getKey());
			for(String val : e.getValue()) {
				System.out.println(val);
			}
		}
		
		System.out.println("\nGrouping => Rating : Book as set");
		Map<Double,Set<Book>> mapSet = booklist.stream().distinct()
						.collect(Collectors.groupingBy(Book::getRating,Collectors.toSet()));
		for(Entry<Double, Set<Book>> e : mapSet.entrySet()) {
			System.out.println("Key : "+e.getKey());
			for(Book val : e.getValue()) {
				System.out.println(val);
			}
		}
		
		System.out.println("\nGrouping => Rating : Book as set(Using treemap, hence ratings will be sorted)");
		mapSet = booklist.stream().distinct()
						.collect(Collectors.groupingBy(Book::getRating,TreeMap::new,Collectors.toSet()));
		for(Entry<Double, Set<Book>> e : mapSet.entrySet()) {
			System.out.println("Key : "+e.getKey());
			for(Book val : e.getValue()) {
				System.out.println(val);
			}
		}
		
		System.out.println("\nMulti level map : ");
		Map<Double,Map<String,List<Book>>> multilevelMap = booklist.stream().distinct()
				.collect(Collectors.groupingBy(Book::getRating,Collectors.groupingBy(Book::getSource)));
		System.out.println(multilevelMap);
		
		System.out.println("\nCounting each rating => Rating : number of ratings");
		Map<Double, Long> ratingsCountMap = booklist.stream().distinct()
				.collect(Collectors.groupingBy(Book::getRating,Collectors.counting()));
		System.out.println(ratingsCountMap);
		
		System.out.println("\nGetting minimum price book from each rating => Rating : minPrice book");
		Map<Double, Optional<Book>> minPriceRatingMap = booklist.stream().distinct()
				.collect(Collectors.groupingBy(Book::getRating,Collectors.minBy(Comparator.comparingDouble(Book::getPrice))));
		System.out.println(minPriceRatingMap);
		
		System.out.println("\nAverage price in each rating => Rating : avg price");
		Map<Double, Double> ratingsAvgMap = booklist.stream().distinct()
				.collect(Collectors.groupingBy(Book::getRating,Collectors.averagingDouble(Book::getPrice)));
		System.out.println(ratingsAvgMap);
		
		System.out.println("\nGenerating summary of price for each rating => Rating : summary of price");
		Map<Double,DoubleSummaryStatistics> ratingSummaryMap = booklist.stream().distinct()
				.collect(Collectors.groupingBy(Book::getRating,Collectors.summarizingDouble(Book::getPrice)));
		System.out.println(ratingSummaryMap);
		
		System.out.println("\nUsing partitioning By(For rating>=4.5) : ");
		Map<Boolean,List<Book>> partitionedRatingMap = booklist.stream().distinct()
				.collect(Collectors.partitioningBy(d -> d.getRating()>=4.5));
		System.out.println(partitionedRatingMap);
	}

	private static void collectToMap(List<Book> booklist) {
		
		System.out.println("\nIn collect to Map method");

//toMap : 3 different overloaded constructor
		
		Map<Long,Book> map = booklist.stream()
		//.collect(Collectors.toMap(b -> b.getIsbn(), b->b)); //This gives error because there is duplicate key
							.collect(Collectors.toMap(b->b.getIsbn(),b->b,(b1,b2) -> b1.getPrice() <= b2.getPrice() ? b1 : b2));
		
		for(Entry<Long, Book> e : map.entrySet()) {
			System.out.println("Key : "+e.getKey()+" ,Value : "+e.getValue());
		}	
		
		System.out.println("map instanceOf Hashmap ? "+ (map instanceof HashMap<Long, Book>));
		
		TreeMap<Long, Book> treeMap = booklist.stream()
			//	.collect(Collectors.toMap(b->b.getIsbn(),b->b,(b1,b2) -> b1.getPrice() <= b2.getPrice() ? b1 : b2,() -> new TreeMap()));
				.collect(Collectors.toMap(Book::getIsbn,Function.identity(),(b1,b2) -> b1.getPrice() <= b2.getPrice() ? b1 : b2,TreeMap::new));
		System.out.println("Printing in TreeMap : ");
		for(Entry<Long, Book> e : treeMap.entrySet()) {
			System.out.println("Key : "+e.getKey()+" ,Value : "+e.getValue());		
		}
		
		
	}

	private static void collectToCollection(List<Book> booklist) {
		
		System.out.println("\nIn collect to collection method");
//toList() :
		List<Book> list = booklist.stream()
							.filter(d -> d.getRating()>=4.5)
							.distinct()
							.collect(Collectors.toList());
		System.out.println("List.size : "+list.size());
		
		//Without using collectors
		list = booklist.stream()
				.filter(d -> d.getRating()>=4.5)
				.distinct()
				.collect(() -> new ArrayList<Book>(), (a,l) -> a.add(l), (a1,a2) -> a1.addAll(a2));
		System.out.println("List.size(without collector) : "+list.size());
		
		//Using method references and constructor reference
		list = booklist.stream()
				.filter(d -> d.getRating()>=4.5)
				.distinct()
				.collect(ArrayList::new,ArrayList::add, ArrayList::addAll);
		System.out.println("List.size(without collector But also using method reference instead of lambda) : "+list.size());

//toSet() :		
		Set<Book> set = booklist.stream()
						.filter(d -> d.getRating()>=4.5)
						.collect(Collectors.toSet());
		System.out.println("Set.size : "+set.size());
		
		//If we want treeset , then above method will not work , hence : 
		TreeSet<Book> treeSet = booklist.stream()
				.filter(d -> d.getRating()>=4.5)
				.collect(Collectors.toCollection(TreeSet :: new));
		System.out.println("TreeSet.size : "+treeSet.size());
		
		
	}

	private static void collect() {
		System.out.println("\nIn collect method");
		
		String[] grades = {"A","A","B"};
		
		StringBuilder builder = Arrays.asList(grades).stream()
				.collect(() -> new StringBuilder(),(sb1,s) -> sb1.append(s),(sb1,sb2) -> sb1.append(sb2));
				System.out.println("String builder result with grades : "+builder);
		
				
		//we can also use method reference instead of lambda here
		builder = Arrays.asList(grades).stream()
				.collect(StringBuilder::new,StringBuilder::append,StringBuilder::append);
				System.out.println("String builder result with grades(using method references) : "+builder);		
		
		//checking with parallel stream		
		builder = Arrays.asList(grades).parallelStream()
				.collect(() -> new StringBuilder(),(sb1,s) -> sb1.append(s),(sb1,sb2) -> sb1.append(sb2));
				System.out.println("String builder result with grades(parallel) : "+builder);	
		
		//Using collectors 		
		String join = Arrays.asList(grades).stream()
				.collect(Collectors.joining());
		System.out.println("Result with collectors.joining (result is string , not stringbuilder) : "+join);
				
	}

	private static void overloadedReduction() {
		
		String[] grades = {"A","A","B"};
		
		String concat = Arrays.asList(grades).stream()
		.reduce("", (s1,s2) -> s1+s2);
		System.out.println("Concat result with grades : "+concat);
		
		StringBuilder builder = Arrays.asList(grades).stream()
		.reduce(new StringBuilder(),(sb1,s) -> sb1.append(s),(sb1,sb2) -> sb1.append(sb2));
		System.out.println("String builder result with grades : "+builder);
		
		StringBuilder builder2 = Arrays.asList(grades).stream()
		.map(s -> new StringBuilder(s))
		.reduce(new StringBuilder(),(sb1,sb2) -> sb1.append(sb2));
		System.out.println("String builder result with grades and using 2nd overloaded reduce: "+builder2);
			
		//Giving wrong answer : Reason begin reduce should not be use for this, use collect
		builder = Arrays.asList(grades).parallelStream()
				.reduce(new StringBuilder(),(sb1,s) -> sb1.append(s),(sb1,sb2) -> sb1.append(sb2));
				System.out.println("String builder result with grades(parallel) : "+builder);
		//Reason reduce does not work is reduce expects immutable, but we are violating that
				//and One shared StringBuilder → multiple threads → chaos ❌
				//for collect this is not case, every thread gets its own string builder
		
		//This will work, because string are immutable		
		concat = Arrays.asList(grades).parallelStream()
		.reduce("", (s1,s2) -> s1+s2);
		System.out.println("Concat result with grades(parallel) : "+concat);
				
		
	}
	

	private static void reduction(List<Book> booklist) {
		System.out.println("\nIn reduce method : ");
		
		/*Optional<Book> result = */booklist.stream()
		.filter(d -> d.getRating()>=4.5)
		.reduce((b1,b2) -> b1.getPrice()<=b2.getPrice() ? b1 : b2)
		.ifPresent(b -> System.out.println("Lowest price book with at least rating of 4.5 is : "+b));
		
		//System.out.println("Lowest price book with at least rating of 4.5 is : "+result.get());
	}

}
