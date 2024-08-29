package com.androiddevelopersguide.ui.screens.kotlinfundamentals

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@Composable
fun KotlinFundamentalsScreen(
    navController: NavController,
    viewModel: KotlinFundamentalsViewModel = hiltViewModel(),
) {
    PageScaffold(
        navController = navController,
        title = "Kotlin fundamentals",
        mobileUI = {
            Introduction()
            VariableAndConstants()
            DataTypes()
            ExpressionsAndStatements()
            Comments()
            Operators()
            Conditions()
            Loops()
            ControlTransfer()
            OOP()
            Generics()
            Annotations()
            Flow()
            Coroutines()
            JavaInteroperability()
            KotlinAndroidExtensions()
            StandardLibrary()
        }
    )
}

@Composable
fun Introduction() {
    Section(
        title = "Introduction",
        description = """
            Kotlin is a modern, statically typed programming language that runs on the Java virtual machine (JVM) and is also compatible with JavaScript. It's designed to be more concise, safe, and interoperable with Java than Java itself.
        
            Here are some of the key features of Kotlin:

            -Statically typed: Kotlin is a statically typed language, which means that the type system checks the types of variables at compile time, preventing type-related errors at runtime.
        
            -Interoperable with Java: Kotlin is 100% interoperable with Java, which means that you can easily call Java code from Kotlin and vice versa.
        
            -Concise syntax: Kotlin has a more concise syntax than Java, which makes it easier to write and read code.
        
            -Null safety: Kotlin has built-in null safety features, which prevent null pointer exceptions and make your code safer.
        
            -Extension functions: Kotlin allows you to add functionality to existing classes through the use of extension functions.
        
            -Coroutines: Kotlin 1.3 and later versions support coroutines, which allow you to write asynchronous code that's much simpler and more efficient than traditional callback-based approaches.
        
            -Data classes: Kotlin provides a simple way to create classes that contain only immutable data and require little boilerplate code.
        """.trimIndent(),
    )
}

@Composable
fun VariableAndConstants() {
    Section(
        title = "Variables & constants",
        description = """
            val:
            val is used to declare a read-only variable, which means its value cannot be changed once it's assigned.
            
            var:
            var is used to declare a mutable variable, which means its value can be changed after it's assigned.
            
            const val:
            const val keyword is used to declare compile-time constants.
        """.trimIndent(),
        code = """
            val readOnlyVariable: Int = 10
            
            var mutableVariable: Int = 20
            
            const val compileTimeConstant: Int = 10
        """.trimIndent()
    )
}

@Composable
fun DataTypes() {
    Section(
        title = "Data types",
        description = """
            Primitive Data Types:
            -Int: 32-bit signed integer
            -Long: 64-bit signed integer
            -Float: 32-bit floating-point number
            -Double: 64-bit floating-point number
            -Boolean: true or false value
            -Char: single character
            -Byte: 8-bit signed integer
            -Short: 16-bit signed integer
            
            Reference Data Types:
            -String: a sequence of characters
            -Array: a collection of elements of the same type
            -List: a collection of elements that can be modified
            -Map: a collection of key-value pairs
            -Set: a collection of unique elements
            
            User-Defined Data Types:
            -Class: a blueprint for creating objects
            -Interface: a contract that specifies a set of methods and properties
        """.trimIndent(),
        code = """
            val myInt: Int = 10
            
            val myLong: Long = 10L
            
            val myFloat: Float = 10.5f
            
            val myDouble: Double = 10.5
            
            val myBoolean: Boolean = true
            
            val myChar: Char = 'a'
            
            val myByte: Byte = 10
            
            val myShort: Short = 10
            
            val myString: String = "Hello, World!"
            
            val myArray: Array<Int> = arrayOf(1, 2, 3, 4, 5)
            
            val myList: List<Int> = listOf(1, 2, 3, 4, 5)
            
            val myMap: Map<String, Int> = mapOf("a" to 1, "b" to 2, "c" to 3)
            
            val mySet: Set<Int> = setOf(1, 2, 3, 4, 5)
            
            class Person(val name: String, val age: Int)
            val person: Person = Person("John", 30)
            
            interface Printable {
                fun print()
            }
            class Document : Printable {
                override fun print() {
                    println("Printing document...")
                }
            }
            val document: Printable = Document()
            document.print()
        """.trimIndent()
    )
}

