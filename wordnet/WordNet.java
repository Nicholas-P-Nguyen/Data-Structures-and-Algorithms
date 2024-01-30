import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

import java.util.HashMap;

public class WordNet {
    private Digraph G;
    private ShortestCommonAncestor SCA;
    private HashMap<Integer, String> idToNouns;
    private HashMap<String, Queue<Integer>> nounsToId;


    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        // initialize the hashmaps
        this.idToNouns = new HashMap<>();
        this.nounsToId = new HashMap<>();
        // read synsets.txt
        In synsetsReader = new In(synsets);
        while (synsetsReader.hasNextLine()) {
            // read in new line and seperate by commas
            String[] synsetTxt = synsetsReader.readLine().split(",");
            // reading index of synsets
            int idIndex = Integer.parseInt(synsetTxt[0]);
            // putting key value pair into Hashmap
            idToNouns.put(idIndex, synsetTxt[1]);
            // reading string and spliting nouns by spaces
            String[] synsetString = synsetTxt[1].split(" ");
            // putting nouns to id into hash table
            for (int i = 0; i < synsetString.length; i++) {
                if (!nounsToId.containsKey(synsetString[i])) {
                    Queue<Integer> queueOfId = new Queue<>();
                    queueOfId.enqueue(idIndex);
                    nounsToId.put(synsetString[i], queueOfId);
                }
                else {
                    nounsToId.get(synsetString[i]).enqueue(idIndex);
                }
            }
        }
        // creating new Digraph with the number of id
        this.G = new Digraph(idToNouns.size());
        // read hypernyms.txt
        In hypernymsReader = new In(hypernyms);
        while (hypernymsReader.hasNextLine()) {
            // read in new line and seperate by commas
            String[] hypernymsTxt = hypernymsReader.readLine().split(",");
            // id to synset or finding the parent
            int idToSynset = Integer.parseInt(hypernymsTxt[0]);
            // adding edges to digraph
            for (int i = 1; i < hypernymsTxt.length; i++) {
                G.addEdge(idToSynset, Integer.parseInt(hypernymsTxt[i]));
            }
        }
        SCA = new ShortestCommonAncestor(G);
    }

    // the set of all WordNet nouns
    public Iterable<String> nouns() {
        return nounsToId.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null) {
            throw new IllegalArgumentException("the word is null");
        }
        return nounsToId.containsKey(word);
    }

    // a synset (second field of synsets.txt) that is a shortest common ancestor
    // of noun1 and noun2 (defined below)
    public String sca(String noun1, String noun2) {
        if (!isNoun(noun1) || !isNoun(noun2)) {
            throw new IllegalArgumentException("One of the nouns don't exist");
        }
        Queue<Integer> firstNoun = nounsToId.get(noun1);
        Queue<Integer> secondNoun = nounsToId.get(noun2);
        String synset = idToNouns.get(SCA.ancestorSubset(firstNoun, secondNoun));
        return synset;
    }

    // distance between noun1 and noun2 (defined below)
    public int distance(String noun1, String noun2) {
        if (!isNoun(noun1) || !isNoun(noun2)) {
            throw new IllegalArgumentException("One of the nouns don't exist");
        }
        Queue<Integer> firstNoun = nounsToId.get(noun1);
        Queue<Integer> secondNoun = nounsToId.get(noun2);
        return SCA.lengthSubset(firstNoun, secondNoun);
    }

    // unit testing (required)
    public static void main(String[] args) {
        WordNet testing = new WordNet(args[0], args[1]);
        int count = 0;
        for (String noun : testing.nouns()) {
            System.out.println(noun);
            count++;
        }
        System.out.println("--------------------------------");
        System.out.println("The number of nouns is: " + count);
        System.out.println(testing.isNoun("a")); // should print true
        System.out.println(testing.isNoun("fibrin")); // should print false
        System.out.println("Distance between 'a' and 'o' is: " + testing.distance("a", "o"));
        System.out.println(
                "Closet common ancestor between 'a' and 'o' is: " + testing.sca("a", "o"));


    }
}
