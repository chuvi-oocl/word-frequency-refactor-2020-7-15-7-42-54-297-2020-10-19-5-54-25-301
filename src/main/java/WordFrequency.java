public class WordFrequency {
    private final String word;
    private final int count;

    public WordFrequency(String word, int count) {
        this.word = word;
        this.count = count;
    }

    public String getWord() {
        return this.word;
    }

    public int getWordCount() {
        return this.count;
    }

    public String buildWordFrequencyLine() {
        return String.format("%s %s", this.getWord(), this.getWordCount());
    }
}
