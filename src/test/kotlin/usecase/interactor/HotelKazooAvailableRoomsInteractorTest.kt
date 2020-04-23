package usecase.interactor

import domain.RoomsEntity
import infrastructure.HotelKuzcoPresenter
import infrastructure.repository.HotelKuzcoDataSource
import infrastructure.repository.HotelKuzcoRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.time.LocalDate

@RunWith(MockitoJUnitRunner::class)
internal class HotelKazooAvailableRoomsInteractorTest {

    private val dataSource = HotelKuzcoDataSource()

    private lateinit var interactor: HotelKazooAvailableRoomsInteractor

    @Before
    fun setUp() {
        interactor = HotelKazooAvailableRoomsInteractor(
            HotelKuzcoRepository(dataSource),
            HotelKuzcoPresenter()
        )
    }

    @Test
    fun `getAvailableRooms - no rooms avalaible`() {
        // When
        val response = interactor.getAvailableRooms(
            checkIn = LocalDate.of(1970, 1, 1),
            checkOut = LocalDate.of(1970, 1, 3),
            numberGuest = 4
        )

        // Then
        assertThat(response).isEqualTo(emptyList<RoomsEntity.Room>())
    }

    @Test
    fun `getAvailableRooms - one room avalaible`() {
        // When
        val response = interactor.getAvailableRooms(
            checkIn = LocalDate.of(1970, 1, 1),
            checkOut = LocalDate.of(1970, 1, 3),
            numberGuest = 3
        )

        // Then
        assertThat(response).isEqualTo(
            listOf(
                RoomsEntity.Room(
                    id = 303,
                    reservations = emptyList(),
                    information = RoomsEntity.Room.Information(
                        floor = 3,
                        beds = listOf(
                            RoomsEntity.Room.Bed.Single,
                            RoomsEntity.Room.Bed.Single,
                            RoomsEntity.Room.Bed.Single
                        ),
                        personCapacity = 3,
                        haveAC = true,
                        haveWiFi = false,
                        havePrivateBathroom = true,
                        haveAccessibility = false
                    )
                )
            )
        )
    }

    @Test
    fun `getAvailableRooms - two rooms available`() {
        // When
        val response = interactor.getAvailableRooms(
            checkIn = LocalDate.of(1970, 1, 1),
            checkOut = LocalDate.of(1970, 1, 3),
            numberGuest = 1
        )

        // Then
        assertThat(response).isEqualTo(
            listOf(
                RoomsEntity.Room(
                    id = 202,
                    reservations = emptyList(),
                    information = RoomsEntity.Room.Information(
                        floor = 2,
                        beds = listOf(
                            RoomsEntity.Room.Bed.Queen
                        ),
                        personCapacity = 1,
                        haveAC = false,
                        haveWiFi = true,
                        havePrivateBathroom = true,
                        haveAccessibility = false
                    )
                ),
                RoomsEntity.Room(
                    id = 303,
                    reservations = emptyList(),
                    information = RoomsEntity.Room.Information(
                        floor = 3,
                        beds = listOf(
                            RoomsEntity.Room.Bed.Single,
                            RoomsEntity.Room.Bed.Single,
                            RoomsEntity.Room.Bed.Single
                        ),
                        personCapacity = 3,
                        haveAC = true,
                        haveWiFi = false,
                        havePrivateBathroom = true,
                        haveAccessibility = false
                    )
                )
            )
        )
    }

    @Test
    fun `getAvailableRooms - all rooms available`() {
        // When
        val response = interactor.getAvailableRooms(
            checkIn = LocalDate.of(1970, 2, 1),
            checkOut = LocalDate.of(1970, 2, 3),
            numberGuest = 1
        )

        // Then
        assertThat(response).isEqualTo(
            listOf(
                RoomsEntity.Room(
                    id = 101,
                    reservations = listOf(
                        RoomsEntity.Room.Reservation(
                            checkInDate = LocalDate.of(1970, 1, 1),
                            checkOutDate = LocalDate.of(1970, 1, 7)
                        )
                    ),
                    information = RoomsEntity.Room.Information(
                        floor = 1,
                        beds = listOf(
                            RoomsEntity.Room.Bed.King
                        ),
                        personCapacity = 2,
                        haveAC = true,
                        haveWiFi = true,
                        havePrivateBathroom = true,
                        haveAccessibility = true
                    )
                ),
                RoomsEntity.Room(
                    id = 202,
                    reservations = emptyList(),
                    information = RoomsEntity.Room.Information(
                        floor = 2,
                        beds = listOf(
                            RoomsEntity.Room.Bed.Queen
                        ),
                        personCapacity = 1,
                        haveAC = false,
                        haveWiFi = true,
                        havePrivateBathroom = true,
                        haveAccessibility = false
                    )
                ),
                RoomsEntity.Room(
                    id = 303,
                    reservations = emptyList(),
                    information = RoomsEntity.Room.Information(
                        floor = 3,
                        beds = listOf(
                            RoomsEntity.Room.Bed.Single,
                            RoomsEntity.Room.Bed.Single,
                            RoomsEntity.Room.Bed.Single
                        ),
                        personCapacity = 3,
                        haveAC = true,
                        haveWiFi = false,
                        havePrivateBathroom = true,
                        haveAccessibility = false
                    )
                )
            )
        )

    }
}