@Composable
fun ExpressionsAndStatements() {
    Section(
        title = "Expressions & statements",
        description = """
            Expression:
            An expression is a piece of code that evaluates to a value. Expressions can be thought of as a combination of values, variables, operators, and function calls that produce a result.
        
            Statement:
            A statement is a piece of code that performs an action, but does not evaluate to a value. Statements are used to control the flow of a program, declare variables, and perform other actions that don't produce a result.
        """.trimIndent(),
        code = """
            // Expression
            val result = 2 + 3

            // Statement
            println("Hello, World!")
        """.trimIndent()
    )
}

@Composable
fun Comments() {
    Section(
        title = "Comments",
        description = """
            Line comments:
            Line comments start with the // symbol and continue until the end of the line. Any text after the // symbol on a line is ignored by the compiler.
        
            Block comments:
            Block comments start with the /* symbol and end with the */ symbol. Any text between these symbols is ignored by the compiler.
        
            Documentation comments:
            Kotlin also supports documentation comments, which are used to generate documentation for your code. Documentation comments start with the /** symbol and end with the */ symbol.
        
            KDoc comments:
            Kotlin also supports KDoc comments, which are used to generate documentation for your code. KDoc comments start with the /** symbol and end with the */ symbol, and use the Markdown syntax to format the text.
        """.trimIndent(),
        code = """
            // This is a line comment
            
            /*
            This is a block comment
            that spans multiple lines
            */
            
            /**
            * This is a documentation comment
            * that describes the purpose of the function
            */
            
            /**
            * This is a KDoc comment
            * 
            * It can contain Markdown syntax
            * 
            * ### Parameters
            * 
            * * `name`: the name to greet
            * 
            * ### Returns
            * 
            * A greeting message
            */
        """.trimIndent()
    )
}

@Composable
fun Operators() {
    Section(
        title = "Operators",
        description = """
            Arithmetic operators:
            +   Addition              a + b
            -   Subtraction           a - b
            *   Multiplication        a * b
            /   Division              a / b
            %   Modulus (remainder)   a % b
            ++  Increment             a++
            --  Decrement             a--
            
            Comparison operators:
            ==  Equal to                  a == b
            !=  Not equal to              a != b
            >   Greater than              a > b
            <   Less than                 a < b
            >=  Greater than or equal to  a >= b
            <=  Less than or equal to     a <= b
            
            Logical operators:
            &&  Logical AND           a && b
            !   Logical NOT           !a

            Assignment operators:
            =   Assignment                  a = b
            +=  Addition assignment         a += b
            -=  Subtraction assignment      a -= b
            *=  Multiplication assignment   a *= b
            /=  Division assignment         a /= b
            %=  Modulus assignment          a %= b
            
            Bitwise operators:
            and   Bitwise AND            a and b
            or    Bitwise OR             a or b
            xor   Bitwise XOR            a xor b
            inv   Bitwise NOT            a.inv()
            shl   Shift left             a shl b
            shr   Shift right            a shr b
            ushr  Unsigned shift right   a ushr b
            
            Other operators:
            as    Cast                   a as String
            is    Type check             a is String
            in    Membership test        a in list
            !in   Non-membership test    a !in list
            
            Kotlin also allows you to overload operators for your own classes. This means you can define how operators work with your custom classes.
        """.trimIndent(),
    )
}

@Composable
fun Conditions() {
    Section(
        title = "Conditions",
        description = """
            If-Else statement:
            The if statement is used to execute a block of code if a certain condition is true.
            
            Else-If statement:
            The else-if statement is used to check multiple conditions and execute a block of code if any of the conditions are true.
            
            When expression:
            The when expression is used to check multiple conditions and execute a block of code if any of the conditions are true.
            
            Conditional expressions:
            Conditional expressions are used to assign a value to a variable based on a condition.
            
            Elvis expression:
            The Elvis operator is used to provide a default value if the expression is null.
        """.trimIndent(),
        code = """
//          // If-Else statement
            if (condition) {
                // code to be executed if condition is true
            }
            
            // Else-If statement
            if (condition1) {
                // code to be executed if condition1 is true
            } else if (condition2) {
                // code to be executed if condition2 is true
            } else {
                // code to be executed if all conditions are false
            }
            
            // When expression
            when (expression) {
                value1 -> {
                    // code to be executed if expression equals value1
                }
                value2 -> {
                    // code to be executed if expression equals value2
                }
                else -> {
                    // code to be executed if expression does not equal any of the values
                }
            }
            
            // Conditional expressions
            
            // Elvis expression 
            val result = expression ?: defaultValue
        """.trimIndent()
    )
}

