import org.junit.Assert;
import org.junit.Test;

public class QuicksortTest {
    // Test if quicksort works correctly
    @Test
    public void checkSort() {
        WordFreq[] words = {new WordFreq("a", 5), new WordFreq("b"), new WordFreq("e", 2), new WordFreq("d", 10)};
        WordFreq[] sortedWords = {new WordFreq("b", 1), new WordFreq("e", 2), new WordFreq("a", 5), new WordFreq("d", 10)};
        QuickSort.quicksort(words, 0, words.length - 1);

        for (int i = 0; i < words.length; i++)
            Assert.assertTrue(words[i].isEqual(sortedWords[i]));
    }
}