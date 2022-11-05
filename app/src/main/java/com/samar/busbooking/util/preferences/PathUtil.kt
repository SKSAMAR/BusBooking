package com.samar.busbooking.util.preferences

sealed class PathUtil(val name: String): java.io.Serializable {
    object FROM: PathUtil("From")
    object TO: PathUtil("To")
}