@Composable
fun Loops() {
    Section(
        title = "Loops",
        description = """
            For loop:
            The for loop is used to iterate over a range, array, or collection.
            
            For loop with index:
            The for loop with index is used to iterate over a range, array, or collection with an index.
            
            While loop:
            The while loop is used to execute a block of code as long as a certain condition is true.
            
            Do-While loop:
            The do-while loop is used to execute a block of code at least once, and then repeat the execution as long as a certain condition is true.
        """.trimIndent(),
        code = """
            // For loop:
            for (item in collection) {
                // code to be executed
            }
            
            // For loop with index:
            for (index in collection.indices) {
                // code to be executed
            }
            
            // While loop:
            while (condition) {
                // code to be executed
            }
            
            // Do-While loop:
            do {
                // code to be executed
            } while (condition)
        """.trimIndent()
    )
}

@Composable
fun ControlTransfer() {
    Section(
        title = "Control transfer",
        description = """
            Return:
            The return statement is used to exit a function and return a value to the caller.
            
            Break:
            The break statement is used to exit a loop or a when expression.
            
            Continue:
            The continue statement is used to skip the current iteration of a loop and move to the next one.
            
            Labels:
            Labels are used to specify the loop or expression to break or continue.
            
            Throw:
            The throw statement is used to throw an exception.
        """.trimIndent(),
        code = """
            // Return
            fun greet(): String {
                return "Hello"
            }
            
            // Break
            for (i in 1..5) {
                if (i == 3) {
                    break
                }
                println(i)
            }
            
            // Continue
            for (i in 1..5) {
                if (i == 3) {
                    continue
                }
                println(i)
            }
            
            // Label
            loop@ for (i in 1..5) {
                if (i == 3) {
                    break@loop
                }
                println(i)
            }
            
            // Throw
            fun divide(a: Int, b: Int): Int {
                if (b == 0) {
                    throw ArithmeticException("Cannot divide by zero")
                }
                return a / b
            }
        """.trimIndent()
    )
}

@Composable
fun OOP() {
    Section(
        title = "Object Oriented Programming",
        description = """
            Class and object:
            A class is a blueprint for creating objects. A class defines the properties and behavior of an object.
            
            Inheritance:
            Inheritance is a mechanism in which one class can inherit the properties and behavior of another class.
            
            Polymorphism:
            Polymorphism is the ability of an object to take on multiple forms
            
            Abstraction:
            Abstraction is the concept of showing only the necessary information to the outside world while hiding the internal details.
            
            Encapsulation:
            Encapsulation is the concept of bundling data and methods that operate on that data within a single unit.
            
            Composition:
            Composition is the concept of creating objects from other objects.
        """.trimIndent(),
        code = """
            // Class
            class Dog {
                var name: String = ""
                var age: Int = 0

                fun bark() {
                    println("Woof!")
                }
            }

            // Object
            val myDog = Dog()
            myDog.name = "Fido"
            myDog.age = 3
            myDog.bark() // Output: Woof!
            
            // Inheritance
            open class Animal {
                var name: String = ""
                var age: Int = 0

                fun eat() {
                    println("Eating...")
                }
            }

            class Dog : Animal() {
                fun bark() {
                    println("Woof!")
                }
            }

            val myDog = Dog()
            myDog.name = "Fido"
            myDog.age = 3
            myDog.eat() // Output: Eating...
            myDog.bark() // Output: Woof!
            
            // Polymorphism
            open class Animal {
                open fun sound() {
                    println("The animal makes a sound")
                }
            }

            class Dog : Animal() {
                override fun sound() {
                    println("The dog barks")
                }
            }

            class Cat : Animal() {
                override fun sound() {
                    println("The cat meows")
                }
            }

            fun makeSound(animal: Animal) {
                animal.sound()
            }

            val myDog = Dog()
            val myCat = Cat()

            makeSound(myDog) // Output: The dog barks
            makeSound(myCat) // Output: The cat meows
            
            // Abstraction
            abstract class Animal {
                abstract fun sound()
            }

            interface Flyable {
                fun fly()
            }

            class Bird : Animal(), Flyable {
                override fun sound() {
                    println("The bird chirps")
                }

                override fun fly() {
                    println("The bird flies")
                }
            }

            val myBird = Bird()
            myBird.sound() // Output: The bird chirps
            myBird.fly() // Output: The bird flies
            
            // Encapsulation
            class BankAccount {
                private var balance: Double = 0.0

                fun deposit(amount: Double) {
                    balance += amount
                }

                fun withdraw(amount: Double) {
                    if (balance >= amount) {
                        balance -= amount
                    } else {
                        println("Insufficient balance")
                    }
                }

                fun getBalance(): Double {
                    return balance
                }
            }

            val myAccount = BankAccount()
            myAccount.deposit(100.0)
            myAccount.withdraw(50.0)
            println(myAccount.getBalance()) // Output: 50.0
            
            // Composition
            class University {
                class Department {
                    var name: String = ""

                    fun printName() {
                        println("Department: test")
                    }
                }

                var department: Department = Department()

                fun printUniversityName() {
                    println("University")
                }
            }

            val university = University()
            university.department.name = "Computer Science"
            university.printUniversityName() // Output: University
            university.department.printName() // Output: Department: Computer Science
        """.trimIndent()
    )
}

