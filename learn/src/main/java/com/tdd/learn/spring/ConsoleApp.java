package com.tdd.learn.spring;

import com.tdd.learn.spring.numberBaseball.BaseballGame;
import com.tdd.learn.spring.numberBaseball.Batter;
import com.tdd.learn.spring.numberBaseball.Pitcher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Scanner;

@SpringBootApplication
public class ConsoleApp implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ConsoleApp.class, args);
    }

    @Override
    public void run(String... args) {

        System.out.println("숫자 야구 게임을 시작!!");
        Scanner scanner = new Scanner(System.in);

        System.out.print("예상하는 숫자 3개를 공백으로 구분하여 입력하세요 (예: 1 2 3): ");
        String input = scanner.nextLine();

        int[] userNumbers = Arrays.stream(input.split(" "))
                                  .mapToInt(Integer::parseInt)
                                  .toArray();


        Pitcher pitcher = new Pitcher(userNumbers);
        Batter batter = new Batter();
        BaseballGame game = new BaseballGame(pitcher, batter);

        game.play();
        scanner.close();
    }
}

