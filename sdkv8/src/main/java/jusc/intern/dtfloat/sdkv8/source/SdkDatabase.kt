package jusc.intern.dtfloat.sdkv8.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [DataEntity::class], version = 1, exportSchema = false)
abstract class SdkDatabase: RoomDatabase() {
    companion object {

        @Volatile
        private var db: SdkDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

        fun getInstance(context: Context): SdkDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance =
                    Room.databaseBuilder(
                        context,
                        SdkDatabase::class.java,
                        DB_NAME
                    ).build()
                db = instance
                return instance
            }
        }
    }

    abstract fun dataDao(): DataDao
}