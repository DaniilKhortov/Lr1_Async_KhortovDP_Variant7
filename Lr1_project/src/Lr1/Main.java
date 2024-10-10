package Lr1;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.Random;

public class Main {
	//Обмеження кількості книг
	static final Semaphore theArtOfWar = new Semaphore(3);
	static final Semaphore the1984 = new Semaphore(3);
	static final Semaphore the2001SpaceOdyssey = new Semaphore(3);
	static final Semaphore theDavinciCode = new Semaphore(3);

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

		//Ініцііалізація потоку бібліотеки
		Runnable library = () -> {
			//Лічильник потоків
			int i = 0;

			//Колекція потоків відвідувачів
			List<Thread> threadList = new ArrayList<>();

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

		//Час на переривання активних потоків
		Thread.sleep(1000);
		System.err.println("=============Бібліотекарі пішли додому================");
	}
}


