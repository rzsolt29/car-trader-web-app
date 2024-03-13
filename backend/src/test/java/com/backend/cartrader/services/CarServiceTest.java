package com.backend.cartrader.services;

import com.backend.cartrader.exception.AuthenticationException;
import com.backend.cartrader.exception.NonExistingCarException;
import com.backend.cartrader.model.BodyType;
import com.backend.cartrader.model.Car;
import com.backend.cartrader.model.Drivetrain;
import com.backend.cartrader.model.User;
import com.backend.cartrader.payload.request.CreateCarRequest;
import com.backend.cartrader.repository.CarRepository;
import com.backend.cartrader.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock
    private CarRepository carRepository;
    @Mock
    private UserRepository userRepository;
    private CarService underTest;

    @BeforeEach
    void setUp() {
        underTest = new CarService(carRepository, userRepository);
    }

    @Test
    void getLoggedInUserThrowsException() {
        //given
        Authentication authentication = new TestingAuthenticationToken("fr0d0@gmail.com",null);
        authentication.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //when
        //then
        assertThatThrownBy(() -> underTest.getLoggedInUser())
                .isInstanceOf(AuthenticationException.class)
                .hasMessageContaining("Internal error");

    }

    @Test
    void canCreateCar() {
        //given
        CreateCarRequest createCarRequest = CreateCarRequest.builder()
                .make("BMW")
                .model("3")
                .price(1000)
                .bodyType(BodyType.SALOON)
                .gearbox("Automatic")
                .fuelType("Diesel")
                .productionYear(2003)
                .colour("Black")
                .doors(5)
                .engineSize(1995)
                .enginePower(110)
                .drivetrain(Drivetrain.DRIVETRAIN_RWD)
                .build();

        var time = Instant.now();
        Car expected = Car.builder()
                .publishedOn(time)
                .make(createCarRequest.getMake())
                .model(createCarRequest.getModel())
                .price(createCarRequest.getPrice())
                .bodyType(createCarRequest.getBodyType())
                .gearbox(createCarRequest.getGearbox())
                .fuelType(createCarRequest.getFuelType())
                .productionYear(createCarRequest.getProductionYear())
                .colour(createCarRequest.getColour())
                .doors(createCarRequest.getDoors())
                .engineSize(createCarRequest.getEngineSize())
                .enginePower(createCarRequest.getEnginePower())
                .drivetrain(createCarRequest.getDrivetrain())
                .owner(User.builder()
                        .email("fr0d0@gmail.com")
                        .password("Fr0d0's_secret")
                        .createdOn(time)
                        .build())
                .build();

        Authentication authentication = new TestingAuthenticationToken("fr0d0@gmail.com",null);
        authentication.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        given(userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()))
                .willReturn(Optional
                        .of(
                            User.builder()
                                .email("fr0d0@gmail.com")
                                .password("Fr0d0's_secret")
                                .createdOn(time)
                                .build()));
        //when
        underTest.createCar(createCarRequest);

        // then
        ArgumentCaptor<Car> createCarRequestArgumentCaptor = ArgumentCaptor.forClass(Car.class);

        verify(carRepository).save(createCarRequestArgumentCaptor.capture());

        Car capturedCar = createCarRequestArgumentCaptor.getValue();
        capturedCar.setPublishedOn(time);

        assertThat(capturedCar).isEqualTo(expected);
    }

    @Test
    @Disabled
    void getMyCars() {
    }

    @Test
    void canGetCarById() {
        //given
        var time = Instant.now();
        given(carRepository.findById(1)).willReturn(Optional.of(Car.builder()
                .id(1)
                .publishedOn(time)
                .make("BMW")
                .model("3")
                .price(1000)
                .bodyType(BodyType.SALOON)
                .gearbox("Automatic")
                .fuelType("Diesel")
                .productionYear(2003)
                .colour("Black")
                .doors(5)
                .engineSize(1995)
                .enginePower(110)
                .drivetrain(Drivetrain.DRIVETRAIN_RWD)
                .owner(User.builder()
                        .email("fr0d0@gmail.com")
                        .password("Fr0d0's_secret")
                        .createdOn(time)
                        .build())
                .build()));

        //when
        //then
        Car expected = Car.builder()
                .id(1)
                .publishedOn(time)
                .make("BMW")
                .model("3")
                .price(1000)
                .bodyType(BodyType.SALOON)
                .gearbox("Automatic")
                .fuelType("Diesel")
                .productionYear(2003)
                .colour("Black")
                .doors(5)
                .engineSize(1995)
                .enginePower(110)
                .drivetrain(Drivetrain.DRIVETRAIN_RWD)
                .owner(User.builder()
                        .email("fr0d0@gmail.com")
                        .password(null)
                        .createdOn(time)
                        .build())
                .build();

        Car actual = underTest.getCar(1);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getCarByInvalidIdThrowsException() {
        //given
        given(carRepository.findById(1)).willReturn(Optional.empty());
        //when
        //then
        assertThatThrownBy(() -> underTest.getCar(1))
                .isInstanceOf(NonExistingCarException.class)
                .hasMessageContaining("Car not found with given id");
    }

    @Test
    @Disabled
    void getCarsByParameters() {
    }

    @Test
    @Disabled
    void deleteCar() {
    }
}