@Composable
fun Generics() {
    Section(
        title = "Generics",
        description = """
            Generics in Kotlin are a way to write reusable code that can work with different data types. Generics allow you to define a class or a function that can work with any data type, rather than being limited to a single data type.
        """.trimIndent(),
        code = """
            // Generic class
            class Box<T>(t: T) {
                var value: T = t
            }
            
            // Generic function
            fun <T : Comparable<T>> max(a: T, b: T): T {
                if (a > b) {
                    return a
                } else {
                    return b
                }
            }
        """.trimIndent()
    )
}

@Composable
fun Annotations() {
    Section(
        title = "Annotations",
        description = """
            Annotations in Kotlin are a way to add metadata to your code. They are used to provide additional information about a class, function, property, or other declaration. 
            
            Annotations are not executed at runtime, but they can be used by the compiler, frameworks, and other tools to generate code, validate inputs, or provide additional functionality.
            
            Here are some common uses of annotations in Kotlin:

            -Dependency Injection: Annotations are used to mark dependencies that need to be injected into a class.
            
            -Validation: Annotations are used to validate inputs, such as checking if a string is not null or empty.
            
            -Serialization: Annotations are used to mark classes and properties that need to be serialized or deserialized.
            
            -Logging: Annotations are used to mark methods or classes that need to be logged.
            
            -Testing: Annotations are used to mark tests and test suites.
        """.trimIndent(),
        code =
        """
            // @JvmName: This annotation is used to specify the name of a Kotlin function or property when it is accessed from Java.
            @JvmName("helloWorld")
            fun hello() {
                println("Hello, World!")
            }
            
            // @Throws: This annotation is used to specify the exceptions that a function can throw.
            @Throws(IOException::class)
            fun readFromFile(path: String) {
                // code that reads from a file
            }
            
            // @RequiresApi: This annotation is used to specify the minimum API level required to use a function or class.
            @RequiresApi(Build.VERSION_CODES.O)
            fun useNewApi() {
                // code that uses a new API
            }
            
            // @Deprecated: This annotation is used to mark a function or class as deprecated.
            @Deprecated("Use the new function instead")
            fun oldFunction() {
                // code that is deprecated
            }
            
            // @Inject: This annotation is used to mark a constructor or property as injectable.
            class MyClass @Inject constructor(private val dependency: Dependency) {
                // code that uses the dependency
            }
            
            // @Retention: This annotation is used to specify how long an annotation is retained.
            @Retention(AnnotationRetention.SOURCE)
            @Target(AnnotationTarget.FUNCTION)
            annotation class MyAnnotation
            
            // @Target: This annotation is used to specify the types of declarations that an annotation can be applied to.
            @Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY)
            annotation class MyAnnotation
            
            // @Repeatable: This annotation is used to specify that an annotation can be repeated.
            @Repeatable
            @Target(AnnotationTarget.FUNCTION)
            annotation class MyAnnotation
        """.trimIndent()
    )
}

