package ca.georgiancollege.ice4

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ca.georgiancollege.ice4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding

    // Create Lists of buttons
    private lateinit var numberButtons: List<Button>
    private lateinit var operatorButtons: List<Button>
    private lateinit var modifierButtons: List<Button>

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        // initializing the binding (between the generated class)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // initialize buttons
        numberButtons = listOf(
            binding.zeroButton, binding.oneButton, binding.twoButton,
            binding.threeButton, binding.fourButton, binding.fiveButton,
            binding.sixButton, binding.sevenButton, binding.eightButton,
            binding.nineButton, binding.decimalButton
        )
        operatorButtons = listOf(
            binding.plusButton, binding.minusButton,
            binding.multiplyButton, binding.divideButton
        )
        modifierButtons = listOf(
            binding.percentButton, binding.plusMinusButton,
            binding.clearButton, binding.deleteButton
        )
        configureNumberInput()
    }

    private fun configureNumberInput()
    {
        numberButtons.forEach { button ->
            button.setOnClickListener {
                // capture input from the button
                val input = button.text.toString()
                // capture the text in the resultEditText
                val currentResultText = binding.resultEditText.text.toString()

                // Prevent multiple decimal points in the current number
                if (input == "." && currentResultText.contains("."))
                {
                    return@setOnClickListener // Do nothing if a decimal already exists
                }
                // If the current result is "0" and input is not ".", replace it
                if (currentResultText == "0" && input != ".")
                {
                    binding.resultEditText.setText(input)
                }
                else
                {
                    binding.resultEditText.append(input)
                }
            }
        }
    }
}