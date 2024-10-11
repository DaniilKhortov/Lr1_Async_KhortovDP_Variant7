package Lr1;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Main {
	//Обмеження кількості книг
	static final Semaphore theArtOfWar = new Semaphore(3);
	static final Semaphore the1984 = new Semaphore(3);
	static final Semaphore the2001SpaceOdyssey = new Semaphore(3);
	static final Semaphore theDaVinciCode = new Semaphore(3);

	//Змінна, що відповідає за відкриття/закриття бібліотеки
	private static boolean isAvailableHours = true;

	//Функція повернення змінної isAvailableHours
	public static synchronized boolean isOpen () {
		return isAvailableHours;
	}

	//Функція закриття бібліотеки
	public static synchronized void closeLibrary () {
		isAvailableHours = false;
		System.err.println("================Бібліотека зачинена===================");
	}

	//Запуск коду
	public static void main(String[] args) throws InterruptedException {
		//Колекція потоків відвідувачів. Є синхронізованим, щоб потоки взаємодіяли з колекцією по-черзі
		List<Thread> threadList = Collections.synchronizedList(new ArrayList<>());

		//Ініцііалізація потоку бібліотеки
		Runnable library = () -> {
			//Лічильник потоків
			int i = 0;


			//Цикл ініціалізації та запуску потоків відвідувачів
			while(isOpen()) {
				Thread student = new Thread(new Visitor(), String.valueOf(i));

				//Додання потоку у колекцію
				threadList.add(student);

				//Запуск потоку відвідувачів
				student.start();

				i++;

				//Очікування протягом секунди
				// Обробка аварійного переривання потоку бібліотеки
				try {
					Thread.sleep(1000);

				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}

			//Переривання активних потоків відвідувачів після закінчення роботи циклу
			for (Thread thread : threadList) {
				if (thread.isAlive()) {
					thread.interrupt();
					//Синхронізація переривань потоків
					try {
						thread.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

		//Запуск потоку бібліотеки
		Thread libraryThread = new Thread(library, "Бібліотека");
		libraryThread.start();


		//Час роботи бібліотеки
		Thread.sleep(12000);

		//Зупинка потоку бібліотеки
		closeLibrary();

		//Синхронізація потоків
		for (Thread thread : threadList) {
				thread.join();
		}



		System.err.println("=============Бібліотекарі пішли додому================");
	}
}


