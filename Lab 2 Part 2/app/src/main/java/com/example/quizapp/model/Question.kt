package com.example.quizapp.model

data class Question<T>(
    val questionText: String,
    val answer: T,
    val difficulty: Difficulty
) {
    fun getCorrectAnswer(): T {
        return answer
    }
}


