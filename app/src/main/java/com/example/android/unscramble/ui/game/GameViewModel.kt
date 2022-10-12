package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private var score = 0
    private var _currentWordCount = 0
    val currentWordCount: Int
        get() = _currentWordCount
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
            ++_currentWordCount
            // Add the new word to the list
            wordsList.add(currentWord)
        }
    }

    init {
        Log.d("GameFragment", "GameViewModel created!")
        getNextWord()
    }

    /*
    * Returns true if the current word count is less than MAX_NO_OF_WORDS.
    * Updates the next word.
    */
    fun nextWord(): Boolean {
        return if (currentWordCount < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment", "GameViewModel destroyed!")
    }
}