package training20.tcmobile.net.http.responses

import training20.tcmobile.mvvm.models.SalonPaymentMethod

class SalonPaymentMethodResponse {

    var id: Int? = null
    var name: String? = null
    var createdAt: String? = null
    var updatedAt: String? = null

    fun model(): SalonPaymentMethod {
        return SalonPaymentMethod(id, name, createdAt, updatedAt)
    }
}