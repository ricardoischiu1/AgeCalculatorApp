/**
 * Age Calculator App
 * This app allows the user to input their first name, last name, and date of birth.
 * It calculates and displays the user's age in a Toast message, while handling invalid inputs and dates.
 */

package edu.psu.sweng888.agecalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {

    // Entry point for the activity where the UI is set up and logic is initialized
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the layout for the activity from the XML file
        setContentView(R.layout.layout)

        // Get references to UI elements: EditTexts for first name, last name, and date of birth, and a Button for age calculation
        val firstNameField = findViewById<EditText>(R.id.firstName)
        val lastNameField = findViewById<EditText>(R.id.lastName)
        val dateOfBirthField = findViewById<EditText>(R.id.dateOfBirth)
        val calculateButton = findViewById<Button>(R.id.calculateAgeButton)

        // Set up an OnClickListener for the "Calculate Age" button to trigger the age calculation logic when clicked
        calculateButton.setOnClickListener {
            // Get the user inputs from the text fields and convert them to strings
            val firstName = firstNameField.text.toString()
            val lastName = lastNameField.text.toString()
            val dateOfBirth = dateOfBirthField.text.toString()

            // Check if any of the input fields are empty and show a Toast asking the user to fill all fields
            if (firstName.isEmpty() || lastName.isEmpty() || dateOfBirth.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Attempt to parse the date of birth input into a valid Date object
            try {
                // Define the expected date format (MM/dd/yyyy) for the user input
                val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.US)

                // Parse the date string into a Date object
                val birthDate = sdf.parse(dateOfBirth)

                // If the date is valid, calculate and display the user's age in a Toast message
                if (birthDate != null) {
                    val age = calculateAge(birthDate)
                    Toast.makeText(this, "$firstName $lastName is $age years old", Toast.LENGTH_SHORT).show()
                } else {
                    // If the date format is invalid, show an error Toast
                    Toast.makeText(this, "Invalid date format", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                // Catch any parsing exceptions and display a Toast if the date format is invalid
                Toast.makeText(this, "Invalid date format", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Function to calculate the user's age based on the birthDate provided.
     * @param birthDate Date object representing the user's date of birth.
     * @return The calculated age in years.
     */
    private fun calculateAge(birthDate: Date): Int {
        // Get the current date
        val today = Calendar.getInstance()

        // Set up a Calendar instance for the birth date
        val birthDay = Calendar.getInstance().apply { time = birthDate }

        // Calculate the difference in years between the current year and the birth year
        var age = today.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR)

        // Check if the current day of the year is before the user's birthday, and adjust the age if necessary
        if (today.get(Calendar.DAY_OF_YEAR) < birthDay.get(Calendar.DAY_OF_YEAR)) {
            age--
        }

        // Return the calculated age
        return age
    }
}