package training20.tcmobile.auth

import io.realm.Realm
import training20.tcmobile.mvvm.models.Model

class AuthManagerRealm: AuthManager() {

    override fun save(model: Model) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction {
            realm.copyToRealm(model)
        }
    }

    override fun currentModel(): Model? {
        return Realm.getDefaultInstance().where(Model::class.java).findFirst()
    }
}