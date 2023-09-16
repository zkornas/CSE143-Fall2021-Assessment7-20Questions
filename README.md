# CSE143-Fall2021-Assessment7-20Questions
Take-home Assessment 7: 20 Questions

## Overview: The Game of 20 Questions
Twenty Questions is a guessing game in which one player chooses a secret object and the other player asks yes/no questions to try to idenfity the chosen object. In our version, the human will be the chooser and begin a round by choosing an object. The computer will be the guesser and attempt to guess that object by asking a series of yes/no questions until it thinks it knows the answer. Then, the computer makes a guess; if its guess is correct, the computer wins, and otherwise the human player wins. If the computer loses, it will add the chosen object to its knowledge base so it will be able to guess it the next time it plays.

## Program Behavior
In this assessment, you will create a class named QuestionsGame to represent the computer’s tree of yes/no questions and answers for playing games of 20 Questions. You will also create a inner-class named QuestionNode to represent the nodes of the tree. You are provided with a client QuestionMain.java that handles user interaction and calls your methods from QuestionsGame to play the game.

You can read the full specification [here](https://courses.cs.washington.edu/courses/cse143/21au/take-home-assessments/a7/a7.pdf)!
