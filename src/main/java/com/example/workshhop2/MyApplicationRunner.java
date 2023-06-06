package com.example.workshhop2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

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
        this.copyLogFile();
    }

    public double calculator(int x, int y) {
        return x / y;
    }

    public void copyLogFile() {

        try {
            File sourceFile = new File("logs/mylog.log");
            File destinationFile = new File("logs/old/mylog.log");
            if (!sourceFile.exists()){
                sourceFile.getParentFile().mkdirs();
                sourceFile.createNewFile();
                logger.info("new log file created");
            }
            if(!destinationFile.exists()){
                destinationFile.getParentFile().mkdirs();
                destinationFile.createNewFile();
                logger.info("destiniation file created");
            }
            Files.copy(sourceFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            logger.info("Copy log file finished");
        } catch (IOException e) {
            // TODO: handle exception
            logger.error("Error");
        }
    }
}