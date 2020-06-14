package training20.tcmobile.mvvm.models

class Hairdresser(val id: Int? = null,
            var name: String? = null,
            var bioText: String? = null,
            var birthday: String? = null,
            identifier: String? = null,
            email: String? = null,
            createdAt: String? = null,
            updatedAt: String? = null
): User(identifier, email, createdAt, updatedAt)
{
}