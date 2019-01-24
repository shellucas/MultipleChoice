package question;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import util.QuestionReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

class MultipleChoiceQuestionTest {

    @Test
    void testValidQuestions() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        List<Path> questions;
        try (Stream<Path> paths = Files.walk(Paths.get(Objects.requireNonNull(classLoader.getResource("questions")).getPath().substring(1)))) {
            paths
                    .filter(Files::isRegularFile)
                    .map(QuestionReader::new)
                    .map(this::testQuestion)
                    .forEach(Assert::assertTrue);
        }
    }

    @Test
    void testMultipleChoice() {
        MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion("When was 9/11", new Answer[]{
                new Answer("On 11 september 2000", false),
                new Answer("On 9 november 2000", false),
                new Answer("On 11 september 2001", true),
                new Answer("On 9 november 2001", false)
        });

        assertTrue(multipleChoiceQuestion.answer("On 11 september 2001"));
        assertFalse(multipleChoiceQuestion.answer("On 9 november 2000"));

    }

    /**
     * Helper method for {@link #testValidQuestions}
     * @param reader
     * @return
     */
    private boolean testQuestion(QuestionReader reader) {
        try {
            List<MultipleChoiceQuestion> questions = reader.read();
            try(Stream<MultipleChoiceQuestion> stream = questions.stream()) {
                assertTrue(stream
                        .map(MultipleChoiceQuestion::getAnswers)
                        .allMatch(answers -> {
                            for (Answer answer: answers) {
                                if (answer.isCorrect()) return true;
                            }
                            return false;
                        }));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}