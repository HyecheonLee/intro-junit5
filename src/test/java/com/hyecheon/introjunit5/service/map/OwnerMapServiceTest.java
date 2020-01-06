package com.hyecheon.introjunit5.service.map;

import com.hyecheon.introjunit5.model.Owner;
import com.hyecheon.introjunit5.model.PetType;
import com.hyecheon.introjunit5.service.PetService;
import com.hyecheon.introjunit5.service.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Owner Map Service Test -")
class OwnerMapServiceTest {
    OwnerMapService ownerMapService;
    PetTypeService petTypeService;
    PetService petService;


    @BeforeEach
    void setUp() {
        petTypeService = new PetTypeMapService();
        petService = new PetMapService();
        ownerMapService = new OwnerMapService(petTypeService, petService);

        System.out.println("First Before Each");
    }

    @Test
    void ownersAreZero() {
        final var ownerCount = ownerMapService.findAll().size();
        assertThat(ownerCount).isZero();
    }

    @DisplayName("Pet Type -")
    @Nested
    class TestCreatePetType {
        @BeforeEach
        void setUp() {
            final var petType1 = new PetType(1L, "Dog");
            final var petType2 = new PetType(2L, "Dog");

            petTypeService.save(petType1);
            petTypeService.save(petType2);

            System.out.println("Nested Before each");
        }

        @Test
        void testPetCount() {
            final var petTypeCount = petTypeService.findAll().size();
            assertThat(petTypeCount).isNotZero().isEqualTo(2);
        }

        @DisplayName("Save Owners Tests -")
        @Nested
        class SaveOwnersTests {
            @BeforeEach
            void setUp() {
                ownerMapService.save(new Owner(1L, "Before", "Each"));
                System.out.println("Saved Owners Before Each");
            }

            @Test
            void saveOwner() {
                final var owner = new Owner(2L, "Joe", "uck");
                final var savedOwner = ownerMapService.save(owner);
                assertThat(savedOwner).isNotNull();
            }

            @DisplayName("Save Owners Tests -")
            @Nested
            class FindOwnersTests {
                @DisplayName("Find Owner")
                @Test
                void findOwner() {
                    final var foundOwner = ownerMapService.findById(1L);
                    assertThat(foundOwner).isNotNull();

                }

                @DisplayName("Find Owner Not Found")
                @Test
                void findOwnerNotFound() {
                    final var foundOwner = ownerMapService.findById(2L);
                    assertThat(foundOwner).isNull();
                }
            }
        }

    }

    @DisplayName("Verify Still Zero Owners")
    @Test
    void ownersAreStillZero() {
        final var ownerCount = ownerMapService.findAll().size();
        assertThat(ownerCount).isZero();

    }
}