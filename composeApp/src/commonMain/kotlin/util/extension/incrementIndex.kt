package util.extension

fun Int.incrementIndex(maxIndex: Int): Int =
    if (this >= maxIndex) {
        0
    } else {
        this + 1
    }
