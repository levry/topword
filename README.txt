Структура

Состав решения
/topword-trie
  базовый проект, содержит реализацию дерева поиска: trie-дерево
  модуль является общим для обоих приложений
  https://en.wikipedia.org/wiki/Trie
/topword-cli
  проект, реализация консольного приложения (часть I)
/topword-web
  проект web-приложения для автопоиска слов (часть II)
/gradle
  wrapper системы сборки gradle



Часть I

Консольное приложение

Сборка приложения 

./gradlew topword-cli:jar

Вызов задачи 'jar' проекта topword-cli, в результате которой 
будет создан jar-пакет /topword/build/libs/topword-cli.jar


Запуск приложения

java -jar topword-cli/build/libs/topword-cli.jar test.in
Результат выполнения будут выведены в консольный вывод

java -jar topword-cli/build/libs/topword-cli.jar test.in > test.out
Результат работы приложения будет записан в файл test.out

time java -jar topword-cli/build/libs/topword-cli.jar test.in > test.out
Запуск и расчет времени выполнения приложения


Часть II

Web приложение

Сборка приложения

./gradlew topword-web:war

Будет собран war-архив topword-web/build/libs/topword-web.war


Запуск приложения

Входной файл с исходными данными указывается через jvm пеменную окружения приложения topword.filename.
Например -Dtopword.filename=path/to/filename

Запуск web приложения c помощьъ задачи системы сборки
./gradlew -Dtopword.filename=file:///path/to/words.txt tomcatRun

где
file:///path/to/words.txt - полное имя файла с исходными данными

Приложение будет доступно по адресу http://localhost:8080