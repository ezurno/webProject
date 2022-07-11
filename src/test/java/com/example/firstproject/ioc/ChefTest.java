package com.example.firstproject.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // IoC SpringBoot 에서 Test를 하기 위해 Autowired와 Component를 사옹하려고 어노테이션
class ChefTest {

    /*
    @Test
    void Cook_Donkachu() {
        // 준비
        Chef chef = new Chef();
        String menu = "Donkachu";

        // 수행
        String food = chef.cook(menu);

        // 예상
        String expected = "Pork 으로 만든 Donkachu";

        // 검증
        assertEquals(expected, food);
        System.out.println(food);
    }

    @Test
    void Cook_Steak() {
        Chef chef = new Chef();
        String menu = "Steak";

        String food = chef.cook(menu);

        String expected = "Beef 으로 만든 Steak";

        assertEquals(expected, food);
        System.out.println(food);
    }
    */

    @Autowired
    IngredientFactory ingredientFactory;

    @Test
    void Cook() {
        // IngredientFactory ingredientFactory = new IngredientFactory();
        // @Autowired 으로 가져오기 위해 직접 객체생성을 하지 않음

        Chef chef = new Chef(ingredientFactory); // 의존성 주입 ==> Autowired 하려면 주석 하고 chef에 @Autowired

        String menu = "Steak";
        String food = chef.cook(menu);

        String expected = "Beef으로 만든 Steak";

        assertEquals(expected, food);
        System.out.println(food);
    }

}