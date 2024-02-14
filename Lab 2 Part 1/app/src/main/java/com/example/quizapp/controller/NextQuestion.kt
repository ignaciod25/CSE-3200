package com.example.quizapp.controller


import com.example.quizapp.model.AllQuestions

class NextQuestion {

    private val totalQuestions = AllQuestions().getNumberOfQuestions()
    private var currentQuestion = 0  // How might we fix this?  Not a @Composable


    fun getNextIncQuestionNumber() : Int {
        currentQuestion = (currentQuestion +1) % totalQuestions
        return currentQuestion
    }

    fun getNextRandomQuestionNumber() : Int {
        val getRand = (0..totalQuestions).random()
        return getRand
    }


}