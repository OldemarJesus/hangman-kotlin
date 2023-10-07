package com.oldemarjesus.run

import kotlin.collections.ArrayList

val availableNameToGuess = listOf("Banana", "Pera", "Maça", "Carro", "Mota", "Alfinete")
var remainGuesses = 6
var word = ""
var guessedCharacters = arrayListOf<String>()

fun main() {
    println("Starting the HANGMAN game!")
    startGame()
}

fun startGame() {
    setRandomWord()
    var gameOver = false
    guessedCharacters = getWordEmptySlot()

    do {
        println("\nYou have $remainGuesses left")
        println("\nGuess the word: ")
        for (element in guessedCharacters)
            print("$element ")

        println("\n\nPlease insert one character: ")
        val input = readlnOrNull() ?:""

        if(input.isEmpty()){
            println("\nSorry, your input is not valid. Please try again!")
        } else {
            if(word.contains(input, true)){
                if(!guessedCharacters.contains(input)){
                    guessedCharacters = updateWordSlot(guessedCharacters, input)

                    val totalCorrectGuesses = guessedCharacters
                        .map { if(it == "_") 0 else 1 }
                        .sumOf { it }

                    if(totalCorrectGuesses == word.length){
                        gameOver = true
                    }
                }else{
                    println("\nThe character is already guessed")
                    remainGuesses--
                }
            }else {
                println("\nSorry, you miss!")
                remainGuesses--

                if(remainGuesses == 0)
                    gameOver=true
            }
        }
    }while (!gameOver)

    if(remainGuesses == 0) {
        println("\nYou lose!")
        printTheWord()
    } else {
        println("\nYou win!")
        printTheWord()
    }
}

fun printTheWord() {
    println("The word was: $word")
}

fun updateWordSlot(wordSlot: ArrayList<String>, characterGuessed: String): ArrayList<String> {
    for(wordIdx in word.indices) {
        if(word[wordIdx].toString().equals(characterGuessed, true)){
            wordSlot[wordIdx] = characterGuessed
        }
    }
    return wordSlot
}

fun setRandomWord() {
    word = availableNameToGuess[availableNameToGuess.indices.random()]
}

fun getWordEmptySlot(): ArrayList<String> {
    val wordEmptySlotArr = arrayListOf<String>()
    for(pos in 1..word.length){
        wordEmptySlotArr.add("_")
    }

    return wordEmptySlotArr
}