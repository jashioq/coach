package presentation.compose.component.blob

import androidx.compose.animation.core.Animatable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.channels.onSuccess
import kotlinx.coroutines.launch
import util.Logger

class BlobAnimationController(
    private val coroutineScope: CoroutineScope,
) {
    private var lastMode = BlobMode.NAVIGATION
    private val requestsChannel = Channel<BlobMode>(capacity = Channel.BUFFERED)

    val blobHeight = Animatable(64F)

    val topHorizontalSpacing = Animatable(0F)
    val bottomHorizontalSpacing = Animatable(0F)
    val verticalSpacing = Animatable(0F)

    val horizontalCornerRadius = Animatable(0F)
    val verticalCornerRadius = Animatable(0F)

    fun requestMode(newMode: BlobMode) {
        requestsChannel
            .trySend(newMode)
            .onFailure {
                Logger().e(
                    this::class.simpleName.toString(),
                    "Failed to send blob mode request: $newMode",
                    null,
                )
            }.onSuccess {
                Logger().d(
                    this::class.simpleName.toString(),
                    "Sent blob mode request: $newMode",
                )
            }
    }

    init {
        coroutineScope.launch {
            for (request in requestsChannel) {
                Logger().d(
                    this@BlobAnimationController::class.simpleName.toString(),
                    "Channel is: $requestsChannel"
                )
                Logger().d(
                    this@BlobAnimationController::class.simpleName.toString(),
                    "Setting blob mode to $request"
                )
                when (lastMode) {
                    BlobMode.NAVIGATION -> handleFromNavigation(request)
                    BlobMode.BUTTON_BAR -> handleFromButtonBar(request)
                    BlobMode.DIALOG -> handleFromDialog(request)
                    BlobMode.DIALOG_WITH_BUTTON -> handleFromDialogWithButton(request)
                    BlobMode.DIALOG_WITH_BUTTON_BAR -> handleFromDialogWithButtonBar(request)
                }

                lastMode = request
            }
        }
    }

    private suspend fun handleFromNavigation(newMode: BlobMode) {
        coroutineScope.launch {
            when (newMode) {
                BlobMode.NAVIGATION -> {
                    launch {
                        //Do nothing
                    }
                }

                BlobMode.BUTTON_BAR -> {
                    launch {
                        topHorizontalSpacing.animateTo(16f)
                    }
                    launch {
                        horizontalCornerRadius.animateTo(32f)
                    }
                }

                BlobMode.DIALOG -> {
                    launch {
                        blobHeight.animateTo(256f)
                    }
                }

                BlobMode.DIALOG_WITH_BUTTON -> {
                    launch {
                        blobHeight.animateTo(256f)
                    }.invokeOnCompletion {
                        launch {
                            verticalSpacing.animateTo(16f)
                        }
                        launch {
                            verticalCornerRadius.animateTo(32f)
                        }
                    }
                }

                BlobMode.DIALOG_WITH_BUTTON_BAR -> {
                    launch {
                        blobHeight.animateTo(256f)
                    }.invokeOnCompletion {
                        launch {
                            verticalSpacing.animateTo(16f)
                        }
                        launch {
                            verticalCornerRadius.animateTo(32f)
                        }
                        launch {
                            bottomHorizontalSpacing.animateTo(16f)
                        }
                        launch {
                            horizontalCornerRadius.animateTo(32f)
                        }
                    }
                }
            }
        }.join()
    }

    private suspend fun handleFromButtonBar(newMode: BlobMode) {
        coroutineScope.launch {
            when (newMode) {
                BlobMode.NAVIGATION -> {
                    launch {
                        topHorizontalSpacing.animateTo(0f)
                    }
                    launch {
                        horizontalCornerRadius.animateTo(0f)
                    }
                }

                BlobMode.BUTTON_BAR -> {
                    launch {
                        //Do nothing
                    }
                }

                BlobMode.DIALOG -> {
                    launch {
                        topHorizontalSpacing.animateTo(0f)
                    }
                    launch {
                        horizontalCornerRadius.animateTo(0f)
                    }.invokeOnCompletion {
                        launch {
                            blobHeight.animateTo(256f)
                        }
                    }
                }

                BlobMode.DIALOG_WITH_BUTTON -> {
                    launch {
                        topHorizontalSpacing.animateTo(0f)
                    }
                    launch {
                        horizontalCornerRadius.animateTo(0f)
                    }.invokeOnCompletion {
                        launch {
                            blobHeight.animateTo(256f)
                        }.invokeOnCompletion {
                            launch {
                                verticalSpacing.animateTo(16f)
                            }
                            launch {
                                verticalCornerRadius.animateTo(32f)
                            }
                        }
                    }

                }

                BlobMode.DIALOG_WITH_BUTTON_BAR -> {
                    launch {
                        topHorizontalSpacing.animateTo(0f)
                    }
                    launch {
                        horizontalCornerRadius.animateTo(0f)
                    }.invokeOnCompletion {
                        launch {
                            blobHeight.animateTo(256f)
                        }.invokeOnCompletion {
                            launch {
                                verticalSpacing.animateTo(16f)
                            }
                            launch {
                                verticalCornerRadius.animateTo(32f)
                            }
                            launch {
                                bottomHorizontalSpacing.animateTo(16f)
                            }
                            launch {
                                horizontalCornerRadius.animateTo(32f)
                            }
                        }
                    }
                }
            }
        }.join()
    }

    private suspend fun handleFromDialog(newMode: BlobMode) {
        coroutineScope.launch {
            when (newMode) {
                BlobMode.NAVIGATION -> {
                    launch {
                        blobHeight.animateTo(64f)
                    }
                }

                BlobMode.BUTTON_BAR -> {
                    launch {
                        blobHeight.animateTo(64f)
                    }.invokeOnCompletion {
                        launch {
                            topHorizontalSpacing.animateTo(16f)
                        }
                        launch {
                            horizontalCornerRadius.animateTo(32f)
                        }
                    }
                }

                BlobMode.DIALOG -> {
                    launch {
                        //Do nothing
                    }
                }

                BlobMode.DIALOG_WITH_BUTTON -> {
                    launch {
                        verticalSpacing.animateTo(16f)
                    }
                    launch {
                        verticalCornerRadius.animateTo(32f)
                    }
                }

                BlobMode.DIALOG_WITH_BUTTON_BAR -> {
                    launch {
                        verticalSpacing.animateTo(16f)
                    }
                    launch {
                        verticalCornerRadius.animateTo(32f)
                    }
                    launch {
                        bottomHorizontalSpacing.animateTo(16f)
                    }
                    launch {
                        horizontalCornerRadius.animateTo(32f)
                    }
                }
            }
        }.join()
    }

    private suspend fun handleFromDialogWithButton(newMode: BlobMode) {
        coroutineScope.launch {
            when (newMode) {
                BlobMode.NAVIGATION -> {
                    launch {
                        verticalSpacing.animateTo(0f)
                    }
                    launch {
                        verticalCornerRadius.animateTo(0f)
                    }.invokeOnCompletion {
                        launch {
                            blobHeight.animateTo(64f)
                        }
                    }
                }

                BlobMode.BUTTON_BAR -> {
                    launch {
                        verticalSpacing.animateTo(0f)
                    }
                    launch {
                        verticalCornerRadius.animateTo(0f)
                    }.invokeOnCompletion {
                        launch {
                            blobHeight.animateTo(64f)
                        }.invokeOnCompletion {
                            launch {
                                topHorizontalSpacing.animateTo(16f)
                            }
                            launch {
                                horizontalCornerRadius.animateTo(32f)
                            }
                        }
                    }
                }

                BlobMode.DIALOG -> {
                    launch {
                        verticalSpacing.animateTo(0f)
                    }
                    launch {
                        verticalCornerRadius.animateTo(0f)
                    }
                }

                BlobMode.DIALOG_WITH_BUTTON -> {
                    launch {
                        //Do nothing
                    }
                }

                BlobMode.DIALOG_WITH_BUTTON_BAR -> {
                    launch {
                        bottomHorizontalSpacing.animateTo(16f)
                    }
                    launch {
                        horizontalCornerRadius.animateTo(32f)
                    }
                }
            }
        }.join()
    }

    private suspend fun handleFromDialogWithButtonBar(newMode: BlobMode) {
        coroutineScope.launch {
            when (newMode) {
                BlobMode.NAVIGATION -> {
                    launch {
                        verticalSpacing.animateTo(0f)
                    }
                    launch {
                        verticalCornerRadius.animateTo(0f)
                    }
                    launch {
                        bottomHorizontalSpacing.animateTo(0f)
                    }
                    launch {
                        horizontalCornerRadius.animateTo(0f)
                    }.invokeOnCompletion {
                        launch {
                            blobHeight.animateTo(64f)
                        }
                    }
                }

                BlobMode.BUTTON_BAR -> {
                    launch {
                        verticalSpacing.animateTo(0f)
                    }
                    launch {
                        verticalCornerRadius.animateTo(0f)
                    }
                    launch {
                        bottomHorizontalSpacing.animateTo(0f)
                    }
                    launch {
                        horizontalCornerRadius.animateTo(0f)
                    }.invokeOnCompletion {
                        launch {
                            blobHeight.animateTo(64f)
                        }.invokeOnCompletion {
                            launch {
                                topHorizontalSpacing.animateTo(16f)
                            }
                            launch {
                                horizontalCornerRadius.animateTo(32f)
                            }
                        }
                    }
                }

                BlobMode.DIALOG -> {
                    launch {
                        verticalSpacing.animateTo(0f)
                    }
                    launch {
                        verticalCornerRadius.animateTo(0f)
                    }
                    launch {
                        bottomHorizontalSpacing.animateTo(0f)
                    }
                    launch {
                        horizontalCornerRadius.animateTo(0f)
                    }
                }

                BlobMode.DIALOG_WITH_BUTTON -> {
                    launch {
                        bottomHorizontalSpacing.animateTo(0f)
                    }
                    launch {
                        horizontalCornerRadius.animateTo(0f)
                    }
                }

                BlobMode.DIALOG_WITH_BUTTON_BAR -> {
                    launch {
                        //Do nothing
                    }
                }
            }
        }.join()
    }
}