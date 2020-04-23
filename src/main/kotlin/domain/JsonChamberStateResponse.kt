package domain

data class JsonRoomStateResponse(
    val chambers: List<Room>
) {
    data class Room(
        val roomNumber: Int,
        val roomInformation: Information,
        val reservations: List<Reservation>
    ) {
        data class Bed(
            val bedType: String
        )

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
            val checkInDate: Long,
            val checkOutDate: Long,
            val reservationName: String
        )
    }
}