package repository

import domain.JsonRoomStateResponse
import domain.RoomsEntity
import java.math.BigDecimal
import java.time.LocalDate

class HotelKuzcoRepository(
    private val dataSource: HotelKuzcoDataSource
) {

    fun getRoomsInformations() = transformToEntity(dataSource.getHotelData())

    private fun transformToEntity(jsonResponse: JsonRoomStateResponse) = RoomsEntity(
        chambers = jsonResponse.chambers.map {
            RoomsEntity.Room(
                id = it.roomNumber,
                information = buildInformation(it.roomInformation),
                reservations = it.reservations.map { reservation ->
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
        "King" -> RoomsEntity.Room.Bed.King
        "Queen" -> RoomsEntity.Room.Bed.Queen
        "Single" -> RoomsEntity.Room.Bed.Single
        else -> RoomsEntity.Room.Bed.Unknown
    }

    private fun transformToDate(date: BigDecimal) = LocalDate.MIN

}