import edu.stanford.nlp.ie.util.RelationTriple;
import edu.stanford.nlp.simple.*;

/**
 * A demo illustrating how to call the OpenIE system programmatically.
 */
public class OpenIEDemo {


    public static void main(String[] args) {
        // Create a CoreNLP document
        Document doc = new Document("""
                The Johannes Kepler University Linz (German: Johannes Kepler Universität Linz, short: JKU) is a public institution of higher education in Austria. It is located in Linz, the capital of Upper Austria. It offers bachelor's, master's, diploma and doctoral degrees in business, engineering, law, science, social sciences and medicine.
                Today, 19,930 students study at the park campus in the northeast of Linz, with one out of nine students being from abroad. The university was the first in Austria to introduce an electronic student ID in 1998.
                The university is the home of the Johann Radon Institute for Computational and Applied Mathematics (RICAM) of the Austrian Academy of Sciences.
                In 2012, the Times Higher Education ranked the JKU at # 41 and in 2015 at # 87 in its list of the top 100 universities under 50 years old. According to the 2012 ranking, the JKU was the fifth best young university in German-speaking Europe. The university attained high scores for quotations, third-party funding, and internationalization efforts.
                """);

        // Iterate over the sentences in the document
        for (Sentence sent : doc.sentences()) {
            // Iterate over the triples in the sentence
            for (RelationTriple triple : sent.openieTriples()) {

                try (HelloWorldExample greeter = new HelloWorldExample("bolt://localhost:7687", "neo4j", "password")) {
                    greeter.printGreeting(triple.subjectLemmaGloss(),
                            triple.relationLemmaGloss(),
                            triple.objectLemmaGloss());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // Print the triple
                System.out.println(triple.confidence + "\t" +
                        triple.subjectLemmaGloss() + "\t   " +
                        triple.relationLemmaGloss() + "\t   " +
                        triple.objectLemmaGloss());
            }
        }
    }
}