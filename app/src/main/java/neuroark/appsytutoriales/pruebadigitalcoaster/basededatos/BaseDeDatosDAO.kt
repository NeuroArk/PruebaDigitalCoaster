package neuroark.appsytutoriales.pruebadigitalcoaster.basededatos

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Perfil::class], version = 1)
abstract class BaseDeDatosDAO : RoomDatabase() {
    abstract fun userDao(): PerfilDao
    companion object {
        @Volatile
        private var INSTANCE: BaseDeDatosDAO? = null

        fun getDatabase(context: Context): BaseDeDatosDAO = INSTANCE ?: synchronized(this){
            val instance = Room.databaseBuilder(
                context.applicationContext,
                BaseDeDatosDAO ::class.java,
                "PruebaDigitalCoaster"
            ).addCallback(object:Callback(){
                @Override
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                }
            }).build()
            INSTANCE = instance
            instance
        }
    }


}
