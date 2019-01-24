package question;

import java.util.Arrays;

public class MultipleChoiceQuestion {

    private String question;
    private Answer[] answers;

    public MultipleChoiceQuestion(String question, Answer[] answers) {
        this.question = question;
        this.answers = answers;
    }

    public boolean answer(String answer) {
        for(Answer ans : answers) {
            if (ans.getAnswer().equals(answer)) {
                if (ans.isCorrect())
                    return true;
            }
        }
        return false;
    }

    public String getQuestion() {
        return question;
    }

    public Answer[] getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return "MultipleChoiceQuestion{" +
                "question='" + question + '\'' +
                ", answers=" + Arrays.toString(answers) +
                '}';
    }
}
