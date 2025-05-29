package util.extension

/**
 * Extension function used for incrementing an index.
 * Will return 0 if the index is greater than maximum index.
 * @param maxIndex the maximum index.
 */
fun Int.incrementIndex(maxIndex: Int): Int =
    if (this >= maxIndex) {
        0
    } else {
        this + 1
    }
