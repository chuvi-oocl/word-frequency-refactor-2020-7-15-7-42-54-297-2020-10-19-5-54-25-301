import java.util.*;

public class WordFrequencyGame {

    private static final String WHITE_SPACE_REGEX = "\\s+";
    private static final String NEW_LINE_DELIMITER = "\n";
    private static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String sentence) {
        try {
            //split the input string with 1 to n pieces of spaces
            String[] words = sentence.split(WHITE_SPACE_REGEX);

            List<WordFrequency> wordFrequencies = new ArrayList<>();
            for (String word : words) {
                wordFrequencies.add(new WordFrequency(word, 1));
            }

            //get the map for the next step of sizing the same word
            Map<String, List<WordFrequency>> wordFrequencyMap = getListMap(wordFrequencies);

            List<WordFrequency> wordFrequencyList = new ArrayList<>();
            for (Map.Entry<String, List<WordFrequency>> entry : wordFrequencyMap.entrySet()) {
                wordFrequencyList.add(new WordFrequency(entry.getKey(), entry.getValue().size()));
            }
            wordFrequencies = wordFrequencyList;

            wordFrequencies.sort((word1, word2) -> word2.getWordCount() - word1.getWordCount());

            StringJoiner wordFrequencyResult = new StringJoiner(NEW_LINE_DELIMITER);
            for (WordFrequency wordFrequency : wordFrequencies) {
                wordFrequencyResult.add(wordFrequency.buildWordFrequencyLine());
            }
            return wordFrequencyResult.toString();
        } catch (Exception e) {
            return CALCULATE_ERROR;
        }
    }

    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> listMap = new HashMap<>();
        for (WordFrequency wordFrequency : wordFrequencyList) {
            if (!listMap.containsKey(wordFrequency.getWord())) {
                ArrayList inputs = new ArrayList<>();
                inputs.add(wordFrequency);
                listMap.put(wordFrequency.getWord(), inputs);
            } else {
                listMap.get(wordFrequency.getWord()).add(wordFrequency);
            }
        }
        return listMap;
    }
}
