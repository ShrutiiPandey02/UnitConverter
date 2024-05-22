package new1.udemy.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import new1.udemy.myapplication.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}


@Composable
fun UnitConverter(){
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember{ mutableStateOf("Meters") }
    var outputUnit by remember{ mutableStateOf("Meters") }
    var iExpanded by remember{ mutableStateOf(false) }
    var oExpanded by remember{ mutableStateOf(false) }
    val conversionFactor = remember{ mutableStateOf(1.00) }
    val oconversionFactor = remember{ mutableStateOf(1.00) }

    fun convertUnit(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0// elvis operator
        val result = (inputValueDouble * conversionFactor.value * 100.0/oconversionFactor.value).roundToInt()/100.0
        outputValue = result.toString()
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally


    ){
        Text("Unit Converter", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue,
            onValueChange = {
                inputValue = it
                convertUnit()
            },
            label = { Text(text = "Enter Value")})
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            //Input box
            Box {
                //Input Button
                Button(onClick = { iExpanded = true }) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "ArrowDown")

                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false })

                {

                    DropdownMenuItem(text = { Text("Centimeter") }, onClick = {
                        iExpanded = false
                        inputUnit = "Centimeter"
                        conversionFactor.value = 0.01
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text("feet") }, onClick = {
                        iExpanded = false
                        inputUnit = "Feet"
                        conversionFactor.value = 0.3048
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text("MilliMeters") }, onClick = {
                        iExpanded = false
                        inputUnit = "MilliMeters"
                        conversionFactor.value = 0.001
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text(text = "Meter") }, onClick = {
                        iExpanded = false
                        inputUnit = "Meter"
                        conversionFactor.value = 1.00
                        convertUnit()
                    })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            //Output Box
            Box {
                //Output Box
                Button(onClick = { oExpanded = true }) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "ArrowDown")

                }


                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false })

                {

                    DropdownMenuItem(text = { Text("Centimeter") }, onClick = {
                        oExpanded = false
                        outputUnit = "Centimeter"
                        oconversionFactor.value = 0.01
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text("feet") }, onClick = {
                        oExpanded = false
                        outputUnit = "feet"
                        oconversionFactor.value = 0.3048
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text("MilliMeters") }, onClick = {
                        oExpanded = false
                        outputUnit = "MilliMeters"
                        oconversionFactor.value = 0.001
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text(text = "Meter") }, onClick = {
                        oExpanded = false
                        outputUnit = "Meters"
                        oconversionFactor.value = 1.00
                        convertUnit()
                    })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: $outputValue ",
            style = MaterialTheme.typography.headlineMedium
        )

    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}
