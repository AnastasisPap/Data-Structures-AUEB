import java.io.PrintStream;

public interface WordCounter {
    void insert(String word);
    WordFreq search(String word);
    void remove(String word);
    void load(String filename);
    int getTotalWords();
    int getDistinctWords();
    int getFrequency(String word);
    WordFreq getMaximumFrequency();
    double getMeanFrequency();
    void addStopWord(String word);
    void removeStopWord(String word);
    void printTreeAlphabetically();
    void printTreeByFrequency();
}
