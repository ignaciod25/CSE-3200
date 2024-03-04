package com.example.quizapp
import org.junit.Test
import org.junit.Assert.*
import com.example.quizapp.model.AllQuestions
import com.example.quizapp.controller.NextQuestion

class QuizAppUnitTest {

    @Test
    fun testNextQuestion() {
        val nextQuestion = NextQuestion()
        assertEquals(0, nextQuestion.getNextIncQuestionNumber())
        assertEquals(1, nextQuestion.getNextIncQuestionNumber())
    }


    @Test
    fun testAnswerCorrectness() {
        val allQuestions = AllQuestions()
        assertTrue(allQuestions.getCorrectAnswerForQuestion(0))
        assertFalse(allQuestions.getCorrectAnswerForQuestion(1))
    }

    // Add more tests based on your specific requirements and functionalities.
}
