import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    private static final String WHITE_SPACE_REGEX = "\\s+";
    private static final String NEW_LINE_DELIMITER = "\n";
    private static final String CALCULATE_ERROR = "Calculate Error";

    public String getWordFrequency(String sentence) {
        try {
            List<WordFrequency> wordFrequencies = getWordFrequencies(sentence);

            wordFrequencies.sort((word1, word2) -> word2.getWordCount() - word1.getWordCount());

            return wordFrequencies.stream().map(WordFrequency::buildWordFrequencyLine).collect(Collectors.joining(NEW_LINE_DELIMITER));
        } catch (Exception exception) {
            return CALCULATE_ERROR;
        }
    }

    private List<WordFrequency> getWordFrequencies(String sentence) {
        List<String> words = Arrays.asList(sentence.split(WHITE_SPACE_REGEX));

        return new HashSet<>(words).stream()
                .map(word -> new WordFrequency(word, Collections.frequency(words, word)))
                .collect(Collectors.toList());
    }
}
