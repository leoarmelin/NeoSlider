# Neo-slider
[![](https://jitpack.io/v/leoarmelin/neo-slider.svg)](https://jitpack.io/#leoarmelin/neo-slider)

A Jetpack Compose library to create extremely customizable sliders.

[](/gifs/example.gif)

## Usage
You can see the [examples here](https://github.com/leoarmelin/neo-slider/tree/master/app/src/main/java/com/leoarmelin/customslider/examples).

1. Customizing the modifiers
~~~ kotlin
@Composable
fun MyCoolSlider() {
    var sliderState by remember { mutableStateOf(0f) }

    CustomSlider(
        value = sliderState,
        onSlide = { newValue, percentage ->
            sliderState = newValue
        },
        containerModifier = Modifier
            .padding(horizontal = 32.dp)
            .fillMaxWidth()
            .height(30.dp),
        trackModifier = Modifier
            .fillMaxWidth()
            .height(8.dp)
            .background(Color(0xFFCCE2CB), RoundedCornerShape(4.dp)),
        thumbModifier = Modifier
            .size(32.dp)
            .background(Color(0xFF97C1A9), CircleShape),
    )
}
~~~

2. Watching the width
~~~ kotlin
@Composable
fun MyFantasticSlider() {
    val density = LocalDensity.current.density
    var sliderState by remember { mutableStateOf(0f) }
    var sliderPercentage by remember { mutableStateOf(0f) }
    var containerWidth by remember { mutableStateOf(0f) }
    val backgroundAnimation by animateColorAsState(when (sliderPercentage) {
        in 0f..0.33f -> Color(0xFFFF968A)
        in 0.33f..0.66f -> Color(0xFFFFFFB5)
        in 0.66f..1f -> Color(0xFFBFFCC6)
        else -> Color.White
    })

    val onUpdateContainerWidth: (newWidth: Float) -> Unit = { containerWidth = it }

    CustomSlider(
        value = sliderState,
        onSlide = { newValue, percentage ->
            sliderState = newValue
            sliderPercentage = percentage
        },
        containerModifier = Modifier
            .padding(horizontal = 32.dp)
            .fillMaxWidth()
            .height(30.dp),
        trackModifier = Modifier
            .width((containerWidth * sliderPercentage / density).dp)
            .height(16.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundAnimation, RoundedCornerShape(8.dp)),
        thumbModifier = Modifier
            .size(32.dp)
            .background(backgroundAnimation, CircleShape),
        onUpdateContainerWidth = onUpdateContainerWidth
    )
}
~~~

## Download

1. Add it in your root build.gradle at the end of repositories:
~~~ kotlin
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
~~~

2. Add the dependency, where `TAG` is the version of: [![](https://jitpack.io/v/leoarmelin/neo-slider.svg)](https://jitpack.io/#leoarmelin/neo-slider)
~~~ kotlin
dependencies {
    implementation 'com.github.leoarmelin:neo-slider:TAG'
    
    // Example: implementation 'com.github.leoarmelin:neo-slider:1.0.0'
}
~~~
