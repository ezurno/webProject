package com.example.firstproject.ioc;

import org.springframework.stereotype.Component;

@Component // 해당 클래스를 객체로 만들고 이를 IoC 컨테이너에 등록
public class IngredientFactory {
    public Ingredient get(String menu) {
        switch (menu) {
            case "Donkachu":
                return new Pork("Pork");
            case "Steak":
                return new Beef("Beef");

            default:
                return null;
        }
    }
}
