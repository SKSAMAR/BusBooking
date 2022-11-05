package com.samar.busbooking.util.type

sealed interface ClassType{
    object OBJECT: ClassType
    object ARRAY : ClassType
    object STRING: ClassType
    object SOMETHINELSE: ClassType
}