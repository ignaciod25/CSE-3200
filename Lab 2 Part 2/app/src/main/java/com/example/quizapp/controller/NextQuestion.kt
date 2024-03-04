package com.example.quizapp.controller


import com.example.quizapp.model.AllQuestions

class NextQuestion {

    private lateinit var allQuestions: AllQuestions
    private var currentQuestion = 0

    init {
        allQuestions = AllQuestions()
    }

    private val totalQuestions: Int
        get() = allQuestions.getNumberOfQuestions()

    fun getNextIncQuestionNumber(): Int {
        currentQuestion = (currentQuestion + 1) % totalQuestions
        return currentQuestion
    }

    fun getNextRandomQuestionNumber(): Int {
        val getRand = (0 until totalQuestions).random()
        return getRand
    }
}

