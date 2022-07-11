package com.example.firstproject.ioc;

public class Chef {
    /*public String cook(String menu) {
        // 재료 준비
        // Pork pork = new Pork("Pork"); => 코드의 의존성 => DI 필요 ex) 재료를 공장으로부터 조달 받는 느낌으로
        // Beef beef = new Beef("Beef");


        // 요리 반환
        // return beef.getName() + " 으로 만든 " + menu; => 의존성이 크므로 사용 x

        // ********************************* //


        return null;
    }*/

    // Chef는 factory 의 위치를 알고있음
    private IngredientFactory ingredientFactory;

    // 1. related problem / Chef와 factory 를 연결하는 DI
    public Chef(IngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    public String cook(String menu) {
        // 재료 준비
        Ingredient ingredient = ingredientFactory.get(menu);

        return ingredient.getName() + "으로 만든 " + menu;


    }
}
