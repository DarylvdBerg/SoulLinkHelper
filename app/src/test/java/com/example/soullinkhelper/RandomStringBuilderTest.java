package com.example.soullinkhelper;

import com.example.soullinkhelper.utility.RandomStringBuilder;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class RandomStringBuilderTest {

    @Test
    public void randomStringSize() {
        String res = RandomStringBuilder.randomString(32);
        assertEquals(32, res.length());
    }
}