@Composable
fun Flow() {
    Section(
        title = "Flow",
        description = """
            Flow is a concept in Kotlin that allows you to handle asynchronous data streams in a concise and expressive way.
            
            A flow is a sequence of values that are emitted over time, similar to a stream in other programming languages. Flows are cold, meaning that they only start emitting values when someone starts collecting them.
            
            Here are some key concepts in Flow:
            
            -Flow: A flow is a sequence of values that are emitted over time.
            
            -Emitter: An emitter is an object that sends values to a flow.
            
            -Collector: A collector is an object that receives values from a flow.
            
            -Subscription: A subscription is an object that represents the connection between an emitter and a collector.
            
            
            Flows can be used to handle a wide range of asynchronous data streams, such as:
            
            -Network requests
            -Database queries
            -File I/O
            -User input
            
            
            Flows provide several benefits over traditional asynchronous programming approaches:

            -Concise code: Flows allow you to write concise and expressive code that's easy to read and maintain.
            
            -Error handling: Flows provide built-in error handling mechanisms, making it easy to handle errors in a centralized way.
            
            -Cancellation: Flows support cancellation, which allows you to cancel the flow when it's no longer needed.
            
            -Backpressure: Flows provide backpressure support, which allows you to control the rate at which values are emitted.
            
            
            Here are some common flow operators:

            -map: Transforms each value emitted by the flow using a given function.
            
            -filter: Filters out values that don't match a given predicate.
            
            -reduce: Reduces the values emitted by the flow using a given function.
            
            -collect: Collects the values emitted by the flow and performs an action on each value.

        """.trimIndent(),
        code = """
            flow {
                for (i in 1..3) {
                    emit(i)
                }
            }.map { value -> value * 2 }
            .collect { value ->
                println("Collected $\value")
            }
        """.trimIndent()
    )
}

@Composable
fun Coroutines() {
    Section(
        title = "Coroutines",
        description = """
            In Kotlin, coroutines are a way to write asynchronous code that's much simpler and more efficient than traditional callbacks or threads.
            
            
            Key concepts:

            -Coroutine scope: A scope that defines the lifetime of a coroutine. You can think of it as a container for coroutines.
            
            -Coroutine builder: A function that creates a coroutine, such as launch or async.
            
            -Suspend function: A function that can be paused and resumed, marked with the suspend keyword.
            
            -Coroutine context: A set of elements that define the execution of a coroutine, such as the dispatcher, job, and scope.
            
            
            Coroutine builders:
            
            -Launch: Creates a coroutine that runs independently, without blocking the calling thread.
            
            -Async: Creates a coroutine that returns a value, which can be awaited using await().
            
            
            Suspend functions:
            A suspend function is a function that can be paused and resumed. You can call a suspend function from a coroutine or another suspend function.
            
            Coroutine context:
            A coroutine context defines the execution of a coroutine. You can specify a dispatcher, job, and scope when creating a coroutine.
            
            Cancellation:
            Coroutines can be cancelled using the cancel() function or by calling cancelAndJoin() on the job.
        """.trimIndent(),
        code = """
            // Launch
            import kotlinx.coroutines.*

            fun main() = runBlocking {
                launch {
                    println("Hello, ")
                    delay(1000)
                    println("World!")
                }
                println("Main thread")
            }
            
            // Async
            import kotlinx.coroutines.*

            fun main() = runBlocking {
                val result = async {
                    delay(1000)
                    "Hello, World!"
                }
                println(result.await())
            }
            
            // Suspend function
            import kotlinx.coroutines.*

            suspend fun mySuspendFunction(): String {
                delay(1000)
                return "Hello, World!"
            }

            fun main() = runBlocking {
                println(mySuspendFunction())
            }
            
            // Coroutine context
            import kotlinx.coroutines.*

            fun main() = runBlocking {
                val scope = CoroutineScope(Dispatchers.IO)
                scope.launch {
                    println("Running on IO dispatcher")
                }
            }
            
            // Cancellation
            import kotlinx.coroutines.*

            fun main() = runBlocking {
                val job = launch {
                    println("Starting...")
                    delay(1000)
                    println("Finishing...")
                }
                delay(500)
                job.cancel()
                job.join()
            }
        """.trimIndent()
    )
}

@Composable
fun JavaInteroperability() {
    Section(
        title = "Java interoperability",
        description = """
            Kotlin is designed to be 100% interoperable with Java, which means you can easily call Java code from Kotlin and vice versa.
        """.trimIndent(),
        code = """
            // Kotlin code
            fun sayHello() {
                println("Hello from Kotlin!")
            }

            // Java code
            public class Main {
                public static void main(String[] args) {
                    KotlinFileKt.sayHello(); // Output: Hello from Kotlin!
                }
            }
        """.trimIndent()
    )
}

