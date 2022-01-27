public class WordFreq{
    private String word;
    private int occurrences;

    public WordFreq(String word) {
        this.word = word;
        occurrences = 1;
    }

    public WordFreq(String word, int occurences) {
        this.word = word;
        this.occurrences = occurences;
    }

    public void addOccurrence() { occurrences++; }

    public int getOccurrences() { return occurrences; }

    public String key() { return word; }

    public boolean isEqual(WordFreq word) {
        return (word.getOccurrences() == this.getOccurrences() && word.key() == this.key());
    }

    public String toString() { return "The word " + word + " has been found " + occurrences + " time" + (occurrences == 1 ? "" : "s") + "."; };
}