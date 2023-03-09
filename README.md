# CleanArchitectureTemplateAndroidView

Цель этого шаблона - стать отправной точкой для новых проектов, следуя лучшим практикам разработки.

# Чистая архитектура с 5 основными модулями для фичи

- UI (для логики пользовательского интерфейса, с MVVM)
- Presentation (что должно быть показано UI)
- Domain (для бизнес-логики)
- Data (защищает бизнес-логику от внешних деталей: web API, базы данных, SharedPreferences и т.п.)
- DataSource (для web API, базы данных, SharedPreferences и т.п.)

# Дополнительные модули

- App (для сборки и DI)
- Core (содержит 5 основных модулей также, как у фичи)

# Навигация

Навигация построена на ([Navigation component](https://developer.android.com/guide/navigation))
Модуль `core:ui` отвечает за коммуникацию со всеми модулями. Про него знают все модули, которым
требуется навигация.

# Другие полезные функции

Эта версия обеспечивает:

- модульность ([Modularization](https://developer.android.com/topic/modularization))
- Управление версиями ([Version catalog](https://docs.gradle.org/current/userguide/platforms.html))
- Использует  ([Material3](https://m3.material.io/))
- ([View binding](https://developer.android.com/topic/libraries/view-binding))
- ([DI - Hilt](https://developer.android.com/training/dependency-injection/hilt-android))

# Начало работы

1. Загрузите этот репозиторий и откройте папку CleanArchitectureTemplateAndroidView в Android Studio
2. В `app/main/java` переименуйте иерархию "ru.vdh.myapp" в необходимую? выбирая при
   этом `All derectories`.
3. Проверьте, была ли иерархия в манифесте переименована в соответствии с этим.
4. В `app/build.gradle` измените идентификатор приложения на новую иерархию.
5. В `string.xml`, установите название вашего приложения
6. В `settings.gradle` в `rootProject.name` установите название корневого проекта
7. Скопировать `newfeature` модуль в `feature` модуль. И переименовать в соотвествии с требуемой
   фичей.
   Таким образом, `newfeature` модуль всегда остается неизменным для нового копирования для другой
   фичи.
8. В `settings.gradle` добавить `include` для всех добавленных модулей соответственно.
9. Пройтись по `namespace` в каждом модуле добавленной фичи и переименовать соответственно.
10. Также переименовываем иерархию пакетов в каждом модуле добавленной фичи, выбирая при
    этом `In current module`.
11. Обновить необходимые импорты, DI, а также `implementation project` зависимости для модулей
    добавленной фичи.
12. Начать разработку :) 

Запускается на:
Android Studio Electric Eel 