@Composable
fun KotlinAndroidExtensions() {
    Section(
        title = "Kotlin android extensions",
        description = """
            Kotlin Android Extensions are a set of tools that provide a more concise and expressive way of writing Android apps in Kotlin.
             
            Here are some of the key features:
            
            -Synthetic properties:
            Kotlin Android Extensions provide synthetic properties for views, which allow you to access views in a more concise way.
            
            -View binding:
            View binding is a feature that allows you to bind views to variables, making it easier to access and manipulate views.
            
            -Parcelable Implementation:
            Kotlin Android Extensions provide a simple way to implement the Parcelable interface for your data classes.
            
            -Android KTX:
            Android KTX is a set of extensions that provide a more concise and expressive way of writing Android code.
            
            -Anko:
            Anko is a library that provides a set of useful extensions for Android development.
        """.trimIndent(),
        code = """
            // Synthetic properties
            class MainActivity : AppCompatActivity() {
                override fun onCreate(savedInstanceState: Bundle?) {
                    super.onCreate(savedInstanceState)
                    setContentView(R.layout.activity_main)

                    textView.text = "Hello, World!"
                }
            }
            
            // View binding
            class MainActivity : AppCompatActivity() {
                private lateinit var binding: ActivityMainBinding

                override fun onCreate(savedInstanceState: Bundle?) {
                    super.onCreate(savedInstanceState)
                    binding = ActivityMainBinding.inflate(layoutInflater)
                    val view = binding.root
                    setContentView(view)

                    binding.textView.text = "Hello, World!"
                }
            }
            
            // Parcelable implementation
            @Parcelize
            data class User(val name: String, val age: Int) : Parcelable
            
            // Android KTX
            class MainActivity : AppCompatActivity() {
                override fun onCreate(savedInstanceState: Bundle?) {
                    super.onCreate(savedInstanceState)
                    setContentView(R.layout.activity_main)

                    val bundle = bundleOf("key" to "value")
                    // ...
                }
            }
            
            // Anko
            class MainActivity : AppCompatActivity() {
                override fun onCreate(savedInstanceState: Bundle?) {
                    super.onCreate(savedInstanceState)
                    verticalLayout {
                        val name = editText()
                        button("Say Hello") {
                            onClick { toast("Hello, $/{name.text}!") }
                        }
                    }
                }
            }
        """.trimIndent()
    )
}

@Composable
fun StandardLibrary() {
    Section(
        title = "Kotlin standard library",
        description = """
            The Kotlin Standard Library is a collection of functions and classes that are part of the Kotlin programming language.
            
            It provides a set of reusable functions and utilities that can be used to perform various tasks, such as:
            
            Collections:
            
            -filter(): Filters a collection based on a predicate.
            
            -map(): Transforms a collection by applying a function to each element.
            
            -reduce(): Reduces a collection to a single value by applying a function.
            
            -sorted(): Sorts a collection in ascending or descending order.
            
            
            Strings:

            -trim(): Removes leading and trailing whitespace from a string.
            
            -split(): Splits a string into a list of substrings based on a delimiter.
            
            -replace(): Replaces a substring with another string.
            
            -toUpperCase(): Converts a string to uppercase.
        """.trimIndent(),
        code = """
            // Collections
            val numbers = listOf(1, 2, 3, 4, 5)
            val evenNumbers = numbers.filter { it % 2 == 0 } // [2, 4]
            val doubleNumbers = numbers.map { it * 2 } // [2, 4, 6, 8, 10]
            val sum = numbers.reduce { acc, i -> acc + i } // 15
            val sortedNumbers = numbers.sorted() // [1, 2, 3, 4, 5]
            
            // Strings
            val str = "   Hello, World!   "
            val trimmedStr = str.trim() // "Hello, World!"
            val words = str.split(", ") // ["Hello", "World!"]
            val replacedStr = str.replace("Hello", "Goodbye") // "   Goodbye, World!   "
            val upperCaseStr = str.toUpperCase() // "   HELLO, WORLD!   "
        """.trimIndent()
    )
}

@Preview
@Composable
fun KotlinFundamentalsScreenPreview() {
    KotlinFundamentalsScreen(navController = rememberNavController())
}