package domain

import java.time.LocalDate

data class RoomsEntity(
    val chambers: List<Room>
) {
    data class Room(
        val id: Int,
        val information: Information,
        val reservations: List<Reservation>
    ) {
        sealed class Bed {
            object King : Bed()
            object Queen: Bed()
            object Single: Bed()
            object Unknown: Bed()
        }

        data class Information(
            val floor: Int,
            val beds: List<Bed>,
            val personCapacity: Int,
            val haveAC: Boolean,
            val haveWiFi: Boolean,
            val havePrivateBathroom: Boolean,
            val haveAccessibility: Boolean
        )

        data class Reservation(
            val checkInDate: LocalDate,
            val checkOutDate: LocalDate
        )
    }
}