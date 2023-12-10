package com.haenari.haenari

@Deprecated("Need to refactor later...")
object LegacyUtil {

    // todo fix throwable...
    // 대응할 수 있는 대응점이 없음(지금은)
    fun Int.handleAmPm(onAM: (Int) -> Unit, onPM : (Int) -> Unit) {
        if(isAM(this)) {
            onAM(this)
        } else {
            onPM(this)
        }
    }

    fun isAM(hour: Int): Boolean {
        return checkHour(hour) < 12
    }

    fun isPM(hour: Int): Boolean {
        return checkHour(hour) >= 12
    }

    private fun checkHour(hour: Int): Int {
        if (hour < 0) throw IllegalArgumentException("Hour cannot be negative.")
        else if (hour >= 24) throw IllegalArgumentException("Hour cannot be bigger than 24.")
        else return hour
    }
}