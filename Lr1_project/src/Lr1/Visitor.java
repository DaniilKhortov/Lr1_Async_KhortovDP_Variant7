package Lr1;


import java.util.Random;

public class Visitor implements Runnable {
    //Генератор випадковостей
    Random favourGenerator = new Random();

    //Масив фраз для прощання з відвідувачами
    String[] byeOptions = {"Гарного дня!\n", "До зустрічі!\n", "Сподіваємось на ваше повернення!\n", "До побачення!\n", "Сподіваємось на ваше повернення!\n", "Auf Wiedersehen!\n", "Do widzenia!\n"};

    //Обрання випадкової фрази
    int randomIndex = favourGenerator.nextInt(byeOptions.length);

    //Змінна, що вказує чи знаходиться відвідувач дома
    boolean isHome = false;

    //Ініціалізація потоку відвідувачів
    @Override
    public void run() {
        try{
            System.err.printf("Відвідувач з потоку %s прийшов до бібліотеки та обирає книгу \n", Thread.currentThread().getName());
            Thread.sleep(200);

            //Обрання випадкової книги
            switch (favourGenerator.nextInt(4)){
                case 0:
                    Main.the1984.acquire();
                    System.out.printf("Відвідувач з потоку %s обрав книгу 1984 відомого автора Д. Орвелла. \n", Thread.currentThread().getName());
                    Thread.sleep(20);
                    System.out.printf("Чудовий вибір! \n");
                    Thread.sleep(20);
                    System.out.printf("Відвідувач з потоку %s іде додому з книгою. \n", Thread.currentThread().getName());
                    Thread.sleep(20);
                    isHome=true;
                    Thread.sleep(6000);

                    System.out.printf("Відвідувач з потоку %s повертається до бібліотеки. \n", Thread.currentThread().getName());
                    isHome=false;
                    Thread.sleep(20);

                    System.out.printf("Відвідувач з потоку %s прочитав книгу '1984' та повертає її назад.\n", Thread.currentThread().getName());
                    Thread.sleep(20);
                    System.err.printf("Відвідувач з потоку %s іде додому. \n", Thread.currentThread().getName());
                    Thread.sleep(20);
                    System.out.printf(byeOptions[randomIndex]);
                    Main.the1984.release();
                    break;


                case 1:
                    Main.theArtOfWar.acquire();
                    System.out.printf("Відвідувач з потоку %s обрав книгу 'Мистецтво війни' відомого воєноначальника Сунь-Дзи. \n", Thread.currentThread().getName());
                    Thread.sleep(20);
                    System.out.printf("У вас гарний смак! \n");
                    Thread.sleep(20);
                    System.out.printf("Відвідувач з потоку %s іде додому з книгою. \n", Thread.currentThread().getName());
                    Thread.sleep(20);
                    isHome=true;
                    Thread.sleep(6000);

                    System.out.printf("Відвідувач з потоку %s повертається до бібліотеки. \n", Thread.currentThread().getName());
                    isHome=false;
                    Thread.sleep(20);

                    System.out.printf("Відвідувач з потоку %s прочитав книгу 'Мистецтво війни' та повертає її назад.\n", Thread.currentThread().getName());
                    Thread.sleep(20);
                    System.err.printf("Відвідувач з потоку %s іде додому. \n", Thread.currentThread().getName());
                    Thread.sleep(20);
                    System.out.printf(byeOptions[randomIndex]);
                    Main.theArtOfWar.release();
                    break;


                case 2:
                    Main.the2001SpaceOdyssey.acquire();
                    System.out.printf("Відвідувач з потоку %s обрав книгу '2001 - космічна одісея' відомого фантаста Артура Кларка. \n", Thread.currentThread().getName());
                    Thread.sleep(20);
                    System.out.printf("Поціновувачі фантастики завжди є інтелігентними людьми! \n");

                    Thread.sleep(20);
                    System.out.printf("Відвідувач з потоку %s іде додому з книгою. \n", Thread.currentThread().getName());
                    Thread.sleep(20);
                    isHome=true;
                    Thread.sleep(6000);

                    System.out.printf("Відвідувач з потоку %s повертається до бібліотеки. \n", Thread.currentThread().getName());
                    isHome=false;
                    Thread.sleep(20);

                    System.out.printf("Відвідувач з потоку %s прочитав книгу '2001 - космічна одісея' та повертає її назад.\n", Thread.currentThread().getName());
                    Thread.sleep(20);

                    System.err.printf("Відвідувач з потоку %s іде додому. \n", Thread.currentThread().getName());
                    Thread.sleep(20);

                    System.out.printf(byeOptions[randomIndex]);
                    Main.the2001SpaceOdyssey.release();
                    break;


                case 3:
                    Main.theDavinciCode.acquire();
                    System.out.printf("Відвідувач з потоку %s обрав книгу 'Код Да Вінчі' сучасного письменника Дена Брауна. \n", Thread.currentThread().getName());
                    Thread.sleep(20);
                    System.out.printf("Гарний вибір! \n");

                    Thread.sleep(20);
                    System.out.printf("Відвідувач з потоку %s іде додому з книгою. \n", Thread.currentThread().getName());
                    Thread.sleep(20);
                    isHome=true;
                    Thread.sleep(6000);

                    System.out.printf("Відвідувач з потоку %s повертається до бібліотеки. \n", Thread.currentThread().getName());
                    isHome=false;
                    Thread.sleep(20);

                    System.out.printf("Відвідувач з потоку %s прочитав книгу 'Код Да Вінчі' та повертає її назад.\n", Thread.currentThread().getName());
                    Thread.sleep(20);

                    System.err.printf("Відвідувач з потоку %s іде додому. \n", Thread.currentThread().getName());
                    Thread.sleep(20);

                    System.out.printf(byeOptions[randomIndex]);
                    Main.theDavinciCode.release();
                    break;
            }

        } catch (InterruptedException e) {
            //Визначення місцезнаходження відвідувача та відповідна реакція бібліотеки
            if(!isHome){
                System.out.printf("Адміністрація просить відвідувача з потоку %s покинути бібліотеку. \n", Thread.currentThread().getName());

                System.err.printf("Відвідувач з потоку %s покинув залу. \n", Thread.currentThread().getName());
            }else{
                System.err.printf("Відвідувач з потоку %s вже не може повернути книгу сьогодні. \n", Thread.currentThread().getName());
            }

        }
    }
}
