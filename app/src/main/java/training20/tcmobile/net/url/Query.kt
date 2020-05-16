package training20.tcmobile.net.url

class Query {

    private var keys = arrayListOf("")
    private var values = arrayListOf("")

    fun append(key: String, value: String) {
        keys.add(key)
        values.add(value)
    }

    fun make(): String {
        return keys.mapIndexed { index, key ->
            key + "=" + values[index]
        }.joinToString("&")
    }
}