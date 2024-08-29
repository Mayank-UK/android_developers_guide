package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation.subscreens.form

import android.os.Build
import android.widget.CalendarView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormScreen(
    navController: NavController,
    viewModel: FormViewModel = hiltViewModel(),
) {
    PageScaffold(
        navController = navController,
        title = "Form",
        mobileUI = {
            FormHandling()
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormHandling() {
    Section(
        title = "Form handling",
        description = """
            This form handles most of the form elements.
        """.trimIndent(),
        demo = {
            val context = LocalContext.current
            var text by remember { mutableStateOf("") }
            var isChecked by remember { mutableStateOf(false) }
            var selectedOption by remember { mutableStateOf("Option 1") }
            var isEnabled by remember { mutableStateOf(false) }
            var sliderValue by remember { mutableStateOf(34f) }
            var dropdownExpanded by remember { mutableStateOf(false) }
            var dropdownSelectedOption by remember { mutableStateOf("Select an option") }
            val dropdownOptions = listOf("Option 1", "Option 2", "Option 3")
            var selectedDate by remember { mutableStateOf(LocalDate.now()) }
            var showDialog by remember { mutableStateOf(false) }
            val dateFormatter = remember { DateTimeFormatter.ofPattern("yyyy-MM-dd") }
            var selectedTime by remember { mutableStateOf(LocalTime.now()) }
            var showTimePickerDialog by remember { mutableStateOf(false) }
            val timeFormatter = remember { DateTimeFormatter.ofPattern("HH:mm") }

            var textError by remember { mutableStateOf<String?>(null) }
            var dropdownError by remember { mutableStateOf<String?>(null) }

            fun validateForm(): Boolean {
                var isValid = true

                if (text.isBlank()) {
                    textError = "Enter some text in input field"
                    isValid = false
                }

                if (dropdownSelectedOption == "Select an option") {
                    dropdownError = "Select a drop down option"
                    isValid = false
                }

                return isValid
            }

            // It is a bad practice to nest composable functions, but here it can tolerated
            @RequiresApi(Build.VERSION_CODES.O)
            @Composable
            fun DatePickerDialog(
                onDismissRequest: () -> Unit,
                onDateSelected: (LocalDate) -> Unit,
                initialDate: LocalDate
            ) {
                val selDate = remember { mutableStateOf(initialDate) }

                Dialog(onDismissRequest = onDismissRequest) {
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = MaterialTheme.colorScheme.surface
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            AndroidView(
                                factory = { context ->
                                    CalendarView(context).apply {
                                        date = initialDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
                                        setOnDateChangeListener { _, year, month, dayOfMonth ->
                                            selDate.value = LocalDate.of(year, month + 1, dayOfMonth)
                                        }
                                    }
                                },
                                modifier = Modifier.wrapContentSize()
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                TextButton(onClick = onDismissRequest) {
                                    Text("Cancel")
                                }
                                TextButton(
                                    onClick = {
                                        onDateSelected(selDate.value)
                                        onDismissRequest()
                                    }
                                ) {
                                    Text("OK")
                                }
                            }
                        }
                    }
                }
            }

            // It is a bad practice to nest composable functions, but here it can tolerated
            @RequiresApi(Build.VERSION_CODES.O)
            @Composable
            fun TimePickerDialog(
                onDismissRequest: () -> Unit,
                onTimeSelected: (LocalTime) -> Unit,
                initialTime: LocalTime
            ) {
                var hour by remember { mutableStateOf(initialTime.hour) }
                var minute by remember { mutableStateOf(initialTime.minute) }

                // It is a bad practice to nest composable functions, but here it can tolerated
                @Composable
                fun NumberPicker(
                    value: Int,
                    onValueChange: (Int) -> Unit,
                    range: IntRange
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        IconButton(onClick = {
                            if (value < range.last) onValueChange(value + 1)
                        }) {
                            Text("▲")
                        }
                        Text(text = String.format("%02d", value))
                        IconButton(onClick = {
                            if (value > range.first) onValueChange(value - 1)
                        }) {
                            Text("▼")
                        }
                    }
                }

                Dialog(onDismissRequest = onDismissRequest) {
                    Surface(
                        shape = MaterialTheme.shapes.medium,
                        color = MaterialTheme.colorScheme.surface
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Select Time",
                                style = MaterialTheme.typography.titleLarge
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                NumberPicker(
                                    value = hour,
                                    onValueChange = { hour = it },
                                    range = 0..23
                                )
                                Text(":")
                                NumberPicker(
                                    value = minute,
                                    onValueChange = { minute = it },
                                    range = 0..59
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                TextButton(onClick = onDismissRequest) {
                                    Text("Cancel")
                                }
                                TextButton(
                                    onClick = {
                                        onTimeSelected(LocalTime.of(hour, minute))
                                        onDismissRequest()
                                    }
                                ) {
                                    Text("OK")
                                }
                            }
                        }
                    }
                }
            }


            // form
            Column {
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Enter your name") },
                    placeholder = { Text("Name") }
                )
                Spacer(Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = { isChecked = it }
                    )
                    Text("Checkbox")
                }
                Spacer(Modifier.height(16.dp))
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = selectedOption == "Option 1",
                            onClick = { selectedOption = "Option 1" }
                        )
                        Text("Option 1")
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = selectedOption == "Option 2",
                            onClick = { selectedOption = "Option 2" }
                        )
                        Text("Option 2")
                    }

                }
                Spacer(Modifier.height(16.dp))
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Switch(
                        checked = isEnabled,
                        onCheckedChange = { isEnabled = it }
                    )
                    Spacer(Modifier.width(16.dp))
                    Text("Switch")
                }
                Spacer(Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Slider(
                        value = sliderValue,
                        onValueChange = { sliderValue = it },
                        valueRange = 0f..100f
                    )
                }
                Spacer(Modifier.height(16.dp))
                Column(
                    Modifier
                        .clickable { dropdownExpanded = !dropdownExpanded }
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = dropdownSelectedOption,
                    )
                    DropdownMenu(
                        expanded = dropdownExpanded,
                        onDismissRequest = { dropdownExpanded = false }
                    ) {
                        dropdownOptions.forEach { option ->
                            DropdownMenuItem(
                                onClick = {
                                    dropdownSelectedOption = option
                                    dropdownExpanded = !dropdownExpanded
                                },
                                text = { Text(option) }
                            )
                        }
                    }
                }
                Spacer(Modifier.height(16.dp))
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Selected Date: ${selectedDate.format(dateFormatter)}",
                        modifier = Modifier
                            .clickable { showDialog = true }
                    )

                    if (showDialog) {
                        DatePickerDialog(
                            onDismissRequest = { showDialog = false },
                            onDateSelected = {
                                selectedDate = it
                                showDialog = false
                            },
                            initialDate = selectedDate
                        )
                    }
                }
                Spacer(Modifier.height(16.dp))
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Selected Time: ${selectedTime.format(timeFormatter)}",
                        modifier = Modifier
                            .clickable { showTimePickerDialog = true }
                    )

                    if (showTimePickerDialog) {
                        TimePickerDialog(
                            onDismissRequest = { showTimePickerDialog = false },
                            onTimeSelected = {
                                selectedTime = it
                                showTimePickerDialog = false
                            },
                            initialTime = selectedTime
                        )
                    }
                }
                Spacer(Modifier.height(16.dp))
                Button(
                    onClick = {
                        println("form validation: ${validateForm()}")
                        if (validateForm()) {
                            Toast.makeText(context, "Form successfully submitted", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(context, "Please fill complete form", Toast.LENGTH_LONG).show()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text("Submit")
                }
                Spacer(Modifier.height(16.dp))
            }
        },
        code = """
            val context = LocalContext.current
            var text by remember { mutableStateOf("") }
            var isChecked by remember { mutableStateOf(false) }
            var selectedOption by remember { mutableStateOf("Option 1") }
            var isEnabled by remember { mutableStateOf(false) }
            var sliderValue by remember { mutableStateOf(34f) }
            var dropdownExpanded by remember { mutableStateOf(false) }
            var dropdownSelectedOption by remember { mutableStateOf("Select an option") }
            val dropdownOptions = listOf("Option 1", "Option 2", "Option 3")
            var selectedDate by remember { mutableStateOf(LocalDate.now()) }
            var showDialog by remember { mutableStateOf(false) }
            val dateFormatter = remember { DateTimeFormatter.ofPattern("yyyy-MM-dd") }
            var selectedTime by remember { mutableStateOf(LocalTime.now()) }
            var showTimePickerDialog by remember { mutableStateOf(false) }
            val timeFormatter = remember { DateTimeFormatter.ofPattern("HH:mm") }

            var textError by remember { mutableStateOf<String?>(null) }
            var dropdownError by remember { mutableStateOf<String?>(null) }

            fun validateForm(): Boolean {
                var isValid = true

                if (text.isBlank()) {
                    textError = "Enter some text in input field"
                    isValid = false
                }

                if (dropdownSelectedOption == "Select an option") {
                    dropdownError = "Select a drop down option"
                    isValid = false
                }

                return isValid
            }

            @RequiresApi(Build.VERSION_CODES.O)
            @Composable
            fun DatePickerDialog(
                onDismissRequest: () -> Unit,
                onDateSelected: (LocalDate) -> Unit,
                initialDate: LocalDate
            ) {
                val selDate = remember { mutableStateOf(initialDate) }

                Dialog(onDismissRequest = onDismissRequest) {
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = MaterialTheme.colorScheme.surface
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            AndroidView(
                                factory = { context ->
                                    CalendarView(context).apply {
                                        date = initialDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
                                        setOnDateChangeListener { _, year, month, dayOfMonth ->
                                            selDate.value = LocalDate.of(year, month + 1, dayOfMonth)
                                        }
                                    }
                                },
                                modifier = Modifier.wrapContentSize()
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                TextButton(onClick = onDismissRequest) {
                                    Text("Cancel")
                                }
                                TextButton(
                                    onClick = {
                                        onDateSelected(selDate.value)
                                        onDismissRequest()
                                    }
                                ) {
                                    Text("OK")
                                }
                            }
                        }
                    }
                }
            }

            @RequiresApi(Build.VERSION_CODES.O)
            @Composable
            fun TimePickerDialog(
                onDismissRequest: () -> Unit,
                onTimeSelected: (LocalTime) -> Unit,
                initialTime: LocalTime
            ) {
                var hour by remember { mutableStateOf(initialTime.hour) }
                var minute by remember { mutableStateOf(initialTime.minute) }

                // It is a bad practice to nest composable functions, but here it can tolerated
                @Composable
                fun NumberPicker(
                    value: Int,
                    onValueChange: (Int) -> Unit,
                    range: IntRange
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        IconButton(onClick = {
                            if (value < range.last) onValueChange(value + 1)
                        }) {
                            Text("▲")
                        }
                        Text(text = String.format("%02d", value))
                        IconButton(onClick = {
                            if (value > range.first) onValueChange(value - 1)
                        }) {
                            Text("▼")
                        }
                    }
                }

                Dialog(onDismissRequest = onDismissRequest) {
                    Surface(
                        shape = MaterialTheme.shapes.medium,
                        color = MaterialTheme.colorScheme.surface
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Select Time",
                                style = MaterialTheme.typography.titleLarge
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                NumberPicker(
                                    value = hour,
                                    onValueChange = { hour = it },
                                    range = 0..23
                                )
                                Text(":")
                                NumberPicker(
                                    value = minute,
                                    onValueChange = { minute = it },
                                    range = 0..59
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                TextButton(onClick = onDismissRequest) {
                                    Text("Cancel")
                                }
                                TextButton(
                                    onClick = {
                                        onTimeSelected(LocalTime.of(hour, minute))
                                        onDismissRequest()
                                    }
                                ) {
                                    Text("OK")
                                }
                            }
                        }
                    }
                }
            }


            // form
            Column {
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Enter your name") },
                    placeholder = { Text("Name") }
                )
                Spacer(Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = { isChecked = it }
                    )
                    Text("Checkbox")
                }
                Spacer(Modifier.height(16.dp))
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = selectedOption == "Option 1",
                            onClick = { selectedOption = "Option 1" }
                        )
                        Text("Option 1")
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = selectedOption == "Option 2",
                            onClick = { selectedOption = "Option 2" }
                        )
                        Text("Option 2")
                    }

                }
                Spacer(Modifier.height(16.dp))
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Switch(
                        checked = isEnabled,
                        onCheckedChange = { isEnabled = it }
                    )
                    Spacer(Modifier.width(16.dp))
                    Text("Switch")
                }
                Spacer(Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Slider(
                        value = sliderValue,
                        onValueChange = { sliderValue = it },
                        valueRange = 0f..100f
                    )
                }
                Spacer(Modifier.height(16.dp))
                Column(
                    Modifier
                        .clickable { dropdownExpanded = !dropdownExpanded }
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = dropdownSelectedOption,
                    )
                    DropdownMenu(
                        expanded = dropdownExpanded,
                        onDismissRequest = { dropdownExpanded = false }
                    ) {
                        dropdownOptions.forEach { option ->
                            DropdownMenuItem(
                                onClick = {
                                    dropdownSelectedOption = option
                                    dropdownExpanded = !dropdownExpanded
                                },
                                text = { Text(option) }
                            )
                        }
                    }
                }
                Spacer(Modifier.height(16.dp))
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Selected Date: $/{selectedDate.format(dateFormatter)}",
                        modifier = Modifier
                            .clickable { showDialog = true }
                    )

                    if (showDialog) {
                        DatePickerDialog(
                            onDismissRequest = { showDialog = false },
                            onDateSelected = {
                                selectedDate = it
                                showDialog = false
                            },
                            initialDate = selectedDate
                        )
                    }
                }
                Spacer(Modifier.height(16.dp))
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Selected Time: $/{selectedTime.format(timeFormatter)}",
                        modifier = Modifier
                            .clickable { showTimePickerDialog = true }
                    )

                    if (showTimePickerDialog) {
                        TimePickerDialog(
                            onDismissRequest = { showTimePickerDialog = false },
                            onTimeSelected = {
                                selectedTime = it
                                showTimePickerDialog = false
                            },
                            initialTime = selectedTime
                        )
                    }
                }
                Spacer(Modifier.height(16.dp))
                Button(
                    onClick = {
                        println("form validation: $/{validateForm()}")
                        if (validateForm()) {
                            Toast.makeText(context, "Form successfully submitted", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(context, "Please fill complete form", Toast.LENGTH_LONG).show()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text("Submit")
                }
                Spacer(Modifier.height(16.dp))
            }
        """.trimIndent()
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun FormScreenPreview() {
    FormScreen(navController = rememberNavController())
}