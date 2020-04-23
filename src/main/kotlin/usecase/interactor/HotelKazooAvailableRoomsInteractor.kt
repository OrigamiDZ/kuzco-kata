package usecase.interactor

import domain.RoomsEntity
import infrastructure.HotelKuzcoPresenter
import infrastructure.repository.HotelKuzcoRepository
import java.time.LocalDate

class HotelKazooAvailableRoomsInteractor(
    private val repository: HotelKuzcoRepository,
    private val presenter: HotelKuzcoPresenter
) {
    fun getAvailableRooms(checkIn: LocalDate, checkOut: LocalDate, numberGuest: Int): List<RoomsEntity.Room> {
        val rooms = repository.getRoomsInformations()
        return findAvailableRooms(rooms, checkIn, checkOut, numberGuest)
    }

    private fun findAvailableRooms(
        rooms: RoomsEntity,
        checkIn: LocalDate,
        checkOut: LocalDate,
        numberGuest: Int
    ): List<RoomsEntity.Room> {
        val roomsAvailable = mutableListOf<RoomsEntity.Room>()
        rooms.chambers.forEach { chamber ->
            if (chamber.information.personCapacity > numberGuest) {
                verifyDates(chamber, checkIn, checkOut, roomsAvailable)
            }
        }
        return roomsAvailable
    }

    private fun verifyDates(
        chamber: RoomsEntity.Room,
        checkIn: LocalDate,
        checkOut: LocalDate,
        roomsAvailable: MutableList<RoomsEntity.Room>
    ) {
        var isThereADate = true
        chamber.reservations.forEach { reservation ->
            if (isRoomTaken(checkIn, checkOut, reservation.checkInDate, reservation.checkOutDate)) {
                isThereADate = false
            }
        }
        if (isThereADate) {
            roomsAvailable.add(chamber)
        }
    }

    private fun isRoomTaken(
        checkIn: LocalDate,
        checkOut: LocalDate,
        guestCheckIn: LocalDate,
        guestCheckOut: LocalDate
    ) = isGuestCheckBetweenReservationDates(guestCheckIn, checkIn, checkOut) || isGuestCheckBetweenReservationDates(guestCheckOut, checkIn, checkOut)

    private fun isGuestCheckBetweenReservationDates(
        reservationCheckIn: LocalDate,
        checkIn: LocalDate,
        checkOut: LocalDate
    ) = reservationCheckIn.isAfter(checkIn) && reservationCheckIn.isBefore(checkOut)


}