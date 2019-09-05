# Android Room

<img width="400px" height="700px" src="https://user-images.githubusercontent.com/53843178/64321338-50273b00-cffb-11e9-9455-9af7a527bd24.gif" />
<br/>
<br/>


## Room

- [안드로이드 아키텍쳐 컴포넌트(Android Architecture Components)](https://developer.android.com/topic/libraries/architecture) 는 앱을 견고하고, 실험 가능하고, 유지 보수성이 뛰어나도록 만들어주는 라이브러리 모음으로, 이중 하나가 Room 이다.
- Room 은 SQLite 의 추상 레이어를 제공하여 SQLite 의 객체를 매핑하는 역할을 하는데, 쉽게 말하면 SQLite 의 기능을 모두 사용할 수 있고, DB 로의 접근을 편하게 도와주는 라이브러리이다.
<br/>
<br/>



## Room Components 구성 요소

<img width="627" alt="RoomArchitecture" src="https://user-images.githubusercontent.com/52689243/61687253-f0cbdf00-ad5c-11e9-9c87-d3fdbf01d418.png">

- Entity: Database 안에 있는 테이블을 Java/Kotlin 클래스로 나타낸 것으로, 데이터 모델 클래스라고 볼 수 있다.
- DAO(Database Access Object): 데이터베이스에 접근해서 실질적으로 insert, delete 등을 수행하는 메소드를 포함한다.
- Database: Database holder 를 포함하며, 앱에 영구 저장되는 데이터와 기본 연결을 위한 주 엑세스 지점이다.
    - RoomDatabase 를 상속는 추상 클래스여야 하며, 테이블과 버전을 정의하는 곳이다.
<br/>
<br/>


## Room Components 작성

####  Entity

```kotlin
@Entity(tableName = "person")
@TypeConverters(DateConverter::class)
data class Person(
    @PrimaryKey
    @ColumnInfo(name = "socialNumber")
    val socialNumber: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "job")
    val job: String,

    @ColumnInfo(name = "created")
    val created: Date
): Serializable
```
- Entity 는 데이터베이스에 저장될 데이터의 형식으로 적어도 하나 이상의 Primary Key 가 필요하.
- @PrimaryKey(autoGenerate = true) 를 이용해 자동으로 생성되게 하는 것도 가능하다.
<br/>
<br/>


#### DAO

```kotlin
@Dao
interface PersonDao {
    @Query("SELECT * FROM person ORDER BY created ASC")
    fun findAll(): DataSource.Factory<Int, Person>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(person: Person)

    @Delete
    fun delete(person: Person)

    @Query("SELECT * FROM person WHERE socialNumber = :socialNumber")
    fun isPerson(socialNumber: String): Boolean

    @Query("UPDATE person SET name = :name, job = :job")
    fun updateInfo(name: String, job: String)
}
```
- DAO 는 데이터베스를 통해 수행할 작업을 정의한 클래스이다.
- 데이터의 삽입, 수정, 삭제 작업이나 저장된 데이터를 불러오는 작업 등을 함수 형태로 정의한다.
- Rx 에서 사용하는 Observable 로 받을 수 있다.
- OnConflictStrategy 는 Primary Key 가 겹칠 경우의 정책을 설정하는 부분이다.
<br/>
<br/>


#### Database

```kotlin
@Database(entities = [Person::class], version = DB_VERSION, exportSchema = false)
abstract class PersonDB: RoomDatabase() {

    abstract fun personDao(): PersonDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "person.db"

        private var INSTANCE: PersonDB? = null

        fun getInstance(context: Context): PersonDB? {
            if (INSTANCE == null) {
                synchronized(PersonDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        PersonDB::class.java,
                        DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }


        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
```
- 데이터베이스 생성을 위한 추상클래스로 RoomDatabase() 를 상속한다.
<br/>
<br/>


## Setting

#### Create BaseApplication.kt (Logger)

```kotlin
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .methodCount(0)
            .methodOffset(7)
            .build()

        addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }
}
```
<br/>

#### build.gradle(Module: app)

```gradle
apply plugin: 'kotlin-kapt'

..

android {
    ..

    dataBinding {
        enabled = true
    }
}

..

dependencies {
    // CardView
    implementation "androidx.cardview:cardview:$cardview_version"

    // RecyclerView
    implementation "androidx.recyclerview:recyclerview:$recyclerview_version"

    // Koin
    implementation "org.koin:koin-android:$koin_version"

    // Koin
    // Koin AndroidX Scope features
    implementation "org.koin:koin-androidx-scope:$koin_version"

    // Koin
    // Koin AndroidX ViewModel features
    implementation "org.koin:koin-androidx-viewmodel:$koin_version"

    // RxAndroid
    implementation "io.reactivex.rxjava2:rxandroid:$rxandroid_version"

    // RxJava
    implementation "io.reactivex.rxjava2:rxjava:$rxjava_version"

    // AAC
    kapt "androidx.lifecycle:lifecycle-compiler:$aac_version"
    implementation "androidx.lifecycle:lifecycle-runtime:$aac_version"

    // AAC
    // ViewModel and LiveData
    implementation "androidx.lifecycle:lifecycle-extensions:$aac_version"

    // Logger
    // AndroidManifest.xml 에서 name 추가 필요
    implementation "com.orhanobut:logger:$logger_version"

    // Room
    implementation "androidx.room:room-runtime:$room_version"
    kapt "androidx.room:room-compiler:$room_version"

    // Room
    // Kotlin Extensions and Coroutines support for Room
    implementation "androidx.room:room-ktx:$room_version"

    // Room
    // RxJava support for Room
    implementation "androidx.room:room-rxjava2:$room_version"

    // Paging
    implementation "androidx.paging:paging-runtime-ktx:$paging_version"
    testImplementation "androidx.paging:paging-common-ktx:$paging_version"

    // Paging
    implementation "androidx.paging:paging-rxjava2-ktx:$paging_version"

    // Material
    implementation "com.google.android.material:material:$material_version"
}
```
<br/>

#### build.gradle(Project: AndroidRoom)

```gradle
ext {
    // CardView
    cardview_version = '1.0.0'

    // RecyclerView
    recyclerview_version = '1.0.0'

    // Koin
    koin_version = '2.0.1'

    // RxAndroid
    rxandroid_version = '2.1.1'

    // RxJava
    rxjava_version = '2.2.10'

    // AAC
    aac_version = '2.0.0'

    // Logger
    logger_version = '2.2.0'

    // Room
    room_version = '2.1.0'

    // Paging
    paging_version = '2.1.0'

    // Material
    material_version = '1.0.0'
}
```
<br/>
<br/>


## Reference
- [CardView and RecyclerView](https://developer.android.com/jetpack/androidx/migrate)
- [Koin](https://github.com/InsertKoinIO/koin)
- [RxAndroid/RxJava](https://github.com/ReactiveX/RxAndroid)
- [AAC](https://developer.android.com/jetpack/androidx/releases/lifecycle)
- [Logger](https://github.com/orhanobut/logger)
- [Room](https://developer.android.com/jetpack/androidx/releases/room#declaring_dependencies)
- [Paging](https://developer.android.com/jetpack/androidx/releases/paging#declaring_dependencies)
- [Material](https://material.io/develop/android/docs/getting-started/)
