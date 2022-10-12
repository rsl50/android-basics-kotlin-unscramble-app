package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private var score = 0
    private var currentWordCount = 0
    private lateinit var _currentScrambledWord: String
    val currentScrambledWord: String
        get() = _currentScrambledWord

    private var wordsList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String

    /*
    * Updates currentWord and currentScrambledWord with the next word.
    */
    private fun getNextWord() {
        currentWord = allWordsList.random()
        val tempWord = currentWord.toCharArray()
        // Shuffle characters in this array
        tempWord.shuffle()

        // Avoid the shuffled word to be the same as the original word
        while (String(tempWord).equals(currentWord, false)) {
            tempWord.shuffle()
        }

        // Check if a word has been used already
        if (wordsList.contains(currentWord)) {
            getNextWord()
        } else {
            // Update the value with the newly scrambled word
            _currentScrambledWord = String(tempWord)
            // Increase the word count
            ++currentWordCount
            // Add the new word to the list
            wordsList.add(currentWord)
        }
    }

    init {
        Log.d("GameFragment", "GameViewModel created!")
        getNextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment", "GameViewModel destroyed!")
    }
}