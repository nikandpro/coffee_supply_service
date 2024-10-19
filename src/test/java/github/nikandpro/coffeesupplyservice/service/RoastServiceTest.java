package github.nikandpro.coffeesupplyservice.service;

import github.nikandpro.coffeesupplyservice.dto.RoastDto;
import github.nikandpro.coffeesupplyservice.entity.Roast;
import github.nikandpro.coffeesupplyservice.mapper.RoastMapper;
import github.nikandpro.coffeesupplyservice.repository.RoastRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoastServiceTest {

    @InjectMocks
    private RoastService roastService;

    @Mock
    private RoastRepository roastRepository;

    @Mock
    private RoastMapper roastMapper;

    private RoastDto roastDto;

    @BeforeEach
    public void setUp() {
        roastDto = new RoastDto(1L, 1L, UUID.randomUUID(), 1L, 60, 40);
    }

    @Test
    public void testSave() {
        // Arrange
        Roast roast = new Roast(); // Create a Roast object to be returned by the mapper
        when(roastMapper.toEntity(roastDto)).thenReturn(roast);

        // Act
        roastService.save(roastDto);

        // Assert
        verify(roastMapper, times(1)).toEntity(roastDto);
        verify(roastRepository, times(1)).save(roast);
        assertDoesNotThrow(() -> roastService.save(roastDto));
    }

    @Test
    public void testSave_NullRoastDto() {
        // Arrange
        RoastDto nullRoastDto = null;

        // Act & Assert
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            roastService.save(nullRoastDto);
        });

        assertEquals("roastDto is null", exception.getMessage());
        verify(roastRepository, never()).save(any());
    }

    @Test
    public void testValidateRoast_ValidRoastDto() {
        // Act & Assert
        assertDoesNotThrow(() -> roastService.validateRoast(roastDto));
    }

    @Test
    public void testValidateRoast_NullRoastDto() {
        // Act & Assert
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            roastService.validateRoast(null);
        });

        assertEquals("roastDto is null", exception.getMessage());
    }
}