package com.example.quizapp.model


class AllQuestions {
    private val questions: List<Question<Boolean>> = listOf(
        Question("The capital of France is Paris.", true, Difficulty.EASY),
        Question("Water boils at 100 degrees Celsius at sea level.", true, Difficulty.MEDIUM),
        Question("The Great Wall of China is visible from space with the naked eye.", false, Difficulty.EASY),
        Question("The Earth is flat", false, Difficulty.EASY),
        Question("Bees communicate with each other by dancing.", true, Difficulty.EASY),
    )

    fun getQuestion(index: Int): Question<Boolean> {
        return questions[index]
    }

    fun getCorrectAnswerForQuestion(index: Int): Boolean {
        return questions[index].getCorrectAnswer()
    }

    fun getNumberOfQuestions(): Int {
        return questions.size
    }
}


