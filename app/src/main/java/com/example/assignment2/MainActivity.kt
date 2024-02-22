package com.example.assignment2

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment2.ui.theme.Assignment2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SpaceArtDisplay()
                }
            }
        }
    }
}


@Composable
fun SpaceArtDisplay( modifier: Modifier = Modifier) {
   val config= LocalConfiguration.current
    when (config.orientation){
        Configuration.ORIENTATION_LANDSCAPE ->{
            artSpaceDisplayLandscape()
        }
        else-> {
            artSpaceDisplayPortrait()
        }
    }
}




@Composable
fun artSpaceDisplayLandscape() {
    var displayImage by remember {
        mutableStateOf(0)
    }
    val imageResource = when (displayImage) {
        0 -> R.drawable.architecture
        1 -> R.drawable.business
        2 -> R.drawable.crafts
        3 -> R.drawable.design

        else -> {
            R.drawable.architecture
        }
    }
    var displayDescription by remember {
        mutableStateOf(0)
    }
    val stringResource = when (displayDescription) {
        0 -> R.string.architecture_image
        1 -> R.string.business_image
        2 -> R.string.crafts_image
        3 -> R.string.design_image

        else -> {
            R.string.architecture_image
        }
    }
    Row(
        modifier = Modifier
            .fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
    )
    {
        Column(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth(0.35f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            Surface(
                modifier = Modifier
                    .fillMaxHeight(0.65f)
                    .fillMaxWidth(1f)
                    .border(BorderStroke((3.dp), SolidColor(Color.White)), RectangleShape)
                    .shadow(15.dp, RectangleShape)
                    .align(alignment = Alignment.CenterHorizontally),
                shape = RoundedCornerShape(5.dp),
                shadowElevation = 10.dp
            ) {
                artworkImageFun(imageResource = imageResource, stringResource = stringResource)
            }
            Spacer(modifier = Modifier.height(20.dp))
            artworkContentFun(stringResource = stringResource)
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
    artworkButtonsFun(displayImage = displayImage,
        displayDescription=displayDescription,
        displayMinus1={
            displayImage -= 1
            displayDescription -=1

        },
        displayEq3={
            displayImage = 3
            displayDescription =3

        },
        displayPlus1={
            displayImage += 1
            displayDescription +=1

        },
        displayEq0={
            displayImage = 0
            displayDescription =0
        },
    )
}




@Composable
fun artSpaceDisplayPortrait() {
    var displayImage by remember {
        mutableStateOf(0)
    }
    val imageResource = when(displayImage) {
        0 -> R.drawable.architecture
        1 -> R.drawable.business
        2 -> R.drawable.crafts
        3 -> R.drawable.design

        else -> {R.drawable.architecture}
    }
    var displayDescription by remember {
        mutableStateOf(0)
    }
    val stringResource = when(displayDescription) {
        0 -> R.string.architecture_image
        1 -> R.string.business_image
        2 -> R.string.crafts_image
        3 -> R.string.design_image

        else -> {R.string.architecture_image}
    }
    Column(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxHeight()
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.65f)
                .fillMaxWidth(1f)
                .border(BorderStroke(3.dp, SolidColor(Color.White)), RectangleShape)
                .shadow(15.dp, RectangleShape)

                .align(alignment = Alignment.CenterHorizontally),
            shape = RoundedCornerShape(5.dp),
            shadowElevation = 10.dp
        ) {
            artworkImageFun(imageResource = imageResource,stringResource=stringResource)

        }
        Spacer(modifier = Modifier.height(60.dp))
        artworkContentFun(stringResource=stringResource)
        Spacer(modifier = Modifier.height(20.dp))
    }
    artworkButtonsFun(displayImage = displayImage,
        displayDescription=displayDescription,
        displayMinus1={
            displayImage -= 1
            displayDescription -=1

        },
        displayEq3={
            displayImage = 3
            displayDescription =3

        },
        displayPlus1={
            displayImage += 1
            displayDescription +=1

        },
        displayEq0={
            displayImage = 0
            displayDescription =0

        },
    )
}
@Composable
fun artworkImageFun(modifier: Modifier = Modifier,
                    @DrawableRes imageResource: Int,
                    @StringRes stringResource: Int) {
    Image(
        painter = painterResource(
            imageResource,
        ),
        contentDescription = stringResource(
            stringResource,
        ),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(35.dp)
    )
}

@Composable
fun artworkContentFun(modifier: Modifier = Modifier,
                      @StringRes stringResource: Int) {
   Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = Color(0xFFECEBF4)
    ) {

        Column(
            modifier = Modifier
                .padding(20.dp)
        ) {
            Text(
                text = stringResource(
                    stringResource,
                ),
                fontSize = 22.sp,
                fontStyle = FontStyle.Italic,

                )
            Row()
            {
                Text(
                    text = stringResource(
                        R.string.author,
                    ),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text =
                    "("
                        .plus(
                            stringResource(R.string.year)
                                .plus(")")
                        ),
                    fontSize = 14.sp,
                )
            }
        }
    }
}


@SuppressLint("ResourceType")
@Composable
fun artworkButtonsFun( modifier: Modifier = Modifier,
                       @DrawableRes  displayImage: Int,
                       @StringRes  displayDescription: Int,
                       displayMinus1: (Int) -> Unit,
                       displayEq3: (Int) ->Unit,
                       displayPlus1: (Int) ->Unit,
                       displayEq0: (Int) ->Unit,
) {
    Row(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Bottom

    ) {
        Button(
            onClick = {
                if (displayImage != 0 && displayDescription != 0) {
                    displayMinus1(displayImage)

                } else if (displayImage == 0 && displayDescription == 0) {
                    displayEq3(displayImage)
                }
            },
            colors=ButtonDefaults.buttonColors(containerColor= Color(73,93,146)),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.width(150.dp)
        ) {
            Text(
                text = "Previous",
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                if (displayImage <3 && displayDescription<3) {
                    displayPlus1(displayImage)

                } else if (displayImage == 3 && displayDescription == 3) {
                    displayEq0(displayImage)

                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(73, 93, 146)),

            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.width(150.dp)
        )
        {
            Text(
                text = "Next",
                color = Color.White
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Assignment2Theme {
        SpaceArtDisplay()
    }
}