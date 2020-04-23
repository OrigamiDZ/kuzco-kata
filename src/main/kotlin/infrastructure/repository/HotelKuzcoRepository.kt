package infrastructure.repository
import domain.JsonRoomStateResponse
import domain.RoomsEntity
import java.time.LocalDate

class HotelKuzcoRepository(
    private val dataSource: HotelKuzcoDataSource
) {
    companion object {
        private const val KING_TYPE = "King"
        private const val QUEEN_TYPE = "Queen"
        private const val SINGLE_TYPE = "Single"
    }
    fun getRoomsInformations() = transformToEntity(dataSource.getHotelData())

    private fun transformToEntity(jsonResponse: JsonRoomStateResponse) = RoomsEntity(
        chambers = jsonResponse.chambers.map { jsonRoom ->
            RoomsEntity.Room(
                id = jsonRoom.roomNumber,
                information = buildInformation(jsonRoom.roomInformation),
                reservations = jsonRoom.reservations.map { reservation ->
                    RoomsEntity.Room.Reservation(
                        transformToDate(reservation.checkInDate),
                        transformToDate(reservation.checkOutDate)
                    )
                }
            )
        }
    )
    private fun buildInformation(roomInformation: JsonRoomStateResponse.Room.Information) = RoomsEntity.Room.Information(
        roomInformation.floor,
        roomInformation.beds.map { bed ->
            getBedType(bed.bedType)
        },
        roomInformation.personCapacity,
        roomInformation.haveAC,
        roomInformation.haveWiFi,
        roomInformation.havePrivateBathroom,
        roomInformation.haveAccessibility
    )
    private fun getBedType(name: String) = when (name) {
        KING_TYPE -> RoomsEntity.Room.Bed.King
        QUEEN_TYPE -> RoomsEntity.Room.Bed.Queen
        SINGLE_TYPE -> RoomsEntity.Room.Bed.Single
        else -> RoomsEntity.Room.Bed.Unknown
    }
    private fun transformToDate(date: Long) = LocalDate.ofEpochDay(date)
}