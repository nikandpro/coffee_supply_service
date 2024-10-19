package github.nikandpro.coffeesupplyservice.service;

import github.nikandpro.coffeesupplyservice.dto.GrainDto;
import github.nikandpro.coffeesupplyservice.entity.Grain;
import github.nikandpro.coffeesupplyservice.mapper.GrainMapper;
import github.nikandpro.coffeesupplyservice.repository.GrainRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GrainServiceTest {

    @InjectMocks
    private GrainService grainService;

    @Mock
    private GrainRepository grainRepository;

    @Mock
    private GrainMapper grainMapper;

    private GrainDto grainDto;

    //
    @BeforeEach
    public void setUp() {
        grainDto = new GrainDto(1L, 60, 1L, 70.0, 20.0, "A");
    }

    @Test
    public void testSaveGrain() {

        // Arrange
        Grain grain = new Grain();

        when(grainMapper.toEntity(any())).thenReturn(grain);

        // Act
        grainService.saveGrain(grainDto);

        // Assert
        verify(grainMapper).toEntity(any());
        verify(grainRepository).save(any());
    }

    @Test
    public void testSaveGrain_NullGrainDto() {
        // Arrange
        GrainDto nullGrainDto = null;

        // Act & Assert
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            grainService.saveGrain(nullGrainDto);
        });

        assertEquals("grainDto can not be null", exception.getMessage());
        verify(grainRepository, never()).save(any());
    }

    @Test
    public void testValidateGrain_ValidGrainDto() {
        // Act & Assert
        assertDoesNotThrow(() -> grainService.validateGrain(grainDto));
    }

    @Test
    public void testValidateGrain_NullGrainDto() {
        // Act & Assert
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            grainService.validateGrain(null);
        });

        assertEquals("grainDto can not be null", exception.getMessage());
    }
}
