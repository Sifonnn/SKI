package tn.esprit.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.services.SkierServicesImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SkierTest {

    @Mock
    private ISkierRepository skierRepository;

    @InjectMocks
    private SkierServicesImpl skierServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveSkier() {
        // Arrange
        Long skierId = 1L;
        Skier skier = new Skier();
        when(skierRepository.findById(skierId)).thenReturn(Optional.of(skier));

        // Act
        Skier retrievedSkier = skierServices.retrieveSkier(skierId);

        // Assert
        assertEquals(skier, retrievedSkier);
        verify(skierRepository, times(1)).findById(skierId);
    }

    @Test
    void testRemoveSkier() {
        // Arrange
        Long skierId = 1L;

        // Act
        skierServices.removeSkier(skierId);

        // Assert
        verify(skierRepository, times(1)).deleteById(skierId);
    }
}
