package com.example.quizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.example.quizapp.controller.NextQuestion
import com.example.quizapp.model.AllQuestions
import com.example.quizapp.ui.theme.QuizAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizAppTheme {
                Column {
                    Text("Welcome to our quiz app")
                    QuizComponent()
                }
            }
        }
    }
}

@Composable
fun QuizComponent() {
    val allQuestions = AllQuestions()
    val nextQuestion = NextQuestion()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        var questionNumber by remember { mutableStateOf(0) }
        var question by remember {
            mutableStateOf(allQuestions.getQuestion(questionNumber).questionText)
        }

        Text(question)
        Row() {
            Button(onClick = { question = "True" }) {
                Text("True button")
            }
            Button(onClick = { question = "False" }) {
                Text("False button")
            }
            Button(onClick = {
                questionNumber = nextQuestion.getNextIncQuestionNumber()
                question = allQuestions.getQuestion(questionNumber).questionText
            }) {
                Text("Next question")
            }
        }
    }
}