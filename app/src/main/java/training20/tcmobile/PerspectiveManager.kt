package training20.tcmobile

object PerspectiveManager {

    private var perspective: Perspective? = null

    fun current(): Perspective? {
        return perspective
    }

    fun setPerspective(perspective: Perspective?) {
        this.perspective = perspective
    }
}