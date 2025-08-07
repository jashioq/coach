package presentation.compose.component.blob

import androidx.compose.animation.core.Animatable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import util.Logger

class BlobAnimationController(
    private val coroutineScope: CoroutineScope,
) {
    private var lastMode = BlobMode.NAVIGATION
    val blobHeight = Animatable(64F)

    val topHorizontalSpacing = Animatable(0F)
    val bottomHorizontalSpacing = Animatable(0F)
    val verticalSpacing = Animatable(0F)

    val horizontalCornerRadius = Animatable(0F)
    val verticalCornerRadius = Animatable(0F)

    fun requestMode(newMode: BlobMode) {
        coroutineScope.coroutineContext.cancelChildren()
        if (lastMode == newMode) {
            Logger().d(this::class.simpleName.toString(), "Blob mode is already $newMode")
            return
        }
        Logger().d(this::class.simpleName.toString(), "Setting blob mode to $newMode")
        when (lastMode) {
            BlobMode.NAVIGATION -> handleFromNavigation(newMode)
            BlobMode.BUTTON_BAR -> handleFromButtonBar(newMode)
            BlobMode.DIALOG -> handleFromDialog(newMode)
            BlobMode.DIALOG_WITH_BUTTON -> handleFromDialogWithButton(newMode)
            BlobMode.DIALOG_WITH_BUTTON_BAR -> handleFromDialogWithButtonBar(newMode)
        }

        lastMode = newMode
    }

    private fun handleFromNavigation(newMode: BlobMode) {
        when (newMode) {
            BlobMode.NAVIGATION -> {
                // Do nothing
            }

            BlobMode.BUTTON_BAR -> {
                coroutineScope.launch {
                    topHorizontalSpacing.animateTo(16f)
                }
                coroutineScope.launch {
                    horizontalCornerRadius.animateTo(32f)
                }
            }

            BlobMode.DIALOG -> {
                coroutineScope.launch {
                    blobHeight.animateTo(256f)
                }
            }

            BlobMode.DIALOG_WITH_BUTTON -> {
                coroutineScope.launch {
                    blobHeight.animateTo(256f)
                }.invokeOnCompletion {
                    coroutineScope.launch {
                        verticalSpacing.animateTo(16f)
                    }
                    coroutineScope.launch {
                        verticalCornerRadius.animateTo(32f)
                    }
                }
            }

            BlobMode.DIALOG_WITH_BUTTON_BAR -> {
                coroutineScope.launch {
                    blobHeight.animateTo(256f)
                }.invokeOnCompletion {
                    coroutineScope.launch {
                        verticalSpacing.animateTo(16f)
                    }
                    coroutineScope.launch {
                        verticalCornerRadius.animateTo(32f)
                    }
                    coroutineScope.launch {
                        bottomHorizontalSpacing.animateTo(16f)
                    }
                    coroutineScope.launch {
                        horizontalCornerRadius.animateTo(32f)
                    }
                }
            }
        }
    }

    private fun handleFromButtonBar(newMode: BlobMode) {
        when (newMode) {
            BlobMode.NAVIGATION -> {
                coroutineScope.launch {
                    topHorizontalSpacing.animateTo(0f)
                }
                coroutineScope.launch {
                    horizontalCornerRadius.animateTo(0f)
                }
            }

            BlobMode.BUTTON_BAR -> {
                // Do nothing
            }

            BlobMode.DIALOG -> {
                coroutineScope.launch {
                    topHorizontalSpacing.animateTo(0f)
                }
                coroutineScope.launch {
                    horizontalCornerRadius.animateTo(0f)
                }.invokeOnCompletion {
                    coroutineScope.launch {
                        blobHeight.animateTo(256f)
                    }
                }
            }

            BlobMode.DIALOG_WITH_BUTTON -> {
                coroutineScope.launch {
                    topHorizontalSpacing.animateTo(0f)
                }
                coroutineScope.launch {
                    horizontalCornerRadius.animateTo(0f)
                }.invokeOnCompletion {
                    coroutineScope.launch {
                        blobHeight.animateTo(256f)
                    }.invokeOnCompletion {
                        coroutineScope.launch {
                            verticalSpacing.animateTo(16f)
                        }
                        coroutineScope.launch {
                            verticalCornerRadius.animateTo(32f)
                        }
                    }
                }

            }

            BlobMode.DIALOG_WITH_BUTTON_BAR -> {
                coroutineScope.launch {
                    topHorizontalSpacing.animateTo(0f)
                }
                coroutineScope.launch {
                    horizontalCornerRadius.animateTo(0f)
                }.invokeOnCompletion {
                    coroutineScope.launch {
                        blobHeight.animateTo(256f)
                    }.invokeOnCompletion {
                        coroutineScope.launch {
                            verticalSpacing.animateTo(16f)
                        }
                        coroutineScope.launch {
                            verticalCornerRadius.animateTo(32f)
                        }
                        coroutineScope.launch {
                            bottomHorizontalSpacing.animateTo(16f)
                        }
                        coroutineScope.launch {
                            horizontalCornerRadius.animateTo(32f)
                        }
                    }
                }
            }
        }
    }

    private fun handleFromDialog(newMode: BlobMode) {
        when (newMode) {
            BlobMode.NAVIGATION -> {
                coroutineScope.launch {
                    blobHeight.animateTo(64f)
                }
            }

            BlobMode.BUTTON_BAR -> {
                coroutineScope.launch {
                    blobHeight.animateTo(64f)
                }.invokeOnCompletion {
                    coroutineScope.launch {
                        topHorizontalSpacing.animateTo(16f)
                    }
                    coroutineScope.launch {
                        horizontalCornerRadius.animateTo(32f)
                    }
                }
            }

            BlobMode.DIALOG -> {
                //Do nothing
            }

            BlobMode.DIALOG_WITH_BUTTON -> {
                coroutineScope.launch {
                    verticalSpacing.animateTo(16f)
                }
                coroutineScope.launch {
                    verticalCornerRadius.animateTo(32f)
                }
            }

            BlobMode.DIALOG_WITH_BUTTON_BAR -> {
                coroutineScope.launch {
                    verticalSpacing.animateTo(16f)
                }
                coroutineScope.launch {
                    verticalCornerRadius.animateTo(32f)
                }
                coroutineScope.launch {
                    bottomHorizontalSpacing.animateTo(16f)
                }
                coroutineScope.launch {
                    horizontalCornerRadius.animateTo(32f)
                }
            }
        }
    }

    private fun handleFromDialogWithButton(newMode: BlobMode) {
        when (newMode) {
            BlobMode.NAVIGATION -> {
                coroutineScope.launch {
                    verticalSpacing.animateTo(0f)
                }
                coroutineScope.launch {
                    verticalCornerRadius.animateTo(0f)
                }.invokeOnCompletion {
                    coroutineScope.launch {
                        blobHeight.animateTo(64f)
                    }
                }
            }

            BlobMode.BUTTON_BAR -> {
                coroutineScope.launch {
                    verticalSpacing.animateTo(0f)
                }
                coroutineScope.launch {
                    verticalCornerRadius.animateTo(0f)
                }.invokeOnCompletion {
                    coroutineScope.launch {
                        blobHeight.animateTo(64f)
                    }.invokeOnCompletion {
                        coroutineScope.launch {
                            topHorizontalSpacing.animateTo(16f)
                        }
                        coroutineScope.launch {
                            horizontalCornerRadius.animateTo(32f)
                        }
                    }
                }
            }

            BlobMode.DIALOG -> {
                coroutineScope.launch {
                    verticalSpacing.animateTo(0f)
                }
                coroutineScope.launch {
                    verticalCornerRadius.animateTo(0f)
                }
            }

            BlobMode.DIALOG_WITH_BUTTON -> {
                // Do nothing
            }

            BlobMode.DIALOG_WITH_BUTTON_BAR -> {
                coroutineScope.launch {
                    bottomHorizontalSpacing.animateTo(16f)
                }
                coroutineScope.launch {
                    horizontalCornerRadius.animateTo(32f)
                }
            }
        }
    }

    private fun handleFromDialogWithButtonBar(newMode: BlobMode) {
        when (newMode) {
            BlobMode.NAVIGATION -> {
                coroutineScope.launch {
                    verticalSpacing.animateTo(0f)
                }
                coroutineScope.launch {
                    verticalCornerRadius.animateTo(0f)
                }
                coroutineScope.launch {
                    bottomHorizontalSpacing.animateTo(0f)
                }
                coroutineScope.launch {
                    horizontalCornerRadius.animateTo(0f)
                }.invokeOnCompletion {
                    coroutineScope.launch {
                        blobHeight.animateTo(64f)
                    }
                }
            }

            BlobMode.BUTTON_BAR -> {
                coroutineScope.launch {
                    verticalSpacing.animateTo(0f)
                }
                coroutineScope.launch {
                    verticalCornerRadius.animateTo(0f)
                }
                coroutineScope.launch {
                    bottomHorizontalSpacing.animateTo(0f)
                }
                coroutineScope.launch {
                    horizontalCornerRadius.animateTo(0f)
                }.invokeOnCompletion {
                    coroutineScope.launch {
                        blobHeight.animateTo(64f)
                    }.invokeOnCompletion {
                        coroutineScope.launch {
                            topHorizontalSpacing.animateTo(16f)
                        }
                        coroutineScope.launch {
                            horizontalCornerRadius.animateTo(32f)
                        }
                    }
                }
            }

            BlobMode.DIALOG -> {
                coroutineScope.launch {
                    verticalSpacing.animateTo(0f)
                }
                coroutineScope.launch {
                    verticalCornerRadius.animateTo(0f)
                }
                coroutineScope.launch {
                    bottomHorizontalSpacing.animateTo(0f)
                }
                coroutineScope.launch {
                    horizontalCornerRadius.animateTo(0f)
                }
            }

            BlobMode.DIALOG_WITH_BUTTON -> {
                coroutineScope.launch {
                    bottomHorizontalSpacing.animateTo(0f)
                }
                coroutineScope.launch {
                    horizontalCornerRadius.animateTo(0f)
                }
            }

            BlobMode.DIALOG_WITH_BUTTON_BAR -> {
                // Do nothing
            }
        }
    }
}