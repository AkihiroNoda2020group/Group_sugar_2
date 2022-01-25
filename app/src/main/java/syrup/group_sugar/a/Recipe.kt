package syrup.group_sugar.a

data class Recipe(
    val ImageId: String,
    val title: String,
    val Item_name: List<String>,
    val Item_serving: List<String>,
    val Howto_text: List<String>
)