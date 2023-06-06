package com.example.workshhop2;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationRunner implements CommandLineRunner {
    private final static Logger logger = LoggerFactory.getLogger(MyApplicationRunner.class);

    @Override
    public void run(String... args) throws Exception {
        logger.info("Application started");
        try {
            double result = this.calculator(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            logger.error("you can't divise with 0");
        }
        logger.info("Application finished");

    }

    public double calculator(int x, int y) {
        return x / y;
    }

    public void copyLogFile() {

        try {
            File sourceFile = new File("logs/mylog.log");
            File destinationFile = new File("logs/old/mylog.log");
            if (sourceFile.exists()){
                sourceFile.getParentFile().mkdirs();
            }
            destinationFile.createNewFile();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}