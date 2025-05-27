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
            // for each button in the numberButtons list set an On click listener
            button.setOnClickListener {
                // display the number pressed in the resultEditText
                val currentResultText = binding.resultEditText.text.toString()
                // If the current result is "0", replace it with the new number
                if (currentResultText == "0")
                {
                    binding.resultEditText.setText(button.text)
                }
                else // Otherwise, append the new number to the existing result
                {
                    binding.resultEditText.append(button.text)
                }
            }
        }
    }
}