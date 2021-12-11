package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefence = "";


    public static int[] heroesHealth = {270, 260, 250, 300, 300, 300, 200, 200};
    public static int[] heroesDamage = {15, 20, 25, 0, 5, 20, 20, 20};


    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "medic", " Goleam", "lucky", "Breserk", "thor"};
    public static int round_number = 0;

    public static void main(String[] args) {
        printStatistics(); // До начала игры вывод статистики
        while (!isGameFinished()) {
            round();
        }
    }


    public static void changeBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); //0,1,2
        bossDefence = heroesAttackType[randomIndex];
        System.out.println("Boss chose " + bossDefence);
    }


    public static void round() {
        round_number++;
        changeBossDefence();
        bossHits();
        heroesHit();
        medicdate();
        Golem();
        Lucky();
        Berserk();
        thorRound();
        printStatistics();
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {


            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesAttackType[i] == bossDefence) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2; //2,3,4,5,6,7,8,9,10
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println("Critical damage " + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    public static void medicdate() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (i == 3) {
                continue;
            }
            if (heroesHealth[i] > 0 && heroesHealth[i] < 100 && heroesHealth[i] > 0) {
                heroesHealth[i] += 50;
                System.out.println("медик лечил " + heroesAttackType[i]);
                break;
            }

        }


    }

    public static void Golem() {

        int save = bossDamage * 1 / 5;
        int alive = 0;
        if (heroesHealth[4] > 0) {
            for (int i = 0; i < heroesDamage.length; i++) {
                if (i == 4) {
                    continue;
                } else if (heroesHealth[i] > 0) {
                    alive++;
                    heroesHealth[i] += save;
                }
            }
            heroesHealth[4] -= save * alive;
            System.out.println("голем получает урон " + save * alive);
        }
    }

    public static void Lucky() {
        Random random = new Random();
        boolean good = random.nextBoolean();

        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] < 0) {
                if (good) {
                    heroesHealth[5] += bossDamage - 10;
                }
            }

        }
        System.out.println(" повезло " + heroesAttackType[5]);


    }

    public static void Berserk() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                heroesHealth[6] -= bossDamage * 1 / 10;
                heroesDamage[6] = heroesDamage[6] + bossDamage * 1 / 10;
                System.out.println("Berserk has just blocked and gained bonus damage by blocking Boss' one");
                break;

            }
        }

    }

    public static void thorRound() {
        Random random = new Random();
        boolean nextThor = random.nextBoolean();
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[7] > 0)
            {
                if (nextThor) {
                    bossDamage = 0;
                    System.out.println(" тор оглушил bossa ");
                    break;


                }
                else {
                    bossDamage=50;
                    break;

                }
            }


        }
    }


    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void printStatistics() {
        System.out.println(round_number + " ROUND _______________");
        System.out.println("Boss health: " + bossHealth + " (" + bossDamage + ")");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + " (" + heroesDamage[i] + ")");

        }
        System.out.println("_______________");
    }
}


