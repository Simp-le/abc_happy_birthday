package com.abc.happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abc.happybirthday.ui.theme.HappyBirthdayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HappyBirthdayTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingImage(
                        message = stringResource(R.string.happy_birthday_text), // stringResource() is a composable function and getString() is not
                        from = stringResource(R.string.signature_text),
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

/*
The compose function:
- must be a noun: DoneButton() (сущ.)
- not a verb or verb phrase: DrawTextField() (не глагол/ глагольная группа)
- not a nouned preposition: TextFieldWithLink() (без предлогов with, by, for, in, on, of, to, between...)
- not an adjective: Bright() (не прилагательное)
- not an adverb: Outside() (не наречие)
- nouns MAY be prefixed by descriptive adjectives: RoundIcon() (может содержать прилагательные спереди)
*/

@Composable
fun GreetingImage(message: String, from: String, modifier: Modifier = Modifier) {
    Box(modifier) {
        Image(
            painter = painterResource(R.drawable.androidparty),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            contentScale = ContentScale.Crop,
            alpha = 0.8F
        ) // для людей с огр. возм. нужно описание или его отсутствие, если это декор
        GreetingText(
            message = message,
            from = from,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
    }
}

/*
* The scalable (масштабируемые) pixels (SP) is a unit of measure for the font size.
* UI elements in Android apps use two different units of measurement:
* density-independent (независимые от плотности) pixels (DP), which we'll use for the layout,
* and scalable pixels (SP). By default, the SP unit is the same size as the DP unit,
* but it resizes based on the user's preferred text size under phone settings.
*
* Dp - неважно, сколько пикселей, физический размер элемента будет везде одинаковым,
* просто элемент займёт либо больше пикселей, либо меньше, в зависимости от плотности пикселей
* Sp - тоже самое, что и Dp, но с учётом множителя размера шрифта из настроек телефона.
* Не используем Sp в layout потому что нам не нужно, чтобы размер шрифта из настроек
* влиял на размер изображений, кнопок и других элементов интерфейса.
*/
@Composable
// It's a best practice to have the Composable accept Modifier parameter,
// and pass that modifier to its first child
fun GreetingText(message: String, from: String, modifier: Modifier = Modifier) {
    // It is a good practice to use padding values in increments of 4.dp
    Column(verticalArrangement = Arrangement.Center, modifier = modifier) {
        Text(
            text = message,
            fontSize = 100.sp,
            lineHeight = 116.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = from,
            fontSize = 36.sp,
            lineHeight = 42.sp,
            textAlign = TextAlign.End,
            modifier = Modifier
                .padding(top = 16.dp)
                .padding(end = 16.dp)
                .align(alignment = Alignment.End)
        )
    }
}


/*
* The composable function must provide default values for any parameters to preview it.
* For this reason, it is recommended not to preview the Greeting() function directly.
* Instead, we need to add another function, the BirthdayCardPreview() function in this case,
* that calls the Greeting() function and passes appropriate arguments to it.
*/
@Preview(showBackground = true)
// This function is only for previewing in the Design pane in Android Studio.
// These changes aren't reflected in the app!
@Composable
// We use Pascal case for naming Compose functions (FirstSecondLastWords)
fun BirthdayCardPreview() {
    HappyBirthdayTheme {
        GreetingImage(
            message = stringResource(R.string.happy_birthday_text),
            from = stringResource(R.string.signature_text),
            modifier = Modifier.fillMaxSize()
        )
    }
}


/*
Summary:
    * Jetpack Compose is a modern toolkit for building Android UI.
Jetpack Compose simplifies and accelerates UI development on Android with less code,
powerful tools, and intuitive Kotlin APIs.

    * The user interface (UI) of an app is what you see on the screen: text, images, buttons,
and many other types of elements.

    * Composable functions are the basic building block of Compose.
A composable function is a function that describes some part of your UI.

    * The Composable function is annotated with the @Composable annotation;
this annotation informs the Compose compiler that this function is intended to convert data into UI.

    * The three basic standard layout elements in Compose are Column, Row, and Box. They are
Composable functions that take Composable content, so you can place items inside. For example,
each child within a Row will be placed horizontally next to each other.
*/

/*
Summary 2 (add images to your Android app):
    * The Resource Manager tab in Android Studio helps you add and organize your images
and other resources.
    * An Image composable is a UI element that displays images in your app.
    * An Image composable should have a content description to make your app more accessible.
    * Text that's shown to the user, such as the birthday greeting, should be extracted into
a string resource to make it easier to translate your app into other languages.
*/