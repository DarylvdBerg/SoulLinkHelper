package com.example.soullinkhelper;

import com.example.soullinkhelper.utility.RandomStringBuilder;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class RandomStringBuilderTest {

    @Test
    public void randomStringSize() {
        for (int i = 0; i < 100; i++){
            String res = RandomStringBuilder.randomString(32);
            System.out.println(res);
            assertEquals(32, res.length());
        }
    }
}