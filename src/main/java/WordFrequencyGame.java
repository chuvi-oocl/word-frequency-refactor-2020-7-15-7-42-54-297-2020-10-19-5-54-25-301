import java.util.*;

public class WordFrequencyGame {
    public String getResult(String sentence) {
        if (sentence.split("\\s+").length == 1) {
            return sentence + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = sentence.split("\\s+");

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

                StringJoiner wordFrequencyResult = new StringJoiner("\n");
                for (WordFrequency wordFrequency : wordFrequencies) {
                    wordFrequencyResult.add(wordFrequency.buildWordFrequencyLine());
                }
                return wordFrequencyResult.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> listMap = new HashMap<>();
        for (WordFrequency wordFrequency : wordFrequencyList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
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
