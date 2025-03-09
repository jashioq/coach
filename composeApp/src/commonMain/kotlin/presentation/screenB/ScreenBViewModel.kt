package presentation.screenB

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.useCase.EmitAllPostsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.jh.coach.data.local.database.PostDto

class ScreenBViewModel(
    private val emitAllPostsUseCase: EmitAllPostsUseCase,
) : ViewModel() {
    private val _posts = MutableStateFlow(listOf<PostDto>())
    val posts = _posts.asStateFlow()

    init {
        viewModelScope.launch {
            emitAllPostsUseCase.call().getOrNull()?.collect { posts ->
                _posts.value = posts
            }
        }
    }
}
