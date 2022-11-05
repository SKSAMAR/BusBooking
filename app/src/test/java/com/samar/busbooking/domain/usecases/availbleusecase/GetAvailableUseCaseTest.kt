package com.samar.busbooking.domain.usecases.availbleusecase

import com.google.common.truth.Truth
import com.samar.busbooking.domain.model.timing.Time
import com.samar.busbooking.util.common.ViewUtils.timeFromMinutesToActual
import org.junit.Test

class GetAvailableUseCaseTest {

    @Test
    fun timeFromMinutesToActualFromBoarding() {
        val timeInMinutes = "1095"
        val expected = Time(hours = 18, minutes = 15, journeyOfTheDay = 0, twentyFourHoursTime = "18 hrs 15 min", twelveHoursTime = "6:15 PM")
        val result = timeInMinutes.timeFromMinutesToActual()
        Truth.assertThat(result).isEqualTo(expected)
    }

    @Test
    fun timeFromMinutesToActualToDestination() {
        val timeInMinutes = "1600"
        val expected = Time(hours = 26, minutes = 40, journeyOfTheDay = 1, twentyFourHoursTime = "26 hrs 40 min", twelveHoursTime = "2:40 AM")
        val result = timeInMinutes.timeFromMinutesToActual()
        Truth.assertThat(result).isEqualTo(expected)
    }
}