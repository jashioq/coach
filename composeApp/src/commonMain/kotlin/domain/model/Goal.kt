package domain.model

data class Goal(
    val name: String,
    val title: String?,
    val reminders: List<String>?,
)