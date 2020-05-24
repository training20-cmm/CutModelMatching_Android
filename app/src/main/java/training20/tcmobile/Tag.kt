package training20.tcmobile

import android.graphics.Color

data class Tag(val name: String, val textColor: Int, val backgroundColor: Int) {
    constructor(name: String, textColor: String, backgroundColor: String): this(name, Color.parseColor(textColor), Color.parseColor(backgroundColor))
    constructor(name: String, textColor: Int, backgroundColor: String): this(name, textColor, Color.parseColor(backgroundColor))
    constructor(name: String, textColor: String, backgroundColor: Int): this(name, Color.parseColor(textColor), backgroundColor)
}