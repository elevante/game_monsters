package com.geekbrains.monsters;

import java.sql.Struct;
import java.util.Scanner;

public class MonstersApp {

    private static Scanner sc = new Scanner((System.in));

    static class GameCharacter {
        String name;
        int hp;
        int maxHp;
        int attackPower;
        boolean block; // false / true

        public GameCharacter(String name, int maxHp, int attackPower){
            this.name = name;
            this.maxHp = maxHp;
            this.hp = this.maxHp;
            this.attackPower = attackPower;
            this.block = false;
        }
        public void attack(GameCharacter target){
            int damage = this.attackPower;
            if (target.block) {
                if (Math.random() < 0.75){
                    System.out.println(target.name + " полностью заблокировал атаку");
                    return;
                } else {
                    damage *= 2;
                }

            }
            target.hp -= damage;
            System.out.println(this.name + " нанес персонажу " + target.name + " урон = " + damage);
            System.out.println("У персонажа " + target.name + " осталось " + target.hp + " / " + target.maxHp  + " ед. здоровья");
        }

        public void blockAction(){
            hp++;
            if (hp > maxHp) {
                hp = maxHp;
            }
            block = true;
            System.out.println(this.name + " пытается заблокировать следующую атаку. Получает +1 ед. здоровья");
        }

        public void reset() {
            block = false;
        }
    }

    public static void main(String[] args) {
        GameCharacter hero = new GameCharacter("Бэтмен", 10, 2);
        GameCharacter monster = new GameCharacter("Гоблин", 6, 2);


        System.out.println(hero.name + " начинает свое путешествие");
        System.out.println(hero.name + " сразу жу попадает в засаду. Из леса на него нападает " + monster.name);

        System.out.println("Битва началась");

        while (true)
        {
            System.out.println("\nХод героя: " + hero.name);
            System.out.println(hero.name + ", каковы будут ваши дейтсвия?");
            hero.reset();
            String input = sc.next();
               if (input.equals("/ударить")) {
                  hero.attack(monster);
                   if(monster.hp <= 0){
                       System.out.println(hero.name + " победил персонажа " + monster.name);
                       break;
                   }
                }
               if (input.equals("/блок")){
                   hero.blockAction();

               }
               System.out.println("\nХод монстра: " + monster.name);
               monster.reset();
               if (Math.random() < 0.5) {
                   monster.attack(hero);
                   if (hero.hp <= 0) {
                       System.out.println(monster.hp + " победил персонажа " + hero.name);
                       break;
                   }
               } else {
                   monster.blockAction();
               }
        }
        System.out.println("Игра завершена");
    }
}
