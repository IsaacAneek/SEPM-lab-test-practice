package com.example.design;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;

class SingletonTest {

    @Test
    @DisplayName("Singleton: Should return the same instance")
    void shouldReturnSameInstance() {

        RestaurantConfig a =
                RestaurantConfig.getInstance();

        RestaurantConfig b =
                RestaurantConfig.getInstance();
        
                
        try {
                assertSame(a, b, "Both instances should be the same");

                System.out.println("[SUCCESS] Both references point to the same object!");

            } catch (AssertionError e) {

                System.out.println("[FAILED] Singleton instance verification failed!");
                System.out.println("[ERROR] " + e.getMessage());

                throw e;
            }
    }
}