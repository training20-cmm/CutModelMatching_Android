package training20.tcmobile.mvvm.viewmodels

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import androidx.databinding.ObservableInt
import training20.tcmobile.ApplicationContext
import training20.tcmobile.mvvm.actions.HairdresserSalonRegistrationActions
import training20.tcmobile.mvvm.event.EventDispatcher
import training20.tcmobile.mvvm.models.Salon
import training20.tcmobile.mvvm.repositories.SalonRepositoryContract
import training20.tcmobile.net.http.HttpClient
import training20.tcmobile.net.http.HttpMethod
import training20.tcmobile.net.http.responses.ErrorResponse
import training20.tcmobile.net.http.responses.SalonResponse
import training20.tcmobile.util.FileUtils
import java.io.File
import java.io.IOException


//fun getPathFromUri(context: Context, uri: Uri): String? {
//    val isAfterKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
//    // DocumentProvider
//    //Log.e(TAG, "uri:" + uri.authority)
//    if (isAfterKitKat && DocumentsContract.isDocumentUri(context, uri)) {
//        if ("com.android.externalstorage.documents" ==
//            uri.authority
//        ) { // ExternalStorageProvider
//            val docId = DocumentsContract.getDocumentId(uri)
//            val split = docId.split(":").toTypedArray()
//            val type = split[0]
//            return if ("primary".equals(type, ignoreCase = true)) {
//                Environment.getExternalStorageDirectory().toString() + "/" + split[1]
//            } else {
//                "/stroage/" + type + "/" + split[1]
//            }
//        } else if ("com.android.providers.downloads.documents" ==
//            uri.authority
//        ) { // DownloadsProvider
//            val id = DocumentsContract.getDocumentId(uri)
//            val contentUri = ContentUris.withAppendedId(
//                Uri.parse("content://downloads/public_downloads"),
//                java.lang.Long.valueOf(id)
//            )
//            return getDataColumn(context, contentUri, null, null)
//        } else if ("com.android.providers.media.documents" ==
//            uri.authority
//        ) { // MediaProvider
//            val docId = DocumentsContract.getDocumentId(uri)
//            val split = docId.split(":").toTypedArray()
//            val type = split[0]
//            var contentUri: Uri? = null
//            contentUri = MediaStore.Files.getContentUri("external")
//            val selection = "_id=?"
//            val selectionArgs = arrayOf(
//                split[1]
//            )
//            return getDataColumn(context, contentUri, selection, selectionArgs)
//        }
//    } else if ("content".equals(uri.scheme, ignoreCase = true)) { //MediaStore
//        return getDataColumn(context, uri, null, null)
//    } else if ("file".equals(uri.scheme, ignoreCase = true)) { // File
//        return uri.path
//    }
//    return null
//}
//
//fun getDataColumn(
//    context: Context, uri: Uri, selection: String?,
//    selectionArgs: Array<String>?
//): String? {
//    var cursor: Cursor? = null
//    val projection = arrayOf(
//        MediaStore.Files.FileColumns.DATA
//    )
//    try {
//        cursor = context.getContentResolver().query(
//            uri, projection, selection, selectionArgs, null
//        )
//        if (cursor != null && cursor.moveToFirst()) {
//            val cindex = cursor.getColumnIndexOrThrow(projection[0])
//            return cursor.getString(cindex)
//        }
//    } finally {
//        cursor?.close()
//    }
//    return null
//}

class HairdresserSalonRegistrationViewModel(
    eventDispatcher: EventDispatcher<HairdresserSalonRegistrationActions>,
    private val salonRepository: SalonRepositoryContract
): BackableViewModel<HairdresserSalonRegistrationActions>(eventDispatcher) {
    var imageResource = ObservableInt()
    var salonname = "title01"
    var salonmemo = "testmemo01"
    var postalcode = "1230001"
    var prefecturePosition = 0
    var address = "sos01"
    var residence = "101"
    var seatsnumber = "5"
    //var gender: ObservableInt? = null
    var cash = false
    var credit = false
    var starttime = "12"
    var endtime = "13"
    var starttime2 = "11"
    var endtime2 = "11"
    var weekPosition = 0
    var prefectures = arrayOf<String>()
    var week = arrayOf<String>()
    var uri = ""

    val paymentMethods: MutableList<String> = mutableListOf()

    // TODO: サーバから支払方法を取得
    fun registerHairdresser() {
        if (cash == true) {
            paymentMethods.add("1")
        } else if (credit == true) {
            paymentMethods.add("2")
        }

        val regularHoliday = week[weekPosition][0].toString()
            salonRepository.store(
                salonname,
                postalcode,
                prefectures[prefecturePosition],
                address,
                residence,
                salonmemo,
                seatsnumber,
                paymentMethods,
                uri,
                starttime,
                endtime,
                starttime2,
                endtime2,
                regularHoliday,
                onSuccess = this::onSalonStoreSuccess
            )
    }

    private fun onSalonStoreSuccess(salon: Salon) {
        eventDispatcher.dispatchEvent { showSalon() }
    }


}