import java.util.*;

public class WordFrequencyGame {
    public String getResult(String sentence) {
        if (sentence.split("\\s+").length == 1) {
            return sentence + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = sentence.split("\\s+");

                List<Input> inputs = new ArrayList<>();
                for (String word : words) {
                    Input input = new Input(word, 1);
                    inputs.add(input);
                }

                //get the map for the next step of sizing the same word
                Map<String, List<Input>> wordFrequencyMap = getListMap(inputs);

                List<Input> wordFrequencyList = new ArrayList<>();
                for (Map.Entry<String, List<Input>> entry : wordFrequencyMap.entrySet()) {
                    Input input = new Input(entry.getKey(), entry.getValue().size());
                    wordFrequencyList.add(input);
                }
                inputs = wordFrequencyList;

                inputs.sort((word1, word2) -> word2.getWordCount() - word1.getWordCount());

                StringJoiner wordFrequencyResult = new StringJoiner("\n");
                for (Input input : inputs) {
                    String wordFrequencyLine = input.getWord() + " " + input.getWordCount();
                    wordFrequencyResult.add(wordFrequencyLine);
                }
                return wordFrequencyResult.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private Map<String, List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> listMap = new HashMap<>();
        for (Input input : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!listMap.containsKey(input.getWord())) {
                ArrayList inputs = new ArrayList<>();
                inputs.add(input);
                listMap.put(input.getWord(), inputs);
            } else {
                listMap.get(input.getWord()).add(input);
            }
        }
        return listMap;
    }
}
