package util;

import question.Answer;
import question.MultipleChoiceQuestion;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class QuestionReader {

    private final Path path;

    public QuestionReader(Path path) {
        this.path = path;
    }

    public List<MultipleChoiceQuestion> read() throws IOException {
        List<MultipleChoiceQuestion> questions = new ArrayList<>();
        System.out.println(path.toAbsolutePath());

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path.toFile())));
        String line;

        while((line = reader.readLine()) != null) {
            Answer[] answers = new Answer[4];
            for (int i = 0; i < answers.length; i++) {
                String answer = reader.readLine().trim();
                answers[i] = new Answer(answer.endsWith("*") ? answer.substring(0, answer.length()-2) : answer, answer.endsWith("*"));
            }
            questions.add(new MultipleChoiceQuestion(line, answers));
        }

        return questions;
    }
}
