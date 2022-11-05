package com.samar.busbooking.util.common

import com.google.common.truth.Truth
import com.samar.busbooking.util.type.ClassType
import org.json.JSONObject
import org.junit.Assert.*

import org.junit.Test

class ViewUtilsTest {

    @Test
    fun checkObjectOrArray() {
        val any = "{ \"details\": [{\"name\": \"Sheikh Samar\"}]}"
        val expected = ClassType.ARRAY
        val result = ViewUtils.checkObjectOrArray(any, "details")

        Truth.assertThat(result).isEqualTo(expected)
    }
}