package com.anamay.demo.cucumber;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.anamay.demo.AppOneApplication;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;

@RunWith(Cucumber.class)
@CucumberContextConfiguration
@SpringBootTest(classes= {AppOneApplication.class, CucumberTest.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
@CucumberOptions(plugin= {"pretty"},features="/AppOne/src/test/resources/features")
public class CucumberTest {

}
