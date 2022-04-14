package com.example.mov.sign.signin

import android.database.sqlite.SQLiteDatabase
import android.os.Build
import android.os.Parcelable
import androidx.annotation.RequiresApi
import kotlinx.android.parcel.Parcelize
import java.util.function.Consumer


@Parcelize
class User (
    var email: String ?="",
    var nama: String ?="",
    var password: String ?="",
    var url: String ?="",
    var username: String ?="",
    var saldo: String ?="",
) : Parcelable
