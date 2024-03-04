package com.example.quizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import com.example.quizapp.controller.NextQuestion
import com.example.quizapp.model.AllQuestions
import com.example.quizapp.ui.theme.QuizAppTheme
import androidx.compose.material3.Text
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.text.style.TextAlign


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var score by remember { mutableStateOf(0) }

            QuizAppTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Quiz App",
                        style = TextStyle(
                            fontSize = 32.sp, // Adjust the font size as needed
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(align = Alignment.CenterHorizontally)
                    )

                    QuizComponent(score) { updatedScore ->
                        score = updatedScore
                    }
                }
            }
        }
    }
}

@Composable
fun QuizComponent(score: Int, onScoreUpdated: (Int) -> Unit) {
    val allQuestions = AllQuestions()
    val nextQuestion = NextQuestion()

    var questionNumber by remember { mutableStateOf(0) }
    var question by remember {
        mutableStateOf(allQuestions.getQuestion(questionNumber).questionText)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = question,
            style = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Button(
            onClick = {
                handleAnswer(true, allQuestions, nextQuestion, questionNumber, score, onScoreUpdated)
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("True button", style = TextStyle(fontSize = 20.sp))
        }
        Button(
            onClick = {
                handleAnswer(false, allQuestions, nextQuestion, questionNumber, score, onScoreUpdated)
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("False button", style = TextStyle(fontSize = 20.sp))
        }
        Button(
            onClick = {
                questionNumber = nextQuestion.getNextIncQuestionNumber()
                question = allQuestions.getQuestion(questionNumber).questionText
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Next question", style = TextStyle(fontSize = 20.sp))
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = "Score: $score",
        style = TextStyle(fontSize = 24.sp)
    )
}

fun handleAnswer(answer: Boolean, allQuestions: AllQuestions, nextQuestion: NextQuestion, questionNumber: Int, score: Int, onScoreUpdated: (Int) -> Unit) {
    if (answer == allQuestions.getCorrectAnswerForQuestion(questionNumber)) {
        onScoreUpdated(score + 1)
    }
}

