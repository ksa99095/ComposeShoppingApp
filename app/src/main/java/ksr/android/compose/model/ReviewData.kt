package ksr.android.compose.model

data class ReviewData(
    val rating: String,
    val id: String,
    val date: String,
    val imageList: List<Int>,
    val reviewContent: String
)
