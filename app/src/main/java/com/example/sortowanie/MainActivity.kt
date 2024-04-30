package com.example.sortowanie

import res.layout.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import com.example.sortowanie.*


class MainActivity : AppCompatActivity() {

    private lateinit var editTextText: EditText
    private lateinit var button: Button
    private lateinit var button2: Button
    private lateinit var textView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextText = findViewById(R.id.editTextText)
        button = findViewById(R.id.button)
        button2 = findViewById(R.id.button2)
        textView = findViewById(R.id.textView)


        button.setOnClickListener {

            val input = editTextText.text.toString()

            val numbers = input.split(",").map { it.trim().toInt() }.toTypedArray()

            val sortedArrayInsertion = insertionSort(numbers)

            val sortedText = sortedArrayInsertion.joinToString(", ")
            textView.text = "Metoda sortowania: Sortowanie przez wstawianie\n\n" +
                    "Początkowy ciąg: $input\n\n" +
                    "Posortowany ciąg: $sortedText"
        }

        button2.setOnClickListener {

            val input = editTextText.text.toString()

            // Konwersja danych na tablicę liczb
            val numbers = input.split(",").map { it.trim().toInt() }.toTypedArray()

            val sortedArrayMerge = mergeSort(numbers)

            val sortedText = sortedArrayMerge.joinToString(", ")
            textView.text = "Metoda sortowania: Sortowanie przez scalanie\n\n" +
                    "Początkowy ciąg: $input\n\n" +
                    "Posortowany ciąg: $sortedText"
        }
    }

    private fun insertionSort(array: Array<Int>): Array<Int> {
        for (i in 1 until array.size) {
            val key = array[i]
            var j = i - 1

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j]
                j--
            }
            array[j + 1] = key
        }
        return array
    }

    private fun mergeSort(array: Array<Int>): Array<Int> {
        if (array.size <= 1) return array

        val middle = array.size / 2
        val left = array.copyOfRange(0, middle)
        val right = array.copyOfRange(middle, array.size)

        return merge(mergeSort(left), mergeSort(right))
    }

    private fun merge(left: Array<Int>, right: Array<Int>): Array<Int> {
        var i = 0
        var j = 0
        val result = mutableListOf<Int>()

        while (i < left.size && j < right.size) {
            if (left[i] < right[j]) {
                result.add(left[i])
                i++
            } else {
                result.add(right[j])
                j++
            }
        }

        while (i < left.size) {
            result.add(left[i])
            i++
        }

        while (j < right.size) {
            result.add(right[j])
            j++
        }

        return result.toTypedArray()
    }
}

